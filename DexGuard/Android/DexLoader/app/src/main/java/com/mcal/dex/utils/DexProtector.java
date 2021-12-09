package com.mcal.dex.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DexProtector {
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static List<File> dexFiles = new ArrayList<>();
    File dexDir;
    File optDir;

    private Class<?> loadedApkClass;
    private WeakReference loadedApkRef;

    @SuppressLint("PrivateApi")
    public DexProtector(@NotNull Context context) {
        mContext = context;
        dexFiles = new ArrayList<>();
        dexDir = context.getDir("apkprotector_dex", Context.MODE_PRIVATE);
        optDir = new File(context.getFilesDir(), "opt");

        /*try {
            @SuppressLint("PrivateApi") Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            @SuppressLint("DiscouragedPrivateApi") Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            Object activityThread = currentActivityThreadMethod.invoke(null);

            Field mPackagesField = activityThreadClass.getDeclaredField("mPackages");
            mPackagesField.setAccessible(true); //取消默认 Java 语言访问控制检查的能力（暴力反射）
            Map mPackages = (Map) mPackagesField.get(activityThread);
            loadedApkRef = (WeakReference) mPackages.get(context.getPackageName());

            loadedApkClass = Class.forName("android.app.LoadedApk");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        FileUtils.deleteFloor(dexDir.getAbsolutePath());
        FileUtils.mkdir(dexDir.getAbsolutePath());
        FileUtils.mkdir(optDir.getAbsolutePath());
    }

    public static Application realApplication(String appClassName) {
        Object currentActivityThread = Reflect.invokeMethod(
                "android.app.ActivityThread", null, "currentActivityThread", new Object[]{}, new Class[]{});
        Object mBoundApplication = Reflect.getFieldValue(
                "android.app.ActivityThread", currentActivityThread,
                "mBoundApplication");
        Object loadedApkInfo = Reflect.getFieldValue(
                "android.app.ActivityThread$AppBindData",
                mBoundApplication, "info");
        Reflect.setFieldValue("android.app.LoadedApk", loadedApkInfo, "mApplication", null);
        Object oldApplication = Reflect.getFieldValue(
                "android.app.ActivityThread", currentActivityThread,
                "mInitialApplication");
        ArrayList<Application> mAllApplications = (ArrayList<Application>) Reflect
                .getFieldValue("android.app.ActivityThread",
                        currentActivityThread, "mAllApplications");
        mAllApplications.remove(oldApplication);

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
                boolean.class, Instrumentation.class);

        Reflect.setFieldValue("android.app.ActivityThread", currentActivityThread, "mInitialApplication", app);
        return app;
    }

    public void install(@NotNull Context context) throws Exception {
        String[] filelist = mContext.getAssets().list("dp-lib");
        for (int o = 0; o < filelist.length; o++) {
            if (filelist[o].endsWith(".lua.mph")) {
                new DexCrypto(mContext, dexFiles).op(context, filelist[o]);
            }
        }
        File dexDir = context.getDir("apkprotector_dex", 0);
        if (!dexDir.exists()) {
            dexDir.mkdirs();
        }
        new MyClassLoader(context, dexFiles, dexDir, optDir).loadDexByVersion();
        //loadDex(context, dexFiles, dexDir);

        FileUtils.deleteFiles(dexDir);
    }

    private void loadDex(Context context, List<File> dexFiles, File versionDir) throws Exception {
        ClassLoader classLoader = null;
        if (getHelper() == null) {
            classLoader = context.getClassLoader();
        } else {
            classLoader = getHelper();
        }

        Field pathListField = Reflect.findField(classLoader, "pathList");
        Object pathList = pathListField.get(classLoader);

        Field dexElementsField = Reflect.findField(pathList, "dexElements");
        Object[] dexElements = (Object[]) dexElementsField.get(pathList);

        Method makeDexElements;
        //makeDexElements = Utils.findMethod(pathList, "makePathElements", List.class, File.class, List.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //5.0 - 6.0
            makeDexElements = Reflect.findMethod(pathList, "makeDexElements", ArrayList.class, File.class, ArrayList.class);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //大于 6.0
            makeDexElements = Reflect.findMethod(pathList, "makePathElements", List.class, File.class, List.class);
        } else {
            return;
        }
        ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
        Object[] addElements = (Object[]) makeDexElements.invoke(pathList, dexFiles, versionDir, suppressedExceptions);
        //合并数组
        Object[] newElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), dexElements.length + addElements.length);
        System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
        System.arraycopy(addElements, 0, newElements, dexElements.length, addElements.length);
        //替换classloader中的element数组
        dexElementsField.set(pathList, newElements);
    }

    public ClassLoader getHelper() {
        try {
            Field declaredField = this.loadedApkClass.getDeclaredField("mClassLoader");
            declaredField.setAccessible(true);
            return (ClassLoader) declaredField.get(this.loadedApkRef.get());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}