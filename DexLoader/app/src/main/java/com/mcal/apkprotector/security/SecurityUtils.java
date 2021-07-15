/* Orginal file: SecurityUtils.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.security*/
package com.mcal.apkprotector.security;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.InstallSourceInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//public class SecurityUtils {
public class SecurityUtils {
    //private static final String TAG = "Package Parser";
    private static final String TAG = llIIIlI(291);

    //private static final String[] metaData = new String[]{"xposedmodule", "xposeddescription"};
    private static final String[] metaData = new String[]{llIIIlI(314), llIIIlI(317)};
    //private static final String[] receivers = new String[]{"com.ui.widgets.AppDisablerWidget", "com.ui.widgets.BinderWidget", "com.ui.widgets.AndroidPatchWidget"};
    private static final String[] receivers = new String[]{llIIIlI(341), llIIIlI(344), llIIIlI(347)};
    //private static final String[] services = new String[]{"com.chelpus.TransferFilesService", "com.chelpus.RootlessInstallService"};
    private static final String[] services = new String[]{llIIIlI(371), llIIIlI(374)};

    //private static final String XPOSED_HELPERS = "de.robv.android.xposed.XposedHelpers";
    private static final String XPOSED_HELPERS = llIIIlI(390);
    //private static final String XPOSED_BRIDGE = "de.robv.android.xposed.XposedBridge";
    private static final String XPOSED_BRIDGE = llIIIlI(405);

    //@Nullable
    @Nullable
    //static String getCurrentSignature(Context context) {
    static String getCurrentSignature(Context context) {
        //byte[] readBuffer = new byte[8192];
        byte[] readBuffer = new byte[8192];
        //Certificate[] certs = null;
        Certificate[] certs = null;
        //try {
        try {
            //JarFile jarFile = new JarFile(context.getApplicationInfo().publicSourceDir);
            JarFile jarFile = new JarFile(context.getApplicationInfo().publicSourceDir);
            //Enumeration entries = jarFile.entries();
            Enumeration entries = jarFile.entries();
            //JarEntry je = (JarEntry) entries.nextElement();
            JarEntry je = (JarEntry) entries.nextElement();
            //while (entries.hasMoreElements()) {
            while (entries.hasMoreElements()) {
                //if (je.isDirectory() || je.getName().startsWith("META-INF/") && je.getName().endsWith(".RSA")) {
                if (je.isDirectory() || je.getName().startsWith(llIIIlI(536)) && je.getName().endsWith(llIIIlI(550))) {
                    //continue;
                    continue;
                    //}
                }
                //Certificate[] localCerts = loadCertificates(jarFile, je, readBuffer);
                Certificate[] localCerts = loadCertificates(jarFile, je, readBuffer);
                //if (certs == null) {
                if (certs == null) {
                    //certs = localCerts;
                    certs = localCerts;
                    //} else {
                } else {
                    //for (Certificate cert : certs) {
                    for (Certificate cert : certs) {
                        //boolean found = false;
                        boolean found = false;
                        //for (Certificate localCert : localCerts) {
                        for (Certificate localCert : localCerts) {
                            //if (cert != null && cert.equals(localCert)) {
                            if (cert != null && cert.equals(localCert)) {
                                //found = true;
                                found = true;
                                //break;
                                break;
                                //}
                            }
                            //}
                        }
                        //if (!found || certs.length != localCerts.length) {
                        if (!found || certs.length != localCerts.length) {
                            //jarFile.close();
                            jarFile.close();

                            //return null;
                            return null;
                            //}
                        }
                        //}
                    }
                    //}
                }
                //jarFile.close();
                jarFile.close();
                //return new String(toChars(certs[0].getEncoded()));
                return new String(toChars(certs[0].getEncoded()));
                //}
            }
            //return null;
            return null;
            //} catch (Exception e) {
        } catch (Exception e) {
            //return null;
            return null;
            //}
        }
        //}
    }

