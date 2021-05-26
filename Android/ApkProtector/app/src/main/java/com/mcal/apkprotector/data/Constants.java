package com.mcal.apkprotector.data;

import com.mcal.apkprotector.BuildConfig;
import com.mcal.apkprotector.utils.FileUtils;
import com.mcal.apkprotector.utils.ScopedStorage;

import java.io.File;

public class Constants {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz123456789";
    public final static String CACHE_PATH = FileUtils.getWorkPath() + File.separator + "cache";
    public final static String SMALI_PATH = FileUtils.getWorkPath() + File.separator + "smali";
    public final static String RELEASE_PATH = FileUtils.getWorkPath() + File.separator + "release";
    public final static String OUTPUT_PATH = FileUtils.getWorkPath() + File.separator + "output";
    public final static String TOOLS_PATH = FileUtils.getWorkPath() + File.separator + "tools";
    public final static String ASSETS_PATH = FileUtils.getWorkPath() + File.separator + "output" + File.separator + "assets";
    public final static String LOG_PATH = ScopedStorage.getStorageDirectory() + File.separator + "ApkProtect" + File.separator + "Log.txt";
    public final static String UNSIGNED_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "app-unsigned.apk";
    public final static String SIGNED_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "app-signed.apk";
    public final static String MANIFEST_PATH = FileUtils.getWorkPath() + File.separator + "output" + File.separator + "AndroidManifest.xml";
    public final static String CONFIG_PATH = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "config.json";
    public static String PROXY_APP = "com.mcal.apkprotector.ProtectApplication";
    public static String PACKAGE_NAME = "com.mcal.apkprotector";
    public static String DEX_PREFIX = "classes-v";
    public static String DEX_SUFFIX = ".bin";
    public static String DEX_DIR = "apkprotector_dex";

    public static String LOG_LINE = "\n\n\n=============================================================================================";
    public static String mobileAdsId = "ca-app-pub-1411495427741055/9929016284";
    public static String rewardedId = BuildConfig.DEBUG ? "ca-app-pub-3940256099942544/5224354917" : "ca-app-pub-1411495427741055/9929016284";
    public static String bannerId = BuildConfig.DEBUG ? "ca-app-pub-3940256099942544/6300978111" : "ca-app-pub-1411495427741055/6045611597";
    public static String intertialId = BuildConfig.DEBUG ? "ca-app-pub-3940256099942544/1033173712" : "ca-app-pub-1411495427741055/8651593635";
}
