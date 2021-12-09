package com.mcal.apkprotector.data.gson;

import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("keystore")
    public KeystoreConfig keystore;
    @SerializedName("secureOptions")
    public SecureConfig secureConfig;
    @SerializedName("hideApkProtector")
    public HideProtector hideApkProtector;

    public Config(KeystoreConfig keystore, SecureConfig secureConfig, HideProtector hideApkProtector) {
        this.keystore = keystore;
        this.secureConfig = secureConfig;
        this.hideApkProtector = hideApkProtector;
    }
}
