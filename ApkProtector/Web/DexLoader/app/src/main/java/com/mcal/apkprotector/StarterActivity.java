package com.mcal.apkprotector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.admin.ffffffffffffffffffffffff.MainActivity;
import com.mcal.apkprotector.multidex.MultiDex;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class StarterActivity extends Activity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.CENTER);
        mainLayout.setPadding(40, 0, 40, 0);
        mainLayout.setLayoutParams(layoutParams);

        ProgressBar b = new ProgressBar(this);
        mainLayout.addView(b);

        setContentView(mainLayout);

        loadDex();
    }

    public void loadDex() {
        File assets = new File(getFilesDir(), "assets");
        if(!assets.exists()) {
            assets.mkdir();
        }
        File dexes = new File(assets, "dexes");
        if(!dexes.exists()) {
            dexes.mkdir();
        }
        Runnable runnable = () -> {
            try {
                URL url = new URL("https://timscriptov.github.io/protector/classes-v1.bin");
                File dex = new File(dexes, "classes-v1.bin");

                InputStream inputStream = new BufferedInputStream(url.openStream());
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dex));

//                byte[] data = new byte[2048];
                int b;
                while ((b = inputStream.read()) != -1) {
                    outputStream.write(b);
                }
                inputStream.close();
                outputStream.close();

                handler.post(() -> {
                    try {
                        MultiDex.install(this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                /*handler.post(() -> {
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
                });*/

                handler.post(() -> {
                    startActivity(new Intent(this, MainActivity.class));
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
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
