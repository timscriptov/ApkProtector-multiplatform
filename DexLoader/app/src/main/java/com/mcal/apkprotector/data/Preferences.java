package com.mcal.apkprotector.data;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.mcal.apkprotector.ProtectApplication;
import com.mcal.apkprotector.utils.CommonUtils;

import org.jetbrains.annotations.NotNull;

public class Preferences {
    public static @NotNull String realApplication() {
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
    }
}
