package com.mcal.apkprotector;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.mcal.apkprotector.data.Const;
import com.mcal.apkprotector.multidex.MultiDex;
import com.mcal.apkprotector.security.Security;
import com.mcal.apkprotector.utils.RefInvoke;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ProtectApplication extends Application {

    private static final String TAG = "Application";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Object currentActivityThread = RefInvoke.invokeStaticMethod(
                "android.app.ActivityThread", "currentActivityThread",
                new Class[]{}, new Object[]{});
        String packageName = getPackageName();
        Map mPackages = (Map) RefInvoke.getFieldOjbect(
                "android.app.ActivityThread", currentActivityThread,
                "mPackages");
        WeakReference wr = (WeakReference) mPackages.get(packageName);

        //MultiDex.install(this);
        try {
            new Security(this, Const.DATA);
        } finally {
            MultiDex.install(this);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        realApplication();
    }

    private void realApplication() {
        // 如果源应用配置有Appliction对象，则替换为源应用Applicaiton，以便不影响源程序逻辑。

        /**
         * 调用静态方法android.app.ActivityThread.currentActivityThread
         * 获取当前activity所在的线程对象
         */
        Object currentActivityThread = RefInvoke.invokeStaticMethod(
                "android.app.ActivityThread", "currentActivityThread",
                new Class[]{}, new Object[]{});
        /**
         * 获取currentActivityThread中的mBoundApplication属性对象，该对象是一个
         *  AppBindData类对象，该类是ActivityThread的一个内部类
         */
        Object mBoundApplication = RefInvoke.getFieldOjbect(
                "android.app.ActivityThread", currentActivityThread,
                "mBoundApplication");
        /**
         * 获取mBoundApplication中的info属性，info 是 LoadedApk类对象
         */
        Object loadedApkInfo = RefInvoke.getFieldOjbect(
                "android.app.ActivityThread$AppBindData",
                mBoundApplication, "info");
        /**
         * loadedApkInfo对象的mApplication属性置为null
         */
        RefInvoke.setFieldOjbect("android.app.LoadedApk", "mApplication",
                loadedApkInfo, null);
        /**
         * 获取currentActivityThread对象中的mInitialApplication属性
         * 这货是个正牌的 Application
         */
        Object oldApplication = RefInvoke.getFieldOjbect(
                "android.app.ActivityThread", currentActivityThread,
                "mInitialApplication");
        /**
         * 获取currentActivityThread对象中的mAllApplications属性
         * 这货是 装Application的列表
         */
        ArrayList<Application> mAllApplications = (ArrayList<Application>) RefInvoke
                .getFieldOjbect("android.app.ActivityThread",
                        currentActivityThread, "mAllApplications");
        //列表对象终于可以直接调用了 remove调了之前获取的application 抹去记录的样子
        mAllApplications.remove(oldApplication);
        /**
         * 获取前面得到LoadedApk对象中的mApplicationInfo属性，是个ApplicationInfo对象
         */
        ApplicationInfo appinfo_In_LoadedApk = (ApplicationInfo) RefInvoke
                .getFieldOjbect("android.app.LoadedApk", loadedApkInfo,
                        "mApplicationInfo");
        /**
         * 获取前面得到AppBindData对象中的appInfo属性，也是个ApplicationInfo对象
         */
        ApplicationInfo appinfo_In_AppBindData = (ApplicationInfo) RefInvoke
                .getFieldOjbect("android.app.ActivityThread$AppBindData",
                        mBoundApplication, "appInfo");
        //把这两个对象的className属性设置为从meta-data中获取的被加密apk的application路径
        if (appinfo_In_LoadedApk != null) {
            appinfo_In_LoadedApk.className = Const.REAL_APPLICATION;
        }
        if (appinfo_In_AppBindData != null) {
            appinfo_In_AppBindData.className = Const.REAL_APPLICATION;
        }
        /**
         * 调用LoadedApk中的makeApplication 方法 造一个application
         * 前面改过路径了
         */
        Application app = (Application) RefInvoke.invokeMethod(
                "android.app.LoadedApk", "makeApplication", loadedApkInfo,
                new Class[]{boolean.class, Instrumentation.class},
                new Object[]{false, null});
        RefInvoke.setFieldOjbect("android.app.ActivityThread",
                "mInitialApplication", currentActivityThread, app);

        Map<String, String> mProviderMap = (Map<String, String>) RefInvoke.getFieldOjbect(
                "android.app.ActivityThread", currentActivityThread,
                "mProviderMap");
        Iterator<String> it = mProviderMap.values().iterator();
        while (it.hasNext()) {
            Object providerClientRecord = it.next();
            Object localProvider = RefInvoke.getFieldOjbect(
                    "android.app.ActivityThread$ProviderClientRecord",
                    providerClientRecord, "mLocalProvider");
            RefInvoke.setFieldOjbect("android.content.ContentProvider",
                    "mContext", localProvider, app);
        }
        if (null == app) {
            Log.e(TAG, "application get is null !");
        } else {
            app.onCreate();
        }
    }
}
