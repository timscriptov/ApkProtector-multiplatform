package com.mcal.dexprotect.utils.file;

import android.os.Build;
import android.os.Environment;

import com.mcal.dexprotect.App;

import java.io.File;

public class ScopedStorage {
    public static File getStorageDirectory() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q && !Environment.isExternalStorageManager()) {
            return App.getContext().getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath());
        } else {
            return Environment.getExternalStorageDirectory();
        }
    }

    public static File getRootDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    public static String getWorkPath() {
        return App.getContext().getFilesDir().getAbsolutePath();
    }
}
