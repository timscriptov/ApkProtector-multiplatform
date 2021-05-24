package com.mcal.apkprotector.data;

import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("keystore")
    public KeystoreConfig keystore;
    @SerializedName("secureOptions")
    public SecureConfig secureConfig;

    public Config(KeystoreConfig keystore, SecureConfig secureConfig) {
        this.keystore = keystore;
        this.secureConfig = secureConfig;
    }
}
