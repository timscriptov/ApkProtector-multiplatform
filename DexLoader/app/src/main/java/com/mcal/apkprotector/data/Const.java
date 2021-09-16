package com.mcal.apkprotector.data;

import com.mcal.apkprotector.utils.CommonUtils;

public class Const {
    // Assets
    public static final String DEX_SUFFIX = CommonUtils.encryptStrings("$DEX_SUFIX", 2);
    public static final String DEX_PREFIX = CommonUtils.encryptStrings("$DEX_PREFIX", 2);
    public static final String DEX_DIR = CommonUtils.encryptStrings("$DEX_DIR", 2);

    // Key for decrypt dexes
    public static final String PROTECT_KEY = CommonUtils.encryptStrings("$PROTECT_KEY", 2);
    public static final String DATA = CommonUtils.encryptStrings("$DATA", 2);

    // Real Application
    public static final String REAL_APPLICATION = "$APPLICATION";

    // Others
    public static final String SECONDARY_DEXES = CommonUtils.encryptStrings("$SECONDARY_DEXES", 2); // secondary-dexes
    public static final String MULTIDEX_LOCK = CommonUtils.encryptStrings("$MULTIDEX_LOCK", 2); // MultiDex.lock
    public static final String CLASSES = CommonUtils.encryptStrings("$CLASSES", 2); // .classes
    public static final String ZIP = CommonUtils.encryptStrings("$ZIP", 2); // .zip
    public static final String CODE_CACHE = CommonUtils.encryptStrings("$CODE_CACHE", 2); // code_cache
}
