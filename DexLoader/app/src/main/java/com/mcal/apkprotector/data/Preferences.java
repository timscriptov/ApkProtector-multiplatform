package com.mcal.apkprotector.data;

import org.jetbrains.annotations.NotNull;

public class Preferences {
    public static @NotNull String realApplication() {
        //return "$REAL_APPLICATION";
        return "com.mcal.apkprotector.App";
    }

    public static @NotNull String protectKey() {
        //return "$PROTECT_KEY";
        return "APKPROTECTOR2021";
    }

    public static @NotNull String getDexDir() {
        //return "$DEX_DIR";// apkprotector_dex
        return "apkprotector_dex";
    }

    public static @NotNull String getDexPrefix() {
        //return "$DEX_PREFIX";// classes-v
        return "classes-v";
    }

    public static @NotNull String getDexSufix() {
        //return "$DEX_SUFIX";// .bin
        return ".bin";
    }
    /*public static @NotNull String realApplication() {
        try {
            ApplicationInfo applicationInfo = ProtectApplication.getContext().getPackageManager().getApplicationInfo
                    (ProtectApplication.getContext().getPackageName(), PackageManager.GET_META_DATA);
            return CommonUtils.encryptStrings(applicationInfo.metaData.getString("RealApplication"), 2);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static @NotNull String protectKey() {
        try {
            ApplicationInfo applicationInfo = ProtectApplication.getContext().getPackageManager().getApplicationInfo
                    (ProtectApplication.getContext().getPackageName(), PackageManager.GET_META_DATA);
            return CommonUtils.encryptStrings(applicationInfo.metaData.getString("ProtectKey"), 2);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static @NotNull String getDexDir() {
        try {
            ApplicationInfo applicationInfo = ProtectApplication.getContext().getPackageManager().getApplicationInfo
                    (ProtectApplication.getContext().getPackageName(), PackageManager.GET_META_DATA);
            return CommonUtils.encryptStrings(applicationInfo.metaData.getString("DexDir"), 2);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static @NotNull String getDexPrefix() {
        try {
            ApplicationInfo applicationInfo = ProtectApplication.getContext().getPackageManager().getApplicationInfo
                    (ProtectApplication.getContext().getPackageName(), PackageManager.GET_META_DATA);
            return CommonUtils.encryptStrings(applicationInfo.metaData.getString("DexPrefix"), 2);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }*/
}
