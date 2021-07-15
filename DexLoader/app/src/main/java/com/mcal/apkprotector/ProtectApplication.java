/* Orginal file: ProtectApplication.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector*/
package com.mcal.apkprotector;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.mcal.apkprotector.data.Const;
import com.mcal.apkprotector.multidex.MultiDex;
import com.mcal.apkprotector.security.Security;
import com.mcal.apkprotector.utils.Reflect;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

//public class ProtectApplication extends Application {
public class ProtectApplication extends Application {

    static String llIIl1(int llIIlJ1) {
        byte[] llIIlJJ = null;
        try {
            if (llIIlJ1 == -1) {
                if (llIIlJ1 == -2) {
                } else if (llIIlJ1 == -3) {
                } else if (llIIlJ1 == -4) {
                }
            }
            if (llIIlJ1 == 247) {
                llIIlJJ = new byte[]{-106, -103, -109, -123, -104, -98, -109, -39, -106, -121, -121, -39, -74, -108, -125, -98, -127, -98, -125, -114, -93, -97, -123, -110, -106, -109};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 253) {
                llIIlJJ = new byte[]{-98, -120, -113, -113, -104, -109, -119, -68, -98, -119, -108, -117, -108, -119, -124, -87, -107, -113, -104, -100, -103};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 280) {
                llIIlJJ = new byte[]{121, 118, 124, 106, 119, 113, 124, 54, 121, 104, 104, 54, 89, 123, 108, 113, 110, 113, 108, 97, 76, 112, 106, 125, 121, 124};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 286) {
                llIIlJJ = new byte[]{115, 92, 113, 107, 112, 122, 95, 110, 110, 114, 119, 125, 127, 106, 119, 113, 112};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 301) {
                llIIlJJ = new byte[]{76, 67, 73, 95, 66, 68, 73, 3, 76, 93, 93, 3, 108, 78, 89, 68, 91, 68, 89, 84, 121, 69, 95, 72, 76, 73, 9, 108, 93, 93, 111, 68, 67, 73, 105, 76, 89, 76};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 307) {
                llIIlJJ = new byte[]{90, 93, 85, 92};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 316) {
                llIIlJJ = new byte[]{93, 82, 88, 78, 83, 85, 88, 18, 93, 76, 76, 18, 112, 83, 93, 88, 89, 88, 125, 76, 87};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 322) {
                llIIlJJ = new byte[]{47, 3, 50, 50, 46, 43, 33, 35, 54, 43, 45, 44};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 340) {
                llIIlJJ = new byte[]{53, 58, 48, 38, 59, 61, 48, 122, 53, 36, 36, 122, 21, 55, 32, 61, 34, 61, 32, 45, 0, 60, 38, 49, 53, 48};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 346) {
                llIIlJJ = new byte[]{55, 19, 52, 51, 46, 51, 59, 54, 27, 42, 42, 54, 51, 57, 59, 46, 51, 53, 52};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 372) {
                llIIlJJ = new byte[]{21, 26, 16, 6, 27, 29, 16, 90, 21, 4, 4, 90, 53, 23, 0, 29, 2, 29, 0, 13, 32, 28, 6, 17, 21, 16};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 378) {
                llIIlJJ = new byte[]{23, 59, 22, 22, 59, 10, 10, 22, 19, 25, 27, 14, 19, 21, 20, 9};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 405) {
                llIIlJJ = new byte[]{-12, -5, -15, -25, -6, -4, -15, -69, -12, -27, -27, -69, -39, -6, -12, -15, -16, -15, -44, -27, -2};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 411) {
                llIIlJJ = new byte[]{-10, -38, -21, -21, -9, -14, -8, -6, -17, -14, -12, -11, -46, -11, -3, -12};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 430) {
                llIIlJJ = new byte[]{-49, -64, -54, -36, -63, -57, -54, -128, -49, -34, -34, -128, -17, -51, -38, -57, -40, -57, -38, -41, -6, -58, -36, -53, -49, -54, -118, -17, -34, -34, -20, -57, -64, -54, -22, -49, -38, -49};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 436) {
                llIIlJJ = new byte[]{-43, -60, -60, -3, -38, -46, -37};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 473) {
                llIIlJJ = new byte[]{-72, -73, -67, -85, -74, -80, -67, -9, -72, -87, -87, -9, -107, -74, -72, -67, -68, -67, -104, -87, -78};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 479) {
                llIIlJJ = new byte[]{-78, -66, -76, -70, -98, -81, -81, -77, -74, -68, -66, -85, -74, -80, -79};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 510) {
                llIIlJJ = new byte[]{-97, -112, -102, -116, -111, -105, -102, -48, -97, -114, -114, -48, -65, -99, -118, -105, -120, -105, -118, -121, -86, -106, -116, -101, -97, -102};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ1 == 516) {
                llIIlJJ = new byte[]{105, 77, 106, 109, 112, 109, 101, 104, 69, 116, 116, 104, 109, 103, 101, 112, 109, 107, 106};

                for (int llIIl1l = 0; llIIl1l < llIIlJJ.length; llIIl1l++) {
                    llIIlJJ[llIIl1l] = (byte) (llIIlJJ[llIIl1l] ^ llIIlJ1);
                }
                {
                    return new String(llIIlJJ, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/

    //@Override
    @Override
    //protected void attachBaseContext(Context base) {
    protected void attachBaseContext(Context base) {
        //super.attachBaseContext(base);
        super.attachBaseContext(base);
        //MultiDex.install(this);
        MultiDex.install(this);
        //}
    }

    //@Override
    @Override
    //public void onCreate() {
    public void onCreate() {
        //super.onCreate();
        super.onCreate();
        //new Security(this, Const.DATA);
        new Security(this, Const.DATA);
        //Application app = changeTopApplication(Const.REAL_APPLICATION);
        Application app = changeTopApplication(Const.REAL_APPLICATION);
        //if (app != null) {
        if (app != null) {
            //app.onCreate();
            app.onCreate();
            //}
        }
        //}
    }
//}

    //private Application changeTopApplication(String appClassName) {
    private Application changeTopApplication(String appClassName) {
        //

        //Object currentActivityThread = Reflect.invokeMethod("android.app.ActivityThread", null, "currentActivityThread", new Object[]{}, null);
        Object currentActivityThread = Reflect.invokeMethod(llIIl1(247), null, llIIl1(253), new Object[]{}, null);
        //Object mBoundApplication = Reflect.getFieldValue(
        Object mBoundApplication = Reflect.getFieldValue(
                //"android.app.ActivityThread", currentActivityThread,
                llIIl1(280), currentActivityThread,
                //"mBoundApplication");
                llIIl1(286));
        //Object loadedApkInfo = Reflect.getFieldValue(
        Object loadedApkInfo = Reflect.getFieldValue(
                //"android.app.ActivityThread$AppBindData",
                llIIl1(301),
                //mBoundApplication, "info");
                mBoundApplication, llIIl1(307));
        //

        //Reflect.setFieldValue("android.app.LoadedApk", loadedApkInfo, "mApplication", null);
        Reflect.setFieldValue(llIIl1(316), loadedApkInfo, llIIl1(322), null);
        //Object oldApplication = Reflect.getFieldValue(
        Object oldApplication = Reflect.getFieldValue(
                //"android.app.ActivityThread", currentActivityThread,
                llIIl1(340), currentActivityThread,
                //"mInitialApplication");
                llIIl1(346));
        //

        //ArrayList<Application> mAllApplications = (ArrayList<Application>) Reflect
        ArrayList<Application> mAllApplications = (ArrayList<Application>) Reflect
                //.getFieldValue("android.app.ActivityThread",
                .getFieldValue(llIIl1(372),
                        //currentActivityThread, "mAllApplications");
                        currentActivityThread, llIIl1(378));
        //mAllApplications.remove(oldApplication);
        mAllApplications.remove(oldApplication);

        //ApplicationInfo loadedApk = (ApplicationInfo) Reflect
        ApplicationInfo loadedApk = (ApplicationInfo) Reflect
                //.getFieldValue("android.app.LoadedApk", loadedApkInfo,
                .getFieldValue(llIIl1(405), loadedApkInfo,
                        //"mApplicationInfo");
                        llIIl1(411));
        //ApplicationInfo appBindData = (ApplicationInfo) Reflect
        ApplicationInfo appBindData = (ApplicationInfo) Reflect
                //.getFieldValue("android.app.ActivityThread$AppBindData",
                .getFieldValue(llIIl1(430),
                        //mBoundApplication, "appInfo");
                        mBoundApplication, llIIl1(436));

        //loadedApk.className = appClassName;
        loadedApk.className = appClassName;
        //appBindData.className = appClassName;
        appBindData.className = appClassName;

        //Application app = (Application) Reflect.invokeMethod(
        Application app = (Application) Reflect.invokeMethod(
                //"android.app.LoadedApk", loadedApkInfo, "makeApplication",
                llIIl1(473), loadedApkInfo, llIIl1(479),
                //new Object[]{false, null},
                new Object[]{false, null},
                //boolean.class, Instrumentation.class);
                boolean.class, Instrumentation.class);

        //Reflect.setFieldValue("android.app.ActivityThread", currentActivityThread, "mInitialApplication", app);
        Reflect.setFieldValue(llIIl1(510), currentActivityThread, llIIl1(516), app);

        //return app;
        return app;
        //}
    }
}//

//
  	