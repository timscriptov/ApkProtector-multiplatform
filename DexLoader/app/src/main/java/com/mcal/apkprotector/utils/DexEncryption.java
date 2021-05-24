package com.mcal.apkprotector.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DexEncryption {

    @NotNull
    @Contract(pure = true)
    private static int[] a(@NotNull int[] iArr) {
        int[] iArr2 = new int[27];
        int i = iArr[0];
        iArr2[0] = i;
        int[] iArr3 = new int[]{iArr[1], iArr[2], iArr[3]};
        for (int i2 = 0; i2 < 26; i2++) {
            iArr3[i2 % 3] = (((iArr3[i2 % 3] >>> 8) | (iArr3[i2 % 3] << 24)) + i) ^ i2;
            i = ((i << 3) | (i >>> 29)) ^ iArr3[i2 % 3];
            iArr2[i2 + 1] = i;
        }
        return iArr2;
    }

    private static void _π(@NotNull int[] iArr, @NotNull int[] iArr2) {
        int i = iArr2[0];
        int i2 = iArr2[1];
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[0];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[1];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[2];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[3];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[4];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[5];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[6];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[7];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[8];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[9];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[10];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[11];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[12];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[13];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[14];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[15];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[16];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[17];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[18];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[19];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[20];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[21];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[22];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[23];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[24];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[25];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[26];
        iArr2[0] = ((i << 3) | (i >>> 29)) ^ i2;
        iArr2[1] = i2;
    }

    public static void decDex(String protectKey, InputStream is, @NotNull OutputStream os) {
        try {
            oss(protectKey, is, os);
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void a(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void oss(String protectKey, @NotNull InputStream inputStream, OutputStream outputStream) throws Exception {
        char[] chars = protectKey.toCharArray();
        int[] __ = new int[4];
        int i = 1;
        int i2 = i + 1;
        __[0] = chars[0] | (chars[i] << 16);
        i = i2 + 1;
        char c = chars[i2];
        i2 = i + 1;
        __[1] = c | (chars[i] << 16);
        i = i2 + 1;
        c = chars[i2];
        i2 = i + 1;
        __[2] = c | (chars[i] << 16);
        i = i2 + 1;
        c = chars[i2];
        i2 = i + 1;
        __[3] = c | (chars[i] << 16);
        int[] iArr2 = new int[2];
        i = i2 + 1;
        c = chars[i2];
        i2 = i + 1;
        iArr2[0] = c | (chars[i] << 16);
        iArr2[1] = chars[i2] | (chars[i2 + 1] << 16);
        __ = a(__);
        byte[] bArr = new byte[8192];
        int i3 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read >= 0) {
                int i4 = i3 + read;
                int i5 = 0;
                while (i3 < i4) {
                    int i6 = i3 % 8;
                    int i7 = i6 / 4;
                    int i8 = i3 % 4;
                    if (i6 == 0) {
                        _π(__, iArr2);
                    }
                    bArr[i5] = (byte) (((byte) (iArr2[i7] >> (i8 * 8))) ^ bArr[i5]);
                    i3++;
                    i5++;
                }
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