    //@Nullable
    @Nullable
    //private static Certificate[] loadCertificates(@NotNull JarFile jarFile, JarEntry je, byte[] readBuffer) {
    private static Certificate[] loadCertificates(@NotNull JarFile jarFile, JarEntry je, byte[] readBuffer) {
        //try {
        try {
            //InputStream is = jarFile.getInputStream(je);
            InputStream is = jarFile.getInputStream(je);
            //while (is.read(readBuffer, 0, readBuffer.length) != -1) {
            while (is.read(readBuffer, 0, readBuffer.length) != -1) {
                //}
            }
            //is.close();
            is.close();
            //return je != null ? je.getCertificates() : null;
            return je != null ? je.getCertificates() : null;
            //} catch (IOException a) {
        } catch (IOException a) {
            //a.printStackTrace();
            a.printStackTrace();
            //return null;
            return null;
            //}
        }
        //}
    }

    //@NotNull
    @NotNull
    //@Contract(pure = true)
    @Contract(pure = true)
    //private static char[] toChars(@NotNull byte[] mSignature) {
    private static char[] toChars(@NotNull byte[] mSignature) {
        //final int N;
        final int N;
        //N = mSignature.length;
        N = mSignature.length;
        //final int N2 = N * 2;
        final int N2 = N * 2;
        //char[] text = new char[N2];
        char[] text = new char[N2];

        //for (int j = 0; j < N; j++) {
        for (int j = 0; j < N; j++) {
            //byte v = mSignature[j];
            byte v = mSignature[j];
            //int d = (v >> 4) & 0xf;
            int d = (v >> 4) & 0xf;
            //text[j * 2] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
            text[j * 2] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
            //d = v & 0xf;
            d = v & 0xf;
            //text[j * 2 + 1] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
            text[j * 2 + 1] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
            //}
        }
        //return text;
        return text;
        //}
    }

    //

