package com.mcal.apkprotector.data;

import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.FileUtils;

import java.io.File;

public class Constants {
    public final static String CACHE_PATH = FileUtils.getWorkPath() + File.separator + "cache";
    public final static String RELEASE_PATH = FileUtils.getWorkPath() + File.separator + "release";
    public final static String OUTPUT_PATH = FileUtils.getWorkPath() + File.separator + "output";
    public final static String SMALI_PATH = FileUtils.getWorkPath() + File.separator + "smali";
    public final static String TOOLS_PATH = FileUtils.getWorkPath() + File.separator + "tools";
    public final static String ASSETS_PATH = FileUtils.getWorkPath() + File.separator + "output" + File.separator + "assets";
    public final static String LOG_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "Log.txt";
    public final static String UNSIGNED_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "app-unsigned.apk";
    public final static String SIGNED_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "app-signed.apk";
    public final static String MANIFEST_PATH = FileUtils.getWorkPath() + File.separator + "output" + File.separator + "AndroidManifest.xml";
    public final static String CONFIG_PATH = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "config.json";
    public final static String DEXLOADER_PATH = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "dexloader.dex";
    public final static String CONFIG_TEMP_PATH = Constants.TOOLS_PATH + File.separator + "config-temp.json";
    public static String PACKAGE_NAME = "com.android.protector";
    public static String DEX_PREFIX = "protect-v";
    public static String DEX_SUFFIX = ".enc";
    public static String DEX_DIR = "protect";
    public static String PROXY_APP = "App";

    // Others
    public static final String SECONDARY_DEXES = "secondary-dexes";
    public static final String MULTIDEX_LOCK = "MultiDex.lock";
    public static final String CLASSES = ".classes";
    public static final String ZIP = ".zip";
    public static final String CODE_CACHE = "code_cache";
}
