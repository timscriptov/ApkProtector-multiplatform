package com.secure.dex;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.util.ArrayMap;

import androidx.multidex.MultiDexApplication;

import com.secure.dex.data.Const;
import com.secure.dex.utils.DexProtector;
import com.secure.dex.utils.Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class ProxyApplication extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            new DexProtector(base).install(base);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!TextUtils.isEmpty(Const.REAL_APP)) {
            try {
                android.app.Application app = bindRealApplication(Const.REAL_APP);
                if (app != null) {
                    app.onCreate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Application bindRealApplication(String packagen) throws Exception{
        if (TextUtils.isEmpty(packagen)){
            return null;
        }
        //Get the context passed in attchBaseContext(context) ContextImpl
        Context baseContext = getBaseContext();
        //Create the user's real application (MyApplication)
        Class<?> delegateClass = null;
        delegateClass = Class.forName(packagen);

        Application delegate = (Application) delegateClass.newInstance();

        //Get the atch() method
        Method attach = Application.class.getDeclaredMethod("attach",Context.class);
        attach.setAccessible(true);
        attach.invoke(delegate,baseContext);

        //Get ContextImpl ---->, mOuterContext(app); Get through the AttachBaseContext callback parameter of Application
        Class<?> contextImplClass = Class.forName("android.app.ContextImpl");
        //Get the mOuterContext attribute
        Field mOuterContextField = contextImplClass.getDeclaredField("mOuterContext");
        mOuterContextField.setAccessible(true);
        mOuterContextField.set(baseContext,delegate);

        //ActivityThread ----> mAllApplication(ArrayList) mMainThread property of ContextImpl
        Field mMainThreadField = contextImplClass.getDeclaredField("mMainThread");
        mMainThreadField.setAccessible(true);
        Object mMainThread = mMainThreadField.get(baseContext);

        //ActivityThread -----> mMainThread attribute of mInitialApplication ContextImpl
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Field mInitialApplicationField = activityThreadClass.getDeclaredField("mInitialApplication");
        mInitialApplicationField.setAccessible(true);
        mInitialApplicationField.set(mMainThread,delegate);

        //ActivityThread ------> mAllApplications(ArrayList) mMainThread property of ContextImpl
        Field mAllApplicationsField = activityThreadClass.getDeclaredField("mAllApplications");
        mAllApplicationsField.setAccessible(true);
        ArrayList<Application> mApplications = (ArrayList<Application>) mAllApplicationsField.get(mMainThread);
        mApplications.remove(this);
        mApplications.add(delegate);

        //LoadedApk -----> mPackageInfo attribute of mApplicaion ContextImpl
        Field mPackageInfoField = contextImplClass.getDeclaredField("mPackageInfo");
        mPackageInfoField.setAccessible(true);
        Object mPackageInfo = mPackageInfoField.get(baseContext);


        Class<?> loadedApkClass = Class.forName("android.app.LoadedApk");
        Field mApplicationField = loadedApkClass.getDeclaredField("mApplication");
        mApplicationField.setAccessible(true);
        mApplicationField.set(mPackageInfo,delegate);

        //Modify ApplicationInfo className LoadedApk
        Field mApplicationInfoField = loadedApkClass.getDeclaredField("mApplicationInfo");
        mApplicationInfoField.setAccessible(true);
        ApplicationInfo mApplicationInfo = (ApplicationInfo) mApplicationInfoField.get(mPackageInfo);
        mApplicationInfo.className = packagen;
        return delegate;
    }
}