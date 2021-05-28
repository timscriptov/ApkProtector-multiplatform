package com.mcal.dexprotect.task;


import java.io.BufferedInputStream;
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
        /*byte[] data = IOUtils.toByteArray(is);
        ManifestModify mm = new ManifestModify(Preferences.getApplicationName());
        FileUtils.writeByteArrayToFile(new File(manifestPath), mm.modifyAxml(data));*/
    }
}
