package com.mcal.apkprotector.task;

import android.content.Context;
import android.util.Log;

import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.ManifestModify;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Изменение AndroidManifest. Замена Application, Activity на другие. А также встраивание своих данных
 */

public class ManifestPatcher {
    private static String TAG = "ApkProtector";
    private static String xpath;

    public static boolean run(@NotNull Context context) {
        xpath = context.getFilesDir().getAbsolutePath();
        try {
            File cmanifestFile = new File(xpath + "/gen/AndroidManifest.xml");
            cmanifestFile.renameTo(new File(xpath + "/gen/AndroidManifest2.xml"));
            byte[] data = FileUtils.readFileToByteArray(new File(xpath + "/gen/AndroidManifest2.xml"));
            ManifestModify mm = new ManifestModify(context, "com.mcal.apkprotector.ProxyApplication");
            FileUtils.writeByteArrayToFile(new File(xpath + "/gen/AndroidManifest.xml"), mm.modifyAxml(data));
            cmanifestFile = new File(xpath + "/gen/AndroidManifest2.xml");
            cmanifestFile.delete();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Patching Manifest failed: ", e);
            return false;
        }
    }
}
