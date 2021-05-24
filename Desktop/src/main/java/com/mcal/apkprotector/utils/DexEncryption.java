package com.mcal.apkprotector.utils;

import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import org.spongycastle.crypto.CryptoException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class DexEncryption {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

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

    private static void encodeSingleDex(String name) throws IOException {
        String outPath = Constants.ASSETS_PATH + File.separator;
        if (name.matches("classes\\.dex")) {
            outPath += Preferences.getDexPrefix() + "1" + Preferences.getDexSuffix();
        } else {
            outPath += Preferences.getDexPrefix() + name.replaceFirst(".+(\\d+).+", "$1") + Preferences.getDexSuffix();
        }
        try {
            DexEncryption.encrypt(Preferences.getProtectKey(), new File(Constants.OUTPUT_PATH, name), new File(outPath));
        } catch (CryptoException e) {
            LoggerUtils.writeLog(" " + e);
        }
    }

    public static void encrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public static void decrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile,
                                 File outputFile) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            LoggerUtils.writeLog(" " + ex);
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }
}