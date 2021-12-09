package com.secure.dex.data.gson;

import com.google.gson.annotations.SerializedName;

public class HideProtector {
    @SerializedName("type")
    public int type;
    @SerializedName("packageName")
    public String packageName;
    @SerializedName("dexDir")
    public String dexDir;
    @SerializedName("dexPrefix")
    public String dexPrefix;
    @SerializedName("dexSuffix")
    public String dexSuffix;
    @SerializedName("proxyApp")
    public String proxyApp;
    @SerializedName("alphabet")
    public String alphabet;
    @SerializedName("dirOpt")
    public String dirOpt;
    @SerializedName("assetsDirDex")
    public String assetsDirDex;
}
