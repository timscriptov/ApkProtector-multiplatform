/* Orginal file: NotificationUtils.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.utils*/
package com.mcal.apkprotector.dD;

//import android.annotation.SuppressLint;

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

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

//public class NotificationUtils {
public class NotificationUtils {
    //private static final String APP_CLONER_NOTIFICATION_CHANNEL_ID = "ApkProtector";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_ID = llII1II(163);
    //private static final String APP_CLONER_NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE = "ApkProtectorHighImportance";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE = llII1II(178);
    //private static final String APP_CLONER_NOTIFICATION_CHANNEL_NAME = "ApkProtector";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_NAME = llII1II(193);
    //private static final String APP_CLONER_NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE = "ApkProtector High Importance";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE = llII1II(208);
    //private static final String TAG = NotificationUtils.class.getSimpleName();
    private static final String TAG = NotificationUtils.class.getSimpleName();
    //private static Application sApplication;
    private static Application sApplication;
    //private static boolean sNotificationChannelCreated;
    private static boolean sNotificationChannelCreated;
    //private static boolean sNotificationChannelCreatedHighImportance;
    private static boolean sNotificationChannelCreatedHighImportance;
    //private static Icon sNotificationIcon;
    private static Icon sNotificationIcon;

    //@Nullable
    @Nullable
    //public static Application getApplication() {
    public static Application getApplication() {
        //Application application = sApplication;
        Application application = sApplication;
        //if (application != null) {
        if (application != null) {
            //return application;
            return application;
            //}
        }
        //try {
        try {
            //@SuppressLint("PrivateApi") Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            @SuppressLint("PrivateApi") Class<?> activityThreadClass = Class.forName(llII1II(333));
            //if (Build.VERSION.SDK_INT >= 9) {
            if (Build.VERSION.SDK_INT >= 9) {
                //sApplication = (Application) activityThreadClass.getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
                sApplication = (Application) activityThreadClass.getMethod(llII1II(366)).invoke(null, new Object[0]);
                //return sApplication;
                return sApplication;
                //}
            }
            //for (Method method : activityThreadClass.getMethods()) {
            for (Method method : activityThreadClass.getMethods()) {
                //if ("currentActivityThread".equals(method.getName())) {
                if (llII1II(419).equals(method.getName())) {
                    //Object currentActivityThread = method.invoke(null, new Object[0]);
                    Object currentActivityThread = method.invoke(null);
                    //for (Method m2 : activityThreadClass.getMethods()) {
                    for (Method m2 : activityThreadClass.getMethods()) {
                        //if ("getApplication".equals(m2.getName())) {
                        if (llII1II(476).equals(m2.getName())) {
                            //sApplication = (Application) m2.invoke(currentActivityThread, new Object[0]);
                            sApplication = (Application) m2.invoke(currentActivityThread, new Object[0]);
                            //return sApplication;
                            return sApplication;
                            //}
                        }
                        //}
                    }
                    //continue;
                    continue;
                    //}
                }
                //}
            }
            //return null;
            return null;
            //} catch (Exception e) {
        } catch (Exception e) {
            //Log.w(TAG, e);
            Log.w(TAG, e);
            //return null;
            return null;
            //}
        }
        //}
    }

