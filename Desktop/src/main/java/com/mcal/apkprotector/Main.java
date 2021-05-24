package com.mcal.apkprotector;

import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.patchers.ManifestPatcher;
import com.mcal.apkprotector.signer.SignerTool;
import com.mcal.apkprotector.utils.*;
import com.mcal.fastzip.FastZip;

import java.io.*;
import java.util.Random;

public class Main {
    //public static String randomPackName = "com.mcal.apkprotector";
    //public static String randomDexPrefix = "classes-v";
    //public static String randomDirName = "apkprotector_dex";

    public static void main(String... args) {
        FileUtils.deleteDir(new File(Constants.OUTPUT_PATH));
        FileUtils.deleteDir(new File(Constants.RELEASE_PATH));
        FileUtils.delete(new File(Constants.LOG_PATH));

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

        File releaseFolder = new File(Constants.RELEASE_PATH);
        if (!releaseFolder.exists()) {
            releaseFolder.mkdir();
            LoggerUtils.writeLog("Dir created: " + releaseFolder.getAbsolutePath());
        }

        long time = System.currentTimeMillis();

        LoggerUtils.writeLog("Work time: " + (System.currentTimeMillis() - time));

        //generateRandomData();

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
            //ManifestPatcher.parseManifest(new BufferedInputStream(new FileInputStream(Constants.MANIFEST_PATH)));
            LoggerUtils.writeLog("Success patch: " + Constants.MANIFEST_PATH);

            Crypto.encodeDexes();
            //DexEncryption.encodeDexes();
            //DexDecryption.decodeDexes();
            LoggerUtils.writeLog("Dex files successful encrypted");

            FastZip.repack(apkPath, Constants.UNSIGNED_PATH);
            LoggerUtils.writeLog("Success compiled: " + Constants.UNSIGNED_PATH);

            if (!SignerTool.sign(Constants.UNSIGNED_PATH, Constants.SIGNED_PATH)) {
                LoggerUtils.writeLog("APK signing error");
                FileUtils.deleteDir(new File(Constants.OUTPUT_PATH));
                return;
            }
            LoggerUtils.writeLog("APK success signed");
        } catch (Exception e) {
            LoggerUtils.writeLog(" " + e);
        }

        FileUtils.delete(new File(Constants.OUTPUT_PATH));
        FileUtils.delete(new File(Constants.UNSIGNED_PATH));
        FileUtils.delete(new File(Constants.CACHE_PATH));
    }

    /*private static void generateRandomData() {
        if (Preferences.isRandomPackageName())
            randomPackName = CommonUtils.generateRandomString(randomPackName);
            Constants.PROXY_APP = Main.randomPackName + ".ProtectApplication";
        if (Preferences.isRandomAssetsResName()) {
            randomDexPrefix = CommonUtils.generateRandomString(randomDexPrefix);
            randomDirName = CommonUtils.generateRandomString(randomDirName);
        }
    }*/
}
