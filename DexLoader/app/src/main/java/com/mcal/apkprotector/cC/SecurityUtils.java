/* Orginal file: SecurityUtils.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.security*/
package com.mcal.apkprotector.cC;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.InstallSourceInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
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
    private static final String TAG = llIIJJl(302);

    //private static final String[] metaData = new String[]{"xposedmodule", "xposeddescription"};
    private static final String[] metaData = new String[]{llIIJJl(325), llIIJJl(328)};
    //private static final String[] receivers = new String[]{"com.ui.widgets.AppDisablerWidget", "com.ui.widgets.BinderWidget", "com.ui.widgets.AndroidPatchWidget"};
    private static final String[] receivers = new String[]{llIIJJl(352), llIIJJl(355), llIIJJl(358)};
    //private static final String[] services = new String[]{"com.chelpus.TransferFilesService", "com.chelpus.RootlessInstallService"};
    private static final String[] services = new String[]{llIIJJl(382), llIIJJl(385)};

    //private static final String XPOSED_HELPERS = "de.robv.android.xposed.XposedHelpers";
    private static final String XPOSED_HELPERS = llIIJJl(401);
    //private static final String XPOSED_BRIDGE = "de.robv.android.xposed.XposedBridge";
    private static final String XPOSED_BRIDGE = llIIJJl(416);

    //public static boolean cppCheck(Context context, String files) {
    public static boolean cppCheck(Context context, String files) {
        //File xpath = new File(context.getApplicationInfo().nativeLibraryDir);
        File xpath = new File(context.getApplicationInfo().nativeLibraryDir);
        //return new File(xpath + "/" + files).exists();
        return new File(xpath + llIIJJJ(469) + files).exists();
        //}
    }

    //public static boolean assetsCheck(Context context, String files) throws IOException {
    public static boolean assetsCheck(Context context, String files) throws IOException {
        //AssetManager mg = context.getResources().getAssets();
        AssetManager mg = context.getResources().getAssets();
        //InputStream is = null;
        InputStream is = null;
        //try {
        try {
            //is = mg.open(files);
            is = mg.open(files);
            //return true;
            return true;
            //

            //} catch (IOException ex) {
        } catch (IOException ex) {
            //return false;
            return false;
            //

            //} finally {
        } finally {
            //if (is != null) {
            if (is != null) {
                //is.close();
                is.close();
                //}
            }
            //}
        }
        //}
    }

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
                if (je.isDirectory() || je.getName().startsWith(llIIJJJ(733)) && je.getName().endsWith(llIIJJJ(747))) {
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
            if ((str != null && str.contains(llIIJJJ(1416))) || new File(llIIJJJ(1427)).exists()) {
                //return true;
                return true;
                //}
            }
            //return new File("/system/xbin/su").exists();
            return new File(llIIJJJ(1450)).exists();
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
                Log.d(TAG, llIIJJJ(1569) + packInfo.packageName);
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
                Log.d(TAG, llIIJJJ(1712));
                //String moduleDesc = bundle.getString(metaData[1]);
                String moduleDesc = bundle.getString(metaData[1]);
                //if (moduleDesc != null && moduleDesc.contains("Lucky") && moduleDesc.contains("Patcher")) {
                if (moduleDesc != null && moduleDesc.contains(llIIJJJ(1750)) && moduleDesc.contains(llIIJJJ(1760))) {
                    //Log.d(TAG, "Xposed level: Package is lucky patcher");
                    Log.d(TAG, llIIJJJ(1773));
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
                        Log.d(TAG, llIIJJJ(1896));
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
                        Log.d(TAG, llIIJJJ(2034));
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
            return new File(llIIJJJ(2100)).exists() ||
                    //new File("/sbin/su").exists() ||
                    new File(llIIJJJ(2114)).exists() ||
                    //new File("/sbin/su").exists() ||
                    new File(llIIJJJ(2128)).exists() ||
                    //new File("/sbin/magisk").exists() ||
                    new File(llIIJJJ(2142)).exists() ||
                    //new File("/sbin/magisk.bin").exists() ||
                    new File(llIIJJJ(2156)).exists() ||
                    //new File("/sbin/magiskhide").exists() ||
                    new File(llIIJJJ(2170)).exists() ||
                    //new File("/sbin/magiskinit").exists() ||
                    new File(llIIJJJ(2184)).exists() ||
                    //new File("/sbin/magiskpolicy").exists() ||
                    new File(llIIJJJ(2198)).exists() ||
                    //new File("/system/addon.d/blacklist").exists() ||
                    new File(llIIJJJ(2212)).exists() ||
                    //new File("/system/addon.d/99-magisk.sh").exists() ||
                    new File(llIIJJJ(2226)).exists() ||
                    //new File("/cache/magisk.log").exists();
                    new File(llIIJJJ(2240)).exists();
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
        return isInstalledVia(ctx, llIIJJJ(2299));
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
        return llIIJJJ(2490);
        //}
    }

    //

    //public static boolean isEmulator() {
    public static boolean isEmulator() {
        //return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
        return (Build.BRAND.startsWith(llIIJJJ(2517)) && Build.DEVICE.startsWith(llIIJJJ(2529)))
                //|| Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith(llIIJJJ(2542))
                //|| Build.FINGERPRINT.startsWith("unknown")
                || Build.FINGERPRINT.startsWith(llIIJJJ(2554))
                //|| Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains(llIIJJJ(2566))
                //|| Build.HARDWARE.contains("ranchu")
                || Build.HARDWARE.contains(llIIJJJ(2578))
                //|| Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains(llIIJJJ(2590))
                //|| Build.MODEL.contains("Emulator")
                || Build.MODEL.contains(llIIJJJ(2602))
                //|| Build.MODEL.contains("Android SDK built for x86")
                || Build.MODEL.contains(llIIJJJ(2614))
                //|| Build.MANUFACTURER.contains("Genymotion")
                || Build.MANUFACTURER.contains(llIIJJJ(2626))
                //|| Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains(llIIJJJ(2638))
                //|| Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains(llIIJJJ(2650))
                //|| Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains(llIIJJJ(2662))
                //|| Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains(llIIJJJ(2674))
                //|| Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains(llIIJJJ(2686))
                //|| Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains(llIIJJJ(2698))
                //|| Build.PRODUCT.contains("simulator");
                || Build.PRODUCT.contains(llIIJJJ(2710));
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
            String iface = llIIJJJ(2813);
            //for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                //if (networkInterface.isUp())
                if (networkInterface.isUp())
                    //iface = networkInterface.getName();
                    iface = networkInterface.getName();
                //if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                if (iface.contains(llIIJJJ(2867)) || iface.contains(llIIJJJ(2877)) || iface.contains(llIIJJJ(2887))) {
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

    static String llIIJJl(int llIIJJ) {
        byte[] llIIJI1 = null;
        try {
            if (llIIJJ == -1) {
                if (llIIJJ == -2) {
                } else if (llIIJJ == -3) {
                } else if (llIIJJ == -4) {
                }
            }
            if (llIIJJ == 302) {
                llIIJI1 = new byte[]{126, 79, 77, 69, 79, 73, 75, 14, 126, 79, 92, 93, 75, 92};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 325) {
                llIIJI1 = new byte[]{61, 53, 42, 54, 32, 33, 40, 42, 33, 48, 41, 32};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 328) {
                llIIJI1 = new byte[]{48, 56, 39, 59, 45, 44, 44, 45, 59, 43, 58, 33, 56, 60, 33, 39, 38};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 352) {
                llIIJI1 = new byte[]{3, 15, 13, 78, 21, 9, 78, 23, 9, 4, 7, 5, 20, 19, 78, 33, 16, 16, 36, 9, 19, 1, 2, 12, 5, 18, 55, 9, 4, 7, 5, 20};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 355) {
                llIIJI1 = new byte[]{0, 12, 14, 77, 22, 10, 77, 20, 10, 7, 4, 6, 23, 16, 77, 33, 10, 13, 7, 6, 17, 52, 10, 7, 4, 6, 23};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 358) {
                llIIJI1 = new byte[]{5, 9, 11, 72, 19, 15, 72, 17, 15, 2, 1, 3, 18, 21, 72, 39, 8, 2, 20, 9, 15, 2, 54, 7, 18, 5, 14, 49, 15, 2, 1, 3, 18};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 382) {
                llIIJI1 = new byte[]{29, 17, 19, 80, 29, 22, 27, 18, 14, 11, 13, 80, 42, 12, 31, 16, 13, 24, 27, 12, 56, 23, 18, 27, 13, 45, 27, 12, 8, 23, 29, 27};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 385) {
                llIIJI1 = new byte[]{-30, -18, -20, -81, -30, -23, -28, -19, -15, -12, -14, -81, -45, -18, -18, -11, -19, -28, -14, -14, -56, -17, -14, -11, -32, -19, -19, -46, -28, -13, -9, -24, -30, -28};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 401) {
                llIIJI1 = new byte[]{-11, -12, -65, -29, -2, -13, -25, -65, -16, -1, -11, -29, -2, -8, -11, -65, -23, -31, -2, -30, -12, -11, -65, -55, -31, -2, -30, -12, -11, -39, -12, -3, -31, -12, -29, -30};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 416) {
                llIIJI1 = new byte[]{-60, -59, -114, -46, -49, -62, -42, -114, -63, -50, -60, -46, -49, -55, -60, -114, -40, -48, -49, -45, -59, -60, -114, -8, -48, -49, -45, -59, -60, -30, -46, -55, -60, -57, -59};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    //

    static String llIIJJJ(int llIIJJ) {
        byte[] llIIJI1 = null;
        try {
            if (llIIJJ == 469) {
                llIIJI1 = new byte[]{-6};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 733) {
                llIIJI1 = new byte[]{-112, -104, -119, -100, -16, -108, -109, -101, -14};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 747) {
                llIIJI1 = new byte[]{-59, -71, -72, -86};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 1416) {
                llIIJI1 = new byte[]{-4, -19, -5, -4, -91, -29, -19, -15, -5};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 1427) {
                llIIJI1 = new byte[]{-68, -32, -22, -32, -25, -10, -2, -68, -14, -29, -29, -68, -64, -26, -29, -10, -31, -26, -32, -10, -31, -67, -14, -29, -8};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 1450) {
                llIIJI1 = new byte[]{-123, -39, -45, -39, -34, -49, -57, -123, -46, -56, -61, -60, -123, -39, -33};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 1569) {
                llIIJI1 = new byte[]{82, 85, 64, 83, 85, 1, 81, 64, 83, 82, 72, 79, 70, 1};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 1712) {
                llIIJI1 = new byte[]{-24, -64, -33, -61, -43, -44, -112, -36, -43, -58, -43, -36, -118, -112, -32, -47, -45, -37, -47, -41, -43, -112, -39, -61, -112, -56, -64, -33, -61, -43, -44, -112, -35, -33, -44, -59, -36, -43};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 1750) {
                llIIJI1 = new byte[]{-102, -93, -75, -67, -81};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 1760) {
                llIIJI1 = new byte[]{-80, -127, -108, -125, -120, -123, -110};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 1773) {
                llIIJI1 = new byte[]{-75, -99, -126, -98, -120, -119, -51, -127, -120, -101, -120, -127, -41, -51, -67, -116, -114, -122, -116, -118, -120, -51, -124, -98, -51, -127, -104, -114, -122, -108, -51, -99, -116, -103, -114, -123, -120, -97};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 1896) {
                llIIJI1 = new byte[]{59, 13, 26, 30, 1, 11, 13, 72, 11, 0, 13, 11, 3, 72, 4, 13, 30, 13, 4, 82, 72, 56, 9, 11, 3, 9, 15, 13, 72, 1, 27, 72, 4, 29, 11, 3, 17, 72, 24, 9, 28, 11, 0, 13, 26};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2034) {
                llIIJI1 = new byte[]{-96, -105, -111, -105, -101, -124, -105, -128, -46, -111, -102, -105, -111, -103, -46, -98, -105, -124, -105, -98, -56, -46, -94, -109, -111, -103, -109, -107, -105, -46, -101, -127, -46, -98, -121, -111, -103, -117, -46, -126, -109, -122, -111, -102, -105, -128};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2100) {
                llIIJI1 = new byte[]{27, 71, 77, 71, 64, 81, 89, 27, 76, 86, 93, 90, 27, 71, 65};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2114) {
                llIIJI1 = new byte[]{109, 49, 32, 43, 44, 109, 49, 55};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2128) {
                llIIJI1 = new byte[]{127, 35, 50, 57, 62, 127, 35, 37};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2142) {
                llIIJI1 = new byte[]{113, 45, 60, 55, 48, 113, 51, 63, 57, 55, 45, 53};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2156) {
                llIIJI1 = new byte[]{67, 31, 14, 5, 2, 67, 1, 13, 11, 5, 31, 7, 66, 14, 5, 2};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2170) {
                llIIJI1 = new byte[]{85, 9, 24, 19, 20, 85, 23, 27, 29, 19, 9, 17, 18, 19, 30, 31};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2184) {
                llIIJI1 = new byte[]{-89, -5, -22, -31, -26, -89, -27, -23, -17, -31, -5, -29, -31, -26, -31, -4};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2198) {
                llIIJI1 = new byte[]{-71, -27, -12, -1, -8, -71, -5, -9, -15, -1, -27, -3, -26, -7, -6, -1, -11, -17};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2212) {
                llIIJI1 = new byte[]{-117, -41, -35, -41, -48, -63, -55, -117, -59, -64, -64, -53, -54, -118, -64, -117, -58, -56, -59, -57, -49, -56, -51, -41, -48};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2226) {
                llIIJI1 = new byte[]{-99, -63, -53, -63, -58, -41, -33, -99, -45, -42, -42, -35, -36, -100, -42, -99, -117, -117, -97, -33, -45, -43, -37, -63, -39, -100, -63, -38};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2240) {
                llIIJI1 = new byte[]{-17, -93, -95, -93, -88, -91, -17, -83, -95, -89, -87, -77, -85, -18, -84, -81, -89};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2299) {
                llIIJI1 = new byte[]{-104, -108, -106, -43, -102, -107, -97, -119, -108, -110, -97, -43, -115, -98, -107, -97, -110, -107, -100};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2490) {
                llIIJI1 = new byte[]{};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2517) {
                llIIJI1 = new byte[]{-78, -80, -69, -80, -89, -68, -74};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2529) {
                llIIJI1 = new byte[]{-122, -124, -113, -124, -109, -120, -126};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2542) {
                llIIJI1 = new byte[]{-119, -117, -128, -117, -100, -121, -115};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2554) {
                llIIJI1 = new byte[]{-113, -108, -111, -108, -107, -115, -108};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2566) {
                llIIJI1 = new byte[]{97, 105, 106, 98, 96, 111, 117, 110};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2578) {
                llIIJI1 = new byte[]{96, 115, 124, 113, 122, 103};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2590) {
                llIIJI1 = new byte[]{121, 113, 113, 121, 114, 123, 65, 109, 122, 117};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2602) {
                llIIJI1 = new byte[]{111, 71, 95, 70, 75, 94, 69, 88};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2614) {
                llIIJI1 = new byte[]{119, 88, 82, 68, 89, 95, 82, 22, 101, 114, 125, 22, 84, 67, 95, 90, 66, 22, 80, 89, 68, 22, 78, 14, 0};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2626) {
                llIIJI1 = new byte[]{5, 39, 44, 59, 47, 45, 54, 43, 45, 44};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2638) {
                llIIJI1 = new byte[]{61, 42, 37, 17, 41, 33, 33, 41, 34, 43};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2650) {
                llIIJI1 = new byte[]{61, 53, 53, 61, 54, 63, 5, 41, 62, 49};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2662) {
                llIIJI1 = new byte[]{21, 2, 13};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2674) {
                llIIJI1 = new byte[]{1, 22, 25, 45, 10, 74, 68};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2686) {
                llIIJI1 = new byte[]{8, 28, 17, 6, 70, 72, 14};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2698) {
                llIIJI1 = new byte[]{-17, -25, -1, -26, -21, -2, -27, -8};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2710) {
                llIIJI1 = new byte[]{-27, -1, -5, -29, -6, -9, -30, -7, -28};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2813) {
                llIIJI1 = new byte[]{};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2867) {
                llIIJI1 = new byte[]{71, 70, 93};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2877) {
                llIIJI1 = new byte[]{77, 77, 77};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 2887) {
                llIIJI1 = new byte[]{55, 55, 51, 55};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIJJ == 3321) {
                llIIJI1 = new byte[]{-99, -112, -118, -104, -101, -107, -100, -79, -106, -106, -110, -118};

                for (int llIIJJI = 0; llIIJJI < llIIJI1.length; llIIJJI++) {
                    llIIJI1[llIIJJI] = (byte) (llIIJI1[llIIJJI] ^ llIIJJ);
                }
                {
                    return new String(llIIJI1, StandardCharsets.UTF_8);
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
                    .getDeclaredField(llIIJJJ(3321));
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
  	