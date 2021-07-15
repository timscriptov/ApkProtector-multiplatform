/* Orginal file: Security.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.security*/
package com.mcal.apkprotector.security;

import android.content.Context;
import android.content.pm.PackageInfo;

import com.mcal.apkprotector.utils.CommonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

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
            String dxTitle = llIIl11(136);
            //

            //if (!data.isEmpty()) {
            if (!data.isEmpty()) {
                //if (obj.getBoolean("cloneCheckBoolean")) {
                if (obj.getBoolean(llIIl11(160))) {
                    //

                    //if (!((context.getPackageManager().getApplicationLabel(context.getApplicationInfo())).equals(obj.getString("Name")))) {
                    if (!((context.getPackageManager().getApplicationLabel(context.getApplicationInfo())).equals(obj.getString(llIIl11(195))))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(213));
                        //}
                    }
                    //

                    //if (!(context.getPackageName().equals(obj.getString("Package")))) {
                    if (!(context.getPackageName().equals(obj.getString(llIIl11(237))))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(255));
                        //}
                    }
                    //

                    //if (!(pInfo.versionName.equals(obj.getString("VName")))) {
                    if (!(pInfo.versionName.equals(obj.getString(llIIl11(277))))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(295));
                        //}
                    }
                    //

                    //if (!(pInfo.versionCode == obj.getInt("VCode"))) {
                    if (!(pInfo.versionCode == obj.getInt(llIIl11(318)))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Modified Data!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(335));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("signatureCheckBoolean")) {
                if (obj.getBoolean(llIIl11(351))) {
                    //if (!obj.getString("signatureCheckString").equals(SecurityUtils.getCurrentSignature(context))) {
                    if (!obj.getString(llIIl11(365)).equals(SecurityUtils.getCurrentSignature(context))) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK signature does not match!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(391));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("rootCheckBoolean")) {
                if (obj.getBoolean(llIIl11(407))) {
                    //if (SecurityUtils.isRooted()) {
                    if (SecurityUtils.isRooted()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected ROOT!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(435));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("luckyPatcherCheckBoolean") && SecurityUtils.isLucky(context)) {
                if (obj.getBoolean(llIIl11(451)) && SecurityUtils.isLucky(context)) {
                    //CommonUtils.showDialogWarn(context, dxTitle, "Detected Lucky Patcher!");
                    CommonUtils.showDialogWarn(context, dxTitle, llIIl11(477));
                    //}
                }
                //

                //if (obj.getBoolean("xposedCheckBoolean")) {
                if (obj.getBoolean(llIIl11(491))) {
                    //if (SecurityUtils.checkXposedExistAndDisableIt()) {
                    if (SecurityUtils.checkXposedExistAndDisableIt()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected Xposed!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(519));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("magiskCheckBoolean")) {
                if (obj.getBoolean(llIIl11(535))) {
                    //if (SecurityUtils.isMagisk()) {
                    if (SecurityUtils.isMagisk()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected Magisk!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(563));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("playstoreCheckBoolean")) {
                if (obj.getBoolean(llIIl11(579))) {
                    //if (!SecurityUtils.isInstalledViaGooglePlay(context)) {
                    if (!SecurityUtils.isInstalledViaGooglePlay(context)) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Please install apk on Google Play!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(609));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("emulatorCheckBoolean")) {
                if (obj.getBoolean(llIIl11(625))) {
                    //if (SecurityUtils.isEmulator()) {
                    if (SecurityUtils.isEmulator()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected APK Running in emulator!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(653));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("debugCheckBoolean")) {
                if (obj.getBoolean(llIIl11(669))) {
                    //if (SecurityUtils.isDebuggable(context)) {
                    if (SecurityUtils.isDebuggable(context)) {
                        //CommonUtils.showDialogWarn(context, "ApkProtector Security", "Detected Debug!");
                        CommonUtils.showDialogWarn(context, llIIl11(695), llIIl11(698));
                        //}
                    }
                    //}
                }
                //if (obj.getBoolean("debugCheckBoolean")) {
                if (obj.getBoolean(llIIl11(713))) {
                    //if (SecurityUtils.detectDebugger()) {
                    if (SecurityUtils.detectDebugger()) {
                        //CommonUtils.showDialogWarn(context, "ApkProtector Security", "Detected Debug!");
                        CommonUtils.showDialogWarn(context, llIIl11(738), llIIl11(741));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("checkVPNBoolean")) {
                if (obj.getBoolean(llIIl11(757))) {
                    //if (SecurityUtils.isVpn()) {
                    if (SecurityUtils.isVpn()) {
                        //CommonUtils.showDialogWarn(context, dxTitle, "Detected VPN!");
                        CommonUtils.showDialogWarn(context, dxTitle, llIIl11(785));
                        //}
                    }
                    //}
                }
                //

                //if (obj.getBoolean("illegalCodeCheckBoolean")) {
                if (obj.getBoolean(llIIl11(801))) {
                    //JSONArray jSONArray = obj.getJSONArray("illegalCodeCheckString");
                    JSONArray jSONArray = obj.getJSONArray(llIIl11(817));

                    //for (int i = 0; i < jSONArray.length(); i++) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        //if (SecurityUtils.illegalCodeCheck(jSONArray.getString(i))) {
                        if (SecurityUtils.illegalCodeCheck(jSONArray.getString(i))) {
                            //CommonUtils.showDialogWarn(context, data, "Detected Illegal code:\n " + jSONArray.getString(i));
                            CommonUtils.showDialogWarn(context, data, llIIl11(879) + jSONArray.getString(i));
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
                if (obj.getBoolean(llIIl11(909))) {
                    //JSONArray jSONArray = obj.getJSONArray("Hooks");
                    JSONArray jSONArray = obj.getJSONArray(llIIl11(925));

                    //for (int i = 0; i < jSONArray.length(); i++) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        //if (SecurityUtils.isInstallPirateApp(context, jSONArray.getString(i))) {
                        if (SecurityUtils.isInstallPirateApp(context, jSONArray.getString(i))) {
                            //CommonUtils.showDialogWarn(context, data, "Detected Pirate App:\n " + jSONArray.getString(i));
                            CommonUtils.showDialogWarn(context, data, llIIl11(990) + jSONArray.getString(i));
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

    static String llIIl11(int llIIl1J) {
        byte[] llIIl1I = null;
        try {
            if (llIIl1J == -1) {
                if (llIIl1J == -2) {
                } else if (llIIl1J == -3) {
                } else if (llIIl1J == -4) {
                }
            }
            if (llIIl1J == 136) {
                llIIl1I = new byte[]{-55, -8, -29, -40, -6, -25, -4, -19, -21, -4, -25, -6};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 160) {
                llIIl1I = new byte[]{-61, -52, -49, -50, -59, -29, -56, -59, -61, -53, -30, -49, -49, -52, -59, -63, -50};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 195) {
                llIIl1I = new byte[]{-115, -94, -82, -90};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 213) {
                llIIl1I = new byte[]{-111, -80, -95, -80, -74, -95, -80, -79, -11, -108, -123, -98, -11, -104, -70, -79, -68, -77, -68, -80, -79, -11, -111, -76, -95, -76, -12};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 237) {
                llIIl1I = new byte[]{-67, -116, -114, -122, -116, -118, -120};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 255) {
                llIIl1I = new byte[]{-69, -102, -117, -102, -100, -117, -102, -101, -33, -66, -81, -76, -33, -78, -112, -101, -106, -103, -106, -102, -101, -33, -69, -98, -117, -98, -34};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 277) {
                llIIl1I = new byte[]{67, 91, 116, 120, 112};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 295) {
                llIIl1I = new byte[]{99, 66, 83, 66, 68, 83, 66, 67, 7, 102, 119, 108, 7, 106, 72, 67, 78, 65, 78, 66, 67, 7, 99, 70, 83, 70, 6};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 318) {
                llIIl1I = new byte[]{104, 125, 81, 90, 91};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 335) {
                llIIl1I = new byte[]{11, 42, 59, 42, 44, 59, 42, 43, 111, 14, 31, 4, 111, 2, 32, 43, 38, 41, 38, 42, 43, 111, 11, 46, 59, 46, 110};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 351) {
                llIIl1I = new byte[]{44, 54, 56, 49, 62, 43, 42, 45, 58, 28, 55, 58, 60, 52, 29, 48, 48, 51, 58, 62, 49};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 365) {
                llIIl1I = new byte[]{30, 4, 10, 3, 12, 25, 24, 31, 8, 46, 5, 8, 14, 6, 62, 25, 31, 4, 3, 10};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 391) {
                llIIl1I = new byte[]{-61, -30, -13, -30, -28, -13, -30, -29, -89, -58, -41, -52, -89, -12, -18, -32, -23, -26, -13, -14, -11, -30, -89, -29, -24, -30, -12, -89, -23, -24, -13, -89, -22, -26, -13, -28, -17, -90};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 407) {
                llIIl1I = new byte[]{-27, -8, -8, -29, -44, -1, -14, -12, -4, -43, -8, -8, -5, -14, -10, -7};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 435) {
                llIIl1I = new byte[]{-9, -42, -57, -42, -48, -57, -42, -41, -109, -31, -4, -4, -25, -110};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 451) {
                llIIl1I = new byte[]{-81, -74, -96, -88, -70, -109, -94, -73, -96, -85, -90, -79, -128, -85, -90, -96, -88, -127, -84, -84, -81, -90, -94, -83};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 477) {
                llIIl1I = new byte[]{-103, -72, -87, -72, -66, -87, -72, -71, -3, -111, -88, -66, -74, -92, -3, -115, -68, -87, -66, -75, -72, -81, -4};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 491) {
                llIIl1I = new byte[]{-109, -101, -124, -104, -114, -113, -88, -125, -114, -120, -128, -87, -124, -124, -121, -114, -118, -123};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 519) {
                llIIl1I = new byte[]{67, 98, 115, 98, 100, 115, 98, 99, 39, 95, 119, 104, 116, 98, 99, 38};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 535) {
                llIIl1I = new byte[]{122, 118, 112, 126, 100, 124, 84, 127, 114, 116, 124, 85, 120, 120, 123, 114, 118, 121};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 563) {
                llIIl1I = new byte[]{119, 86, 71, 86, 80, 71, 86, 87, 19, 126, 82, 84, 90, 64, 88, 18};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 579) {
                llIIl1I = new byte[]{51, 47, 34, 58, 48, 55, 44, 49, 38, 0, 43, 38, 32, 40, 1, 44, 44, 47, 38, 34, 45};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 609) {
                llIIl1I = new byte[]{49, 13, 4, 0, 18, 4, 65, 8, 15, 18, 21, 0, 13, 13, 65, 0, 17, 10, 65, 14, 15, 65, 38, 14, 14, 6, 13, 4, 65, 49, 13, 0, 24, 64};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 625) {
                llIIl1I = new byte[]{20, 28, 4, 29, 16, 5, 30, 3, 50, 25, 20, 18, 26, 51, 30, 30, 29, 20, 16, 31};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 653) {
                llIIl1I = new byte[]{-55, -24, -7, -24, -18, -7, -24, -23, -83, -52, -35, -58, -83, -33, -8, -29, -29, -28, -29, -22, -83, -28, -29, -83, -24, -32, -8, -31, -20, -7, -30, -1, -84};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 669) {
                llIIl1I = new byte[]{-7, -8, -1, -24, -6, -34, -11, -8, -2, -10, -33, -14, -14, -15, -8, -4, -13};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 695) {
                llIIl1I = new byte[]{-10, -57, -36, -25, -59, -40, -61, -46, -44, -61, -40, -59, -105, -28, -46, -44, -62, -59, -34, -61, -50};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 698) {
                llIIl1I = new byte[]{-2, -33, -50, -33, -39, -50, -33, -34, -102, -2, -33, -40, -49, -35, -101};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 713) {
                llIIl1I = new byte[]{-83, -84, -85, -68, -82, -118, -95, -84, -86, -94, -117, -90, -90, -91, -84, -88, -89};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 738) {
                llIIl1I = new byte[]{-93, -110, -119, -78, -112, -115, -106, -121, -127, -106, -115, -112, -62, -79, -121, -127, -105, -112, -117, -106, -101};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 741) {
                llIIl1I = new byte[]{-95, -128, -111, -128, -122, -111, -128, -127, -59, -95, -128, -121, -112, -126, -60};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 757) {
                llIIl1I = new byte[]{-106, -99, -112, -106, -98, -93, -91, -69, -73, -102, -102, -103, -112, -108, -101};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 785) {
                llIIl1I = new byte[]{85, 116, 101, 116, 114, 101, 116, 117, 49, 71, 65, 95, 48};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 801) {
                llIIl1I = new byte[]{72, 77, 77, 68, 70, 64, 77, 98, 78, 69, 68, 98, 73, 68, 66, 74, 99, 78, 78, 77, 68, 64, 79};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 817) {
                llIIl1I = new byte[]{88, 93, 93, 84, 86, 80, 93, 114, 94, 85, 84, 114, 89, 84, 82, 90, 98, 69, 67, 88, 95, 86};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 879) {
                llIIl1I = new byte[]{43, 10, 27, 10, 12, 27, 10, 11, 79, 38, 3, 3, 10, 8, 14, 3, 79, 12, 0, 11, 10, 85, 101, 79};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 909) {
                llIIl1I = new byte[]{-27, -30, -30, -26, -50, -27, -24, -18, -26, -49, -30, -30, -31, -24, -20, -29};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 925) {
                llIIl1I = new byte[]{-43, -14, -14, -10, -18};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
            if (llIIl1J == 990) {
                llIIl1I = new byte[]{-102, -69, -86, -69, -67, -86, -69, -70, -2, -114, -73, -84, -65, -86, -69, -2, -97, -82, -82, -28, -44, -2};

                for (int llIII = 0; llIII < llIIl1I.length; llIII++) {
                    llIIl1I[llIII] = (byte) (llIIl1I[llIII] ^ llIIl1J);
                }
                {
                    return new String(llIIl1I, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/
}//
  	