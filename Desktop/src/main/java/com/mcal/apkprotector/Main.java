package com.mcal.apkprotector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.gson.ConfigTemp;
import com.mcal.apkprotector.fastzip.FastZip;
import com.mcal.apkprotector.patchers.DexCrypto;
import com.mcal.apkprotector.patchers.ManifestPatcher;
import com.mcal.apkprotector.signer.SignatureTool;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.FileUtils;
import com.mcal.apkprotector.utils.LoggerUtils;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String @NotNull ... args) {
        clearTempFiles();
        generateRandom();

        LoggerUtils.writeLog("----------ApkProtector running----------------");

        LoggerUtils.writeLog("Default dir of ApkProtector" + System.getProperty("user.dir"));

        String cmd = args[0];
        if (!"b".equals(cmd)) {
            LoggerUtils.writeLog("Using: java -jar ApkProtector.jar b ApkPath");
            return;
        }

        String apkPath = args[1];
        LoggerUtils.writeLog("Path to APK: " + apkPath);

        File outputFolder = new File(Constants.OUTPUT_PATH);
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
            LoggerUtils.writeLog("Dir created: " + outputFolder.getAbsolutePath());
        }

        File smaliFolder = new File(Constants.SMALI_PATH);
        if (!smaliFolder.exists()) {
            smaliFolder.mkdir();
            LoggerUtils.writeLog("Dir created: " + smaliFolder.getAbsolutePath());
        }

        File releaseFolder = new File(Constants.RELEASE_PATH);
        if (!releaseFolder.exists()) {
            releaseFolder.mkdir();
            LoggerUtils.writeLog("Dir created: " + releaseFolder.getAbsolutePath());
        }

        long time = System.currentTimeMillis();

        try {
            FastZip.extract(apkPath, Constants.OUTPUT_PATH);
            LoggerUtils.writeLog("Success unpack: " + apkPath);

            ByteArrayInputStream bis = new ByteArrayInputStream(ManifestPatcher.parseManifest());
            FileOutputStream fos = new FileOutputStream(Constants.MANIFEST_PATH);
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = bis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            LoggerUtils.writeLog("Success patch: " + Constants.MANIFEST_PATH);

            DexCrypto.encodeDexes();
            LoggerUtils.writeLog("Dex files successful encrypted");

            FastZip.repack(apkPath, Constants.UNSIGNED_PATH);
            LoggerUtils.writeLog("Success compiled: " + Constants.UNSIGNED_PATH);

            if (!SignatureTool.sign(Constants.UNSIGNED_PATH, Constants.SIGNED_PATH)) {
                LoggerUtils.writeLog("APK signing error");
                return;
            }
            LoggerUtils.writeLog("APK success signed");
        } catch (Exception e) {
            LoggerUtils.writeLog("Error: " + e);
        } finally {
            clearTempFiles();
        }
        LoggerUtils.writeLog("Work time: " + (System.currentTimeMillis() - time));
    }

    private static void clearTempFiles() {
        new File(Constants.CONFIG_TEMP_PATH).delete();
        FileUtils.deleteDir(new File(Constants.CACHE_PATH));
        FileUtils.deleteDir(new File(Constants.SMALI_PATH));
        FileUtils.deleteDir(new File(Constants.OUTPUT_PATH));
    }

    private static void generateRandom() {
        ConfigTemp config = new ConfigTemp();
        config.packageName = CommonUtils.generateRandomString(Constants.PACKAGE_NAME);
        config.dexFolder = CommonUtils.generateRandomString(Constants.DEX_DIR);
        config.dexPrefix = CommonUtils.generateRandomString(Constants.DEX_PREFIX);
        config.dexSuffix = CommonUtils.generateRandomString(Constants.DEX_SUFFIX);
        config.proxyApp = CommonUtils.generateRandomString(Constants.PROXY_APP);

        config.SECONDARY_DEXES = CommonUtils.generateRandomString(Constants.SECONDARY_DEXES);
        config.MULTIDEX_LOCK = CommonUtils.generateRandomString(Constants.MULTIDEX_LOCK);
        config.CLASSES = CommonUtils.generateRandomString(Constants.CLASSES);
        config.ZIP = CommonUtils.generateRandomString(Constants.ZIP);
        config.CODE_CACHE = CommonUtils.generateRandomString(Constants.CODE_CACHE);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileUtils.writeFile(Constants.CONFIG_TEMP_PATH, gson.toJson(config), false);
    }
}