    //public static boolean isRooted() {
    public static boolean isRooted() {
        //try {
        try {
            //String str = Build.TAGS;
            String str = Build.TAGS;
            //if ((str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            if ((str != null && str.contains(llIIIlI(1219))) || new File(llIIIlI(1230)).exists()) {
                //return true;
                return true;
                //}
            }
            //return new File("/system/xbin/su").exists();
            return new File(llIIIlI(1253)).exists();
            //} catch (Exception e) {
        } catch (Exception e) {
            //e.printStackTrace();
            e.printStackTrace();
            //return false;
            return false;
            //}
        }
        //}
    }

    //public static boolean isLucky(Context context) {
    public static boolean isLucky(Context context) {
        //try {
        try {
            //int flags = PackageManager.GET_RECEIVERS | PackageManager.GET_SERVICES;
            int flags = PackageManager.GET_RECEIVERS | PackageManager.GET_SERVICES;

            //@SuppressLint("QueryPermissionsNeeded") List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(flags);
            @SuppressLint("QueryPermissionsNeeded") List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(flags);

            //for (PackageInfo packInfo : packages) {
            for (PackageInfo packInfo : packages) {
                //Log.d(TAG, "start parsing " + packInfo.packageName);
                Log.d(TAG, llIIIlI(1372) + packInfo.packageName);
                //if (isXposedModule(context.getPackageManager().getApplicationInfo(packInfo.packageName, PackageManager.GET_META_DATA))
                if (isXposedModule(context.getPackageManager().getApplicationInfo(packInfo.packageName, PackageManager.GET_META_DATA))
                        //|| detectServices(packInfo) || detectReceivers(packInfo)) {
                        || detectServices(packInfo) || detectReceivers(packInfo)) {
                    //return true;
                    return true;
                    //}
                }
                //}
            }
            //} catch (Exception e) {
        } catch (Exception e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return false;
        return false;
        //}
    }

    //public static boolean isXposedModule(ApplicationInfo appInfo) {
    public static boolean isXposedModule(ApplicationInfo appInfo) {
        //Bundle bundle = appInfo.metaData;
        Bundle bundle = appInfo.metaData;

        //try {
        try {
            //if (bundle.getBoolean(metaData[0])) {
            if (bundle.getBoolean(metaData[0])) {
                //Log.d(TAG, "Xposed level: Package is xposed module");
                Log.d(TAG, llIIIlI(1515));
                //String moduleDesc = bundle.getString(metaData[1]);
                String moduleDesc = bundle.getString(metaData[1]);
                //if (moduleDesc != null && moduleDesc.contains("Lucky") && moduleDesc.contains("Patcher")) {
                if (moduleDesc != null && moduleDesc.contains(llIIIlI(1553)) && moduleDesc.contains(llIIIlI(1563))) {
                    //Log.d(TAG, "Xposed level: Package is lucky patcher");
                    Log.d(TAG, llIIIlI(1576));
                    //return true;
                    return true;
                    //}
                }
                //}
            }
            //} catch (NullPointerException e) {
        } catch (NullPointerException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return false;
        return false;
        //}
    }

    //public static boolean detectServices(PackageInfo packInfo) {
    public static boolean detectServices(PackageInfo packInfo) {
        //ServiceInfo[] serviceInfoArr = packInfo.services;
        ServiceInfo[] serviceInfoArr = packInfo.services;

        //try {
        try {
            //for (ServiceInfo serviceInfo : serviceInfoArr) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                //for (String serviceName : services) {
                for (String serviceName : services) {
                    //if (serviceInfo.name.equals(serviceName)) {
                    if (serviceInfo.name.equals(serviceName)) {
                        //Log.d(TAG, "Service check level: Package is lucky patcher");
                        Log.d(TAG, llIIIlI(1699));
                        //return true;
                        return true;
                        //}
                    }
                    //}
                }
                //}
            }
            //} catch (NullPointerException e) {
        } catch (NullPointerException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return false;
        return false;
        //}
    }

    //public static boolean detectReceivers(PackageInfo packInfo) {
    public static boolean detectReceivers(PackageInfo packInfo) {
        //ActivityInfo[] receiverArr = packInfo.receivers;
        ActivityInfo[] receiverArr = packInfo.receivers;

        //try {
        try {
            //for (ActivityInfo receiver : receiverArr) {
            for (ActivityInfo receiver : receiverArr) {
                //for (String receiverName : receivers) {
                for (String receiverName : receivers) {
                    //Log.d(TAG, receiver.name);
                    Log.d(TAG, receiver.name);
                    //if (receiver.name.equals(receiverName)) {
                    if (receiver.name.equals(receiverName)) {
                        //Log.d(TAG, "Receiver check level: Package is lucky patcher");
                        Log.d(TAG, llIIIlI(1837));
                        //return true;
                        return true;
                        //}
                    }
                    //}
                }
                //}
            }
            //} catch (NullPointerException e) {
        } catch (NullPointerException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return false;
        return false;
        //}
    }

    //

    //public static boolean isMagisk() {
    public static boolean isMagisk() {
        //try {
        try {
            //return new File("/system/xbin/su").exists() ||
            return new File(llIIIl1(1903)).exists() ||
                    //new File("/sbin/su").exists() ||
                    new File(llIIIl1(1917)).exists() ||
                    //new File("/sbin/su").exists() ||
                    new File(llIIIl1(1931)).exists() ||
                    //new File("/sbin/magisk").exists() ||
                    new File(llIIIl1(1945)).exists() ||
                    //new File("/sbin/magisk.bin").exists() ||
                    new File(llIIIl1(1959)).exists() ||
                    //new File("/sbin/magiskhide").exists() ||
                    new File(llIIIl1(1973)).exists() ||
                    //new File("/sbin/magiskinit").exists() ||
                    new File(llIIIl1(1987)).exists() ||
                    //new File("/sbin/magiskpolicy").exists() ||
                    new File(llIIIl1(2001)).exists() ||
                    //new File("/system/addon.d/blacklist").exists() ||
                    new File(llIIIl1(2015)).exists() ||
                    //new File("/system/addon.d/99-magisk.sh").exists() ||
                    new File(llIIIl1(2029)).exists() ||
                    //new File("/cache/magisk.log").exists();
                    new File(llIIIl1(2043)).exists();
            //} catch (Exception e) {
        } catch (Exception e) {
            //e.printStackTrace();
            e.printStackTrace();
            //return false;
            return false;
            //}
        }
        //}
    }

    //

    //public static boolean isInstalledViaGooglePlay(Context ctx) {
    public static boolean isInstalledViaGooglePlay(Context ctx) {
        //return isInstalledVia(ctx, "com.android.vending");
        return isInstalledVia(ctx, llIIIl1(2102));
        //}
    }

    //public static boolean isInstalledVia(Context ctx, String required) {
    public static boolean isInstalledVia(Context ctx, String required) {
        //String installer = getInstallerPackageName(ctx);
        String installer = getInstallerPackageName(ctx);
        //return required.equals(installer);
        return required.equals(installer);
        //}
    }

    //private static String getInstallerPackageName(Context ctx) {
    private static String getInstallerPackageName(Context ctx) {
        //try {
        try {
            //String packageName = ctx.getPackageName();
            String packageName = ctx.getPackageName();
            //PackageManager pm = ctx.getPackageManager();
            PackageManager pm = ctx.getPackageManager();
            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                //InstallSourceInfo info = pm.getInstallSourceInfo(packageName);
                InstallSourceInfo info = pm.getInstallSourceInfo(packageName);
                //if (info != null) {
                if (info != null) {
                    //return info.getInstallingPackageName();
                    return info.getInstallingPackageName();
                    //}
                }
                //}
            }
            //return pm.getInstallerPackageName(packageName);
            return pm.getInstallerPackageName(packageName);
            //} catch (PackageManager.NameNotFoundException e) {
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return "";
        return llIIIl1(2293);
        //}
    }

    //

    //public static boolean isEmulator() {
    public static boolean isEmulator() {
        //return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
        return (Build.BRAND.startsWith(llIIIl1(2320)) && Build.DEVICE.startsWith(llIIIl1(2332)))
                //|| Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith(llIIIl1(2345))
                //|| Build.FINGERPRINT.startsWith("unknown")
                || Build.FINGERPRINT.startsWith(llIIIl1(2357))
                //|| Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains(llIIIl1(2369))
                //|| Build.HARDWARE.contains("ranchu")
                || Build.HARDWARE.contains(llIIIl1(2381))
                //|| Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains(llIIIl1(2393))
                //|| Build.MODEL.contains("Emulator")
                || Build.MODEL.contains(llIIIl1(2405))
                //|| Build.MODEL.contains("Android SDK built for x86")
                || Build.MODEL.contains(llIIIl1(2417))
                //|| Build.MANUFACTURER.contains("Genymotion")
                || Build.MANUFACTURER.contains(llIIIl1(2429))
                //|| Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains(llIIIl1(2441))
                //|| Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains(llIIIl1(2453))
                //|| Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains(llIIIl1(2465))
                //|| Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains(llIIIl1(2477))
                //|| Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains(llIIIl1(2489))
                //|| Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains(llIIIl1(2501))
                //|| Build.PRODUCT.contains("simulator");
                || Build.PRODUCT.contains(llIIIl1(2513));
        //}
    }

    //

    //public static boolean isDebuggable(@NotNull Context context) {
    public static boolean isDebuggable(@NotNull Context context) {
        //return ((context.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
        return ((context.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
        //}
    }

    //

    //public static boolean detectDebugger() {
    public static boolean detectDebugger() {
        //return Debug.isDebuggerConnected();
        return Debug.isDebuggerConnected();
        //}
    }

    //

    //public static boolean isVpn() {
    public static boolean isVpn() {
        //try {
        try {
            //String iface = "";
            String iface = llIIIl1(2616);
            //for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                //if (networkInterface.isUp())
                if (networkInterface.isUp())
                    //iface = networkInterface.getName();
                    iface = networkInterface.getName();
                //if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                if (iface.contains(llIIIl1(2670)) || iface.contains(llIIIl1(2680)) || iface.contains(llIIIl1(2690))) {
                    //return true;
                    return true;
                    //}
                }
                //}
            }
            //} catch (Exception e) {
        } catch (Exception e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return false;
        return false;
        //}
    }

    //

    //public static boolean illegalCodeCheck(String classPath) throws ClassNotFoundException {
    public static boolean illegalCodeCheck(String classPath) throws ClassNotFoundException {
        //return Class.forName(classPath) != null;
        return Class.forName(classPath) != null;
        //}
    }

    //

    //@SuppressLint("WrongConstant")
    @SuppressLint("WrongConstant")
    //public static boolean isInstallPirateApp(@NotNull Context context, String packageName) {
    public static boolean isInstallPirateApp(@NotNull Context context, String packageName) {
        //try {
        try {
            //context.getPackageManager().getPackageInfo(packageName, 1);
            context.getPackageManager().getPackageInfo(packageName, 1);
            //return true;
            return true;
            //} catch (PackageManager.NameNotFoundException e) {
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //return false;
            return false;
            //}
        }
        //}
    }

    //public static boolean checkXposedExistAndDisableIt() {
    public static boolean checkXposedExistAndDisableIt() {
        //return SecurityUtils.getSingleInstance().tryShutdownXposed();
        return SecurityUtils.getSingleInstance().tryShutdownXposed();
        //}
    }

    //@Contract(pure = true)
    @Contract(pure = true)
    //private static SecurityUtils getSingleInstance() {
    private static SecurityUtils getSingleInstance() {
        //return SecurityUtils.SingletonHolder.singleInstance;
        return SecurityUtils.SingletonHolder.singleInstance;
        //}
    }

    //

    static String llIIIlI(int llIIIll) {
        byte[] llIIIl = null;
        try {
            if (llIIIll == -1) {
                if (llIIIll == -2) {
                } else if (llIIIll == -3) {
                } else if (llIIIll == -4) {
                }
            }
            if (llIIIll == 291) {
                llIIIl = new byte[]{115, 66, 64, 72, 66, 68, 70, 3, 115, 66, 81, 80, 70, 81};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 314) {
                llIIIl = new byte[]{66, 74, 85, 73, 95, 94, 87, 85, 94, 79, 86, 95};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 317) {
                llIIIl = new byte[]{69, 77, 82, 78, 88, 89, 89, 88, 78, 94, 79, 84, 77, 73, 84, 82, 83};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 341) {
                llIIIl = new byte[]{54, 58, 56, 123, 32, 60, 123, 34, 60, 49, 50, 48, 33, 38, 123, 20, 37, 37, 17, 60, 38, 52, 55, 57, 48, 39, 2, 60, 49, 50, 48, 33};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 344) {
                llIIIl = new byte[]{59, 55, 53, 118, 45, 49, 118, 47, 49, 60, 63, 61, 44, 43, 118, 26, 49, 54, 60, 61, 42, 15, 49, 60, 63, 61, 44};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 347) {
                llIIIl = new byte[]{56, 52, 54, 117, 46, 50, 117, 44, 50, 63, 60, 62, 47, 40, 117, 26, 53, 63, 41, 52, 50, 63, 11, 58, 47, 56, 51, 12, 50, 63, 60, 62, 47};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 371) {
                llIIIl = new byte[]{16, 28, 30, 93, 16, 27, 22, 31, 3, 6, 0, 93, 39, 1, 18, 29, 0, 21, 22, 1, 53, 26, 31, 22, 0, 32, 22, 1, 5, 26, 16, 22};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 374) {
                llIIIl = new byte[]{21, 25, 27, 88, 21, 30, 19, 26, 6, 3, 5, 88, 36, 25, 25, 2, 26, 19, 5, 5, 63, 24, 5, 2, 23, 26, 26, 37, 19, 4, 0, 31, 21, 19};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 390) {
                llIIIl = new byte[]{-30, -29, -88, -12, -23, -28, -16, -88, -25, -24, -30, -12, -23, -17, -30, -88, -2, -10, -23, -11, -29, -30, -88, -34, -10, -23, -11, -29, -30, -50, -29, -22, -10, -29, -12, -11};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 405) {
                llIIIl = new byte[]{-15, -16, -69, -25, -6, -9, -29, -69, -12, -5, -15, -25, -6, -4, -15, -69, -19, -27, -6, -26, -16, -15, -69, -51, -27, -6, -26, -16, -15, -41, -25, -4, -15, -14, -16};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 536) {
                llIIIl = new byte[]{85, 93, 76, 89, 53, 81, 86, 94, 55};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 550) {
                llIIIl = new byte[]{8, 116, 117, 103};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1219) {
                llIIIl = new byte[]{-73, -90, -80, -73, -18, -88, -90, -70, -80};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1230) {
                llIIIl = new byte[]{-31, -67, -73, -67, -70, -85, -93, -31, -81, -66, -66, -31, -99, -69, -66, -85, -68, -69, -67, -85, -68, -32, -81, -66, -91};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1253) {
                llIIIl = new byte[]{-54, -106, -100, -106, -111, -128, -120, -54, -99, -121, -116, -117, -54, -106, -112};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1372) {
                llIIIl = new byte[]{47, 40, 61, 46, 40, 124, 44, 61, 46, 47, 53, 50, 59, 124};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1515) {
                llIIIl = new byte[]{-77, -101, -124, -104, -114, -113, -53, -121, -114, -99, -114, -121, -47, -53, -69, -118, -120, -128, -118, -116, -114, -53, -126, -104, -53, -109, -101, -124, -104, -114, -113, -53, -122, -124, -113, -98, -121, -114};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1553) {
                llIIIl = new byte[]{93, 100, 114, 122, 104};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1563) {
                llIIIl = new byte[]{75, 122, 111, 120, 115, 126, 105};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1576) {
                llIIIl = new byte[]{112, 88, 71, 91, 77, 76, 8, 68, 77, 94, 77, 68, 18, 8, 120, 73, 75, 67, 73, 79, 77, 8, 65, 91, 8, 68, 93, 75, 67, 81, 8, 88, 73, 92, 75, 64, 77, 90};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1699) {
                llIIIl = new byte[]{-16, -58, -47, -43, -54, -64, -58, -125, -64, -53, -58, -64, -56, -125, -49, -58, -43, -58, -49, -103, -125, -13, -62, -64, -56, -62, -60, -58, -125, -54, -48, -125, -49, -42, -64, -56, -38, -125, -45, -62, -41, -64, -53, -58, -47};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1837) {
                llIIIl = new byte[]{127, 72, 78, 72, 68, 91, 72, 95, 13, 78, 69, 72, 78, 70, 13, 65, 72, 91, 72, 65, 23, 13, 125, 76, 78, 70, 76, 74, 72, 13, 68, 94, 13, 65, 88, 78, 70, 84, 13, 93, 76, 89, 78, 69, 72, 95};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    //

    static String llIIIl1(int llIIIll) {
        byte[] llIIIl = null;
        try {
            if (llIIIll == 1903) {
                llIIIl = new byte[]{64, 28, 22, 28, 27, 10, 2, 64, 23, 13, 6, 1, 64, 28, 26};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1917) {
                llIIIl = new byte[]{82, 14, 31, 20, 19, 82, 14, 8};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1931) {
                llIIIl = new byte[]{-92, -8, -23, -30, -27, -92, -8, -2};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1945) {
                llIIIl = new byte[]{-74, -22, -5, -16, -9, -74, -12, -8, -2, -16, -22, -14};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1959) {
                llIIIl = new byte[]{-120, -44, -59, -50, -55, -120, -54, -58, -64, -50, -44, -52, -119, -59, -50, -55};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1973) {
                llIIIl = new byte[]{-102, -58, -41, -36, -37, -102, -40, -44, -46, -36, -58, -34, -35, -36, -47, -48};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 1987) {
                llIIIl = new byte[]{-20, -80, -95, -86, -83, -20, -82, -94, -92, -86, -80, -88, -86, -83, -86, -73};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2001) {
                llIIIl = new byte[]{-2, -94, -77, -72, -65, -2, -68, -80, -74, -72, -94, -70, -95, -66, -67, -72, -78, -88};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2015) {
                llIIIl = new byte[]{-16, -84, -90, -84, -85, -70, -78, -16, -66, -69, -69, -80, -79, -15, -69, -16, -67, -77, -66, -68, -76, -77, -74, -84, -85};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2029) {
                llIIIl = new byte[]{-62, -98, -108, -98, -103, -120, -128, -62, -116, -119, -119, -126, -125, -61, -119, -62, -44, -44, -64, -128, -116, -118, -124, -98, -122, -61, -98, -123};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2043) {
                llIIIl = new byte[]{-44, -104, -102, -104, -109, -98, -44, -106, -102, -100, -110, -120, -112, -43, -105, -108, -100};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2102) {
                llIIIl = new byte[]{85, 89, 91, 24, 87, 88, 82, 68, 89, 95, 82, 24, 64, 83, 88, 82, 95, 88, 81};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2293) {
                llIIIl = new byte[]{};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2320) {
                llIIIl = new byte[]{119, 117, 126, 117, 98, 121, 115};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2332) {
                llIIIl = new byte[]{123, 121, 114, 121, 110, 117, 127};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2345) {
                llIIIl = new byte[]{78, 76, 71, 76, 91, 64, 74};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2357) {
                llIIIl = new byte[]{64, 91, 94, 91, 90, 66, 91};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2369) {
                llIIIl = new byte[]{38, 46, 45, 37, 39, 40, 50, 41};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2381) {
                llIIIl = new byte[]{63, 44, 35, 46, 37, 56};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2393) {
                llIIIl = new byte[]{62, 54, 54, 62, 53, 60, 6, 42, 61, 50};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2405) {
                llIIIl = new byte[]{32, 8, 16, 9, 4, 17, 10, 23};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2417) {
                llIIIl = new byte[]{48, 31, 21, 3, 30, 24, 21, 81, 34, 53, 58, 81, 19, 4, 24, 29, 5, 81, 23, 30, 3, 81, 9, 73, 71};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2429) {
                llIIIl = new byte[]{58, 24, 19, 4, 16, 18, 9, 20, 18, 19};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2441) {
                llIIIl = new byte[]{-6, -19, -30, -42, -18, -26, -26, -18, -27, -20};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2453) {
                llIIIl = new byte[]{-14, -6, -6, -14, -7, -16, -54, -26, -15, -2};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2465) {
                llIIIl = new byte[]{-46, -59, -54};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2477) {
                llIIIl = new byte[]{-34, -55, -58, -14, -43, -107, -101};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2489) {
                llIIIl = new byte[]{-49, -37, -42, -63, -127, -113, -55};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2501) {
                llIIIl = new byte[]{-96, -88, -80, -87, -92, -79, -86, -73};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2513) {
                llIIIl = new byte[]{-94, -72, -68, -92, -67, -80, -91, -66, -93};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2616) {
                llIIIl = new byte[]{};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2670) {
                llIIIl = new byte[]{26, 27, 0};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2680) {
                llIIIl = new byte[]{8, 8, 8};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 2690) {
                llIIIl = new byte[]{-14, -14, -10, -14};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
            if (llIIIll == 3124) {
                llIIIl = new byte[]{80, 93, 71, 85, 86, 88, 81, 124, 91, 91, 95, 71};

                for (int llIIIlJ = 0; llIIIlJ < llIIIl.length; llIIIlJ++) {
                    llIIIl[llIIIlJ] = (byte) (llIIIl[llIIIlJ] ^ llIIIll);
                }
                {
                    return new String(llIIIl, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/

    //@Deprecated
    @Deprecated
    //public boolean isXposedExists() {
    public boolean isXposedExists() {
        //try {
        try {
            //ClassLoader
            ClassLoader
                    //.getSystemClassLoader()
                    .getSystemClassLoader()
                    //.loadClass(XPOSED_HELPERS)
                    .loadClass(XPOSED_HELPERS)
                    //.newInstance();
                    .newInstance();
            //} catch (InstantiationException | IllegalAccessException e) {
        } catch (InstantiationException | IllegalAccessException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //return true;
            return true;
            //} catch (ClassNotFoundException e) {
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //return false;
            return false;
            //}
        }

        //try {
        try {
            //ClassLoader
            ClassLoader
                    //.getSystemClassLoader()
                    .getSystemClassLoader()
                    //.loadClass(XPOSED_BRIDGE)
                    .loadClass(XPOSED_BRIDGE)
                    //.newInstance();
                    .newInstance();
            //} catch (InstantiationException | IllegalAccessException e) {
        } catch (InstantiationException | IllegalAccessException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //return true;
            return true;
            //} catch (ClassNotFoundException e) {
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //return false;
            return false;
            //}
        }
        //return true;
        return true;
        //}
    }
//}

    //private boolean tryShutdownXposed() {
    private boolean tryShutdownXposed() {
        //Field xpdisabledHooks;
        Field xpdisabledHooks;
        //try {
        try {
            //xpdisabledHooks = ClassLoader.getSystemClassLoader()
            xpdisabledHooks = ClassLoader.getSystemClassLoader()
                    //.loadClass(XPOSED_BRIDGE)
                    .loadClass(XPOSED_BRIDGE)
                    //.getDeclaredField("disableHooks");
                    .getDeclaredField(llIIIl1(3124));
            //xpdisabledHooks.setAccessible(true);
            xpdisabledHooks.setAccessible(true);
            //xpdisabledHooks.set(null, Boolean.TRUE);
            xpdisabledHooks.set(null, Boolean.TRUE);
            //return true;
            return true;
            //} catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //return false;
            return false;
            //}
        }
        //}
    }

    //private static class SingletonHolder {
    private static class SingletonHolder {
        //private static final SecurityUtils singleInstance = new SecurityUtils();
        private static final SecurityUtils singleInstance = new SecurityUtils();
        //}
    }
}//

//
  	