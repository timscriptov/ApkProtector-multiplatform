package com.mcal.apkprotector;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.mcal.apkprotector.multidex.MultiDex;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.Reflect;

import java.util.ArrayList;

public class ProtectApplication extends Application {
    public static final String DEX_SUFFIX = CommonUtils.encryptStrings("$DEX_SUFIX", 2);
    public static final String DEX_PREFIX = CommonUtils.encryptStrings("$DEX_PREFIX", 2);
    public static final String DEX_DIR = CommonUtils.encryptStrings("$DEX_DIR", 2);
    public static final String PROTECT_KEY = CommonUtils.encryptStrings("$PROTECT_KEY", 2);
    public static final String REAL_APPLICATION = "$REAL_APPLICATION";

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Application app = changeTopApplication(REAL_APPLICATION);
        if (app != null) {
            app.onCreate();
        }
    }

    public static Context getContext() {
        if (context == null) {
            context = new ProtectApplication();
        }
        return context;
    }

    private Application changeTopApplication(String appClassName) {
        //有值的话调用该Applicaiton
        Object currentActivityThread = Reflect.invokeMethod("android.app.ActivityThread", null, "currentActivityThread", new Object[]{}, null);
        Object mBoundApplication = Reflect.getFieldValue(
                "android.app.ActivityThread", currentActivityThread,
                "mBoundApplication");
        Object loadedApkInfo = Reflect.getFieldValue(
                "android.app.ActivityThread$AppBindData",
                mBoundApplication, "info");
        //把当前进程的mApplication 设置成了null
        Reflect.setFieldValue("android.app.LoadedApk", loadedApkInfo, "mApplication", null);
        Object oldApplication = Reflect.getFieldValue(
                "android.app.ActivityThread", currentActivityThread,
                "mInitialApplication");
        //http://www.codeceo.com/article/android-context.html
        ArrayList<Application> mAllApplications = (ArrayList<Application>) Reflect
                .getFieldValue("android.app.ActivityThread",
                        currentActivityThread, "mAllApplications");
        mAllApplications.remove(oldApplication);//删除oldApplication

        ApplicationInfo loadedApk = (ApplicationInfo) Reflect
                .getFieldValue("android.app.LoadedApk", loadedApkInfo,
                        "mApplicationInfo");
        ApplicationInfo appBindData = (ApplicationInfo) Reflect
                .getFieldValue("android.app.ActivityThread$AppBindData",
                        mBoundApplication, "appInfo");

        loadedApk.className = appClassName;
        appBindData.className = appClassName;

        Application app = (Application) Reflect.invokeMethod(
                "android.app.LoadedApk", loadedApkInfo, "makeApplication",
                new Object[]{false, null},
                boolean.class, Instrumentation.class);//执行 makeApplication（false,null）

        Reflect.setFieldValue("android.app.ActivityThread", currentActivityThread, "mInitialApplication", app);

        return app;
    }
}
