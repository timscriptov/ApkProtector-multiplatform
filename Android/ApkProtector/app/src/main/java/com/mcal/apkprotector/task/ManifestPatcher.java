package com.mcal.apkprotector.task;


import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.ManifestModify;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Изменение AndroidManifest. Замена Application, Activity на другие. А также встраивание своих данных
 */

public class ManifestPatcher {
    public static boolean manifestPatch(String manifestPath) {
        try {
            parseManifest(manifestPath, new BufferedInputStream(new FileInputStream(manifestPath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void parseManifest(String manifestPath, InputStream is) throws IOException {
        byte[] data = IOUtils.toByteArray(is);
        ManifestModify mm = new ManifestModify(Preferences.getApplicationName());
        FileUtils.writeByteArrayToFile(new File(manifestPath), mm.modifyAxml(data));
    }
}
