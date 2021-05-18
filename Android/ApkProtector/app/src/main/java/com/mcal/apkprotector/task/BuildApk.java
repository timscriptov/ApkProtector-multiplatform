package com.mcal.apkprotector.task;

import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.utils.LoggerUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Сжатие распакованных файлов в APK
 */

public class BuildApk {
    public static boolean buildApk(String sourceDir, String zipFile) {
        LoggerUtils.writeLog(Constants.LOG_LINE);
        try {
            compress(sourceDir, zipFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void compress(String dirPath, String zipFile) {
        final Path sourceDir = Paths.get(dirPath);

        try {
            final ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    try {
                        Path targetFile = sourceDir.relativize(file);
                        outputStream.putNextEntry(new ZipEntry(targetFile.toString()));
                        LoggerUtils.writeLog("Сжатие: " + targetFile);
                        byte[] bytes = Files.readAllBytes(file);
                        outputStream.write(bytes, 0, bytes.length);
                        outputStream.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}