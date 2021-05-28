package com.mcal.dexprotect.data.dto.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;

import com.mcal.dexprotect.R;
import com.mcal.dexprotect.async.presentation.GetIcon;
import com.mcal.dexprotect.utils.CommonUtils;
import com.mcal.dexprotect.utils.InstallProvider;
import com.mcal.dexprotect.utils.ScopedStorage;
import com.mcal.dexprotect.utils.SourceInfo;
import com.mcal.dexprotect.utils.StringUtils;
import com.mcal.dexprotect.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class HomeListAdapter extends ArrayAdapter<SourceInfo> {
    private static final String TAG = "HomeListAdapter";
    public File apk;

    public HomeListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public HomeListAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public HomeListAdapter(@NonNull Context context, int resource, @NonNull SourceInfo[] objects) {
        super(context, resource, objects);
    }

    public HomeListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull SourceInfo[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public HomeListAdapter(File apk, @NonNull Context context, int resource, @NonNull List<SourceInfo> objects) {
        super(context, resource, objects);
        this.apk = apk;
    }

    public HomeListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<SourceInfo> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NotNull
    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, @NotNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.history_list_item, null);
        }
        final SourceInfo pkg = getItem(position);
        String iconPath = ScopedStorage.getStorageDirectory() + "/ApkProtect/output/" + pkg.getPackageName() + "/" + pkg.getPackageLabel() + ".apk";
        Log.e(TAG, "getView: " + iconPath);

        final ViewHolder holder = new ViewHolder();
        holder.cardlayout = convertView.findViewById(R.id.historylistitemCardView1);
        holder.packageLabel = convertView.findViewById(R.id.history_item_label);
        holder.packageName = convertView.findViewById(R.id.history_item_package);
        holder.packageDetail = convertView.findViewById(R.id.history_item_detail);
        holder.packageIcon = convertView.findViewById(R.id.history_item_icon);

        convertView.setTag(holder);

        holder.packageLabel.setText(pkg.getPackageLabel());
        holder.packageName.setText(pkg.getPackageName());
        holder.packageDetail.setText(StringUtils.humanReadableByteCount(new File(iconPath).length(), false));

        if (pkg.getPackageLabel().equalsIgnoreCase(pkg.getPackageName())) {
            holder.packageName.setVisibility(View.INVISIBLE);
        }

        if (new File(iconPath).exists()) {
            //Bitmap iconBitmap = BitmapFactory.decodeFile(iconPath);
            holder.packageIcon.setTag(iconPath);
            GetIcon.getInstance().resolve(iconPath, holder.packageIcon);
//            new ApkIcon(getContext(), holder.packageIcon).execute(iconPath);
        } else {
            holder.packageIcon.setImageResource(R.drawable.ic_launcher);
        }
        View finalConvertView = convertView;
        holder.cardlayout.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(getContext(), holder.cardlayout);
            popup.getMenuInflater().inflate(R.menu.menu_history, popup.getMenu());

            //popup.getMenu().add("Install");
            //popup.getMenu().add("Delete");
            Object menuHelper;
            Class[] argTypes;
            try {
                Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
                fMenuHelper.setAccessible(true);
                menuHelper = fMenuHelper.get(popup);
                argTypes = new Class[]{boolean.class};
                menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
            } catch (Exception e) {
                e.printStackTrace();
            }

            popup.show();
            popup.setOnMenuItemClickListener(item -> {
                //apk = new File(pkg.getPackagePath() + "/" + pkg.getPackageLabel() + ".apk");
                apk = new File(Environment.getExternalStorageDirectory() + "/ApkProtect/output/" + pkg.getPackageName() + "/" + pkg.getPackageLabel() + ".apk");
                switch (item.getItemId()) {
                    case R.id.install:
                        try {
                            if (apk.exists()) {
                                if (CommonUtils.isSameSign(getContext(), apk.getAbsolutePath(), pkg.getPackageName())) {
                                    InstallProvider.install(getContext(), apk);
                                } else {
                                    new AlertDialog.Builder(finalConvertView.getContext())
                                            .setCancelable(false)
                                            .setTitle(R.string.warning)
                                            .setMessage(R.string.different_signature)
                                            .setPositiveButton(android.R.string.ok, (p1, p2) -> InstallProvider.uninstall((AppCompatActivity) getContext(), pkg.getPackageName()))
                                            .setNegativeButton(android.R.string.cancel, null)
                                            .create().show();
                                }
                            } else {
                                Utils.deleteFolder(new File(Environment.getExternalStorageDirectory() + "/ApkProtect/output/" + pkg.getPackageName()));
                                // FIXME СУКА
                                notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.delete:
                        try {
                            Utils.deleteFolder(new File(pkg.getPackagePath()));
                            //FIXME СУКА
                            notifyDataSetChanged();
                            Toast.makeText(getContext(), pkg.getPackageLabel() + getContext().getString(R.string.apk_deleted), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
                return true;
            });
        });
        return convertView;
    }

    public static class ViewHolder {
        CardView cardlayout;
        AppCompatTextView packageLabel;
        AppCompatTextView packageDetail;
        AppCompatTextView packageName;
        AppCompatImageView packageIcon;
    }
}
