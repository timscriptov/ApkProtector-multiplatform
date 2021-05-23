package com.mcal.apkprotector.data;

import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("keystore")
    public KeystoreConfig keystore;
   /* @SerializedName("protectKey")
    public String protectKey;
    @SerializedName("dexFolderName")
    public String dexFolderName;
    @SerializedName("randomName")
    public boolean randomName;
    @SerializedName("replaceDexName")
    public String replaceDexName;
    @SerializedName("applicationName")
    public String applicationName;*/
    @SerializedName("secureOptions")
    public SecureConfig secureConfig;

    public Config(KeystoreConfig keystore,
                  SecureConfig secureConfig
                  /*String protectKey,
                  String dexFolderName,
                  boolean randomName,
                  String replaceDexName,
                  String applicationName*/) {
        this.keystore = keystore;
        this.secureConfig = secureConfig;
        /*this.protectKey = protectKey;
        this.dexFolderName = dexFolderName;
        this.randomName = randomName;
        this.replaceDexName = replaceDexName;
        this.applicationName = applicationName;*/
    }
}
