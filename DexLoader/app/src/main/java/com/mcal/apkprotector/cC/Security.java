/* Orginal file: Security.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.security*/
package com.mcal.apkprotector.cC;

//import android.content.Context;

import android.content.Context;
import android.content.pm.PackageInfo;

import com.mcal.apkprotector.dD.CommonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.charset.StandardCharsets;
//import java.io.InputStream;


//public class Security {
public class Security {
    //public Security(Context context, String data) {
    public Security(Context context, String data) {
        //try {
        try {
            //PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            //JSONObject obj = new JSONObject(data);
            JSONObject obj = new JSONObject(data);
            //

            //String dxTitle = "ApkProtector";
            String dxTitle = llIIJII(154);
            //

            //if (!data.isEmpty()) {
            if (!data.isEmpty()) {
                //if (obj.getBoolean("cloneCheckBoolean")) {
                if (obj.getBoolean(llIIJII(178))) {
                    //

                    //if (!((context.getPackageManager().getApplicationLabel(context.getApplicationInfo())).equals(obj.getString("Name")))) {
                    if (!((context.getPackageManager().getApplicationLabel(context.getApplicationInfo())).equals(obj.getString(llIIJII(213))))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(231));
                        //}
                    }
                    //

                    //if (!(context.getPackageName().equals(obj.getString("Package")))) {
                    if (!(context.getPackageName().equals(obj.getString(llIIJII(255))))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(273));
                        //}
                    }
                    //

                    //if (!(pInfo.versionName.equals(obj.getString("VName")))) {
                    if (!(pInfo.versionName.equals(obj.getString(llIIJII(295))))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(313));
                        //}
                    }
                    //

                    //if (!(pInfo.versionCode == obj.getInt("VCode"))) {
                    if (!(pInfo.versionCode == obj.getInt(llIIJII(336)))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(353));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("signatureCheckBoolean")) {
                if (obj.getBoolean(llIIJII(369))) {
                    //if (!obj.getString("signatureCheckString").equals(SecurityUtils.getCurrentSignature(context))) {
                    if (!obj.getString(llIIJII(383)).equals(SecurityUtils.getCurrentSignature(context))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK signature does not match!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(409));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("rootCheckBoolean")) {
                if (obj.getBoolean(llIIJII(425))) {
                    //if (SecurityUtils.isRooted()) {
                    if (SecurityUtils.isRooted()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected ROOT!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(453));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("luckyPatcherCheckBoolean") && SecurityUtils.isLucky(context)) {
                if (obj.getBoolean(llIIJII(469)) && SecurityUtils.isLucky(context)) {
                    //CommonUtils.showDialogWarn(context, dxTitle, "Detected Lucky Patcher!");
                    CommonUtils.showDialogWarn(context, dxTitle, llIIJII(495));
                    //}
                }
                //

                //if (obj.getBoolean("xposedCheckBoolean")) {
                if (obj.getBoolean(llIIJII(509))) {
                    //if (SecurityUtils.checkXposedExistAndDisableIt()) {
                    if (SecurityUtils.checkXposedExistAndDisableIt()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected Xposed!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(537));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("magiskCheckBoolean")) {
                if (obj.getBoolean(llIIJII(553))) {
                    //if (SecurityUtils.isMagisk()) {
                    if (SecurityUtils.isMagisk()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected Magisk!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(581));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("playstoreCheckBoolean")) {
                if (obj.getBoolean(llIIJII(597))) {
                    //if (!SecurityUtils.isInstalledViaGooglePlay(context)) {
                    if (!SecurityUtils.isInstalledViaGooglePlay(context)) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Please install apk on Google Play!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(627));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("emulatorCheckBoolean")) {
                if (obj.getBoolean(llIIJII(643))) {
                    //if (SecurityUtils.isEmulator()) {
                    if (SecurityUtils.isEmulator()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Running in emulator!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(671));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("debugCheckBoolean")) {
                if (obj.getBoolean(llIIJII(687))) {
                    //if (SecurityUtils.isDebuggable(context)) {
                    if (SecurityUtils.isDebuggable(context)) {
                        //CommonUtils.showDialogWarn(context, "ApkProtector Security", "Detected Debug!");
                        CommonUtils.showDialogWarn(context, llIIJII(713), llIIJII(716));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("modexCheckBoolean")) {
                if (obj.getBoolean(llIIJII(732))) {
                    //File xpath = context.getDir("libs", Context.MODE_PRIVATE);
                    File xpath = context.getDir(llIIJII(748), Context.MODE_PRIVATE);
                    //String[] files = new String[]{xpath + "/App_dex/classes.dex",
                    String[] files = new String[]{xpath + llIIJII(775),
                            //xpath + "/App_dex/Modex.txt", xpath + "/arm64-v8a/libIOHook.so",
                            xpath + llIIJII(782), xpath + llIIJII(789),
                            //xpath + "/arm64-v8a/libmock.so", xpath + "/arm64-v8a/libsandhook.so",
                            xpath + llIIJII(796), xpath + llIIJII(803),
                            //xpath + "/armeabi-v7a/libIOHook.so", xpath + "/armeabi-v7a/libmock.so",
                            xpath + llIIJII(810), xpath + llIIJII(817),
                            //xpath + "/armeabi-v7a/libsandhook.so"};
                            xpath + llIIJII(824)};
                    //for (String s : files) {
                    for (String s : files) {
                        //if(new File(s).exists()) {
                        if (new File(s).exists()) {
                            //CommonUtils.showDialogWarn(context, "ApkProtector Security", "Detected Modex 3.0!");
                            CommonUtils.showDialogWarn(context, llIIJII(865), llIIJII(868));
                            //}
                        }
                        //}
                    }
                    //}
                }
                //if (obj.getBoolean("debugCheckBoolean")) {
                if (obj.getBoolean(llIIJII(885))) {
                    //if (SecurityUtils.detectDebugger()) {
                    if (SecurityUtils.detectDebugger()) {
                        //CommonUtils.showDialogWarn(context, "ApkProtector Security", "Detected Debug!");
                        CommonUtils.showDialogWarn(context, llIIJII(910), llIIJII(913));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("checkVPNBoolean")) {
                if (obj.getBoolean(llIIJII(929))) {
                    //if (SecurityUtils.isVpn()) {
                    if (SecurityUtils.isVpn()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected VPN!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIJII(957));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("illegalCodeCheckBoolean")) {
                if (obj.getBoolean(llIIJII(973))) {
                    //JSONArray jSONArray = obj.getJSONArray("illegalCodeCheckString");
                    JSONArray jSONArray = obj.getJSONArray(llIIJII(989));

                    //for (int i = 0; i < jSONArray.length(); i++) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        //if (SecurityUtils.illegalCodeCheck(jSONArray.getString(i))) {
                        if (SecurityUtils.illegalCodeCheck(jSONArray.getString(i))) {
                            //CommonUtils.showDialogWarn(context, data, "Detected Illegal code:\n " + jSONArray.getString(i));
                            CommonUtils.showDialogWarn(context, data, llIIJII(1051) + jSONArray.getString(i));
                            //break;
                            break;
                            //}
                        }
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("hookCheckBoolean")) {
                if (obj.getBoolean(llIIJII(1081))) {
                    //JSONArray jSONArray = obj.getJSONArray("Hooks");
                    JSONArray jSONArray = obj.getJSONArray(llIIJII(1097));

                    //for (int i = 0; i < jSONArray.length(); i++) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        //if (SecurityUtils.isInstallPirateApp(context, jSONArray.getString(i))) {
                        if (SecurityUtils.isInstallPirateApp(context, jSONArray.getString(i))) {
                            //CommonUtils.showDialogWarn(context, data, "Detected Pirate App:\n " + jSONArray.getString(i));
                            CommonUtils.showDialogWarn(context, data, llIIJII(1162) + jSONArray.getString(i));
                            //break;
                            break;
                            //}
                        }
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("assetsCheckBoolean")) {
                if (obj.getBoolean(llIIJII(1192))) {
                    //JSONArray jSONArray = obj.getJSONArray("assetsCheckString");
                    JSONArray jSONArray = obj.getJSONArray(llIIJII(1208));

                    //for (int i = 0; i < jSONArray.length(); i++) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        //if (SecurityUtils.assetsCheck(context, jSONArray.getString(i))) {
                        if (SecurityUtils.assetsCheck(context, jSONArray.getString(i))) {
                            //CommonUtils.showDialogWarn(context, data, "Detected Pirate App:\n " + jSONArray.getString(i));
                            CommonUtils.showDialogWarn(context, data, llIIJII(1273) + jSONArray.getString(i));
                            //break;
                            break;
                            //}
                        }
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("cppLibsCheckBoolean")) {
                if (obj.getBoolean(llIIJII(1303))) {
                    //JSONArray jSONArray = obj.getJSONArray("cppLibsCheckString");
                    JSONArray jSONArray = obj.getJSONArray(llIIJII(1319));

                    //for (int i = 0; i < jSONArray.length(); i++) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        //if (SecurityUtils.cppCheck(context, jSONArray.getString(i))) {
                        if (SecurityUtils.cppCheck(context, jSONArray.getString(i))) {
                            //CommonUtils.showDialogWarn(context, data, "Detected Pirate App:\n " + jSONArray.getString(i));
                            CommonUtils.showDialogWarn(context, data, llIIJII(1384) + jSONArray.getString(i));
                            //break;
                            break;
                            //}
                        }
                        //}
                    }
                    //}
                }

                //}
            }
            //} catch (Throwable e) {
        } catch (Throwable e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //}
    }
//}

    static String llIIJII(int llIIJIl) {
        byte[] llIIJI = null;
        try {
            if (llIIJIl == -1) {
                if (llIIJIl == -2) {
                } else if (llIIJIl == -3) {
                } else if (llIIJIl == -4) {
                }
            }
            if (llIIJIl == 154) {
                llIIJI = new byte[]{-37, -22, -15, -54, -24, -11, -18, -1, -7, -18, -11, -24};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 178) {
                llIIJI = new byte[]{-47, -34, -35, -36, -41, -15, -38, -41, -47, -39, -16, -35, -35, -34, -41, -45, -36};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 213) {
                llIIJI = new byte[]{-101, -76, -72, -80};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 231) {
                llIIJI = new byte[]{-93, -126, -109, -126, -124, -109, -126, -125, -57, -90, -73, -84, -57, -86, -120, -125, -114, -127, -114, -126, -125, -57, -93, -122, -109, -122, -58};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 255) {
                llIIJI = new byte[]{-81, -98, -100, -108, -98, -104, -102};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 273) {
                llIIJI = new byte[]{85, 116, 101, 116, 114, 101, 116, 117, 49, 80, 65, 90, 49, 92, 126, 117, 120, 119, 120, 116, 117, 49, 85, 112, 101, 112, 48};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 295) {
                llIIJI = new byte[]{113, 105, 70, 74, 66};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 313) {
                llIIJI = new byte[]{125, 92, 77, 92, 90, 77, 92, 93, 25, 120, 105, 114, 25, 116, 86, 93, 80, 95, 80, 92, 93, 25, 125, 88, 77, 88, 24};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 336) {
                llIIJI = new byte[]{6, 19, 63, 52, 53};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 353) {
                llIIJI = new byte[]{37, 4, 21, 4, 2, 21, 4, 5, 65, 32, 49, 42, 65, 44, 14, 5, 8, 7, 8, 4, 5, 65, 37, 0, 21, 0, 64};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 369) {
                llIIJI = new byte[]{2, 24, 22, 31, 16, 5, 4, 3, 20, 50, 25, 20, 18, 26, 51, 30, 30, 29, 20, 16, 31};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 383) {
                llIIJI = new byte[]{12, 22, 24, 17, 30, 11, 10, 13, 26, 60, 23, 26, 28, 20, 44, 11, 13, 22, 17, 24};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 409) {
                llIIJI = new byte[]{-35, -4, -19, -4, -6, -19, -4, -3, -71, -40, -55, -46, -71, -22, -16, -2, -9, -8, -19, -20, -21, -4, -71, -3, -10, -4, -22, -71, -9, -10, -19, -71, -12, -8, -19, -6, -15, -72};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 425) {
                llIIJI = new byte[]{-37, -58, -58, -35, -22, -63, -52, -54, -62, -21, -58, -58, -59, -52, -56, -57};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 453) {
                llIIJI = new byte[]{-127, -96, -79, -96, -90, -79, -96, -95, -27, -105, -118, -118, -111, -28};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 469) {
                llIIJI = new byte[]{-71, -96, -74, -66, -84, -123, -76, -95, -74, -67, -80, -89, -106, -67, -80, -74, -66, -105, -70, -70, -71, -80, -76, -69};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 495) {
                llIIJI = new byte[]{-85, -118, -101, -118, -116, -101, -118, -117, -49, -93, -102, -116, -124, -106, -49, -65, -114, -101, -116, -121, -118, -99, -50};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 509) {
                llIIJI = new byte[]{-123, -115, -110, -114, -104, -103, -66, -107, -104, -98, -106, -65, -110, -110, -111, -104, -100, -109};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 537) {
                llIIJI = new byte[]{93, 124, 109, 124, 122, 109, 124, 125, 57, 65, 105, 118, 106, 124, 125, 56};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 553) {
                llIIJI = new byte[]{68, 72, 78, 64, 90, 66, 106, 65, 76, 74, 66, 107, 70, 70, 69, 76, 72, 71};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 581) {
                llIIJI = new byte[]{1, 32, 49, 32, 38, 49, 32, 33, 101, 8, 36, 34, 44, 54, 46, 100};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 597) {
                llIIJI = new byte[]{37, 57, 52, 44, 38, 33, 58, 39, 48, 22, 61, 48, 54, 62, 23, 58, 58, 57, 48, 52, 59};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 627) {
                llIIJI = new byte[]{35, 31, 22, 18, 0, 22, 83, 26, 29, 0, 7, 18, 31, 31, 83, 18, 3, 24, 83, 28, 29, 83, 52, 28, 28, 20, 31, 22, 83, 35, 31, 18, 10, 82};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 643) {
                llIIJI = new byte[]{-26, -18, -10, -17, -30, -9, -20, -15, -64, -21, -26, -32, -24, -63, -20, -20, -17, -26, -30, -19};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 671) {
                llIIJI = new byte[]{-37, -6, -21, -6, -4, -21, -6, -5, -65, -34, -49, -44, -65, -51, -22, -15, -15, -10, -15, -8, -65, -10, -15, -65, -6, -14, -22, -13, -2, -21, -16, -19, -66};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 687) {
                llIIJI = new byte[]{-53, -54, -51, -38, -56, -20, -57, -54, -52, -60, -19, -64, -64, -61, -54, -50, -63};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 713) {
                llIIJI = new byte[]{-120, -71, -94, -103, -69, -90, -67, -84, -86, -67, -90, -69, -23, -102, -84, -86, -68, -69, -96, -67, -80};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 716) {
                llIIJI = new byte[]{-120, -87, -72, -87, -81, -72, -87, -88, -20, -120, -87, -82, -71, -85, -19};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 732) {
                llIIJI = new byte[]{-79, -77, -72, -71, -92, -97, -76, -71, -65, -73, -98, -77, -77, -80, -71, -67, -78};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 748) {
                llIIJI = new byte[]{-128, -123, -114, -97};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 775) {
                llIIJI = new byte[]{40, 70, 119, 119, 88, 99, 98, 127, 40, 100, 107, 102, 116, 116, 98, 116, 41, 99, 98, 127};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 782) {
                llIIJI = new byte[]{33, 79, 126, 126, 81, 106, 107, 118, 33, 67, 97, 106, 107, 118, 32, 122, 118, 122};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 789) {
                llIIJI = new byte[]{58, 116, 103, 120, 35, 33, 56, 99, 45, 116, 58, 121, 124, 119, 92, 90, 93, 122, 122, 126, 59, 102, 122};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 796) {
                llIIJI = new byte[]{51, 125, 110, 113, 42, 40, 49, 106, 36, 125, 51, 112, 117, 126, 113, 115, 127, 119, 50, 111, 115};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 803) {
                llIIJI = new byte[]{12, 66, 81, 78, 21, 23, 14, 85, 27, 66, 12, 79, 74, 65, 80, 66, 77, 71, 75, 76, 76, 72, 13, 80, 76};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 810) {
                llIIJI = new byte[]{5, 75, 88, 71, 79, 75, 72, 67, 7, 92, 29, 75, 5, 70, 67, 72, 99, 101, 98, 69, 69, 65, 4, 89, 69};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 817) {
                llIIJI = new byte[]{30, 80, 67, 92, 84, 80, 83, 88, 28, 71, 6, 80, 30, 93, 88, 83, 92, 94, 82, 90, 31, 66, 94};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 824) {
                llIIJI = new byte[]{23, 89, 74, 85, 93, 89, 90, 81, 21, 78, 15, 89, 23, 84, 81, 90, 75, 89, 86, 92, 80, 87, 87, 83, 22, 75, 87};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 865) {
                llIIJI = new byte[]{32, 17, 10, 49, 19, 14, 21, 4, 2, 21, 14, 19, 65, 50, 4, 2, 20, 19, 8, 21, 24};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 868) {
                llIIJI = new byte[]{32, 1, 16, 1, 7, 16, 1, 0, 68, 41, 11, 0, 1, 28, 68, 87, 74, 84, 69};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 885) {
                llIIJI = new byte[]{17, 16, 23, 0, 18, 54, 29, 16, 22, 30, 55, 26, 26, 25, 16, 20, 27};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 910) {
                llIIJI = new byte[]{-49, -2, -27, -34, -4, -31, -6, -21, -19, -6, -31, -4, -82, -35, -21, -19, -5, -4, -25, -6, -9};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 913) {
                llIIJI = new byte[]{-43, -12, -27, -12, -14, -27, -12, -11, -79, -43, -12, -13, -28, -10, -80};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 929) {
                llIIJI = new byte[]{-62, -55, -60, -62, -54, -9, -15, -17, -29, -50, -50, -51, -60, -64, -49};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 957) {
                llIIJI = new byte[]{-7, -40, -55, -40, -34, -55, -40, -39, -99, -21, -19, -13, -100};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 973) {
                llIIJI = new byte[]{-92, -95, -95, -88, -86, -84, -95, -114, -94, -87, -88, -114, -91, -88, -82, -90, -113, -94, -94, -95, -88, -84, -93};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 989) {
                llIIJI = new byte[]{-76, -79, -79, -72, -70, -68, -79, -98, -78, -71, -72, -98, -75, -72, -66, -74, -114, -87, -81, -76, -77, -70};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1051) {
                llIIJI = new byte[]{95, 126, 111, 126, 120, 111, 126, 127, 59, 82, 119, 119, 126, 124, 122, 119, 59, 120, 116, 127, 126, 33, 17, 59};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1081) {
                llIIJI = new byte[]{81, 86, 86, 82, 122, 81, 92, 90, 82, 123, 86, 86, 85, 92, 88, 87};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1097) {
                llIIJI = new byte[]{1, 38, 38, 34, 58};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1162) {
                llIIJI = new byte[]{-50, -17, -2, -17, -23, -2, -17, -18, -86, -38, -29, -8, -21, -2, -17, -86, -53, -6, -6, -80, -128, -86};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1192) {
                llIIJI = new byte[]{-55, -37, -37, -51, -36, -37, -21, -64, -51, -53, -61, -22, -57, -57, -60, -51, -55, -58};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1208) {
                llIIJI = new byte[]{-39, -53, -53, -35, -52, -53, -5, -48, -35, -37, -45, -21, -52, -54, -47, -42, -33};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1273) {
                llIIJI = new byte[]{-67, -100, -115, -100, -102, -115, -100, -99, -39, -87, -112, -117, -104, -115, -100, -39, -72, -119, -119, -61, -13, -39};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1303) {
                llIIJI = new byte[]{116, 103, 103, 91, 126, 117, 100, 84, 127, 114, 116, 124, 85, 120, 120, 123, 114, 118, 121};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1319) {
                llIIJI = new byte[]{68, 87, 87, 107, 78, 69, 84, 100, 79, 66, 68, 76, 116, 83, 85, 78, 73, 64};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
            if (llIIJIl == 1384) {
                llIIJI = new byte[]{44, 13, 28, 13, 11, 28, 13, 12, 72, 56, 1, 26, 9, 28, 13, 72, 41, 24, 24, 82, 98, 72};

                for (int llIIJIJ = 0; llIIJIJ < llIIJI.length; llIIJIJ++) {
                    llIIJI[llIIJIJ] = (byte) (llIIJI[llIIJIJ] ^ llIIJIl);
                }
                {
                    return new String(llIIJI, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/
}//
  	