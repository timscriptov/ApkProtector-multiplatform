package com.mcal.apkprotector.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import org.jetbrains.annotations.NotNull;

public class Preferences {

    public static @NotNull String realApplication(@NotNull Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                    (context.getPackageName(), PackageManager.GET_META_DATA);
            return CommonUtils.encryptStrings(applicationInfo.metaData.getString("RealApplication"), 2);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static @NotNull String protectKey(@NotNull Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                    (context.getPackageName(), PackageManager.GET_META_DATA);
            return CommonUtils.encryptStrings(applicationInfo.metaData.getString("ProtectKey"), 2);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static @NotNull String dexFolderName(@NotNull Context context) {
        if(Preferences.getRandomName(context)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                        (context.getPackageName(), PackageManager.GET_META_DATA);
                return CommonUtils.encryptStrings(applicationInfo.metaData.getString("DexFolderName"), 2);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "dex";
            }
        } else {
            return "dex";
        }
    }

    public static @NotNull String getReplaceDexName(Context context) {
        if(Preferences.getRandomName(context)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                        (context.getPackageName(), PackageManager.GET_META_DATA);
                return CommonUtils.encryptStrings(applicationInfo.metaData.getString("ReplaceDexName"), 2);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "bin";
            }
        } else {
            return "bin";
        }
    }

    private static boolean getRandomName(@NotNull Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                    (context.getPackageName(), PackageManager.GET_META_DATA);
            return CommonUtils.encryptStrings(applicationInfo.metaData.getString("RandomName"), 2).equals("true");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
