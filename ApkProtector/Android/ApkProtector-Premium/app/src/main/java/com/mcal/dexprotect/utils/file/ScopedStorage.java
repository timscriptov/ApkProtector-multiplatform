package com.mcal.dexprotect.utils.file;

import android.os.Build;
import android.os.Environment;

import androidx.annotation.NonNull;

import com.mcal.dexprotect.App;

import java.io.File;

public class ScopedStorage {
    public static File getStorageDirectory() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q && !Environment.isExternalStorageManager()) {
            return App.getContext().getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath());
        } else {
            return new File(Environment.getExternalStorageDirectory() + "/ApkProtect");
        }
    }

    public static File getRootDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    @NonNull
    public static String getFilesDir() {
        return App.getContext().getFilesDir().getAbsolutePath();
    }
}
