package com.mcal.apkprotector.data;

import com.mcal.apkprotector.utils.CommonUtils;

public class Const {
    public static final String APP_NAME = "$APP_NAME";

    // Assets
    public static final String DEX_SUFFIX = ".bin";///CommonUtils.encryptStrings("$DEX_SUFFIX", 2);
    public static final String DEX_PREFIX = "classes-v";//CommonUtils.encryptStrings("$DEX_PREFIX", 2);
    public static final String DEX_DIR = "dexes";//CommonUtils.encryptStrings("$DEX_DIR", 2);

    // Key for decrypt dexes
    public static final String PROTECT_KEY = "PROTECTOR2021";//CommonUtils.encryptStrings("$PROTECT_KEY", 2);
    public static final String DATA = CommonUtils.encryptStrings("$DATA", 2);

    // Real Application
    public static final String REAL_APPLICATION = "$REAL_APP";

    // Others
    public static final String SECONDARY_DEXES = "secondary-dexes";//CommonUtils.encryptStrings("$SECONDARY_DEXES", 2); // secondary-dexes
    public static final String MULTIDEX_LOCK = "MultiDex.lock";//CommonUtils.encryptStrings("$MULTIDEX_LOCK", 2); // MultiDex.lock
    public static final String CLASSES = ".classes";//CommonUtils.encryptStrings("$CLASSES", 2); // .classes
    public static final String ZIP = ".zip";//CommonUtils.encryptStrings("$ZIP", 2); // .zip
    public static final String CODE_CACHE = "code_cache";//CommonUtils.encryptStrings("$CODE_CACHE", 2); // code_cache
}
