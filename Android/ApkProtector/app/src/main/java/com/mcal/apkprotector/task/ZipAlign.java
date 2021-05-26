package com.mcal.apkprotector.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;

import com.mcal.apkprotector.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ZipAlign {
    public static boolean fnAapt(Context context, String input, String ouput) {
        try {
            String[] verifyparams = {"-f", "4", input, ouput};
            fnAapt(context, verifyparams);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint("UnsafeDynamicallyLoadedCode")
    public static void fnAapt(Context context, String[] strArr) {
        long start = 0;
        try {
            AssetManager assets = context.getAssets();
            File aapt = new File(context.getFilesDir().getAbsolutePath(), "zipalign");
            InputStream aapt_in = assets.open(Build.CPU_ABI + "/bin/zipalign");
            FileUtils.copyFile(aapt_in, context.getFilesDir().getAbsolutePath() + File.separator + "zipalign");
            aapt_in.close();
            aapt.setExecutable(true);

            //System.load("zipalign");

            if (!aapt.isFile()) {
                FileUtils.copyFile(aapt_in, context.getFilesDir().getAbsolutePath() + File.separator + "zipalign");
            }

            // show arguments
            start = System.currentTimeMillis();
            StringBuilder absolutePath = new StringBuilder(aapt.getAbsolutePath());
            System.out.println("zipalign arguments:");
            for (String s : strArr) {
                absolutePath.append(" ").append(s);
                System.out.println(s);
            }

            Process exec = Runtime.getRuntime().exec(absolutePath.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                System.out.println(readLine);
            }
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            while (true) {
                String readLine2 = bufferedReader2.readLine();
                if (readLine2 == null) {
                    break;
                }
                System.err.println(readLine2);
            }
        } catch (Throwable t) {
            System.err.println("Error occurred!\n" + t.getMessage());
            t.printStackTrace();
        }
        System.out.println("\nDone in " + ((System.currentTimeMillis() - start) / 1000) + " sec.\n");
    }
}
