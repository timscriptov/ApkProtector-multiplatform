package com.mcal.apkprotector.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Xor {
    public static void exfr(@NotNull String protectKey, @NotNull InputStream inputStream, OutputStream outputStream) throws IOException {
        char[] key = protectKey.toCharArray();
        int[] iArr = new int[4];
        int i = 1;
        int i2 = i + 1;
        iArr[0] = key[0] | (key[i] << 16);
        i = i2 + 1;
        char c = key[i2];
        i2 = i + 1;
        iArr[1] = c | (key[i] << 16);
        i = i2 + 1;
        c = key[i2];
        i2 = i + 1;
        iArr[2] = c | (key[i] << 16);
        i = i2 + 1;
        c = key[i2];
        i2 = i + 1;
        iArr[3] = c | (key[i] << 16);
        int[] iArr2 = new int[2];
        i = i2 + 1;
        c = key[i2];
        i2 = i + 1;
        iArr2[0] = c | (key[i] << 16);
        iArr2[1] = key[i2] | (key[i2 + 1] << 16);
        iArr = FxIjsF(iArr);
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

    @Contract(pure = true)
    private static int @NotNull [] FxIjsF(int @NotNull [] iArr) {
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
}