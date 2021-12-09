package com.mcal.apkprotector.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MyAppInfo {
    private static PackageManager pm;
    private static PackageInfo pi;

    public MyAppInfo(@NotNull Context c, String apkpath) {
        pm = c.getPackageManager();
        pi = pm.getPackageArchiveInfo(apkpath, 0);
        pi.applicationInfo.sourceDir = apkpath;
        pi.applicationInfo.publicSourceDir = apkpath;
    }

    @NotNull
    public static String getAppName() {
        return (String) pi.applicationInfo.loadLabel(pm);
    }

    @Contract(pure = true)
    public static String getPackage() {
        return pi.applicationInfo.packageName;
    }

    @Contract(pure = true)
    public static String getVName() {
        return pi.versionName;
    }

    @Contract(pure = true)
    public static int getVCode() {
        return pi.versionCode;
    }

    public Drawable getIcon() {
        return pi.applicationInfo.loadIcon(pm);
    }

    public boolean isDebug() {
        return ((pi.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
    }
}
