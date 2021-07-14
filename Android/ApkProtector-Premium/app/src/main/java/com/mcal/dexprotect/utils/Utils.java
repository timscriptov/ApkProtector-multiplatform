package com.mcal.dexprotect.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_ID = "ApkProtector";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE = "ApkProtectorHighImportance";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_NAME = "ApkProtector";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE = "ApkProtector High Importance";
    private static final String TAG = Utils.class.getSimpleName();
    private static Application sApplication;
    private static boolean sNotificationChannelCreated;
    private static boolean sNotificationChannelCreatedHighImportance;
    private static Icon sNotificationIcon;

    @NotNull
    public static String buildID() {
        return "20" +
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10;
    }

    @NotNull
    public static String sealing(@NotNull String input) {
        int i2;
        byte[] bArr = new byte[]{(byte) 3, (byte) 5, (byte) 9, (byte) 1};
        int length = bArr.length;
        byte[] bytes = input.getBytes();
        for (i2 = 0; i2 < bytes.length; i2 += 1) {
            bytes[i2] = (byte) (bytes[i2] ^ bArr[i2 % length]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] cArr = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        for (i2 = 0; i2 < bytes.length; i2 += 1) {
            stringBuilder.append(cArr[(bytes[i2] >> 4) & 15]);
            stringBuilder.append(cArr[bytes[i2] & 15]);
        }
        return stringBuilder.toString();
    }

    public static boolean sourceExists(@NotNull File sourceDir) {
        if (sourceDir.exists() && sourceDir.isDirectory()) {
            File infoFile = new File(sourceDir + "/info.mz");
            if (infoFile.exists() && infoFile.isFile()) {
                SourceInfo sourceInfo = SourceInfo.getSourceInfo(infoFile);
                return sourceInfo != null;
            }
        }
        return false;
    }

    @Nullable
    public static SourceInfo getSourceInfoFromSourcePath(@NotNull File sourceDir) {
        if (sourceDir.isDirectory()) {
            File infoFile = new File(sourceDir + "/info.mz");
            if (infoFile.exists() && infoFile.isFile()) {
                return SourceInfo.getSourceInfo(infoFile);
            }
        }
        return null;
    }

    public static void deleteFolder(@NotNull File folder) {
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    @SuppressLint("ObsoleteSdkInt")
    public static void showDialogWarn(@NotNull Context context, String str, String str2) {
        @SuppressLint("WrongConstant") NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        new Notification.Builder(context);
        Notification.Builder when = new Notification.Builder(context).setContentTitle(str).setContentText(str2).setWhen(System.currentTimeMillis());
        Utils.setSmallNotificationIcon(when, true);
        notificationManager.notify(1621363246, when.getNotification());
        try {
            Class main = Class.forName("java.lang.System");
            Method method = main.getDeclaredMethod("exit", new Class[]{int.class});
            method.setAccessible(true);
            method.invoke(new Object(), 0);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ResourceType")
    public static void setSmallNotificationIcon(Notification.Builder b, boolean highImportance) {
        if (Build.VERSION.SDK_INT >= 23) {
            Icon icon = getNotificationIcon();
            if (icon != null) {
                b.setSmallIcon(icon);
            } else {
                b.setSmallIcon(17301569);
            }
            b.setColor(-16537100);
        } else {
            b.setSmallIcon(17301569);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            boolean z;
            String str2;
            NotificationChannel channel;
            Application application;
            NotificationManager notificationManager;
            if (highImportance) {
                try {
                    z = sNotificationChannelCreatedHighImportance;
                    str2 = APP_CLONER_NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE;
                    if (!z) {
                        channel = new NotificationChannel(str2, APP_CLONER_NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE, 4);
                        application = getApplication();
                        if (application != null) {
                            notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                            if (notificationManager != null) {
                                notificationManager.createNotificationChannel(channel);
                                sNotificationChannelCreatedHighImportance = true;
                                Log.i(TAG, "setSmallNotificationIcon; notification channel created: AppClonerHighImportance");
                            }
                        }
                    }
                    b.setChannelId(str2);
                    b.setPriority(1);
                    return;
                } catch (Throwable t) {
                    Log.w(TAG, t);
                    return;
                }
            }
            z = sNotificationChannelCreated;
            str2 = APP_CLONER_NOTIFICATION_CHANNEL_ID;
            if (!z) {
                channel = new NotificationChannel(str2, APP_CLONER_NOTIFICATION_CHANNEL_NAME, 2);
                application = getApplication();
                if (application != null) {
                    notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                    if (notificationManager != null) {
                        notificationManager.createNotificationChannel(channel);
                        sNotificationChannelCreated = true;
                        //Log.i(TAG, "setSmallNotificationIcon; notification channel created: AppCloner");
                    }
                }
            }
            b.setChannelId(str2);
        } else if (highImportance && Build.VERSION.SDK_INT >= 16) {
            b.setPriority(1);
        }
    }

    private static Icon getNotificationIcon() {
        if (sNotificationIcon == null) {
            try {
                byte[] data = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAADwAAAAqCAMAAADYrcjGAAAAk1BMVEUAAAD///////////////// //////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////// ///////////////////////ROyVeAAAAMHRSTlMAAmvfPMy3Gm3UbDeM0pCPOHR7em5bSCUD+JhQ Dvu1RdNYrRToQvTsJ90H5glxven9mmFBAAAA40lEQVRIx+3VyQ6CQBBFUVQQZVAQQeZJRkXt//86 TTQRtStVsGLh3Z9d9ytB+DeNZvPFYj4bZ0WJPZLEMXTJXi0Hcnm1Zr3WikyVqqazn3RNpdgNA9ri 1mBgBopNGJso3sF4h2ILxhaK9zDeo9iGsY1iB8YOig8wPqDY9SDrufgr0SGsE56nD2GfgAMIBwQc RnwbhZRvFfNxTPrPCR8ntDVIeTYlTknGwxl1iPJfm5P371h82+JIX8+y+rRVOWR71bpva3XYcjft 27bN0ItxUs5PelZOIw5Od7nebtdL97/40+gORH5wbo1YdEMAAAAASUVORK5CYII= ", 0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    sNotificationIcon = Icon.createWithBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
        return sNotificationIcon;
    }

    @Nullable
    public static Application getApplication() {
        Application application = sApplication;
        if (application != null) {
            return application;
        }
        try {
            @SuppressLint("PrivateApi") Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            if (Build.VERSION.SDK_INT >= 9) {
                sApplication = (Application) activityThreadClass.getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
                return sApplication;
            }
            for (Method method : activityThreadClass.getMethods()) {
                if ("currentActivityThread".equals(method.getName())) {
                    Object currentActivityThread = method.invoke(null, new Object[0]);
                    for (Method m2 : activityThreadClass.getMethods()) {
                        if ("getApplication".equals(m2.getName())) {
                            sApplication = (Application) m2.invoke(currentActivityThread, new Object[0]);
                            return sApplication;
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (Exception e) {
            Log.w(TAG, e);
            return null;
        }
    }
}