    //@SuppressLint("ResourceType")
    @SuppressLint("ResourceType")
    //public static void setSmallNotificationIcon(Notification.Builder b, boolean highImportance) {
    public static void setSmallNotificationIcon(Notification.Builder b, boolean highImportance) {
        //if (Build.VERSION.SDK_INT >= 23) {
        if (Build.VERSION.SDK_INT >= 23) {
            //Icon icon = getNotificationIcon();
            Icon icon = getNotificationIcon();
            //if (icon != null) {
            if (icon != null) {
                //b.setSmallIcon(icon);
                b.setSmallIcon(icon);
                //} else {
            } else {
                //b.setSmallIcon(17301569);
                b.setSmallIcon(17301569);
                //}
            }
            //b.setColor(-16537100);
            b.setColor(-16537100);
            //} else {
        } else {
            //b.setSmallIcon(17301569);
            b.setSmallIcon(17301569);
            //}
        }
        //if (Build.VERSION.SDK_INT >= 26) {
        if (Build.VERSION.SDK_INT >= 26) {
            //boolean z;
            boolean z;
            //String str2;
            String str2;
            //NotificationChannel channel;
            NotificationChannel channel;
            //Application application;
            Application application;
            //NotificationManager notificationManager;
            NotificationManager notificationManager;
            //if (highImportance) {
            if (highImportance) {
                //try {
                try {
                    //z = sNotificationChannelCreatedHighImportance;
                    z = sNotificationChannelCreatedHighImportance;
                    //str2 = APP_CLONER_NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE;
                    str2 = APP_CLONER_NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE;
                    //if (!z) {
                    if (!z) {
                        //channel = new NotificationChannel(str2, APP_CLONER_NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE, 4);
                        channel = new NotificationChannel(str2, APP_CLONER_NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE, 4);
                        //application = getApplication();
                        application = getApplication();
                        //if (application != null) {
                        if (application != null) {
                            //notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                            //if (notificationManager != null) {
                            if (notificationManager != null) {
                                //notificationManager.createNotificationChannel(channel);
                                notificationManager.createNotificationChannel(channel);
                                //sNotificationChannelCreatedHighImportance = true;
                                sNotificationChannelCreatedHighImportance = true;
                                //}
                            }
                            //}
                        }
                        //}
                    }
                    //b.setChannelId(str2);
                    b.setChannelId(str2);
                    //b.setPriority(1);
                    b.setPriority(1);
                    //return;
                    return;
                    //} catch (Throwable t) {
                } catch (Throwable t) {
                    //Log.w(TAG, t);
                    Log.w(TAG, t);
                    //return;
                    return;
                    //}
                }
                //}
            }
            //z = sNotificationChannelCreated;
            z = sNotificationChannelCreated;
            //str2 = APP_CLONER_NOTIFICATION_CHANNEL_ID;
            str2 = APP_CLONER_NOTIFICATION_CHANNEL_ID;
            //if (!z) {
            if (!z) {
                //channel = new NotificationChannel(str2, APP_CLONER_NOTIFICATION_CHANNEL_NAME, 2);
                channel = new NotificationChannel(str2, APP_CLONER_NOTIFICATION_CHANNEL_NAME, 2);
                //application = getApplication();
                application = getApplication();
                //if (application != null) {
                if (application != null) {
                    //notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                    //if (notificationManager != null) {
                    if (notificationManager != null) {
                        //notificationManager.createNotificationChannel(channel);
                        notificationManager.createNotificationChannel(channel);
                        //sNotificationChannelCreated = true;
                        sNotificationChannelCreated = true;
                        //}
                    }
                    //}
                }
                //}
            }
            //b.setChannelId(str2);
            b.setChannelId(str2);
            //} else if (highImportance && Build.VERSION.SDK_INT >= 16) {
        } else if (highImportance && Build.VERSION.SDK_INT >= 16) {
            //b.setPriority(1);
            b.setPriority(1);
            //}
        }
        //}
    }

    //@TargetApi(Build.VERSION_CODES.M)
    @TargetApi(Build.VERSION_CODES.M)
    //private static Icon getNotificationIcon() {
    private static Icon getNotificationIcon() {
        //if (sNotificationIcon == null) {
        if (sNotificationIcon == null) {
            //try {
            try {
                //byte[] data = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAADwAAAAqCAMAAADYrcjGAAAAk1BMVEUAAAD///////////////// //////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////// ///////////////////////ROyVeAAAAMHRSTlMAAmvfPMy3Gm3UbDeM0pCPOHR7em5bSCUD+JhQ Dvu1RdNYrRToQvTsJ90H5glxven9mmFBAAAA40lEQVRIx+3VyQ6CQBBFUVQQZVAQQeZJRkXt//86 TTQRtStVsGLh3Z9d9ytB+DeNZvPFYj4bZ0WJPZLEMXTJXi0Hcnm1Zr3WikyVqqazn3RNpdgNA9ri 1mBgBopNGJso3sF4h2ILxhaK9zDeo9iGsY1iB8YOig8wPqDY9SDrufgr0SGsE56nD2GfgAMIBwQc RnwbhZRvFfNxTPrPCR8ntDVIeTYlTknGwxl1iPJfm5P371h82+JIX8+y+rRVOWR71bpva3XYcjft 27bN0ItxUs5PelZOIw5Od7nebtdL97/40+gORH5wbo1YdEMAAAAASUVORK5CYII= ", 0);
                byte[] data = Base64.decode(llII1II(1113), 0);
                //sNotificationIcon = Icon.createWithBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                sNotificationIcon = Icon.createWithBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                //} catch (Exception e) {
            } catch (Exception e) {
                //Log.w(TAG, e);
                Log.w(TAG, e);
                //}
            }
            //}
        }
        //return sNotificationIcon;
        return sNotificationIcon;
        //}
    }
//}

