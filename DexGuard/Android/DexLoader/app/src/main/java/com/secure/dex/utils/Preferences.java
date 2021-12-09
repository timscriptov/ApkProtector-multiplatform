package com.secure.dex.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.secure.dex.data.Const;

public class Preferences {

    public static String realApplication(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                    (context.getPackageName(), PackageManager.GET_META_DATA);
            return CommonUtils.encryptStrings(applicationInfo.metaData.getString(Const.REAL_APP), 2);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String protectKey(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                    (context.getPackageName(), PackageManager.GET_META_DATA);
            return CommonUtils.encryptStrings(applicationInfo.metaData.getString(Const.PROTECT_KEY), 2);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
