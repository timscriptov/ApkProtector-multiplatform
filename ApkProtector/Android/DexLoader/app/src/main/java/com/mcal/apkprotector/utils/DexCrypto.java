package com.mcal.apkprotector.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.DeflaterInputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;

public class DexCrypto {
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