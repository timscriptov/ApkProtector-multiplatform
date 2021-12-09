package com.mcal.dex.utils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FileUtils {
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp;
        for (String s : tempList) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + s);
            } else {
                temp = new File(path + File.separator + s);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + s);//先删除文件夹里面的文件
                delFolder(path + "/" + s);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    public static void mkdir(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFloor(String dir) {
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                deleteFiles(new File(file.getAbsolutePath()));
            } else {
                deleteFloor(file.getAbsolutePath());
            }
        }
    }

    /**
     * Метод для удаления папки с файлами
     *
     * @param path - путь к папке
     */
    public static void deleteFiles(File path) {
        try {
            Class main = Class.forName("java.io.File");
            Constructor contructor = main.getConstructor(String.class);
            Constructor contructor2 = main.getConstructor(String.class, String.class);
            Object obj = contructor.newInstance(path);
            Method method = main.getDeclaredMethod("delete");
            method.setAccessible(true);
            String[] entries = ((File) obj).list();
            for (String s : entries) {
                Object obj2 = contructor2.newInstance(((File) obj).getPath(), s);
                method.invoke(obj2);
            }
            boolean b = (boolean) method.invoke(obj);
            System.out.println(b);
        } catch (IllegalArgumentException | InstantiationException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException a) {
            a.printStackTrace();
        }
    }

    /**
     * Метод удаления файлов
     */
    public static void delete() {
        try {
            Class main = Class.forName("java.io.File");
            Method method = main.getDeclaredMethod("delete", new Class[]{int.class});
            method.setAccessible(true);
            method.invoke(new Object(), 0);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException a) {
            a.printStackTrace();
        }
    }
}
