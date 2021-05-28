package com.mcal.dexprotect.data;

import com.mcal.dexprotect.BuildConfig;
import com.mcal.dexprotect.utils.ScopedStorage;

import java.io.File;

public class Constants {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz123456789";
    public final static String CACHE_PATH = ScopedStorage.getWorkPath() + File.separator + "cache";
    public final static String SMALI_PATH = ScopedStorage.getWorkPath() + File.separator + "smali";
    public final static String RELEASE_PATH = ScopedStorage.getWorkPath() + File.separator + "release";
    public final static String OUTPUT_PATH = ScopedStorage.getWorkPath() + File.separator + "output";
    public final static String ASSETS_PATH = ScopedStorage.getWorkPath() + File.separator + "output" + File.separator + "assets";
    public final static String LOG_PATH = ScopedStorage.getStorageDirectory() + File.separator + "ApkProtect" + File.separator + "Log.txt";
    public final static String UNSIGNED_PATH = ScopedStorage.getWorkPath() + File.separator + "release" + File.separator + "app-unsigned.apk";
    public final static String MANIFEST_PATH = ScopedStorage.getWorkPath() + File.separator + "output" + File.separator + "AndroidManifest.xml";
    public static String PACKAGE_NAME = "com.mcal.dexprotect";
    public static String DEX_PREFIX = "classes-v";
    public static String DEX_SUFFIX = ".bin";
    public static String DEX_DIR = "dex";

    public static final String UTF_8 = "UTF-8";

    public static String mobileAdsId = "ca-app-pub-1411495427741055/9929016284";
    public static String rewardedId = BuildConfig.DEBUG ? "ca-app-pub-3940256099942544/5224354917" : "ca-app-pub-1411495427741055/9929016284";
    public static String bannerId = BuildConfig.DEBUG ? "ca-app-pub-3940256099942544/6300978111" : "ca-app-pub-1411495427741055/6045611597";
    public static String intertialId = BuildConfig.DEBUG ? "ca-app-pub-3940256099942544/1033173712" : "ca-app-pub-1411495427741055/8651593635";
}
