package com.mcal.apkprotector.utils;

import android.media.MediaCodec;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

  /*  public static void encrypt(String key, File inputFile, File outputFile)
            throws MediaCodec.CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }*/

    public static void decrypt(String key, InputStream is,
                               OutputStream os)
            throws MediaCodec.CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, is, os);
    }

    private static void doCrypto(int cipherMode, String key, InputStream is,
                                 OutputStream os) throws MediaCodec.CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes("UTF-8"), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[0x4000];
            while ((len = is.read(buffer)) > 0) {
                bos.write(buffer, 0 , len);
            }

            byte[] outputBytes = cipher.doFinal(bos.toByteArray());

            os.write(outputBytes);
            is.close();
            bos.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            Log.d("Eccrypt", " " + ex);
        }
    }
}