package com.mcal.apkprotector.patchers;

import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.LoggerUtils;
import com.mcal.apkprotector.utils.Xor;
import org.jetbrains.annotations.NotNull;

import java.io.*;

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
        Xor.encrypt(Preferences.getProtectKey(), new FileInputStream(new File(Constants.OUTPUT_PATH, name)), new FileOutputStream(outPath));
    }
}