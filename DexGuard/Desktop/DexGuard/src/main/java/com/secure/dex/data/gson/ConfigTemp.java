package com.secure.dex.data.gson;

import com.google.gson.annotations.SerializedName;

    public class ConfigTemp {
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
        @SerializedName("dirOpt")
        public String dirOpt;
        @SerializedName("assetsDirDex")
        public String assetsDirDex;

        @SerializedName("realApp")
        public String realApp;
        @SerializedName("protectKey")
        public String protectKey;
    }