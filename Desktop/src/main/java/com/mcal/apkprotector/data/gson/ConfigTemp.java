package com.mcal.apkprotector.data.gson;

import com.google.gson.annotations.SerializedName;

public class ConfigTemp {
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


    @SerializedName("SECONDARY_DEXES")
    public String SECONDARY_DEXES;
    @SerializedName("MULTIDEX_LOCK")
    public String MULTIDEX_LOCK;
    @SerializedName("CLASSES")
    public String CLASSES;
    @SerializedName("ZIP")
    public String ZIP;
    @SerializedName("CODE_CACHE")
    public String CODE_CACHE;

        /*public ConfigTemp(String packageName, String dexFolder, String dexPrefix, String dexSuffix, String proxyApp) {
            this.packageName = packageName;
            this.dexFolder = dexFolder;
            this.dexPrefix = dexPrefix;
            this.dexSuffix = dexSuffix;
            this.proxyApp = proxyApp;
        }*/
}