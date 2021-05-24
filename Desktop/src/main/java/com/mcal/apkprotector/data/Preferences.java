package com.mcal.apkprotector.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Preferences {
    public static Config config() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            String content = FileUtils.readFile(Constants.CONFIG_PATH, StandardCharsets.UTF_8);
            return gson.fromJson(content, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            // Если файл конфигурации не найден, используем настройки по умолчанию
            String jsonScript = "{\n" +
                    "    \"keystore\":{\n" +
                    "        \"keystoreCustom\":true,\n" +
                    "        \n" +
                    "        \"keystorePath\":\"debug.jks\",\n" +
                    "        \"keystorePassword\":\"debug\",\n" +
                    "        \"keystoreAlias\":\"debug\",\n" +
                    "        \"certPassword\":\"debug\",\n" +
                    "        \n" +
                    "        \"signatureV1Enabled\":true,\n" +
                    "        \"signatureV2Enabled\":true,\n" +
                    "        \"signatureV3Enabled\":false,\n" +
                    "        \"signatureV4Enabled\":false\n" +
                    "    },\n" +
                    "\n" +
                    "    \"secureOptions\": {\n" +
                    "        \"encryptionKey\": \"12345678911111111111111111111111\",\n" +
                    "        \"randomPackageName\": false,\n" +
                    "        \"randomAssetsResName\": false\n" +
                    "    }\n" +
                    "\n" +
                    "}";
            return gson.fromJson(jsonScript, Config.class);
        }
    }

    public static boolean isRandomPackageName() {
        return config().secureConfig.randomPackageName;
    }

    public static String isSignaturePath() {
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

    public static String getDexDir() {
        if (Preferences.isRandomPackageName()) {
            return CommonUtils.generateRandomString(Constants.DEX_DIR);
        } else {
            return Constants.DEX_DIR;
        }
    }

    public static String getPackageName() {
        if (Preferences.isRandomPackageName()) {
            return CommonUtils.generateRandomString(Constants.PACKAGE_NAME);
        } else {
            return Constants.PACKAGE_NAME;
        }
    }

    public static String getDexPrefix() {
        if (Preferences.isRandomPackageName()) {
            return CommonUtils.generateRandomString(Constants.DEX_PREFIX);
        } else {
            return Constants.DEX_PREFIX;
        }
    }

    public static String getDexSuffix() {
        if (Preferences.isRandomPackageName()) {
            return CommonUtils.generateRandomString(Constants.DEX_SUFFIX);
        } else {
            return Constants.DEX_SUFFIX;
        }
    }

    public static String getApplicationName() {
        return getPackageName() + ".ProtectApplication";
    }

    public static boolean getZipaligner() {
        return config().keystore.zipaligner;
    }
}
