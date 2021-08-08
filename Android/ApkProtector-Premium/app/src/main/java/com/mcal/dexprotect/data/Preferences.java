package com.mcal.dexprotect.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.mcal.dexprotect.App;
import com.mcal.dexprotect.utils.Utils;

import org.jetbrains.annotations.Contract;

import java.io.File;

public final class Preferences {
    private static SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());

    public Preferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
    }

    public static boolean getProtectManifest() {
        return preferences.getBoolean("protectManifest", false);
    }

    public static String getTempAxml() {
        return preferences.getString("tempAxml", "");
    }

    public static void setTempAxml(String flag) {
        preferences.edit().putString("tempAxml", flag).apply();
    }

    @Contract(pure = true)
    public static SharedPreferences getDefSharedPreferences() {
        return preferences;
    }

    public static boolean getOldDexloaderBoolean() {
        return preferences.getBoolean("oldDexloaderBoolean", false);
    }

    public static boolean getZipAlignerBoolean() {
        return preferences.getBoolean("zipalignerBoolean", false);
    }

    public static String getAlignmentString() {
        return preferences.getString("alignmentString", "4");
    }

    public static boolean getForceBoolean() {
        return preferences.getBoolean("forceBoolean", false);
    }

    public static boolean getZopfliBoolean() {
        return preferences.getBoolean("zopfliBoolean", false);
    }

    public static boolean getPageAlignSharedLibsBoolean() {
        return preferences.getBoolean("pageAlignSharedLibsBoolean", false);
    }

    public static boolean getDexProtectBoolean() {
        return preferences.getBoolean("dexProtectBoolean", false);
    }

    public static void setDexProtectBoolean(boolean flag) {
        preferences.edit().putBoolean("dexProtectBoolean", flag).apply();
    }

    public static boolean getEncryptResourcesBoolean() {
        return preferences.getBoolean("encryptResourcesBoolean", false);
    }

    public static boolean getSignApkBoolean() {
        return preferences.getBoolean("signApkBoolean", false);
    }

    public static void setSignApkBoolean(boolean flag) {
        preferences.edit().putBoolean("signApkBoolean", flag).apply();
    }

    public static String isProtectKeyString(String str) {
        return preferences.getString("protectKeyString", str);
    }

    public static String getProtectKeyString() {
        return preferences.getString("protectKeyString", Utils.sealing(Utils.buildID()));
    }

    public static String isApkPath() {
        return preferences.getString("apkPath", "");
    }

    public static void isSplashActivityBoolean(boolean flag) {
        preferences.edit().putBoolean("splashActivityBoolean", flag).apply();
    }

    public static boolean isSplashActivityBoolean() {
        return preferences.getBoolean("splashActivityBoolean", false);
    }

    public static void isTitleNotificationBoolean(boolean flag) {
        preferences.edit().putBoolean("titleNotificationBoolean", flag).apply();
    }

    public static boolean isTitleNotificationBoolean() {
        return preferences.getBoolean("titleNotificationBoolean", false);
    }

    public static void isWelcomeMessageString(String flag) {
        preferences.edit().putString("welcomeMessageString", flag).apply();
    }

    public static String isWelcomeMessageString() {
        return preferences.getString("welcomeMessageString", "Powered by ApkProtector");
    }

    public static int isWelcomeModeInt(int i) {
        return preferences.getInt("welcomeModeInt", i);
    }

    public static void isWelcomeMessageBoolean(boolean flag) {
        preferences.edit().putBoolean("welcomeMessageBoolean", flag).apply();
    }

    public static boolean isWelcomeMessageBoolean() {
        return preferences.getBoolean("welcomeMessageBoolean", false);
    }

    public static boolean isCheckVPNBoolean() {
        return preferences.getBoolean("checkVPNBoolean", false);
    }

    public static void isCheckVPNBoolean(boolean flag) {
        preferences.edit().putBoolean("checkVPNBoolean", flag).apply();
    }

    public static void isCrashNotificationBoolean(boolean flag) {
        preferences.edit().putBoolean("crashNotificationBoolean", flag).apply();
    }

    public static boolean isCrashNotificationBoolean() {
        return preferences.getBoolean("crashNotificationBoolean", false);
    }

    public static void isDeviceLockBoolean(boolean flag) {
        preferences.edit().putBoolean("deviceLockBoolean", flag).apply();
    }

    public static boolean isDeviceLockBoolean() {
        return preferences.getBoolean("deviceLockBoolean", false);
    }

    public static void setAssetsCheckString(String flag) {
        preferences.edit().putString("assetsCheckString", flag).apply();
    }

    public static void setCppLibCheckString(String flag) {
        preferences.edit().putString("cppLibCheckString", flag).apply();
    }

    public static String getCppLibCheckString() {
        return preferences.getString("cppLibCheckString", "libfuck.so\nlibarm.so\nlibarm_signer.so\nlibsandhook.so\nlibEpic.so\nlibcnfix.so");
    }

    public static String getAssetsCheckString() {
        return preferences.getString("assetsCheckString", "Hook_apk\nlibjiagu.so\nlibjiagu_a64.so\nlibjiagu_x64.so\nlibjiagu_x86.so\narm\nhook.apk\nArm_Epic\nApp_dex/classes.dex\nApp_dex/Modex.txt\nHook_so/arm64-v8a/libIOHook.so\nHook_so/arm64-v8a/libmocls.so\nHook_so/arm64-v8a/libsandhook.so\nHook_so/armeabi-v7a/libmocls.so\nHook_so/armeabi-v7a/libsandhook.so\nHook_so/armeabi-v7a/libIOHook.so\npackage$Info");
    }

    public static void isHookCheckString(String flag) {
        preferences.edit().putString("hookCheckString", flag).apply();
    }

    public static String isHookCheckString() {
        return preferences.getString("hookCheckString", "com.topjohnwu.magisk\nde.robv.android.xposed");
    }

    public static void isHookCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("hookCheckBoolean", flag).apply();
    }

    public static boolean isHookCheckBoolean() {
        return preferences.getBoolean("hookCheckBoolean", false);
    }

    public static void isIllegalCodeCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("illegalCodeCheckBoolean", flag).apply();
    }

    public static boolean getResNameArscBoolean() {
        return preferences.getBoolean("resNameArscBoolean", false);
    }

    public static void setResNameArscBoolean(Boolean flag) {
        preferences.edit().putBoolean("resNameArscBoolean", flag).apply();
    }

    public static String getResNameArscString() {
        return preferences.getString("resNameArscString", "ApkProtector");
    }

    public static void setResNameArscString(String flag) {
        preferences.edit().putString("resNameArscString", flag).apply();
    }

    public static boolean isIllegalCodeCheckBoolean() {
        return preferences.getBoolean("illegalCodeCheckBoolean", false);
    }

    public static void isIllegalCodeCheckString(String flag) {
        preferences.edit().putString("illegalCodeCheckString", flag).apply();
    }

    public static String isIllegalCodeCheckString() {
        return preferences.getString("illegalCodeCheckString", "bin.mt.apksignaturekillerplus.HookApplication\ncc.binmt.signature.PmsHookApplication\ncc.binmt.signature.Hook");
    }

    public static void isCloneCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("cloneCheckBoolean", flag).apply();
    }

    public static boolean isCloneCheckBoolean() {
        return preferences.getBoolean("cloneCheckBoolean", false);
    }

    public static void isEmulatorCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("emulatorCheckBoolean", flag).apply();
    }

    public static boolean isEmulatorCheckBoolean() {
        return preferences.getBoolean("emulatorCheckBoolean", false);
    }

    public static void isPlaystoreCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("playstoreCheckBoolean", flag).apply();
    }

    public static boolean isPlaystoreCheckBoolean() {
        return preferences.getBoolean("playstoreCheckBoolean", false);
    }

    public static void isDebugCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("debugCheckBoolean", flag).apply();
    }

    public static boolean isDebugCheckBoolean() {
        return preferences.getBoolean("debugCheckBoolean", false);
    }

    public static void isXposedCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("xposedCheckBoolean", flag).apply();
    }

    public static boolean isXposedCheckBoolean() {
        return preferences.getBoolean("xposedCheckBoolean", false);
    }

    public static void isMagiskCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("magiskCheckBoolean", flag).apply();
    }

    public static boolean isMagiskCheckBoolean() {
        return preferences.getBoolean("magiskCheckBoolean", false);
    }

    public static void isRootCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("rootCheckBoolean", flag).apply();
    }

    public static boolean isRootCheckBoolean() {
        return preferences.getBoolean("rootCheckBoolean", false);
    }

    public static void isSignatureCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("signatureCheckBoolean", flag).apply();
    }

    public static boolean isSignatureCheckBoolean() {
        return preferences.getBoolean("signatureCheckBoolean", false);
    }

    public static void setAssetsCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("assetsCheckBoolean", flag).apply();
    }

    public static void setCppLibCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("cppLibCheckBoolean", flag).apply();
    }

    public static boolean isCppLibCheckBoolean() {
        return preferences.getBoolean("cppLibCheckBoolean", false);
    }

    public static boolean isAssetsCheckBoolean() {
        return preferences.getBoolean("assetsCheckBoolean", false);
    }

    public static void isLuckyPatcherCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("luckyPatcherCheckBoolean", flag).apply();
    }

    public static void setModexHookCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("modexHookBoolean", flag).apply();
    }

    public static boolean isModexHookCheckBoolean() {
        return preferences.getBoolean("modexHookBoolean", false);
    }

    public static boolean isLuckyPatcherCheckBoolean() {
        return preferences.getBoolean("luckyPatcherCheckBoolean", false);
    }

    public static String isUserIgnoredClasses(String str) {
        return preferences.getString("userIgnoredClasses", str);
    }

    public static String isCertPassword() {
        return preferences.getString("certPassword", "");
    }

    public static String isSignaturePassword() {
        return preferences.getString("signaturePassword", "");
    }

    public static String isSignatureAlias() {
        return preferences.getString("signatureAlias", "");
    }

    public static String isSignaturePath() {
        return preferences.getString("signaturePath", "");
    }

    public static boolean isCustomSignature() {
        return preferences.getBoolean("customSignature", false);
    }

    public static boolean isOptimizeDexBoolean() {
        return preferences.getBoolean("optimizeDexBoolean", true);
    }

    public static void isCustomAppNameString(String flag) {
        preferences.edit().putString("customAppNameString", flag).apply();
    }

    public static String isCustomAppNameString() {
        return preferences.getString("customAppNameString", "com.mcal.dexprotect.ProxyApplication");
    }

    public static void isCustomAppNameBoolean(boolean flag) {
        preferences.edit().putBoolean("customAppNameBoolean", flag).apply();
    }

    public static boolean isCustomAppNameBoolean() {
        return preferences.getBoolean("customAppNameBoolean", false);
    }

    public static void isEncryptResourcesBoolean(boolean value) {
        preferences.edit().putBoolean("encryptResourcesBoolean", value).apply();
    }

    public static boolean isEncryptResourcesBoolean() {
        return preferences.getBoolean("encryptResourcesBoolean", false);
    }

    public static void setEncryptResourcesBoolean(boolean flag) {
        preferences.edit().putBoolean("encryptResourcesBoolean", flag).apply();
    }

    public static boolean isNightModeEnabled() {
        return preferences.getBoolean("night_mode", false);
    }

    public static void setNightModeEnabled(boolean flag) {
        preferences.edit().putBoolean("night_mode", flag).apply();
    }

    public static boolean getSignatureV1() {
        return preferences.getBoolean("signatureV1", true);
    }

    public static boolean getSignatureV2() {
        return preferences.getBoolean("signatureV2", true);
    }

    public static boolean getSignatureV3() {
        return preferences.getBoolean("signatureV3", false);
    }

    public static boolean getSignatureV4() {
        return preferences.getBoolean("signatureV4", false);
    }


    public static String getProtectKey() {
        return preferences.getString("protectKeyString", Utils.sealing(Utils.buildID()));
    }

    // Имя папки с дексами
    public static String getDexLoader() {
        switch (Preferences.getTypeHideApkProtector()) {
            case "0":
                return Constants.OUTPUT_PATH + File.separator + "dexloader.dex";
            case "1":
            case "2":
                //DexCloner.dexPatching(Constants.OUTPUT_PATH + File.separator + "dexloader.dex");
                return Constants.OUTPUT_PATH + File.separator + "dexloader.dex";
        }
        return null;
    }

    // Имя папки с дексами
    public static String getFolderDexesName() {
        switch (Preferences.getTypeHideApkProtector()) {
            case "0":
                return Constants.DEX_DIR;
            case "1":
            case "2":
                return preferences.getString("customFolderDexesName", Constants.DEX_DIR);
        }
        return null;
    }

    public static void setFolderDexesName(String flag) {
        preferences.edit().putString("customFolderDexesName", flag).apply();
    }

    // Имя пакета
    public static String getPackageName() {
        switch (Preferences.getTypeHideApkProtector()) {
            case "0":
                return Constants.PACKAGE_NAME;
            case "1":
            case "2":
                return preferences.getString("customPackageName", Constants.PACKAGE_NAME);
        }
        return null;
    }

    public static void setPackageName(String flag) {
        preferences.edit().putString("customPackageName", flag).apply();
    }


    // Имя дексов
    public static String getPrefixDexesName() {
        switch (Preferences.getTypeHideApkProtector()) {
            case "0":
                return Constants.DEX_PREFIX;
            case "1":
            case "2":
                return preferences.getString("customPrefixDexesName", Constants.DEX_PREFIX);
        }
        return null;
    }

    public static void setPrefixDexesName(String flag) {
        preferences.edit().putString("customPrefixDexesName", flag).apply();
    }

    // Расширение дексов
    public static String getSuffixDexesName() {
        switch (Preferences.getTypeHideApkProtector()) {
            case "0":
                return Constants.DEX_SUFFIX;
            case "1":
            case "2":
                return preferences.getString("customSuffixDexesName", Constants.DEX_SUFFIX);
        }
        return null;
    }

    public static void setSuffixDexesName(String flag) {
        preferences.edit().putString("customSuffixDexesName", flag).apply();
    }

    public static String getTypeHideApkProtector() {
        return preferences.getString("typeHideApkProtector", "0");
    }

    public static String getApplicationName() {
        return getPackageName() + ".ProtectApplication";
    }
}