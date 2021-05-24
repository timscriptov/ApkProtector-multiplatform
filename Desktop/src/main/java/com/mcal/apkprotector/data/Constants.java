package com.mcal.apkprotector.data;

import com.mcal.apkprotector.utils.FileUtils;

import java.io.File;

public class Constants {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz123456789";
    public final static String CACHE_PATH = FileUtils.getWorkPath() + File.separator + "cache";
    public final static String RELEASE_PATH = FileUtils.getWorkPath() + File.separator + "release";
    public final static String OUTPUT_PATH = FileUtils.getWorkPath() + File.separator + "output";
    public final static String TOOLS_PATH = FileUtils.getWorkPath() + File.separator + "tools";
    public final static String ASSETS_PATH = FileUtils.getWorkPath() + File.separator + "output" + File.separator + "assets";
    public final static String DEXLOADER_PATH = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "dexloader.dex";
    public final static String LOG_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "Log.txt";
    public final static String UNSIGNED_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "app-unsigned.apk";
    public final static String SIGNED_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "app-signed.apk";
    public final static String MANIFEST_PATH = FileUtils.getWorkPath() + File.separator + "output" + File.separator + "AndroidManifest.xml";
    public final static String CONFIG_PATH = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "config.json";
    public static String PROXY_APP = "com.mcal.apkprotector.ProtectApplication";
    public static String PACKAGE_NAME = "com.mcal.apkprotector";
    public static String DEX_PREFIX = "classes-v";
    public static String DEX_SUFFIX = ".bin";
    public static String DEX_DIR = "apkprotector_dex";
}
