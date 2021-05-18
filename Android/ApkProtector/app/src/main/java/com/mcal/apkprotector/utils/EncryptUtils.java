package com.mcal.apkprotector.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EncryptUtils {

    private static final int BUFF_SIZE = 1024 * 1024 * 5; // 10MB
    private static final String KEY = "6717454797118328";

    public static byte[] encryptXXTEA(byte[] data) {
        return XxTea.encrypt(data, KEY);
    }

    /**
     * 已过时，请使用{@link #encryptXXTEA(byte[])}
     *
     * @param data
     * @return
     */
    @NotNull
    @Contract("_ -> param1")
    @Deprecated
    public static byte[] encrypt(@NotNull byte[] data) {
        String key = KEY;
        int keyLen = key.length();
        int size = data.length;

        // 加密数据
        int i = 0;
        int offset = 0;
        for (; i < size; ++i, ++offset) {
            if (offset >= keyLen) {
                offset = 0;
            }
            data[i] ^= key.charAt(offset);
        }
        return data;
    }

    public static void encrypt(File file, File outFile) {
        if (!Utils.exists(file)) {
            //Log.e("ApkProtector","file not exists!!! : " + file.getAbsolutePath());
            return;
        }

        FileInputStream in;
        FileOutputStream out;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(outFile);
            ByteArrayOutputStream byteOutput;

            try {
                byteOutput = new ByteArrayOutputStream();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                // 文件过大，内存分配失败
                return;
            }

            byte[] buff = new byte[BUFF_SIZE];
            int len;
            while ((len = in.read(buff)) != -1) {
                byteOutput.write(buff, 0, len);
            }

            byte[] encryptData = encryptXXTEA(byteOutput.toByteArray());
            out.write(encryptData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
