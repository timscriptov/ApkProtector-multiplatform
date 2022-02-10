package com.mcal.dexprotect.data;

import com.mcal.dexprotect.utils.file.ScopedStorage;

import java.io.File;

public class Constants {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public final static String CACHE_PATH = ScopedStorage.getFilesDir() + File.separator + "cache";
    public final static String SMALI_PATH = ScopedStorage.getFilesDir() + File.separator + "smali";
    public final static String RELEASE_PATH = ScopedStorage.getFilesDir() + File.separator + "release";
    public final static String OUTPUT_PATH = ScopedStorage.getFilesDir() + File.separator + "output";
    public final static String ASSETS_PATH = ScopedStorage.getFilesDir() + File.separator + "output" + File.separator + "assets";
    public final static String LOG_PATH = ScopedStorage.getStorageDirectory() + File.separator + "Log.txt";
    public final static String UNSIGNED_PATH = ScopedStorage.getFilesDir() + File.separator + "release" + File.separator + "app-unsigned.apk";
    public final static String MANIFEST_PATH = ScopedStorage.getFilesDir() + File.separator + "output" + File.separator + "AndroidManifest.xml";
    public static final String UTF_8 = "UTF-8";
    public static String PACKAGE_NAME = "com.android.protector";
    public static String DEX_PREFIX = "cls-v";
    public static String DEX_SUFFIX = ".mz";
    public static String DEX_DIR = "protect";

    // Others
    public static final String SECONDARY_DEXES = "secondary-dexes";
    public static final String MULTIDEX_LOCK = "MultiDex.lock";
    public static final String CLASSES = ".classes";
    public static final String ZIP = ".zip";
    public static final String CODE_CACHE = "code_cache";
}
