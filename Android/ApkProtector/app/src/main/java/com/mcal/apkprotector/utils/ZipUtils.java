package com.mcal.apkprotector.utils;

import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.util.zip.Deflater;

public class ZipUtils {
    public static boolean pack(String input, String output) {
        try {
            ZipUtil.pack(new File(input), new File(output), Deflater.BEST_COMPRESSION);
            return true;
        } catch (Exception e) {
            LoggerUtils.writeLog("ApkProtector" + "Build Apk failed: " + e);
            return false;
        }
    }

    public static boolean unpack(String inputZip, String outputFolder) {
        try {
            ZipUtil.unpack(new File(inputZip), new File(outputFolder));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}