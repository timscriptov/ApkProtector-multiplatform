package com.secure.dex.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.secure.dex.data.gson.Config;
import com.secure.dex.data.gson.ConfigTemp;
import com.secure.dex.utils.FileUtils;
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

    public static String getProtectKey() {
        return config().secureConfig.encryptionKey;
    }

    public static int getTypeHideApkProtector() {
        return config().hideApkProtector.type;
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

    public static @Nullable String getAssetsDirDex() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.ASSETS_DIR_DEX;
            case 1: // Custom
                return config().hideApkProtector.assetsDirDex;
            case 2: // Random
            case 3: // Random
                return configTemp().assetsDirDex;
        }
        return null;
    }

    public static @Nullable String getDexDir() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.DEX_DIR;
            case 1: // Custom
                return config().hideApkProtector.dexDir;
            case 2: // Random
            case 3: // Random
                return configTemp().dexDir;
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

    public static @Nullable String getDirOpt() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.DIR_OPT;
            case 1: // Custom
            case 3: // Custom
                return config().hideApkProtector.dirOpt;
            case 2: // Random
                return configTemp().dirOpt;
        }
        return null;
    }

    public static @Nullable String getMRealApp() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.REAL_APP;
            case 1: // Custom
            case 3: // Custom
                return config().hideApkProtector.realApp;
            case 2: // Random
                return configTemp().realApp;
        }
        return null;
    }

    public static @Nullable String getMProtectKey() {
        switch (Preferences.getTypeHideApkProtector()) {
            case 0: // Disabled
                return Constants.PROTECT_KEY;
            case 1: // Custom
            case 3: // Custom
                return config().hideApkProtector.protectKey;
            case 2: // Random
                return configTemp().protectKey;
        }
        return null;
    }

    public static String getAlphabet() {
        return config().hideApkProtector.alphabet;
    }
}
