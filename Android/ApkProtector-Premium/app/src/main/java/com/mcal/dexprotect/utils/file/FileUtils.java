package com.mcal.dexprotect.utils.file;

import android.content.Context;

import com.mcal.dexprotect.utils.LoggerUtils;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class FileUtils {
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = readAllBytes(new FileInputStream(path));
        return new String(encoded, encoding);
    }

    public static byte[] readAllBytes(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while ((len = is.read(buffer)) > 0)
            bos.write(buffer, 0, len);
        is.close();
        return bos.toByteArray();
    }

    public static void writeInt(byte[] data, int off, int value) {
        data[off++] = (byte) (value & 0xFF);
        data[off++] = (byte) ((value >>> 8) & 0xFF);
        data[off++] = (byte) ((value >>> 16) & 0xFF);
        data[off] = (byte) ((value >>> 24) & 0xFF);
    }

    public static int readInt(byte[] data, int off) {
        return data[off + 3] << 24 | (data[off + 2] & 0xFF) << 16 | (data[off + 1] & 0xFF) << 8
                | data[off] & 0xFF;
    }

    public static byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }

    public static void writeByteArrayToFile(File file, byte[] bfile) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bfile);
        fos.close();
    }

    public static void inputStreamRaw(Context context, Integer i, String targetFile) {
        try {
            InputStream initialStream = context.getResources().openRawResource(i);

            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            OutputStream outStream = new FileOutputStream(new File(targetFile));
            outStream.write(buffer);
        } catch (IOException e) {
            LoggerUtils.writeLog("Extract raw failed: " + e);
        }
    }

    public static void inputStreamAssets(Context context, String name, String targetFile) {
        try {
            InputStream initialStream = context.getResources().getAssets().open(name);

            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            OutputStream outStream = new FileOutputStream(new File(targetFile));
            outStream.write(buffer);
        } catch (IOException e) {
            LoggerUtils.writeLog("Extract asset failed: " + e);
        }
    }

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
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest)) {
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
        for (String line; (line = br.readLine()) != null; )
            text.append(line).append(System.lineSeparator());
        return text.toString();
    }
}
