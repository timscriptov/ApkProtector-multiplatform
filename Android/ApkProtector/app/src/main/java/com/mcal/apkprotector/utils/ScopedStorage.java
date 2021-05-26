package com.mcal.apkprotector.utils;

import android.os.Environment;

import com.mcal.apkprotector.App;

import java.io.File;

public class ScopedStorage {
    public static File getStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    public static String getWorkPath() {
        return App.getContext().getFilesDir().getAbsolutePath();
    }
}
