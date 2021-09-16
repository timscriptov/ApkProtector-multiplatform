/* Orginal file: CommonUtils.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.utils*/
package com.mcal.apkprotector.dD;

//import android.app.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

//public class CommonUtils {
public class CommonUtils {
    //public static boolean deleteDirectory(File directoryToBeDeleted) {
    public static boolean deleteDirectory(File directoryToBeDeleted) {
        //File[] allContents = directoryToBeDeleted.listFiles();
        File[] allContents = directoryToBeDeleted.listFiles();
        //if (allContents != null) {
        if (allContents != null) {
            //for (File file : allContents) {
            for (File file : allContents) {
                //deleteDirectory(file);
                deleteDirectory(file);
                //}
            }
            //}
        }
        //return directoryToBeDeleted.delete();
        return directoryToBeDeleted.delete();
        //}
    }

    //

    //@NotNull
    @NotNull
    //public static String encryptStrings(String str, int i) {
    public static String encryptStrings(String str, int i) {
        //try {
        try {
            //StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stringBuilder = new StringBuilder();
            //for (int j = 0; j < str.length(); j++) {
            for (int j = 0; j < str.length(); j++) {
                //stringBuilder.append((char) (str.charAt(j) ^ a(i)[j % a(i).length]));
                stringBuilder.append((char) (str.charAt(j) ^ a(i)[j % a(i).length]));
                //}
            }
            //return stringBuilder.toString();
            return stringBuilder.toString();
            //} catch (Exception ex) {
        } catch (Exception ex) {
            //return "";
            return llIIJ1l(310);
            //}
        }
        //}
    }

    //@NotNull
    @NotNull
    //@Contract(value = "_ -> new", pure = true)
    @Contract(value = "_ -> new", pure = true)
    //private static char[] a(int i) {
    private static char[] a(int i) {
        //switch (i) {
        switch (i) {
            //case 0:
            case 0:
                //return new char[]{'\u925d', '\u325d', '\ue399', '\u8742', '\uf09b', '\u1473', '\u7904', '\u8dea', '\ud6a9', '\ud519', '\u8a82', '\uc5a1'};
                return new char[]{'\u925d', '\u325d', '\ue399', '\u8742', '\uf09b', '\u1473', '\u7904', '\u8dea', '\ud6a9', '\ud519', '\u8a82', '\uc5a1'};
            //case 1:
            case 1:
                //return new char[]{'\u3005', '\u3006'};
                return new char[]{'\u3005', '\u3006'};
            //case 2:
            case 2:
                //return new char[]{'\u6033'};
                return new char[]{'\u6033'};
            //case 3:
            case 3:
                //return new char[]{',', 'w', '\ufffc', '\u00b4', '\uffc8', '\u00b2', 'K', '\u0083', 'r', 'A', '}', '\u008b', '\u0085', '\u00c1', '\f', '\u00a9', 'O', '\u00ac', '\u008b', '8', '\u0011', 'D', ')', 'z', ']', '\u00c6', '\u00e4', 'W', '\u00b3', '\u008e', '\ufff4', '\u00a1', '\uffeb', '0', '\u0086', '\u008f', '\u001f', '\ufff6', '\u00ac', '\u00cb', 'Y', '\uffef', '\u00cb', 't', ' ', '\ufff8', '8', '\u00b7', '\u00d5', 'U', 'o', '\uffdc', 's', '\u001b', '\u0015', '1', '.', 'z', '*', '\u008d', '+', '\\', 'm', '\u000b', '\u0095', '9', '<', 'r', '\u00dc', '\u0090', '\u00b5', '\u00c2', '\u00a9', 'j', '\ufff0'};
                return new char[]{',', 'w', '\ufffc', '\u00b4', '\uffc8', '\u00b2', 'K', '\u0083', 'r', 'A', '}', '\u008b', '\u0085', '\u00c1', '\f', '\u00a9', 'O', '\u00ac', '\u008b', '8', '\u0011', 'D', ')', 'z', ']', '\u00c6', '\u00e4', 'W', '\u00b3', '\u008e', '\ufff4', '\u00a1', '\uffeb', '0', '\u0086', '\u008f', '\u001f', '\ufff6', '\u00ac', '\u00cb', 'Y', '\uffef', '\u00cb', 't', ' ', '\ufff8', '8', '\u00b7', '\u00d5', 'U', 'o', '\uffdc', 's', '\u001b', '\u0015', '1', '.', 'z', '*', '\u008d', '+', '\\', 'm', '\u000b', '\u0095', '9', '<', 'r', '\u00dc', '\u0090', '\u00b5', '\u00c2', '\u00a9', 'j', '\ufff0'};
            //}
        }
        //return new char[]{};
        return new char[]{};
        //}
    }

