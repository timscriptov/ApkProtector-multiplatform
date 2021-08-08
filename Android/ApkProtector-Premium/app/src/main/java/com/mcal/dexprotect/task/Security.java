package com.mcal.dexprotect.task;

import com.mcal.dexprotect.data.Preferences;
import com.mcal.dexprotect.utils.MyAppInfo;
import com.mcal.dexprotect.utils.security.SignCheck;

import org.json.JSONArray;
import org.json.JSONObject;

public class Security {
    public static String write(String apkPath) {
        try {
            JSONObject jSONObject = new JSONObject();
            String[] tag = new String[]{"Name", "Package", "VName"};
            String[] value = new String[]{MyAppInfo.getAppName(), MyAppInfo.getPackage(), MyAppInfo.getVName()};
            for (int i = 0; i < tag.length; i++) {
                jSONObject.put(tag[i], value[i]);
            }
            // Проверка установленного Lucky Patcher
            jSONObject.put("luckyPatcherCheckBoolean", Preferences.isLuckyPatcherCheckBoolean());
            // Проверка подписи
            jSONObject.put("signatureCheckString", SignCheck.getCurrentSignature(apkPath));
            jSONObject.put("signatureCheckBoolean", Preferences.isSignatureCheckBoolean());
            // Проверка Root прав
            jSONObject.put("rootCheckBoolean", Preferences.isRootCheckBoolean());
            // Проверка Magisk(Root)
            jSONObject.put("magiskCheckBoolean", Preferences.isMagiskCheckBoolean());
            // Проверка Xposed
            jSONObject.put("xposedCheckBoolean", Preferences.isXposedCheckBoolean());
            // Проверка Modex 3.0
            jSONObject.put("modexCheckBoolean", Preferences.isModexHookCheckBoolean());
            // Проверка отладки
            jSONObject.put("debugCheckBoolean", Preferences.isDebugCheckBoolean());
            // Проверка установки в Google Play Store
            jSONObject.put("playstoreCheckBoolean", Preferences.isPlaystoreCheckBoolean());
            // Проверка устройства на эмулятор
            jSONObject.put("emulatorCheckBoolean", Preferences.isEmulatorCheckBoolean());
            // Проверка имени пакета, версии и код версии
            jSONObject.put("cloneCheckBoolean", Preferences.isCloneCheckBoolean());
            jSONObject.put("VCode", MyAppInfo.getVCode());
            // Проверка встроенных классов
            jSONObject.put("illegalCodeCheckBoolean", Preferences.isIllegalCodeCheckBoolean());
            if (Preferences.isIllegalCodeCheckBoolean()) {
                JSONArray jSONArray = new JSONArray();
                value = Preferences.isIllegalCodeCheckString().split("\n");
                for (Object put : value) {
                    jSONArray.put(put);
                }
                jSONObject.put("illegalCodeCheckString", jSONArray);
            }
            // Проверка C++ библиотек
            jSONObject.put("cppLibsCheckBoolean", Preferences.isCppLibCheckBoolean());
            if (Preferences.isCppLibCheckBoolean()) {
                JSONArray jSONArray = new JSONArray();
                value = Preferences.getCppLibCheckString().split("\n");
                for (Object put : value) {
                    jSONArray.put(put);
                }
                jSONObject.put("cppLibsCheckString", jSONArray);
            }
            // Проверка assets файлов
            jSONObject.put("assetsCheckBoolean", Preferences.isAssetsCheckBoolean());
            if (Preferences.isAssetsCheckBoolean()) {
                JSONArray jSONArray = new JSONArray();
                value = Preferences.getAssetsCheckString().split("\n");
                for (Object put : value) {
                    jSONArray.put(put);
                }
                jSONObject.put("assetsCheckString", jSONArray);
            }
            // Проверка пиратских приложений
            jSONObject.put("hookCheckBoolean", Preferences.isHookCheckBoolean());
            if (Preferences.isHookCheckBoolean()) {
                JSONArray jSONArray = new JSONArray();
                value = Preferences.isHookCheckString().split("\n");
                for (Object put : value) {
                    jSONArray.put(put);
                }
                jSONObject.put("Hooks", jSONArray);
            }
            // Проверка VPN
            jSONObject.put("checkVPNBoolean", Preferences.isCheckVPNBoolean());

            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
