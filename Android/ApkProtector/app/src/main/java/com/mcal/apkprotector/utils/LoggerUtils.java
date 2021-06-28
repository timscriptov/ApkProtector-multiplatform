package com.mcal.apkprotector.utils;

import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.file.ScopedStorage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerUtils {
    public static void writeLog(String info) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(ScopedStorage.getStorageDirectory() + File.separator + "ApkProtect" + File.separator + "Log.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(info);
            bw.write("\n");
            Preferences.setTempAxml(info);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}