package com.mcal.apkprotector.data.gson;

import com.google.gson.annotations.SerializedName;

public class SecureConfig {
    @SerializedName("encryptionKey")
    public String encryptionKey;

    public SecureConfig(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
