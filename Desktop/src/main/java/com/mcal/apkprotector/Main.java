package com.mcal.apkprotector;

import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.patchers.ManifestPatcher;
import com.mcal.apkprotector.signer.SignatureTool;
import com.mcal.apkprotector.utils.DexEncryption;
import com.mcal.apkprotector.utils.FileUtils;
import com.mcal.apkprotector.utils.LoggerUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Main {
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

            DexEncryption.encodeDexes();
            LoggerUtils.writeLog("Dex files successful encrypted");

            FastZip.repack(apkPath, Constants.UNSIGNED_PATH);
            LoggerUtils.writeLog("Success compiled: " + Constants.UNSIGNED_PATH);

            if (!SignatureTool.sign(Constants.UNSIGNED_PATH, Constants.SIGNED_PATH)) {
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
}
