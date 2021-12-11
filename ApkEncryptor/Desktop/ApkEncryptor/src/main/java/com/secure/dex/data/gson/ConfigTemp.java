package com.secure.dex.data.gson;

import com.google.gson.annotations.SerializedName;

public class ConfigTemp {
    @SerializedName("packageName")
    public String packageName;
    @SerializedName("dexPrefix")
    public String dexPrefix;
    @SerializedName("dexSuffix")
    public String dexSuffix;
    @SerializedName("proxyApp")
    public String proxyApp;
    @SerializedName("assetsDirDex")
    public String assetsDirDex;
}