    static String llII1II(int llII1Il) {
        byte[] llII1I = null;
        try {
            if (llII1Il == -1) {
                if (llII1Il == -2) {
                } else if (llII1Il == -3) {
                } else if (llII1Il == -4) {
                }
            }
            if (llII1Il == 163) {
                llII1I = new byte[]{-30, -45, -56, -13, -47, -52, -41, -58, -64, -41, -52, -47};

                for (int llII1IJ = 0; llII1IJ < llII1I.length; llII1IJ++) {
                    llII1I[llII1IJ] = (byte) (llII1I[llII1IJ] ^ llII1Il);
                }
                {
                    return new String(llII1I, StandardCharsets.UTF_8);
                }
            }
            if (llII1Il == 178) {
                llII1I = new byte[]{-13, -62, -39, -30, -64, -35, -58, -41, -47, -58, -35, -64, -6, -37, -43, -38, -5, -33, -62, -35, -64, -58, -45, -36, -47, -41};

                for (int llII1IJ = 0; llII1IJ < llII1I.length; llII1IJ++) {
                    llII1I[llII1IJ] = (byte) (llII1I[llII1IJ] ^ llII1Il);
                }
                {
                    return new String(llII1I, StandardCharsets.UTF_8);
                }
            }
            if (llII1Il == 193) {
                llII1I = new byte[]{-128, -79, -86, -111, -77, -82, -75, -92, -94, -75, -82, -77};

                for (int llII1IJ = 0; llII1IJ < llII1I.length; llII1IJ++) {
                    llII1I[llII1IJ] = (byte) (llII1I[llII1IJ] ^ llII1Il);
                }
                {
                    return new String(llII1I, StandardCharsets.UTF_8);
                }
            }
            if (llII1Il == 208) {
                llII1I = new byte[]{-111, -96, -69, -128, -94, -65, -92, -75, -77, -92, -65, -94, -16, -104, -71, -73, -72, -16, -103, -67, -96, -65, -94, -92, -79, -66, -77, -75};

                for (int llII1IJ = 0; llII1IJ < llII1I.length; llII1IJ++) {
                    llII1I[llII1IJ] = (byte) (llII1I[llII1IJ] ^ llII1Il);
                }
                {
                    return new String(llII1I, StandardCharsets.UTF_8);
                }
            }
            if (llII1Il == 333) {
                llII1I = new byte[]{44, 35, 41, 63, 34, 36, 41, 99, 44, 61, 61, 99, 12, 46, 57, 36, 59, 36, 57, 52, 25, 37, 63, 40, 44, 41};

                for (int llII1IJ = 0; llII1IJ < llII1I.length; llII1IJ++) {
                    llII1I[llII1IJ] = (byte) (llII1I[llII1IJ] ^ llII1Il);
                }
                {
                    return new String(llII1I, StandardCharsets.UTF_8);
                }
            }
            if (llII1Il == 366) {
                llII1I = new byte[]{13, 27, 28, 28, 11, 0, 26, 47, 30, 30, 2, 7, 13, 15, 26, 7, 1, 0};

                for (int llII1IJ = 0; llII1IJ < llII1I.length; llII1IJ++) {
                    llII1I[llII1IJ] = (byte) (llII1I[llII1IJ] ^ llII1Il);
                }
                {
                    return new String(llII1I, StandardCharsets.UTF_8);
                }
            }
            if (llII1Il == 419) {
                llII1I = new byte[]{-64, -42, -47, -47, -58, -51, -41, -30, -64, -41, -54, -43, -54, -41, -38, -9, -53, -47, -58, -62, -57};

                for (int llII1IJ = 0; llII1IJ < llII1I.length; llII1IJ++) {
                    llII1I[llII1IJ] = (byte) (llII1I[llII1IJ] ^ llII1Il);
                }
                {
                    return new String(llII1I, StandardCharsets.UTF_8);
                }
            }
            if (llII1Il == 476) {
                llII1I = new byte[]{-69, -71, -88, -99, -84, -84, -80, -75, -65, -67, -88, -75, -77, -78};

                for (int llII1IJ = 0; llII1IJ < llII1I.length; llII1IJ++) {
                    llII1I[llII1IJ] = (byte) (llII1I[llII1IJ] ^ llII1Il);
                }
                {
                    return new String(llII1I, StandardCharsets.UTF_8);
                }
            }
            if (llII1Il == 1113) {
                llII1I = new byte[]{48, 15, 27, 22, 11, 46, 105, 18, 30, 62, 54, 24, 24, 24, 24, 23, 10, 12, 49, 28, 12, 62, 24, 24, 24, 29, 46, 24, 24, 24, 24, 40, 26, 24, 20, 24, 24, 24, 29, 0, 43, 58, 51, 30, 24, 24, 24, 24, 50, 104, 27, 20, 15, 28, 12, 24, 24, 24, 29, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 121, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 121, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 121, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 11, 22, 32, 15, 60, 24, 24, 24, 24, 20, 17, 11, 10, 13, 53, 20, 24, 24, 52, 47, 63, 9, 20, 32, 106, 30, 52, 106, 12, 59, 29, 60, 20, 105, 41, 26, 9, 22, 17, 11, 110, 60, 52, 108, 59, 10, 26, 12, 29, 114, 19, 49, 8, 121, 29, 47, 44, 104, 11, 61, 23, 0, 43, 11, 13, 54, 8, 47, 13, 42, 19, 96, 105, 17, 108, 62, 53, 33, 47, 60, 55, 96, 52, 52, 31, 27, 24, 24, 24, 24, 109, 105, 53, 28, 8, 15, 11, 16, 33, 114, 106, 15, 32, 8, 111, 26, 8, 27, 27, 31, 12, 15, 8, 8, 3, 15, 24, 8, 8, 60, 3, 19, 11, 50, 1, 45, 118, 118, 97, 111, 121, 13, 13, 8, 11, 45, 10, 45, 15, 42, 30, 21, 49, 106, 3, 96, 61, 96, 32, 45, 27, 114, 29, 60, 23, 3, 47, 9, 31, 0, 51, 109, 59, 3, 105, 14, 19, 9, 3, 21, 28, 20, 1, 13, 19, 1, 48, 105, 17, 58, 55, 52, 104, 3, 43, 106, 14, 48, 50, 32, 15, 40, 40, 56, 35, 55, 106, 11, 23, 41, 61, 62, 23, 24, 96, 43, 48, 121, 104, 52, 27, 62, 27, 54, 41, 23, 30, 19, 42, 54, 106, 42, 31, 109, 49, 107, 16, 21, 33, 49, 56, 18, 96, 35, 29, 60, 54, 96, 48, 30, 42, 0, 104, 48, 27, 97, 0, 22, 48, 62, 97, 46, 9, 40, 29, 0, 96, 10, 29, 43, 44, 63, 62, 43, 105, 10, 30, 42, 28, 108, 111, 55, 29, 107, 30, 63, 62, 24, 20, 16, 27, 46, 8, 58, 121, 11, 55, 46, 59, 49, 3, 11, 47, 31, 63, 23, 33, 13, 9, 43, 9, 26, 11, 97, 55, 45, 29, 15, 16, 60, 13, 0, 53, 13, 50, 55, 30, 46, 33, 53, 104, 48, 9, 19, 63, 52, 108, 9, 106, 110, 104, 49, 97, 107, 114, 19, 16, 1, 97, 114, 32, 114, 43, 11, 15, 22, 14, 11, 110, 104, 59, 41, 47, 56, 106, 1, 0, 58, 51, 63, 45, 121, 107, 110, 59, 23, 105, 16, 45, 33, 12, 42, 108, 9, 60, 53, 3, 22, 16, 46, 108, 22, 61, 110, 55, 60, 59, 45, 61, 21, 96, 110, 118, 109, 105, 114, 62, 22, 11, 17, 108, 46, 59, 54, 104, 0, 61, 28, 20, 24, 24, 24, 24, 24, 10, 12, 15, 22, 11, 18, 108, 26, 0, 16, 16, 100, 121};

                for (int llII1IJ = 0; llII1IJ < llII1I.length; llII1IJ++) {
                    llII1I[llII1IJ] = (byte) (llII1I[llII1IJ] ^ llII1Il);
                }
                {
                    return new String(llII1I, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/
}//

//
  	