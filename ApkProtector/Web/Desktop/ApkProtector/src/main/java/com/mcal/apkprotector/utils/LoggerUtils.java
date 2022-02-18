package com.mcal.apkprotector.utils;

import com.mcal.apkprotector.data.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerUtils {

    public static void writeLog(String info) {
        System.out.println(info);
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            File logger = new File(Constants.LOG_PATH);
            if (!logger.exists()) {
                logger.getParentFile().mkdirs();
                logger.createNewFile();
            }
            fw = new FileWriter(logger, true);
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
