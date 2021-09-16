package com.oscar0812.obfuscation.utils;

import android.content.Context;

import java.io.File;

public class FileUtils {
    public static String genName(Context ctx, String path, String name, String suff, int cnt) {
        boolean overwrite = true;//Settings.getb(ctx, "overwrite_apk", true);
        if (overwrite) {
            return name + suff;
        } else {
            try {
                String tn = name;
                if (cnt > 0) {
                    tn = name + "(" + cnt + ")";
                }
                File check = new File(path, tn + suff);
                if (check.exists()) {
                    return genName(ctx, path, name, suff, ++cnt);
                } else {
                    return tn + suff;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return name + suff;
            }
        }
    }
}
