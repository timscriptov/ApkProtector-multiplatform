package com.mcal.apkprotector.data;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.mcal.apkprotector.App;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.Utils;

import org.jetbrains.annotations.Contract;

public final class Preferences {
    private static SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());

    public Preferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
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

    public static void isHookCheckString(String flag) {
        preferences.edit().putString("hookCheckString", flag).apply();
    }

    public static String isHookCheckString() {
        return preferences.getString("hookCheckString", "");
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

    public static boolean isIllegalCodeCheckBoolean() {
        return preferences.getBoolean("illegalCodeCheckBoolean", false);
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

    public static void isLuckyPatcherCheckBoolean(boolean flag) {
        preferences.edit().putBoolean("luckyPatcherCheckBoolean", flag).apply();
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
        return preferences.getString("customAppNameString", "com.mcal.apkprotector.ProxyApplication");
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

    public static String getDexDir() {
        if (Preferences.isRandomPackageName()) {
            return CommonUtils.generateRandomString(Constants.DEX_DIR);
        } else {
            return Constants.DEX_DIR;
        }
    }

    public static String getPackageName() {
        if (Preferences.isRandomPackageName()) {
            return CommonUtils.generateRandomString(Constants.PACKAGE_NAME);
        } else {
            return Constants.PACKAGE_NAME;
        }
    }

    public static String getDexPrefix() {
        if (Preferences.isRandomPackageName()) {
            return CommonUtils.generateRandomString(Constants.DEX_PREFIX);
        } else {
            return Constants.DEX_PREFIX;
        }
    }

    public static String getDexSuffix() {
        if (Preferences.isRandomPackageName()) {
            return CommonUtils.generateRandomString(Constants.DEX_SUFFIX);
        } else {
            return Constants.DEX_SUFFIX;
        }
    }

    public static String getApplicationName() {
        return getPackageName() + ".ProtectApplication";
    }

    public static boolean isRandomPackageName() {
        return preferences.getBoolean("randomName", false);
    }
}