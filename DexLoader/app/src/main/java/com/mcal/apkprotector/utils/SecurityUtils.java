package com.mcal.apkprotector.utils;

import android.content.Context;
import android.content.pm.PackageManager;

import java.lang.reflect.Method;

public class SecurityUtils {
    private boolean isVerigiInstaller() {
        try {
            /* getting context */
            Class appGlobals = Class.forName("android.app.AppGlobals");
            Class application = Class.forName("android.app.Application");
            Method getInitialApplication = appGlobals.getMethod("getInitialApplication");
            Method createPackageContext = application.getMethod("createPackageContext", String.class, int.class);
            Context context = (Context) createPackageContext.invoke(getInitialApplication.invoke(appGlobals), "ИМЯ ПАКЕТА", 0);

            /* getting PackageManager */
            Method getPackageManager = context.getClass().getMethod("getPackageManager");
            PackageManager pm = (PackageManager) getPackageManager.invoke(context);

            /* check install on gp */
            Method getInstallerPackageName = pm.getClass().getMethod("getInstallerPackageName", String.class);
            String installerPackageName = (String) getInstallerPackageName.invoke(pm, "ИМЯ ПАКЕТА");
            return installerPackageName != null && installerPackageName.startsWith("com.android.vending");
        } catch (Exception e) {
            return false;
        }
    }
}
