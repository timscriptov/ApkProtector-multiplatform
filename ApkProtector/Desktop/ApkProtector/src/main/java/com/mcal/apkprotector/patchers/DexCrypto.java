package com.mcal.apkprotector.patchers;

import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.LoggerUtils;
import com.mcal.apkprotector.utils.Xor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.spongycastle.crypto.CryptoException;

import java.io.*;
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

    private static void encodeSingleDex(@NotNull String name) throws IOException {
        String outPath = Constants.ASSETS_PATH + File.separator;
        if (name.matches("classes\\.dex")) {
            outPath += Preferences.getDexPrefix() + "1" + Preferences.getDexSuffix();
        } else {
            outPath += Preferences.getDexPrefix() + name.replaceFirst(".+(\\d+).+", "$1") + Preferences.getDexSuffix();
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

        Xor.exfr(protectKey, is, os);
        os.close();
        is.close();
    }

    public static void encrypt(String protectKey, InputStream input, OutputStream output) throws Exception {
        DeflaterInputStream is = new DeflaterInputStream(input);
        DeflaterOutputStream os = new DeflaterOutputStream(output);

        Xor.exfr(protectKey, is, os);
        os.close();
        is.close();
    }
}