package com.mcal.apkprotector.utils;

import org.jetbrains.annotations.NotNull;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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