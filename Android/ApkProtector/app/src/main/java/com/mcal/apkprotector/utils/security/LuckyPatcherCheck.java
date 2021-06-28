package com.mcal.apkprotector.utils.security;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class LuckyPatcherCheck {
    private static final String TAG = "Package Parser";

    private static String[] metaData = new String[]{"xposedmodule", "xposeddescription"};
    private static String[] receivers = new String[]{"com.ui.widgets.AppDisablerWidget", "com.ui.widgets.BinderWidget", "com.ui.widgets.AndroidPatchWidget"};
    private static String[] services = new String[]{"com.chelpus.TransferFilesService", "com.chelpus.RootlessInstallService"};

    public static boolean isLucky(Context context) {
        try {
            int flags = PackageManager.GET_RECEIVERS | PackageManager.GET_SERVICES;

            List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(flags);

            for (PackageInfo packInfo : packages) {
                Log.d(TAG, "start parsing " + packInfo.packageName);
                if (isXposedModule(context.getPackageManager().getApplicationInfo(packInfo.packageName, PackageManager.GET_META_DATA))
                        || detectServices(packInfo) || detectReceivers(packInfo)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isXposedModule(ApplicationInfo appInfo) {
        Bundle bundle = appInfo.metaData;

        try {
            if (bundle.getBoolean(metaData[0])) {
                Log.d(TAG, "Xposed level: Package is xposed module");
                String moduleDesc = bundle.getString(metaData[1]);
                if (moduleDesc != null && moduleDesc.contains("Lucky") && moduleDesc.contains("Patcher")) {
                    Log.d(TAG, "Xposed level: Package is lucky patcher");
                    return true;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean detectServices(PackageInfo packInfo) {
        ServiceInfo[] serviceInfoArr = packInfo.services;

        try {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                for (String serviceName : services) {
                    if (serviceInfo.name.equals(serviceName)) {
                        Log.d(TAG, "Service check level: Package is lucky patcher");
                        return true;
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean detectReceivers(PackageInfo packInfo) {
        ActivityInfo[] receiverArr = packInfo.receivers;

        try {
            for (ActivityInfo receiver : receiverArr) {
                for (String receiverName : receivers) {
                    Log.d(TAG, receiver.name);
                    if (receiver.name.equals(receiverName)) {
                        Log.d(TAG, "Receiver check level: Package is lucky patcher");
                        return true;
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
