package com.mcal.apkprotector.data;

import com.google.gson.annotations.SerializedName;

public class SecureConfig {
    @SerializedName("encryptionKey")
    public String encryptionKey;
    @SerializedName("randomPackageName")
    public boolean randomPackageName;
    @SerializedName("randomAssetsResName")
    public boolean randomAssetsResName;

    public SecureConfig(String encryptionKey, boolean randomPackageName, boolean randomAssetsResName) {
        this.encryptionKey = encryptionKey;
        this.randomPackageName = randomPackageName;
        this.randomAssetsResName = randomAssetsResName;
    }
}
