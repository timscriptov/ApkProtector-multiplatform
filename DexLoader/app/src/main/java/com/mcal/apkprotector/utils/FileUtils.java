package com.mcal.apkprotector.utils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class FileUtils {
    public static void writeString(File file, String str) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        try {
            out.write(str);
        } catch (IOException e) {
            System.out.println("Exception " + e);
        } finally {
            out.close();
        }
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = readAllBytes(new FileInputStream(path));
        return new String(encoded, encoding);
    }

    public static byte [] readAllBytes(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while ((len = is.read(buffer)) > 0)
            bos.write(buffer, 0, len);
        is.close();
        return bos.toByteArray();
    }

    public static void writeFile(InputStream inputStream, FileOutputStream outputPath) throws IOException {
        int size = inputStream.available();
        byte[] bucket = new byte[size];

        inputStream.read(bucket);

        try (FileOutputStream outputStream = outputPath) {
            outputStream.write(bucket);
        }
    }
}