    //

    //public static void showDialogWarn(@NotNull Context context, String title, String msg) {
    public static void showDialogWarn(@NotNull Context context, String title, String msg) {
        //NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(llIIJ1l(743));
        //new Notification.Builder(context);
        new Notification.Builder(context);
        //Notification.Builder when = new Notification.Builder(context).setContentTitle(title).setContentText(msg).setWhen(System.currentTimeMillis());
        Notification.Builder when = new Notification.Builder(context).setContentTitle(title).setContentText(msg).setWhen(System.currentTimeMillis());
        //NotificationUtils.setSmallNotificationIcon(when, true);
        NotificationUtils.setSmallNotificationIcon(when, true);
        //notificationManager.notify(1621363246, when.getNotification());
        notificationManager.notify(1621363246, when.getNotification());
        //CommonUtils.exit();
        CommonUtils.exit();
        //}
    }

    //

    //public static void exit() {
    public static void exit() {
        //try {
        try {
            //Class main = Class.forName("java.lang.System");
            Class main = Class.forName(llIIJ1l(856));
            //Method method = main.getDeclaredMethod("exit", new Class[]{int.class});
            Method method = main.getDeclaredMethod(llIIJ1l(870), int.class);
            //method.setAccessible(true);
            method.setAccessible(true);
            //method.invoke(new Object(), 0);
            method.invoke(new Object(), 0);
            //} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //}
    }
//}

    static String llIIJ1l(int llIIJ1) {
        byte[] llIIJJ1 = null;
        try {
            if (llIIJ1 == -1) {
                if (llIIJ1 == -2) {
                } else if (llIIJ1 == -3) {
                } else if (llIIJ1 == -4) {
                }
            }
            if (llIIJ1 == 310) {
                llIIJJ1 = new byte[]{};

                for (int llIIJ1I = 0; llIIJ1I < llIIJJ1.length; llIIJ1I++) {
                    llIIJJ1[llIIJ1I] = (byte) (llIIJJ1[llIIJ1I] ^ llIIJ1);
                }
                {
                    return new String(llIIJJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJ1 == 743) {
                llIIJJ1 = new byte[]{-119, -120, -109, -114, -127, -114, -124, -122, -109, -114, -120, -119};

                for (int llIIJ1I = 0; llIIJ1I < llIIJJ1.length; llIIJ1I++) {
                    llIIJJ1[llIIJ1I] = (byte) (llIIJJ1[llIIJ1I] ^ llIIJ1);
                }
                {
                    return new String(llIIJJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJ1 == 856) {
                llIIJJ1 = new byte[]{50, 57, 46, 57, 118, 52, 57, 54, 63, 118, 11, 33, 43, 44, 61, 53};

                for (int llIIJ1I = 0; llIIJ1I < llIIJJ1.length; llIIJ1I++) {
                    llIIJJ1[llIIJ1I] = (byte) (llIIJJ1[llIIJ1I] ^ llIIJ1);
                }
                {
                    return new String(llIIJJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJ1 == 870) {
                llIIJJ1 = new byte[]{3, 30, 15, 18};

                for (int llIIJ1I = 0; llIIJ1I < llIIJJ1.length; llIIJ1I++) {
                    llIIJJ1[llIIJ1I] = (byte) (llIIJJ1[llIIJ1I] ^ llIIJ1);
                }
                {
                    return new String(llIIJJ1, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/
}//

//
  	