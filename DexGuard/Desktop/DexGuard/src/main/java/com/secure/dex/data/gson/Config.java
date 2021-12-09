package com.secure.dex.data.gson;

import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("secureOptions")
    public SecureConfig secureConfig;
    @SerializedName("hideApkProtector")
    public HideProtector hideApkProtector;

    public Config(SecureConfig secureConfig, HideProtector hideApkProtector) {
        this.secureConfig = secureConfig;
        this.hideApkProtector = hideApkProtector;
    }
}
