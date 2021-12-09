package com.secure.dex.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import com.secure.dex.data.Const;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DexProtector {
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static List<File> dexFiles = new ArrayList<>();
    File dexDir;
    File optDir;

    private Class<?> loadedApkClass;
    private WeakReference loadedApkRef;

    @SuppressLint("PrivateApi")
    public DexProtector(Context context) {
        mContext = context;
        dexFiles = new ArrayList<>();
        dexDir = context.getDir("app_dex", Context.MODE_PRIVATE);
        optDir = new File(context.getFilesDir(), "opt");

        FileUtils.deleteFloor(dexDir.getAbsolutePath());
        FileUtils.mkdir(dexDir.getAbsolutePath());
        FileUtils.mkdir(optDir.getAbsolutePath());
    }

    public void install(Context context) throws Exception {
        String[] filelist = mContext.getAssets().list(Const.DP_LIB);
        for (int o = 0; o < filelist.length; o++) {
            if (filelist[o].endsWith(Const.LUA_MPH)) {
                new DexCrypto(mContext, dexFiles).op(context, filelist[o]);
            }
        }
        File dexDir = context.getDir("app_dex", 0);
        if (!dexDir.exists()) {
            dexDir.mkdirs();
        }

        new MyClassLoader(context, dexFiles, dexDir, optDir).loadDexByVersion();

        FileUtils.deleteFiles(dexDir);
    }

    public ClassLoader getHelper() {
        try {
            Field declaredField = this.loadedApkClass.getDeclaredField("mClassLoader");
            declaredField.setAccessible(true);
            return (ClassLoader) declaredField.get(this.loadedApkRef.get());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}