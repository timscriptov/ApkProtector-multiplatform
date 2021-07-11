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
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SecurityUtils {
    private static final String TAG = "Package Parser";

    private static final String[] metaData = new String[]{"xposedmodule", "xposeddescription"};
    private static final String[] receivers = new String[]{"com.ui.widgets.AppDisablerWidget", "com.ui.widgets.BinderWidget", "com.ui.widgets.AndroidPatchWidget"};
    private static final String[] services = new String[]{"com.chelpus.TransferFilesService", "com.chelpus.RootlessInstallService"};

    private static final String XPOSED_HELPERS = "de.robv.android.xposed.XposedHelpers";
    private static final String XPOSED_BRIDGE = "de.robv.android.xposed.XposedBridge";

    @Nullable
    static String getCurrentSignature(Context context) {
        byte[] readBuffer = new byte[8192];
        Certificate[] certs = null;
        try {
            JarFile jarFile = new JarFile(context.getApplicationInfo().publicSourceDir);
            Enumeration entries = jarFile.entries();
            JarEntry je = (JarEntry) entries.nextElement();
            while (entries.hasMoreElements()) {
                if (je.isDirectory() || je.getName().startsWith("META-INF/") && je.getName().endsWith(".RSA")) {
                    continue;
                }
                Certificate[] localCerts = loadCertificates(jarFile, je, readBuffer);
                if (certs == null) {
                    certs = localCerts;
                } else {
                    for (Certificate cert : certs) {
                        boolean found = false;
                        for (Certificate localCert : localCerts) {
                            if (cert != null && cert.equals(localCert)) {
                                found = true;
                                break;
                            }
                        }
                        if (!found || certs.length != localCerts.length) {
                            jarFile.close();

                            return null;
                        }
                    }
                }
                jarFile.close();
                return new String(toChars(certs[0].getEncoded()));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private static Certificate[] loadCertificates(@NotNull JarFile jarFile, JarEntry je, byte[] readBuffer) {
        try {
            InputStream is = jarFile.getInputStream(je);
            while (is.read(readBuffer, 0, readBuffer.length) != -1) {
            }
            is.close();
            return je != null ? je.getCertificates() : null;
        } catch (IOException a) {
            a.printStackTrace();
            return null;
        }
    }

    @NotNull
    @Contract(pure = true)
    private static char[] toChars(@NotNull byte[] mSignature) {
        final int N;
        N = mSignature.length;
        final int N2 = N * 2;
        char[] text = new char[N2];

        for (int j = 0; j < N; j++) {
            byte v = mSignature[j];
            int d = (v >> 4) & 0xf;
            text[j * 2] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
            d = v & 0xf;
            text[j * 2 + 1] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
        }
        return text;
    }

    /**
     * Проверка root прав
     *
     * @return
     */
    public static boolean isRooted() {
        try {
            String str = Build.TAGS;
            if ((str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
            return new File("/system/xbin/su").exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isLucky(Context context) {
        try {
            int flags = PackageManager.GET_RECEIVERS | PackageManager.GET_SERVICES;

            @SuppressLint("QueryPermissionsNeeded") List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(flags);

            for (PackageInfo packInfo : packages) {
                Log.d(TAG, "start parsing " + packInfo.packageName);
                if (isXposedModule(context.getPackageManager().getApplicationInfo(packInfo.packageName, PackageManager.GET_META_DATA))
                        || detectServices(packInfo) || detectReceivers(packInfo)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isXposedModule(ApplicationInfo appInfo) {
        Bundle bundle = appInfo.metaData;

        try {
            if (bundle.getBoolean(metaData[0])) {
                Log.d(TAG, "Xposed level: Package is xposed module");
                String moduleDesc = bundle.getString(metaData[1]);
                if (moduleDesc != null && moduleDesc.contains("Lucky") && moduleDesc.contains("Patcher")) {
                    Log.d(TAG, "Xposed level: Package is lucky patcher");
                    return true;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean detectServices(PackageInfo packInfo) {
        ServiceInfo[] serviceInfoArr = packInfo.services;

        try {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                for (String serviceName : services) {
                    if (serviceInfo.name.equals(serviceName)) {
                        Log.d(TAG, "Service check level: Package is lucky patcher");
                        return true;
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean detectReceivers(PackageInfo packInfo) {
        ActivityInfo[] receiverArr = packInfo.receivers;

        try {
            for (ActivityInfo receiver : receiverArr) {
                for (String receiverName : receivers) {
                    Log.d(TAG, receiver.name);
                    if (receiver.name.equals(receiverName)) {
                        Log.d(TAG, "Receiver check level: Package is lucky patcher");
                        return true;
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Проверка root прав от Magisk
     *
     * @return
     */
    public static boolean isMagisk() {
        try {
            return new File("/system/xbin/su").exists() ||
                    new File("/sbin/su").exists() ||
                    new File("/sbin/su").exists() ||
                    new File("/sbin/magisk").exists() ||
                    new File("/sbin/magisk.bin").exists() ||
                    new File("/sbin/magiskhide").exists() ||
                    new File("/sbin/magiskinit").exists() ||
                    new File("/sbin/magiskpolicy").exists() ||
                    new File("/system/addon.d/blacklist").exists() ||
                    new File("/system/addon.d/99-magisk.sh").exists() ||
                    new File("/cache/magisk.log").exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Проверка установки приложения из маркета
     *
     * @return
     */
    public static boolean isInstalledViaGooglePlay(Context ctx) {
        return isInstalledVia(ctx, "com.android.vending");
    }

    public static boolean isInstalledVia(Context ctx, String required) {
        String installer = getInstallerPackageName(ctx);
        return required.equals(installer);
    }

    private static String getInstallerPackageName(Context ctx) {
        try {
            String packageName = ctx.getPackageName();
            PackageManager pm = ctx.getPackageManager();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                InstallSourceInfo info = pm.getInstallSourceInfo(packageName);
                if (info != null) {
                    return info.getInstallingPackageName();
                }
            }
            return pm.getInstallerPackageName(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Проверка устройства на эмулятор
     *
     * @return
     */
    public static boolean isEmulator() {
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator");
    }

    /**
     * Проверка флага в манифесте
     *
     * @param context
     * @return
     */
    public static boolean isDebuggable(@NotNull Context context) {
        return ((context.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
    }

    /**
     * Проверка подключения отладки
     *
     * @return
     */
    public static boolean detectDebugger() {
        return Debug.isDebuggerConnected();
    }

    /**
     * Проверка запущенного VPN
     *
     * @return
     */
    public static boolean isVpn() {
        try {
            String iface = "";
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp())
                    iface = networkInterface.getName();
                if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Проверка встроенных классов
     *
     * @param classPath - путь к классу
     * @return
     * @throws ClassNotFoundException
     */
    public static boolean illegalCodeCheck(String classPath) throws ClassNotFoundException {
        return Class.forName(classPath) != null;
    }

    /**
     * Проверка установленных приложений
     *
     * @param context
     * @param packageName - имя пакета приложения
     * @return
     */
    @SuppressLint("WrongConstant")
    public static boolean isInstallPirateApp(@NotNull Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkXposedExistAndDisableIt() {
        return SecurityUtils.getSingleInstance().tryShutdownXposed();
    }

    @Contract(pure = true)
    private static SecurityUtils getSingleInstance() {
        return SecurityUtils.SingletonHolder.singleInstance;
    }

    /**
     * 通过检查是否已经加载了XP类来检测
     *
     * @return
     */
    @Deprecated
    public boolean isXposedExists() {
        try {
            ClassLoader
                    .getSystemClassLoader()
                    .loadClass(XPOSED_HELPERS)
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            ClassLoader
                    .getSystemClassLoader()
                    .loadClass(XPOSED_BRIDGE)
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 尝试关闭XP框架
     * 先通过isXposedExistByThrow判断有没有XP框架
     * 有的话先hookXP框架的全局变量disableHooks
     * <p>
     * 漏洞在，如果XP框架先hook了isXposedExistByThrow的返回值，那么后续就没法走了
     * 现在直接先hookXP框架的全局变量disableHooks
     *
     * @return 是否关闭成功的结果
     */
    private boolean tryShutdownXposed() {
        Field xpdisabledHooks;
        try {
            xpdisabledHooks = ClassLoader.getSystemClassLoader()
                    .loadClass(XPOSED_BRIDGE)
                    .getDeclaredField("disableHooks");
            xpdisabledHooks.setAccessible(true);
            xpdisabledHooks.set(null, Boolean.TRUE);
            return true;
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static class SingletonHolder {
        private static final SecurityUtils singleInstance = new SecurityUtils();
    }
}
