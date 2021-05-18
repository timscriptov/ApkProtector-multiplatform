package com.mcal.apkprotector.task;

import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.utils.LoggerUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipApk {
    private static final int BUFFER_SIZE = 4096;

    public static boolean unzip(String zipFilePath, String destDir) {
        LoggerUtils.writeLog(Constants.LOG_LINE);

        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                if (fileName.contains("classes-v1.bin")) {
                    return false;
                } else if (!fileName.contains("META-INF")) {
                    File newFile = new File(destDir + File.separator + fileName);
                    System.out.println("Unzipping to " + newFile.getAbsolutePath());
                    LoggerUtils.writeLog("Распаковка: " + newFile.getAbsolutePath());
                    //create directories for sub directories in zip
                    new File(newFile.getParent()).mkdirs();
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    //close this ZipEntry
                    zis.closeEntry();
                }
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
