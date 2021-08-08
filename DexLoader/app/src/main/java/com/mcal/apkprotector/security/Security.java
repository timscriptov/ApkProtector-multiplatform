package com.mcal.apkprotector.security;

import android.content.Context;
import android.content.pm.PackageInfo;

import com.mcal.apkprotector.utils.CommonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;

public class Security {
    public Security(Context context, String data) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            JSONObject obj = new JSONObject(data);
            // Пользовательский заголовок уведомлений
            String dxTitle = "ApkProtector";
            // Проверка имени пакета приложения и версии приложения в манифесте
            if (!data.isEmpty()) {
                if (obj.getBoolean("cloneCheckBoolean")) {
                    // Имя приложения
                    if (!((context.getPackageManager().getApplicationLabel(context.getApplicationInfo())).equals(obj.getString("Name")))) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                    }
                    // Имя пакета приложения
                    if (!(context.getPackageName().equals(obj.getString("Package")))) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                    }
                    // Версия приложения
                    if (!(pInfo.versionName.equals(obj.getString("VName")))) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                    }
                    // Код приложения
                    if (!(pInfo.versionCode == obj.getInt("VCode"))) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                    }
                }
                // Проверка подписи
                if (obj.getBoolean("signatureCheckBoolean")) {
                    if (!obj.getString("signatureCheckString").equals(SecurityUtils.getCurrentSignature(context))) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected APK signature does not match!");
                    }
                }
                // Проверка root прав
                if (obj.getBoolean("rootCheckBoolean")) {
                    if (SecurityUtils.isRooted()) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected ROOT!");
                    }
                }
                // Проверка установленного LP
                if (obj.getBoolean("luckyPatcherCheckBoolean") && SecurityUtils.isLucky(context)) {
                    CommonUtils.showDialogWarn(context, dxTitle, "Detected Lucky Patcher!");
                }
                // Проверка установленного Xposed
                if (obj.getBoolean("xposedCheckBoolean")) {
                    if (SecurityUtils.checkXposedExistAndDisableIt()) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected Xposed!");
                    }
                }
                // Проверка root прав от Magisk
                if (obj.getBoolean("magiskCheckBoolean")) {
                    if (SecurityUtils.isMagisk()) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected Magisk!");
                    }
                }
                // Проверка установки приложения из маркета
                if (obj.getBoolean("playstoreCheckBoolean")) {
                    if (!SecurityUtils.isInstalledViaGooglePlay(context)) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Please install apk on Google Play!");
                    }
                }
                // Проверка устройства на эмулятор
                if (obj.getBoolean("emulatorCheckBoolean")) {
                    if (SecurityUtils.isEmulator()) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Running in emulator!");
                    }
                }
                // Проверка отладки приложения
                if (obj.getBoolean("debugCheckBoolean")) {
                    if (SecurityUtils.isDebuggable(context)) {
                        CommonUtils.showDialogWarn(context, "ApkProtector Security", "Detected Debug!");
                    }
                }
                // Проверка Modex 3.0
                if (obj.getBoolean("modexCheckBoolean")) {
                    File xpath = context.getDir("libs", Context.MODE_PRIVATE);
                    String[] files = new String[]{xpath + "/App_dex/classes.dex",
                            xpath + "/App_dex/Modex.txt", xpath + "/arm64-v8a/libIOHook.so",
                            xpath + "/arm64-v8a/libmock.so", xpath + "/arm64-v8a/libsandhook.so",
                            xpath + "/armeabi-v7a/libIOHook.so", xpath + "/armeabi-v7a/libmock.so",
                            xpath + "/armeabi-v7a/libsandhook.so"};
                    for (String s : files) {
                        if(new File(s).exists()) {
                            CommonUtils.showDialogWarn(context, "ApkProtector Security", "Detected Modex 3.0!");
                        }
                    }
                }
                if (obj.getBoolean("debugCheckBoolean")) {
                    if (SecurityUtils.detectDebugger()) {
                        CommonUtils.showDialogWarn(context, "ApkProtector Security", "Detected Debug!");
                    }
                }
                // Проверка запущенного VPN
                if (obj.getBoolean("checkVPNBoolean")) {
                    if (SecurityUtils.isVpn()) {
                        CommonUtils.showDialogWarn(context, dxTitle, "Detected VPN!");
                    }
                }
                // Проверка встроенных классов
                if (obj.getBoolean("illegalCodeCheckBoolean")) {
                    JSONArray jSONArray = obj.getJSONArray("illegalCodeCheckString");

                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (SecurityUtils.illegalCodeCheck(jSONArray.getString(i))) {
                            CommonUtils.showDialogWarn(context, data, "Detected Illegal code:\n " + jSONArray.getString(i));
                            break;
                        }
                    }
                }
                // Проверка установленных приложений
                if (obj.getBoolean("hookCheckBoolean")) {
                    JSONArray jSONArray = obj.getJSONArray("Hooks");

                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (SecurityUtils.isInstallPirateApp(context, jSONArray.getString(i))) {
                            CommonUtils.showDialogWarn(context, data, "Detected Pirate App:\n " + jSONArray.getString(i));
                            break;
                        }
                    }
                }
                // Проверка файлов в assets
                if (obj.getBoolean("assetsCheckBoolean")) {
                    JSONArray jSONArray = obj.getJSONArray("assetsCheckString");

                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (SecurityUtils.assetsCheck(context, jSONArray.getString(i))) {
                            CommonUtils.showDialogWarn(context, data, "Detected Pirate App:\n " + jSONArray.getString(i));
                            break;
                        }
                    }
                }
                // Проверка C++ библиотек
                if (obj.getBoolean("cppLibsCheckBoolean")) {
                    JSONArray jSONArray = obj.getJSONArray("cppLibsCheckString");

                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (SecurityUtils.cppCheck(context, jSONArray.getString(i))) {
                            CommonUtils.showDialogWarn(context, data, "Detected Pirate App:\n " + jSONArray.getString(i));
                            break;
                        }
                    }
                }

            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}