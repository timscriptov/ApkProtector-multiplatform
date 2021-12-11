package com.mcal.apkprotector;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.ArrayMap;

import com.mcal.apkprotector.data.Const;
import com.mcal.apkprotector.multidex.MultiDex;
import com.mcal.apkprotector.utils.Reflect;

import java.util.ArrayList;
import java.util.Iterator;

public class ProtectApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        /*try {
            new Security(this, Const.DATA);
        } finally {
            MultiDex.install(this);
        }*/
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Application app = realApplication();
            if (app != null) {
                app.onCreate();
            }
    }

    private Application realApplication() {
        // Определяем наличие Applicaiton
        Object currentActivityThread = Reflect.invokeMethod("android.app.ActivityThread", null, "currentActivityThread", new Object[]{}, null);
        Object mBoundApplication = Reflect.getFieldValue(
                "android.app.ActivityThread", currentActivityThread,
                "mBoundApplication");
        Object loadedApkInfo = Reflect.getFieldValue(
                "android.app.ActivityThread$AppBindData",
                mBoundApplication, "info");
        // Установить для текущего процесса Application значение null
        Reflect.setFieldValue("android.app.LoadedApk", loadedApkInfo, "mApplication", null);
        Object oldApplication = Reflect.getFieldValue(
                "android.app.ActivityThread", currentActivityThread,
                "mInitialApplication");
        // http://www.codeceo.com/article/android-context.html
        ArrayList<Application> mAllApplications = (ArrayList<Application>) Reflect
                .getFieldValue("android.app.ActivityThread",
                        currentActivityThread, "mAllApplications");
        if (mAllApplications != null) {
            mAllApplications.remove(oldApplication);// Удалить старый Application
        }

        ApplicationInfo loadedApk = (ApplicationInfo) Reflect
                .getFieldValue("android.app.LoadedApk", loadedApkInfo,
                        "mApplicationInfo");
        ApplicationInfo appBindData = (ApplicationInfo) Reflect
                .getFieldValue("android.app.ActivityThread$AppBindData",
                        mBoundApplication, "appInfo");

        if (loadedApk != null) {
            loadedApk.className = Const.REAL_APPLICATION;
        }
        if (appBindData != null) {
            appBindData.className = Const.REAL_APPLICATION;
        }

        Application app = (Application) Reflect.invokeMethod(
                "android.app.LoadedApk", loadedApkInfo, "makeApplication",
                new Object[]{false, null},
                boolean.class, Instrumentation.class); // Выполнить makeApplication (false, null)

        Reflect.setFieldValue("android.app.ActivityThread", currentActivityThread, "mInitialApplication", app);

        //
        ArrayMap<String, String> mProviderMap = (ArrayMap<String, String>) Reflect.getFieldOjbect(
                "android.app.ActivityThread", currentActivityThread,
                "mProviderMap");
        Iterator<String> it = null;
        if (mProviderMap != null) {
            it = mProviderMap.values().iterator();
        }
        if (it != null) {
            while (it.hasNext()) {
                Object providerClientRecord = it.next();
                Object localProvider = Reflect.getFieldOjbect(
                        "android.app.ActivityThread$ProviderClientRecord",
                        providerClientRecord, "mLocalProvider");
                Reflect.setFieldOjbect("android.content.ContentProvider",
                        "mContext", localProvider, app);
            }
        }
        return app;
    }
}
