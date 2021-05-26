package com.mcal.apkprotector.utils;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileCustomUtils {
    private static String TAG = "ApkProtector";

    public static void inputStreamRaw(Context context, Integer i, String targetFile) {
        try {
            InputStream initialStream = context.getResources().openRawResource(i);

            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            OutputStream outStream = new FileOutputStream(new File(targetFile));
            outStream.write(buffer);
        } catch (IOException e) {
            Log.e(TAG, "inputStreamRaw()  ", e);
            LoggerUtils.writeLog("Extract raw failed: " + e);
        }
    }

    public static void inputStreamAssets(Context context, String name, String targetFile) {
        try {
            InputStream initialStream = context.getResources().getAssets().open(name);

            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            OutputStream outStream = new FileOutputStream(new File(targetFile));
            outStream.write(buffer);
        } catch (IOException e) {
            Log.e(TAG, "inputStreamAssets() ", e);
            LoggerUtils.writeLog("Extract asset failed: " + e);
        }
    }

    /**
     * 根据路径的字符串创建路径
     *
     * @param path 文件或目录路径
     * @return 创建路径是否出错
     */
    public static boolean makePath(String path) {
        File file = new File(path);

        if (file.exists()) {
            return true;
        }

        if (path.lastIndexOf('.') != -1) {

            int lastIndexOf = path.lastIndexOf('/');
            if (lastIndexOf == -1) {
                lastIndexOf = path.lastIndexOf('\\');
            }
            if (lastIndexOf == -1) {
                LogUtils.w("makePath error : path '" + path + "' is not legal");
                return false;
            }

            File parentDir = new File(path.substring(0, lastIndexOf));
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            return true;
        } else {
            file.mkdir();
            return true;
        }
    }

    /**
     * 递归文件
     *
     * @param file        根目录或文件
     * @param fileHandler 文件处理器，如果handle方法返回true表示递归子目录与文件，否则不递归
     */
    public static void recusive(File file, FileHandlerUtils fileHandler) {

        if (file == null || !file.exists() || fileHandler == null) {
            return;
        }

        if (fileHandler.handle(file)) {
            // 递归
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File tmp : files) {
                    recusive(tmp, fileHandler);
                }
            }
        }
    }

    /**
     * 文件是否存在
     *
     * @param file
     * @return
     */
    public static boolean exists(File file) {
        return file != null && file.exists();
    }

    /**
     * 获取所有文件和目录
     *
     * @param file
     * @return
     */
    public static List<File> listAllFiles(File file) {
        ArrayList<File> files = new ArrayList<>();
        recusive(file, f -> {
            files.add(f);
            return true;
        });
        return files;
    }
}
