package com.mcal.dexprotect.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;

import com.mcal.dexprotect.R;
import com.mcal.dexprotect.async.presentation.AppListPresenter;
import com.mcal.dexprotect.data.dto.applist.AppInteractor;
import com.mcal.dexprotect.data.dto.applist.PackageInfoHolder;
import com.mcal.dexprotect.data.dto.applist.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AppListDialog implements AppInteractor {
    private Context mContext;
    private AppCompatEditText mEdit;
    private AlertDialog adx;

    public AppListDialog(Context c, AppCompatEditText e) {
        mContext = c;
        mEdit = e;
        //ApplicationLoader runner = new ApplicationLoader(mContext, this);
        AppListPresenter runner = new AppListPresenter();
        runner.attach(mContext, this);
        runner.execute();
    }

    @Override
    public void setup(ArrayList<PackageInfoHolder> packageInfoHolders) {
        final ArrayAdapter<PackageInfoHolder> aa = new ArrayAdapter<PackageInfoHolder>(mContext, R.layout.package_list_item, packageInfoHolders) {
            @NotNull
            @SuppressLint({"InflateParams", "SetTextI18n"})
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.package_list_item, null);
                }

                PackageInfoHolder pkg = getItem(position);

                ViewHolder holder = new ViewHolder();

                holder.packageLabel = convertView.findViewById(R.id.pkg_name);
                holder.packageName = convertView.findViewById(R.id.pkg_id);
                holder.packageVersion = convertView.findViewById(R.id.pkg_version);
                holder.packageFilePath = convertView.findViewById(R.id.pkg_dir);
                holder.packageIcon = convertView.findViewById(R.id.pkg_img);
                holder.position = position;

                convertView.setTag(holder);

                holder.packageLabel.setText(pkg.packageLabel);
                holder.packageName.setText(pkg.packageName);
                holder.packageVersion.setText(getContext().getString(R.string.version) + pkg.packageVersion);
                holder.packageFilePath.setText(pkg.packageFilePath);

                holder.packageIcon.setImageDrawable(pkg.packageIcon);

                return convertView;
            }
        };
        AlertDialog.Builder adb = new AlertDialog.Builder(mContext, R.style.AlertDialogTheme);
        adb.setTitle(R.string.select_apk);
        adb.setNegativeButton(android.R.string.cancel, null);
        adb.setAdapter(aa, (p1, p2) -> {
            PackageInfoHolder pkg = aa.getItem(p2);
            mEdit.setText(pkg.packageFilePath);
            adx.dismiss();
        });
        adb.create();
        adx = adb.show();
    }
}
