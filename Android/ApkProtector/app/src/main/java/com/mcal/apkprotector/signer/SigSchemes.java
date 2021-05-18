package com.mcal.apkprotector.signer;

import com.mcal.apkprotector.data.Preferences;

public class SigSchemes {
    public static final int SIG_SCHEME_V1 = 1;
    public static final int SIG_SCHEME_V2 = 1 << 1;
    public static final int SIG_SCHEME_V3 = 1 << 2;
    public static final int SIG_SCHEME_V4 = 1 << 3;

    public static int fromPref() {
        return SigSchemes.SIG_SCHEME_V1 | SigSchemes.SIG_SCHEME_V2 | SigSchemes.SIG_SCHEME_V3 | SigSchemes.SIG_SCHEME_V4;
    }

    public static boolean v1SchemeEnabled() {
        return Preferences.getSignatureV1();
    }

    public static boolean v2SchemeEnabled() {
        return Preferences.getSignatureV2();
    }

    public static boolean v3SchemeEnabled() {
        return Preferences.getSignatureV3();
    }

    public static boolean v4SchemeEnabled() {
        return Preferences.getSignatureV4();
    }
}