package com.mcal.apkprotector.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcal.apkprotector.data.gson.Config;
import com.mcal.apkprotector.data.gson.ConfigTemp;
import com.mcal.apkprotector.utils.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Preferences {
    public static @Nullable ConfigTemp configTemp() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            String content = FileUtils.readFile(Constants.CONFIG_TEMP_PATH, StandardCharsets.UTF_8);
            return gson.fromJson(content, ConfigTemp.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static @Nullable Config config() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            String content = FileUtils.readFile(Constants.CONFIG_PATH, StandardCharsets.UTF_8);
            return gson.fromJson(content, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static @NotNull String isSignaturePath() {
        return Constants.TOOLS_PATH + File.separator + config().keystore.keystorePath;
    }

    public static String isSignatureAlias() {
        return config().keystore.keystoreAlias;
    }

    public static String isCertPassword() {
        return config().keystore.certPassword;
    }

    public static String isSignaturePassword() {
        return config().keystore.keystorePassword;
    }

    public static boolean getSignatureV1() {
        return config().keystore.signatureV1Enabled;
    }

    public static boolean getSignatureV2() {
        return config().keystore.signatureV2Enabled;
    }

    public static boolean getSignatureV3() {
        return config().keystore.signatureV3Enabled;
    }

    public static boolean getSignatureV4() {
        return config().keystore.signatureV4Enabled;
    }

    public static String getProtectKey() {
        return config().secureConfig.encryptionKey;
    }

    public static int getTypeHideApkProtector() {
        return config().hideApkProtector.type;
    }

    public static @Nullable String getDexDir() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.DEX_DIR;
            case 1: // Custom
                return config().hideApkProtector.dexFolder;
            case 2: // Random
            case 3: // Random
                return configTemp().dexFolder;
        }
        return null;
    }

    public static @Nullable String getPackageName() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.PACKAGE_NAME;
            case 1: // Custom
            case 3: // Custom
                return config().hideApkProtector.packageName;
            case 2: // Random
                return configTemp().packageName;
        }
        return null;
    }

    public static @Nullable String getProxyAppName() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.PROXY_APP;
            case 1: // Custom
            case 3: // Custom
                return config().hideApkProtector.proxyApp;
            case 2: // Random
                return configTemp().proxyApp;
        }
        return null;
    }

    public static @Nullable String getDexPrefix() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.DEX_PREFIX;
            case 1: // Custom
                return config().hideApkProtector.dexPrefix;
            case 2: // Random
            case 3: // Random
                return configTemp().dexPrefix;
        }
        return null;
    }

    public static @Nullable String getDexSuffix() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.DEX_SUFFIX;
            case 1: // Custom
            case 3: // Custom
                return config().hideApkProtector.dexSuffix;
            case 2: // Random
                return configTemp().dexSuffix;
        }
        return null;
    }

    public static boolean getZipaligner() {
        return config().keystore.zipaligner;
    }

    public static String getAlphabet() {
        return config().hideApkProtector.alphabet;
    }

    public static String SECONDARY_DEXES() {
        return configTemp().SECONDARY_DEXES;
    }

    public static String MULTIDEX_LOCK() {
        return configTemp().MULTIDEX_LOCK;
    }

    public static String CLASSES() {
        return configTemp().CLASSES;
    }

    public static String ZIP() {
        return configTemp().ZIP;
    }

    public static String CODE_CACHE() {
        return configTemp().CODE_CACHE;
    }
}
