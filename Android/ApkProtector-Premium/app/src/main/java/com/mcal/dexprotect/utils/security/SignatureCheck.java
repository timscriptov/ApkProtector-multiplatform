package com.mcal.dexprotect.utils.security;

import android.annotation.SuppressLint;
import android.content.Context;

import com.mcal.dexprotect.BuildConfig;
import com.mcal.dexprotect.R;
import com.mcal.dexprotect.data.Constants;
import com.mcal.dexprotect.utils.Utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SignatureCheck {
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    private static final String sign = "c9f118716f9c23da05096efe5d9a83d3";

    public static boolean start(Context context) {
        if (Objects.equals(stringToMD5(getApkSignInfo(context)), sign) || BuildConfig.DEBUG) {
            return true;
        } else {
            Utils.showDialogWarn(context, "ApkProtector Security", context.getString(R.string.vending_message));
            return false;
        }
    }

    @Nullable
    public static String getApkSignInfo(Context context) {
        byte[] readBuffer = new byte[8192];
        Certificate[] certs = null;
        try {
            JarFile jarFile = new JarFile(context.getApplicationInfo().publicSourceDir);
            Enumeration entries = jarFile.entries();
            JarEntry je = (JarEntry) entries.nextElement();
            while (entries.hasMoreElements()) {

                if (je.isDirectory() || je.getName().startsWith("META-INF/") && je.getName().endsWith(".RSA")) {
                    continue;
                }

                Certificate[] localCerts = loadCertificates(jarFile, je, readBuffer);

                if (certs == null) {
                    certs = localCerts;

                } else {

                    for (Certificate cert : certs) {
                        boolean found = false;

                        for (Certificate localCert : localCerts) {
                            if (cert != null && cert.equals(localCert)) {
                                found = true;
                                break;
                            }
                        }

                        if (!found || certs.length != localCerts.length) {
                            jarFile.close();

                            return null;
                        }
                    }
                }
                jarFile.close();

                return new String(toChars(certs[0].getEncoded()));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    @Nullable
    public static String stringToMD5(String str) {
        try {
            try {
                byte[] hash;

                try {
                    hash = MessageDigest.getInstance("MD5").digest(str.getBytes(Constants.UTF_8));
                } catch (NoSuchAlgorithmException e) {
                    return null;
                }

                StringBuilder hex = new StringBuilder(hash.length * 2);

                for (byte b : hash) {
                    if ((b & 0xFF) < 0x10)
                        hex.append("0");
                    hex.append(Integer.toHexString(b & 0xFF));
                }
                return hex.toString();
            } catch (Exception a) {
                return null;
            }
        } catch (Exception a) {
            return null;
        }
    }

    @Nullable
    private static Certificate[] loadCertificates(@NotNull JarFile jarFile, JarEntry je, byte[] readBuffer) {
        try {
            InputStream is = jarFile.getInputStream(je);
            while (is.read(readBuffer, 0, readBuffer.length) != -1) {
            }
            is.close();
            return je != null ? je.getCertificates() : null;
        } catch (IOException a) {
            a.printStackTrace();
            return null;
        }
    }

    @NotNull
    @Contract(pure = true)
    private static char[] toChars(@NotNull byte[] mSignature) {
        final int N;
        N = mSignature.length;
        final int N2 = N * 2;
        char[] text = new char[N2];

        for (int j = 0; j < N; j++) {
            byte v = mSignature[j];
            int d = (v >> 4) & 0xf;
            text[j * 2] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
            d = v & 0xf;
            text[j * 2 + 1] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
        }
        return text;
    }
}