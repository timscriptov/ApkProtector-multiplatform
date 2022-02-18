package com.mcal.apkprotector;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ProtectApplication extends Application {

    private static final String TAG = "ProtectApplication";
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static SharedPreferences preferences;

    public static Context getContext() {
        return context;
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        context = base;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //new Security(this, Const.DATA);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
