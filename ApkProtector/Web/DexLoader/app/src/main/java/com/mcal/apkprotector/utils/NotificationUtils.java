package com.mcal.apkprotector.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
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

import com.mcal.apkprotector.data.Const;

import java.lang.reflect.Method;

public class NotificationUtils {
    private static final String TAG = NotificationUtils.class.getSimpleName();
    private static Application sApplication;
    private static boolean sNotificationChannelCreated;
    private static boolean sNotificationChannelCreatedHighImportance;
    private static Icon sNotificationIcon;

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
                }
            }
            return null;
        } catch (Exception e) {
            Log.w(TAG, e);
            return null;
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
            NotificationChannel channel;
            Application application;
            NotificationManager notificationManager;
            if (highImportance) {
                try {
                    z = sNotificationChannelCreatedHighImportance;
                    if (!z) {
                        channel = new NotificationChannel(Const.APP_NAME, Const.APP_NAME, 4);
                        application = getApplication();
                        if (application != null) {
                            notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                            if (notificationManager != null) {
                                notificationManager.createNotificationChannel(channel);
                                sNotificationChannelCreatedHighImportance = true;
                            }
                        }
                    }
                    b.setChannelId(Const.APP_NAME);
                    b.setPriority(1);
                    return;
                } catch (Throwable t) {
                    t.printStackTrace();
                    return;
                }
            }
            z = sNotificationChannelCreated;
            if (!z) {
                channel = new NotificationChannel(Const.APP_NAME, Const.APP_NAME, 2);
                application = getApplication();
                if (application != null) {
                    notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                    if (notificationManager != null) {
                        notificationManager.createNotificationChannel(channel);
                        sNotificationChannelCreated = true;
                    }
                }
            }
            b.setChannelId(Const.APP_NAME);
        } else if (highImportance && Build.VERSION.SDK_INT >= 16) {
            b.setPriority(1);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static Icon getNotificationIcon() {
        if (sNotificationIcon == null) {
            try {
                byte[] data = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAADwAAAAqCAMAAADYrcjGAAAAk1BMVEUAAAD///////////////// //////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////// ///////////////////////ROyVeAAAAMHRSTlMAAmvfPMy3Gm3UbDeM0pCPOHR7em5bSCUD+JhQ Dvu1RdNYrRToQvTsJ90H5glxven9mmFBAAAA40lEQVRIx+3VyQ6CQBBFUVQQZVAQQeZJRkXt//86 TTQRtStVsGLh3Z9d9ytB+DeNZvPFYj4bZ0WJPZLEMXTJXi0Hcnm1Zr3WikyVqqazn3RNpdgNA9ri 1mBgBopNGJso3sF4h2ILxhaK9zDeo9iGsY1iB8YOig8wPqDY9SDrufgr0SGsE56nD2GfgAMIBwQc RnwbhZRvFfNxTPrPCR8ntDVIeTYlTknGwxl1iPJfm5P371h82+JIX8+y+rRVOWR71bpva3XYcjft 27bN0ItxUs5PelZOIw5Od7nebtdL97/40+gORH5wbo1YdEMAAAAASUVORK5CYII= ", 0);
                sNotificationIcon = Icon.createWithBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
        return sNotificationIcon;
    }
}
