package com.mcal.dex;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import androidx.multidex.MultiDexApplication;

import com.mcal.dex.utils.DexProtector;
import com.mcal.dex.utils.Preferences;

import org.jetbrains.annotations.NotNull;

public class ProxyApplication extends MultiDexApplication {


    @Override
    protected void attachBaseContext(@NotNull Context base) {
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
        if (!TextUtils.isEmpty(Preferences.realApplication(this))) {
            Application app = DexProtector.realApplication(Preferences.realApplication(this));
            if (app != null) {
                app.onCreate();
            }
        }
    }
}