package com.mcal.apkprotector;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DexDecryption {
    public static void decDex(Context context, FileInputStream is, @NotNull File file) {
        try {
            FileInputStream fis = is;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = fis.read(bytes)) > 0) {
                bos.write(bytes, 0, len);
            }
            BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(bos.toByteArray()));
            bos.close();
            fis.close();
            FileOutputStream fos = new FileOutputStream(file);
            while ((len = bis.read(bytes)) > 0) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            Log.d("DexDecrypt", " " + e);
        }
    }
}
