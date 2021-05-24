package com.mcal.apkprotector.signer;

import com.android.apksigner.ApkSignerTool;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.signer.platform.PlatformDor;
import com.mcal.apkprotector.utils.FileUtils;

import java.util.Arrays;

public class SignatureTool {
    public static boolean apksigner(String inputPath, String outputPath) {
        try {
            sign(inputPath, outputPath);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sign(String inputPath, String outputPath) {
        boolean result = false;
        try {
            String tempPath = Preferences.getZipaligner() ? PlatformDor.zipalign(inputPath) : inputPath;
            System.out.println(tempPath);
            if (tempPath != null) {
                String[] signparams = {"sign",
                        "--ks", Preferences.isSignaturePath(),
                        "--ks-key-alias", Preferences.isSignatureAlias(),
                        "--ks-pass", "pass:" + Preferences.isCertPassword(),
                        "--key-pass", "pass:" + Preferences.isSignaturePassword(),
                        "--v1-signing-enabled", "" + Preferences.getSignatureV1(),
                        "--v2-signing-enabled", "" + Preferences.getSignatureV2(),
                        "--v3-signing-enabled", "" + Preferences.getSignatureV3(),
                        "--v4-signing-enabled", "" + Preferences.getSignatureV4(),
                        "--out",
                        outputPath,
                        tempPath};
                System.out.println(Arrays.asList(signparams));
                ApkSignerTool.main(signparams);
                result = FileUtils.isExists(outputPath);
                if (result) {
                    verify(outputPath);
                    String logging = FileUtils.readFileContent(Monitor.file.getAbsolutePath());
                    if (Preferences.getSignatureV1()) {
                        result = result && logging.contains("Verified using v1 scheme (JAR signing): true");
                    }
                    if (Preferences.getSignatureV2()) {
                        result = result && logging.contains("Verified using v2 scheme (APK Signature Scheme v2): true");
                    }
                    if (Preferences.getSignatureV3()) {
                        result = result && logging.contains("Verified using v3 scheme (APK Signature Scheme v3): true");
                    }
                    if (Preferences.getSignatureV4()) {
                        result = result && logging.contains("Verified using v4 scheme (APK Signature Scheme v4): true");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("V2 sign result : " + result);
        }
        return result;
    }

    public static void verify(String path) throws Exception {
        String[] verifyparams = {"verify", "-v", path};
        ApkSignerTool.main(verifyparams);
    }
}