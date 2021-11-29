package com.mcal.dexprotect.task;

import androidx.annotation.NonNull;

import com.mcal.dexprotect.data.Constants;
import com.mcal.dexprotect.data.Preferences;
import com.mcal.dexprotect.utils.LoggerUtils;
import com.mcal.dexprotect.utils.Xor;

import org.spongycastle.crypto.CryptoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
            File[] dexFolder = new File(Constants.OUTPUT_PATH).listFiles();
            if (dexFolder != null) {
                for (File file : dexFolder) {
                    if (file.getName().endsWith(".dex")) {
                        LoggerUtils.writeLog("Encrypting: " + file.getName());
                        encodeSingleDex(file.getName());
                    }
                }
            }
        } catch (Exception e) {
            LoggerUtils.writeLog(" " + e);
        }
    }

    private static void encodeSingleDex(@NonNull String name) {
        String outPath = Constants.ASSETS_PATH + File.separator;
        if (name.matches("classes\\.dex")) {
            outPath += "classes-v" + "1" + Preferences.getSuffixDexesName();
        } else {
            outPath += "classes-v" + name.replaceFirst(".+(\\d+).+", "$1") + Preferences.getSuffixDexesName();
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