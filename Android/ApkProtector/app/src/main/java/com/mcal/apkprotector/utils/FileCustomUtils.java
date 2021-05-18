package com.mcal.apkprotector.utils;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileCustomUtils {
    private static String TAG = "ApkProtector";

    public static void inputStreamRaw(Context context, Integer i, String path) {
        try {
            InputStream inputStream = context.getResources().openRawResource(i);
            FileOutputStream outputStream = new FileOutputStream(path);
            c(inputStream, outputStream);
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.e(TAG, "inputStreamRaw()  ", e);
        }
    }

    public static boolean inputStreamAssets(Context context, String name, String path) {
        try {
            InputStream inputStream = context.getResources().getAssets().open(name);
            FileOutputStream outputStream = new FileOutputStream(path);
            FileCustomUtils.c(inputStream, outputStream);
            inputStream.close();
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "inputStreamAssets() ", e);
            return false;
        }
    }

    public static void c(@NotNull InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = 0;
                read = inputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
     * 拷贝一个目录
     *
     * @param dir
     * @param copy
     */
    public static boolean copyDir(File dir, File copy) {
        return copyDir(dir, copy, true);
    }

    /**
     * 拷贝一个目录
     *
     * @param dir
     * @param copy
     * @param includeDir 是否包含dir目录
     * @return
     */
    public static boolean copyDir(File dir, File copy, boolean includeDir) {
        try {
            if (includeDir) {
                FileUtils.copyDirectoryToDirectory(dir, copy);
            } else {
                FileUtils.copyDirectory(dir, copy);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return copy.exists();
    }

    /**
     * 拷贝一个文件
     *
     * @param file
     * @param copy
     */
    public static boolean copyFile(File file, File copy) {
        try {
            FileUtils.copyFile(file, copy);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return copy.exists();
    }

    /**
     * 拷贝一个文件或目录
     *
     * @param file
     * @param copy
     * @return
     */
    public static boolean copy(File file, File copy) {
        if (file.isFile()) {
            copyFile(file, copy);
        } else {
            copyDir(file, copy);
        }
        return copy.exists();
    }

    /**
     * 删除一个文件或目录
     *
     * @param file
     * @return
     */
    public static boolean delete(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 清理一个目录
     *
     * @param dir
     * @return
     */
    public static boolean cleanDirectory(File dir) {
        try {
            if (!exists(dir) || dir.isFile()) {
                return false;
            }
            FileUtils.cleanDirectory(dir);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
     * 移动一个文件或目录
     *
     * @param file 文件
     * @param dest 目标路径
     * @return
     */
    public static boolean move(File file, File dest) {
        if (!exists(file)) {
            return false;
        }
        if (file.isDirectory()) {
            try {
                FileUtils.moveDirectory(file, dest);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileUtils.moveFile(file, dest);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
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
