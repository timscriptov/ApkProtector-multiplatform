package com.mcal.apkprotector.data;

import com.mcal.apkprotector.utils.FileUtils;
import com.mcal.apkprotector.Main;

import java.io.File;

public class Constants {
    public static String PROXY_APP = "com.mcal.apkprotector.ProtectApplication";
    public static String PACKAGE_NAME = "com.mcal.apkprotector";
    public static String DEX_PREFIX = "classes-v";
    public static String DEX_SUFFIX = ".bin";
    public static String DEX_DIR = "apkprotector_dex";
    public final static String CONFIG_PATH = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "config.json";
    public final static String PEM_PATH = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "key" + File.separator + "testkey.x509.pem";
    public final static String PK8_PATH = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "key" + File.separator + "testkey.pk8";
    public final static String CACHE_PATH = FileUtils.getWorkPath() + File.separator + "cache";
    public final static String RELEASE_PATH = FileUtils.getWorkPath() + File.separator + "release";
    public final static String UNSIGNED_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "app-unsigned.apk";
    public final static String SIGNED_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "app-signed.apk";
    public final static String MANIFEST_PATH = FileUtils.getWorkPath() + File.separator + "output" + File.separator + "AndroidManifest.xml";
    public final static String OUTPUT_PATH = FileUtils.getWorkPath() + File.separator + "output";
    public final static String LOG_PATH = FileUtils.getWorkPath() + File.separator + "release" + File.separator + "Log.txt";
    public final static String TOOLS_PATH = FileUtils.getWorkPath() + File.separator + "tools";
    public final static String ASSETS_PATH = FileUtils.getWorkPath() + File.separator + "output" + File.separator + "assets";

    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz123456789";
    //public static final String[] extractFiles = new String[]{"AndroidManifest.xml", "classes.dex"};
}
