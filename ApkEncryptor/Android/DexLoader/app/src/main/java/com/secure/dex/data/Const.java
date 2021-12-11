package com.secure.dex.data;

import com.secure.dex.utils.CommonUtils;

public class Const {
    public static String DP_LIB = CommonUtils.encryptStrings("$ASSETS_DIR_DEX", 2); // dp-lib
    public static String LUA_MPH = CommonUtils.encryptStrings("$DEX_SUFFIX", 2); // .lua.mph

    public static String REAL_APP = "$REAL_APP"; // com.secure.dex.ProxyApplication
    public static String PROTECT_KEY = CommonUtils.encryptStrings("$PROTECT_KEY", 2);
}