package com.secure.dex.data.gson;

import com.google.gson.annotations.SerializedName;

public class HideProtector {
    @SerializedName("type")
    public int type;
    @SerializedName("packageName")
    public String packageName;
    @SerializedName("dexFolder")
    public String dexFolder;
    @SerializedName("dexPrefix")
    public String dexPrefix;
    @SerializedName("dexSuffix")
    public String dexSuffix;
    @SerializedName("proxyApp")
    public String proxyApp;
    @SerializedName("alphabet")
    public String alphabet;

    public HideProtector(int type, String packageName, String dexFolder, String dexPrefix, String dexSuffix, String proxyApp, String alphabet) {
        this.type = type;
        this.packageName = packageName;
        this.dexFolder = dexFolder;
        this.dexPrefix = dexPrefix;
        this.dexSuffix = dexSuffix;
        this.proxyApp = proxyApp;
        this.alphabet = alphabet;
    }
}
