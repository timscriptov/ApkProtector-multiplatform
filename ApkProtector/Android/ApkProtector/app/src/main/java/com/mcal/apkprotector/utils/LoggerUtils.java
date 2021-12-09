package com.mcal.apkprotector.utils;

import com.mcal.apkprotector.data.Constants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerUtils {
    public static void writeLog(String info) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(Constants.LOG_PATH, true);
            bw = new BufferedWriter(fw);
            bw.write(info);
            bw.write("\n");
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