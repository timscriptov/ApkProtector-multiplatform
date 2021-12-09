package com.secure.dex.data;

import com.secure.dex.utils.FileUtils;

import java.io.File;

public class Constants {
    public final static String CACHE_PATH = FileUtils.getHomePath() + File.separator + "cache";
    public final static String RELEASE_PATH = FileUtils.getHomePath() + File.separator + "release";
    public final static String OUTPUT_PATH = FileUtils.getHomePath() + File.separator + "output";
    public final static String SMALI_PATH = FileUtils.getHomePath() + File.separator + "smali";
    public final static String TOOLS_PATH = FileUtils.getHomePath() + File.separator + "tools";
    public final static String ASSETS_PATH = FileUtils.getHomePath() + File.separator + "output" + File.separator + "assets";
    public final static String LOG_PATH = FileUtils.getHomePath() + File.separator + "release" + File.separator + "Log.txt";
    public final static String UNSIGNED_PATH = FileUtils.getHomePath() + File.separator + "release" + File.separator + "app-unsigned.apk";
    public final static String SIGNED_PATH = FileUtils.getHomePath() + File.separator + "release" + File.separator + "app-signed.apk";
    public final static String MANIFEST_PATH = FileUtils.getHomePath() + File.separator + "output" + File.separator + "AndroidManifest.xml";
    public final static String CONFIG_PATH = FileUtils.getHomePath() + File.separator + "tools" + File.separator + "config.json";
    public final static String DEXLOADER_PATH = FileUtils.getHomePath() + File.separator + "tools" + File.separator + "classes.dex";
    public final static String CONFIG_TEMP_PATH = Constants.TOOLS_PATH + File.separator + "config-temp.json";
    public static String PACKAGE_NAME = "com.android.protector";
    public static String DEX_PREFIX = "classes-v";
    public static String DEX_SUFFIX = ".bin";
    public static String ASSETS_DIR_DEX = "dexes";
    public static String PROXY_APP = "MyApplication";
}
