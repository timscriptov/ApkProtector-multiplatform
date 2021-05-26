package com.mcal.apkprotector.utils;

import android.os.Build;

public class SecurityUtils {
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

    public static boolean illegalCodeCheck() throws ClassNotFoundException {
        return Class.forName("cc.binmt.signature.PmsHookApplication") != null ||
                Class.forName("arm.a") == null ||
                Class.forName("arm.ArmKill") == null ||
                Class.forName("np.manager.Copyright") == null ||
                Class.forName("anymy.sign.BinSignatureFix") != null ||
                Class.forName("apkeditor.patch.signature.Fix") != null ||
                Class.forName("com.anymy.reflection") != null ||
                Class.forName("bin.mt.apksignaturekillerplus.HookApplication") == null ||
                Class.forName("np.App") == null ||
                Class.forName("np.manager.FuckSign") == null ||
                Class.forName("np.manager.Copyright") == null ||
                Class.forName("cc.binmt.signature.Hook") != null;
    }
}
