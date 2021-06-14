package com.mcal.dexprotect.data.dto.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputLayout;
import com.mcal.dexprotect.R;
import com.mcal.dexprotect.async.presentation.GetIcon;
import com.mcal.dexprotect.databinding.ApkInfoBinding;
import com.mcal.dexprotect.utils.CommonUtils;
import com.mcal.dexprotect.utils.InstallProvider;
import com.mcal.dexprotect.utils.MyAppInfo;
import com.mcal.dexprotect.utils.SourceInfo;
import com.mcal.dexprotect.utils.StringUtils;
import com.mcal.dexprotect.utils.Utils;
import com.mcal.dexprotect.utils.file.ScopedStorage;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import ru.svolf.melissa.sheet.SweetContentDialog;
import ru.svolf.melissa.sheet.SweetViewDialog;

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
            apk = new File(ScopedStorage.getStorageDirectory() + "/ApkProtect/output/" + pkg.getPackageName() + "/" + pkg.getPackageLabel() + ".apk");

            View apk_info = LayoutInflater.from(getContext()).inflate(R.layout.apk_info, null);

            AppCompatImageView icon = apk_info.findViewById(R.id.icon);
            AppCompatTextView app_path = apk_info.findViewById(R.id.app_path);
            AppCompatTextView app_name = apk_info.findViewById(R.id.app_name);
            AppCompatTextView package_name = apk_info.findViewById(R.id.package_name);
            icon.setImageDrawable(new MyAppInfo(getContext(), apk.getAbsolutePath()).getIcon());
            app_name.setText(pkg.getPackageLabel());
            package_name.setText(pkg.getPackageName());
            app_path.setText(pkg.getPackagePath().replace("info.mz", "").replace("/storage/emulated/0/", ""));
            final SweetContentDialog dialog = new SweetContentDialog(getContext());
            dialog.setTitle(R.string.about);
            dialog.setView(apk_info);
            dialog.setPositive(R.drawable.ic_play, getContext().getString(R.string.install), v1 -> {
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
                        Utils.deleteFolder(new File(ScopedStorage.getStorageDirectory() + "/ApkProtect/output/" + pkg.getPackageName()));
                        // FIXME СУКА
                        notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.cancel();
            });
            dialog.setNegative(R.drawable.ic_delete, getContext().getString(R.string.delete), v1 -> {
                try {
                    Utils.deleteFolder(new File(pkg.getPackagePath()));
                    //FIXME СУКА
                    notifyDataSetChanged();
                    Toast.makeText(getContext(), pkg.getPackageLabel() + getContext().getString(R.string.apk_deleted), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.cancel();
            });
            dialog.show();
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
