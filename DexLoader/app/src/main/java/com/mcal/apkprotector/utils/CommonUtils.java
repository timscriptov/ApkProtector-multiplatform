package com.mcal.apkprotector.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommonUtils {
    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
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
     * Всплывающее уведомление о том, что приложение было модифицировано...
     *
     * @param title - заголовок уведомления
     * @param msg   - сообщение уведомления
     */
    public static void showDialogWarn(@NotNull Context context, String title, String msg) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        new Notification.Builder(context);
        Notification.Builder when = new Notification.Builder(context).setContentTitle(title).setContentText(msg).setWhen(System.currentTimeMillis());
        NotificationUtils.setSmallNotificationIcon(when, true);
        notificationManager.notify(1621363246, when.getNotification());
        CommonUtils.exit();
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
}
