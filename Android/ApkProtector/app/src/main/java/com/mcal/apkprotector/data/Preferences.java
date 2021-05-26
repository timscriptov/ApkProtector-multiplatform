package com.mcal.apkprotector.data;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.mcal.apkprotector.App;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.Utils;

import org.jetbrains.annotations.Contract;

public final class Preferences {
    public static boolean getOldDexloaderBoolean() {
        return App.getPreferences().getBoolean("oldDexloaderBoolean", false);
    }

    public static boolean getZipAlignerBoolean() {
        return App.getPreferences().getBoolean("zipalignerBoolean", false);
    }

    public static String getAlignmentString() {
        return App.getPreferences().getString("alignmentString", "4");
    }

    public static boolean getForceBoolean() {
        return App.getPreferences().getBoolean("forceBoolean", false);
    }

    public static boolean getZopfliBoolean() {
        return App.getPreferences().getBoolean("zopfliBoolean", false);
    }

    public static boolean getPageAlignSharedLibsBoolean() {
        return App.getPreferences().getBoolean("pageAlignSharedLibsBoolean", false);
    }

    public static boolean getDexProtectBoolean() {
        return App.getPreferences().getBoolean("dexProtectBoolean", false);
    }

    public static void setDexProtectBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("dexProtectBoolean", flag).apply();
    }

    public static boolean getEncryptResourcesBoolean() {
        return App.getPreferences().getBoolean("encryptResourcesBoolean", false);
    }

    public static boolean getSignApkBoolean() {
        return App.getPreferences().getBoolean("signApkBoolean", false);
    }

    public static void setSignApkBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("signApkBoolean", flag).apply();
    }

    public static String isProtectKeyString(String str) {
        return App.getPreferences().getString("protectKeyString", str);
    }

    public static String getProtectKeyString() {
        return App.getPreferences().getString("protectKeyString", Utils.sealing(Utils.buildID()));
    }

    public static String isApkPath() {
        return App.getPreferences().getString("apkPath", "");
    }

    public static void isSplashActivityBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("splashActivityBoolean", flag).apply();
    }

    public static boolean isSplashActivityBoolean() {
        return App.getPreferences().getBoolean("splashActivityBoolean", false);
    }

    public static void isTitleNotificationBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("titleNotificationBoolean", flag).apply();
    }

    public static boolean isTitleNotificationBoolean() {
        return App.getPreferences().getBoolean("titleNotificationBoolean", false);
    }

    public static void isWelcomeMessageString(String flag) {
        App.getPreferences().edit().putString("welcomeMessageString", flag).apply();
    }

    public static String isWelcomeMessageString() {
        return App.getPreferences().getString("welcomeMessageString", "Powered by ApkProtector");
    }

    public static int isWelcomeModeInt(int i) {
        return App.getPreferences().getInt("welcomeModeInt", i);
    }

    public static void isWelcomeMessageBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("welcomeMessageBoolean", flag).apply();
    }

    public static boolean isWelcomeMessageBoolean() {
        return App.getPreferences().getBoolean("welcomeMessageBoolean", false);
    }

    public static boolean isCheckVPNBoolean() {
        return App.getPreferences().getBoolean("checkVPNBoolean", false);
    }

    public static void isCrashNotificationBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("crashNotificationBoolean", flag).apply();
    }

    public static boolean isCrashNotificationBoolean() {
        return App.getPreferences().getBoolean("crashNotificationBoolean", false);
    }

    public static void isDeviceLockBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("deviceLockBoolean", flag).apply();
    }

    public static boolean isDeviceLockBoolean() {
        return App.getPreferences().getBoolean("deviceLockBoolean", false);
    }

    public static void isHookCheckString(String flag) {
        App.getPreferences().edit().putString("hookCheckString", flag).apply();
    }

    public static String isHookCheckString() {
        return App.getPreferences().getString("hookCheckString", "");
    }

    public static void isHookCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("hookCheckBoolean", flag).apply();
    }

    public static boolean isHookCheckBoolean() {
        return App.getPreferences().getBoolean("hookCheckBoolean", false);
    }

    public static void isIllegalCodeCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("illegalCodeCheckBoolean", flag).apply();
    }

    public static boolean isIllegalCodeCheckBoolean() {
        return App.getPreferences().getBoolean("illegalCodeCheckBoolean", false);
    }

    public static void isCloneCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("cloneCheckBoolean", flag).apply();
    }

    public static boolean isCloneCheckBoolean() {
        return App.getPreferences().getBoolean("cloneCheckBoolean", false);
    }

    public static void isEmulatorCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("emulatorCheckBoolean", flag).apply();
    }

    public static boolean isEmulatorCheckBoolean() {
        return App.getPreferences().getBoolean("emulatorCheckBoolean", false);
    }

    public static void isPlaystoreCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("playstoreCheckBoolean", flag).apply();
    }

    public static boolean isPlaystoreCheckBoolean() {
        return App.getPreferences().getBoolean("playstoreCheckBoolean", false);
    }

    public static void isDebugCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("debugCheckBoolean", flag).apply();
    }

    public static boolean isDebugCheckBoolean() {
        return App.getPreferences().getBoolean("debugCheckBoolean", false);
    }

    public static void isXposedCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("xposedCheckBoolean", flag).apply();
    }

    public static boolean isXposedCheckBoolean() {
        return App.getPreferences().getBoolean("xposedCheckBoolean", false);
    }

    public static void isMagiskCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("magiskCheckBoolean", flag).apply();
    }

    public static boolean isMagiskCheckBoolean() {
        return App.getPreferences().getBoolean("magiskCheckBoolean", false);
    }

    public static void isRootCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("rootCheckBoolean", flag).apply();
    }

    public static boolean isRootCheckBoolean() {
        return App.getPreferences().getBoolean("rootCheckBoolean", false);
    }

    public static void isSignatureCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("signatureCheckBoolean", flag).apply();
    }

    public static boolean isSignatureCheckBoolean() {
        return App.getPreferences().getBoolean("signatureCheckBoolean", false);
    }

    public static void isLuckyPatcherCheckBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("luckyPatcherCheckBoolean", flag).apply();
    }

    public static boolean isLuckyPatcherCheckBoolean() {
        return App.getPreferences().getBoolean("luckyPatcherCheckBoolean", false);
    }

    public static String isUserIgnoredClasses(String str) {
        return App.getPreferences().getString("userIgnoredClasses", str);
    }

    public static String isCertPassword() {
        return App.getPreferences().getString("certPassword", "");
    }

    public static String isSignaturePassword() {
        return App.getPreferences().getString("signaturePassword", "");
    }

    public static String isSignatureAlias() {
        return App.getPreferences().getString("signatureAlias", "");
    }

    public static String isSignaturePath() {
        return App.getPreferences().getString("signaturePath", "");
    }

    public static boolean isCustomSignature() {
        return App.getPreferences().getBoolean("customSignature", false);
    }

    public static boolean isOptimizeDexBoolean() {
        return App.getPreferences().getBoolean("optimizeDexBoolean", true);
    }

    public static void isCustomAppNameString(String flag) {
        App.getPreferences().edit().putString("customAppNameString", flag).apply();
    }

    public static String isCustomAppNameString() {
        return App.getPreferences().getString("customAppNameString", "com.mcal.apkprotector.ProxyApplication");
    }

    public static void isCustomAppNameBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("customAppNameBoolean", flag).apply();
    }

    public static boolean isCustomAppNameBoolean() {
        return App.getPreferences().getBoolean("customAppNameBoolean", false);
    }

    public static void isEncryptResourcesBoolean(boolean value) {
        App.getPreferences().edit().putBoolean("encryptResourcesBoolean", value).apply();
    }

    public static boolean isEncryptResourcesBoolean() {
        return App.getPreferences().getBoolean("encryptResourcesBoolean", false);
    }

    public static void setEncryptResourcesBoolean(boolean flag) {
        App.getPreferences().edit().putBoolean("encryptResourcesBoolean", flag).apply();
    }

    public static boolean isNightModeEnabled() {
        return App.getPreferences().getBoolean("night_mode", false);
    }

    public static void setNightModeEnabled(boolean flag) {
        App.getPreferences().edit().putBoolean("night_mode", flag).apply();
    }

    public static boolean getSignatureV1() {
        return App.getPreferences().getBoolean("signatureV1", true);
    }

    public static boolean getSignatureV2() {
        return App.getPreferences().getBoolean("signatureV2", true);
    }

    public static boolean getSignatureV3() {
        return App.getPreferences().getBoolean("signatureV3", false);
    }

    public static boolean getSignatureV4() {
        return App.getPreferences().getBoolean("signatureV4", false);
    }


    public static String getProtectKey() {
        return App.getPreferences().getString("protectKeyString", Utils.sealing(Utils.buildID()));
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
        return App.getPreferences().getBoolean("randomName", false);
    }
}