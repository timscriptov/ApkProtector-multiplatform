package com.mcal.apkprotector.utils;

/**********************************************************\
 |                                                          |
 | XXTEA.java                                               |
 |                                                          |
 | XXTEA encryption algorithm library for Java.             |
 |                                                          |
 | Encryption Algorithm Authors:                            |
 |      David J. Wheeler                                    |
 |      Roger M. Needham                                    |
 |                                                          |
 | Code Authors: Ma Bingyao <mabingyao@gmail.com>           |
 | LastModified: Mar 10, 2015                               |
 |                                                          |
 \**********************************************************/

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.UnsupportedEncodingException;

public final class XxTea {

    private static final int DELTA = 0x9E3779B9;

    @Contract(pure = true)
    private XxTea() {
    }

    @Contract(pure = true)
    private static int MX(int sum, int y, int z, int p, int e, @NotNull int[] k) {
        return (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
    }

    public static final byte[] encrypt(@NotNull byte[] data, byte[] key) {
        if (data.length == 0) {
            return data;
        }
        return toByteArray(
                encrypt(toIntArray(data, true), toIntArray(fixKey(key), false)), false);
    }

    @Nullable
    public static final byte[] encrypt(@NotNull String data, byte[] key) {
        try {
            return encrypt(data.getBytes("UTF-8"), key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static final byte[] encrypt(byte[] data, @NotNull String key) {
        try {
            return encrypt(data, key.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static final byte[] encrypt(@NotNull String data, @NotNull String key) {
        try {
            return encrypt(data.getBytes("UTF-8"), key.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static final String encryptToBase64String(byte[] data, byte[] key) {
        byte[] bytes = encrypt(data, key);
        if (bytes == null) return null;
        return TeaBase64.encode(bytes);
    }

    @Nullable
    public static final String encryptToBase64String(String data, byte[] key) {
        byte[] bytes = encrypt(data, key);
        if (bytes == null) return null;
        return TeaBase64.encode(bytes);
    }

    @Nullable
    public static final String encryptToBase64String(byte[] data, String key) {
        byte[] bytes = encrypt(data, key);
        if (bytes == null) return null;
        return TeaBase64.encode(bytes);
    }

    @Nullable
    public static final String encryptToBase64String(String data, String key) {
        byte[] bytes = encrypt(data, key);
        if (bytes == null) return null;
        return TeaBase64.encode(bytes);
    }

    public static final byte[] decrypt(@NotNull byte[] data, byte[] key) {
        if (data.length == 0) {
            return data;
        }
        return toByteArray(
                decrypt(toIntArray(data, false), toIntArray(fixKey(key), false)), true);
    }

    @Nullable
    public static final byte[] decrypt(byte[] data, @NotNull String key) {
        try {
            return decrypt(data, key.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final byte[] decryptBase64String(String data, byte[] key) {
        return decrypt(TeaBase64.decode(data), key);
    }

    public static final byte[] decryptBase64String(String data, String key) {
        return decrypt(TeaBase64.decode(data), key);
    }

    @Nullable
    public static final String decryptToString(byte[] data, byte[] key) {
        try {
            byte[] bytes = decrypt(data, key);
            if (bytes == null) return null;
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static final String decryptToString(byte[] data, String key) {
        try {
            byte[] bytes = decrypt(data, key);
            if (bytes == null) return null;
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static final String decryptBase64StringToString(String data, byte[] key) {
        try {
            byte[] bytes = decrypt(TeaBase64.decode(data), key);
            if (bytes == null) return null;
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static final String decryptBase64StringToString(String data, String key) {
        try {
            byte[] bytes = decrypt(TeaBase64.decode(data), key);
            if (bytes == null) return null;
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @NotNull
    @Contract("_, _ -> param1")
    private static int[] encrypt(@NotNull int[] v, int[] k) {
        int n = v.length - 1;

        if (n < 1) {
            return v;
        }
        int p, q = 6 + 52 / (n + 1);
        int z = v[n], y, sum = 0, e;

        while (q-- > 0) {
            sum = sum + DELTA;
            e = sum >>> 2 & 3;
            for (p = 0; p < n; p++) {
                y = v[p + 1];
                z = v[p] += MX(sum, y, z, p, e, k);
            }
            y = v[0];
            z = v[n] += MX(sum, y, z, p, e, k);
        }
        return v;
    }

    @NotNull
    @Contract("_, _ -> param1")
    private static int[] decrypt(@NotNull int[] v, int[] k) {
        int n = v.length - 1;

        if (n < 1) {
            return v;
        }
        int p, q = 6 + 52 / (n + 1);
        int z, y = v[0], sum = q * DELTA, e;

        while (sum != 0) {
            e = sum >>> 2 & 3;
            for (p = n; p > 0; p--) {
                z = v[p - 1];
                y = v[p] -= MX(sum, y, z, p, e, k);
            }
            z = v[n];
            y = v[0] -= MX(sum, y, z, p, e, k);
            sum = sum - DELTA;
        }
        return v;
    }

    @NotNull
    private static byte[] fixKey(@NotNull byte[] key) {
        if (key.length == 16) return key;
        byte[] fixedkey = new byte[16];
        if (key.length < 16) {
            System.arraycopy(key, 0, fixedkey, 0, key.length);
        } else {
            System.arraycopy(key, 0, fixedkey, 0, 16);
        }
        return fixedkey;
    }

    @NotNull
    @Contract(pure = true)
    private static int[] toIntArray(@NotNull byte[] data, boolean includeLength) {
        int n = (((data.length & 3) == 0)
                ? (data.length >>> 2)
                : ((data.length >>> 2) + 1));
        int[] result;

        if (includeLength) {
            result = new int[n + 1];
            result[n] = data.length;
        } else {
            result = new int[n];
        }
        n = data.length;
        for (int i = 0; i < n; ++i) {
            result[i >>> 2] |= (0x000000ff & data[i]) << ((i & 3) << 3);
        }
        return result;
    }


    @Nullable
    @Contract(pure = true)
    private static byte[] toByteArray(@NotNull int[] data, boolean includeLength) {
        int n = data.length << 2;

        if (includeLength) {
            int m = data[data.length - 1];
            n -= 4;
            if ((m < n - 3) || (m > n)) {
                return null;
            }
            n = m;
        }
        byte[] result = new byte[n];

        for (int i = 0; i < n; ++i) {
            result[i] = (byte) (data[i >>> 2] >>> ((i & 3) << 3));
        }
        return result;
    }
}
