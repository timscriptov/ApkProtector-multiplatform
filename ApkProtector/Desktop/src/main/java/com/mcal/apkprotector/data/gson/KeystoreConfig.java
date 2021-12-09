package com.mcal.apkprotector.data.gson;

import com.google.gson.annotations.SerializedName;

public class KeystoreConfig {
    @SerializedName("customSignature")
    public boolean customSignature;
    @SerializedName("keystorePath")
    public String keystorePath;
    @SerializedName("keystorePassword")
    public String keystorePassword;
    @SerializedName("keystoreAlias")
    public String keystoreAlias;
    @SerializedName("certPassword")
    public String certPassword;

    public KeystoreConfig(boolean customSignature, String keystorePath, String keystorePassword, String keystoreAlias, String certPassword) {
        this.customSignature = customSignature;
        this.keystorePath = keystorePath;
        this.keystorePassword = keystorePassword;
        this.keystoreAlias = keystoreAlias;
        this.certPassword = certPassword;
    }
}
