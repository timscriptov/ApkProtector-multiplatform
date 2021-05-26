package com.mcal.apkprotector.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.mcal.apkprotector.data.Constants;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class CommonUtils {

    public static String generateRandomString(String str) {
        Random random = new Random();
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (arr[i] != '.') {
                arr[i] = Constants.ALPHABET.charAt(random.nextInt(Constants.ALPHABET.length()));
            }
        }
        return new String(arr);
    }

    @Contract(pure = true)
    public static boolean isSystemPackage(@NotNull PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    @NotNull
    public static String versionName(Context context) {
        try {
            @SuppressLint("WrongConstant") PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
            return "Version " + packageInfo.versionName;
        } catch (Exception e) {
            return "null";
        }
    }

    public static boolean isSameSign(Context context, String str, String pkg) {
        try {
            if (appInstalledOrNot(context.getPackageManager(), pkg)) {
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(pkg, 0);
                String sign = SignCheck.getCurrentSignature(str);

                String sign2 = SignCheck.getCurrentSignature(appInfo.publicSourceDir);
                if (sign.equals(sign2)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean appInstalledOrNot(@NotNull PackageManager packageManager, String uri) {
        boolean app_installed;
        try {
            packageManager.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    /**
     * Метод для шифрования строк
     *
     * @param str - текст для шифрования
     * @param i   - символы
     * @return
     */
    @NotNull
    public static String encryptStrings(String str, int i) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < str.length(); j++) {
                stringBuilder.append((char) (str.charAt(j) ^ a(i)[j % a(i).length]));
            }
            return stringBuilder.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    private static char[] a(int i) {
        switch (i) {
            case 0:
                return new char[]{'\u925d', '\u325d', '\ue399', '\u8742', '\uf09b', '\u1473', '\u7904', '\u8dea', '\ud6a9', '\ud519', '\u8a82', '\uc5a1'};
            case 1:
                return new char[]{'\u3005', '\u3006'};
            case 2:
                return new char[]{'\u6033'};
            case 3:
                return new char[]{',', 'w', '\ufffc', '\u00b4', '\uffc8', '\u00b2', 'K', '\u0083', 'r', 'A', '}', '\u008b', '\u0085', '\u00c1', '\f', '\u00a9', 'O', '\u00ac', '\u008b', '8', '\u0011', 'D', ')', 'z', ']', '\u00c6', '\u00e4', 'W', '\u00b3', '\u008e', '\ufff4', '\u00a1', '\uffeb', '0', '\u0086', '\u008f', '\u001f', '\ufff6', '\u00ac', '\u00cb', 'Y', '\uffef', '\u00cb', 't', ' ', '\ufff8', '8', '\u00b7', '\u00d5', 'U', 'o', '\uffdc', 's', '\u001b', '\u0015', '1', '.', 'z', '*', '\u008d', '+', '\\', 'm', '\u000b', '\u0095', '9', '<', 'r', '\u00dc', '\u0090', '\u00b5', '\u00c2', '\u00a9', 'j', '\ufff0'};
        }
        return new char[]{};
    }

    /**
     * Метод для завершения работы приложения
     */
    public static void exit() {
        try {
            Class main = Class.forName("java.lang.System");
            Method method = main.getDeclaredMethod("exit", new Class[]{int.class});
            method.setAccessible(true);
            method.invoke(new Object(), 0);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";//#
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
