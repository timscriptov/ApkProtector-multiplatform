package com.mcal.apkprotector.utils;

import android.os.Environment;

import java.io.File;

public class ScopedStorage {
    public static File getStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }
}
