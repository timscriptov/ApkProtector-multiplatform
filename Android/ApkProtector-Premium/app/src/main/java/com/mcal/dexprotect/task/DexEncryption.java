package com.mcal.dexprotect.task;

import android.content.Context;
import android.util.Log;

import com.mcal.dexprotect.data.Preferences;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterInputStream;

/**
 * Шифрование дексов
 */

public class DexEncryption {
    private static String TAG = "ApkProtector";
    private static String xpath;
    private static DeflaterInputStream isx;

    public static boolean enDex(@NotNull Context context) {
        xpath = context.getFilesDir().getAbsolutePath();

        File assets = new File(xpath + "/gen/assets");
        if (!assets.exists()) {
            assets.mkdir();
        }
        try {
            for (File file : new File(xpath + "/gen").listFiles()) {
                if (file.getName().endsWith(".dex")) {
                    Log.e(TAG, "Encrypting: " + file.getName());
                    okEnDex(file.getName());
                    new File(xpath + "/gen/" + file.getName()).delete();
                }
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "enDex() failed: ", e);
            return false;
        }
    }

    private static void okEnDex(String name) throws Exception {
        File assets = new File(xpath + "/gen/assets");
        if (!assets.exists()) {
            assets.mkdir();
        }
        File folder = new File(xpath + "/gen/assets/dex");
        if (!folder.exists()) {
            folder.mkdir();
        }
        FileInputStream is = new FileInputStream(xpath + "/gen/" + name);
        FileOutputStream os = new FileOutputStream(xpath + "/gen/assets/dex/" + name.replace("classes", name.equals("classes.dex") ? "classes-v1" : "classes-v").replace("dex", "bin"));
        isx = new DeflaterInputStream(is);
        exfr(isx, os);
        isx.close();
    }

    private static void nDnv(@NotNull int[] iArr, @NotNull int[] iArr2) {
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

    @NotNull
    @Contract(pure = true)
    private static int[] FxIjsF(@NotNull int[] iArr) {
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

    private static void exfr(@NotNull InputStream inputStream, OutputStream outputStream) throws Exception {
        char[] toCharArray = Preferences.getProtectKeyString().toCharArray();
        int[] iArr = new int[4];
        int i = 1;
        int i2 = i + 1;
        iArr[0] = toCharArray[0] | (toCharArray[i] << 16);
        i = i2 + 1;
        char c = toCharArray[i2];
        i2 = i + 1;
        iArr[1] = c | (toCharArray[i] << 16);
        i = i2 + 1;
        c = toCharArray[i2];
        i2 = i + 1;
        iArr[2] = c | (toCharArray[i] << 16);
        i = i2 + 1;
        c = toCharArray[i2];
        i2 = i + 1;
        iArr[3] = c | (toCharArray[i] << 16);
        int[] iArr2 = new int[2];
        i = i2 + 1;
        c = toCharArray[i2];
        i2 = i + 1;
        iArr2[0] = c | (toCharArray[i] << 16);
        iArr2[1] = toCharArray[i2] | (toCharArray[i2 + 1] << 16);
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
                    if (i6 == 0) {
                        nDnv(iArr, iArr2);
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
