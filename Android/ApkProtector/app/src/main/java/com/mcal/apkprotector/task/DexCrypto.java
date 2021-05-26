package com.mcal.apkprotector.task;

import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.LoggerUtils;

import org.spongycastle.crypto.CryptoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterInputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;

public class DexCrypto {

    public static void encodeDexes() {
        File assets = new File(Constants.ASSETS_PATH);
        if (!assets.exists()) {
            assets.mkdir();
        }
        try {
            for (File file : new File(Constants.OUTPUT_PATH).listFiles()) {
                if (file.getName().endsWith(".dex")) {
                    LoggerUtils.writeLog("Encrypting: " + file.getName());
                    encodeSingleDex(file.getName());
                }
            }
        } catch (Exception e) {
            LoggerUtils.writeLog(" " + e);
        }
    }

    private static void encodeSingleDex(String name) {
        String outPath = Constants.ASSETS_PATH + File.separator;
        if (name.matches("classes\\.dex")) {
            outPath += "classes-v" + "1" + Preferences.getDexSuffix();
        } else {
            outPath += "classes-v" + name.replaceFirst(".+(\\d+).+", "$1") + Preferences.getDexSuffix();
        }
        try {
            DexCrypto.encrypt(Preferences.getProtectKey(), new FileInputStream(new File(Constants.OUTPUT_PATH, name)), new FileOutputStream(outPath));
        } catch (CryptoException e) {
            LoggerUtils.writeLog(" " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decrypt(String protectKey, InputStream input, OutputStream output) throws Exception {
        InflaterInputStream is = new InflaterInputStream(input);
        InflaterOutputStream os = new InflaterOutputStream(output);

        exfr(protectKey, is, os);
        os.close();
        is.close();
    }

    public static void encrypt(String protectKey, InputStream input, OutputStream output) throws Exception {
        DeflaterInputStream is = new DeflaterInputStream(input);
        DeflaterOutputStream os = new DeflaterOutputStream(output);

        exfr(protectKey, is, os);
        os.close();
        is.close();
    }

    private static void exfr(String protectKey, InputStream inputStream, OutputStream outputStream) throws Exception {
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

    private static int[] FxIjsF(int[] iArr) {
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

    private static void nDnv(int[] iArr, int[] iArr2) {
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
}