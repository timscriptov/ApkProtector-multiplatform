package com.mcal.apkprotector.utils;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileUtils {
    public static boolean copyFileStream(@NotNull File file, @NotNull File file1) {
        try {
            copyFileUsingStream(file, file1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    public static void copyFile(InputStream source, String dest) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(dest));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = source.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            source.close();
            os.close();
        }
    }

    public static void delete(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    delete(f);
                }
                file.delete();
            } else {
                file.delete();
            }
        }
    }

   public static boolean deleteDir(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDir(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public static String readFileToString(File file) throws IOException {
        StringBuilder text = new StringBuilder();
        FileInputStream fileStream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
        for ( String line; (line = br.readLine()) != null; )
            text.append(line).append(System.lineSeparator());
        return text.toString();
    }
}
