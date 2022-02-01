package com.mcal.apkprotector.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.mcal.apkprotector.BuildConfig;
import com.mcal.apkprotector.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class InstallProvider {
    private static final String TAG = InstallProvider.class.getSimpleName();

    public static void installApplication(Context context, File filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uriFromFile(context, filePath), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "Error in opening the file!");
        }
    }

    public static Uri uriFromFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

    public static void uninstall(@NotNull Activity activity, String app_pkg_name) {
        Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
        intent.setData(Uri.parse("package:" + app_pkg_name));
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        activity.startActivityForResult(intent, 1);
    }
}
