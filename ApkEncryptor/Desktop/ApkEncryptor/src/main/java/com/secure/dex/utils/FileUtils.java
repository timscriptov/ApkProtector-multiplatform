package com.secure.dex.utils;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static void byteToFile(String outputFile, byte @NotNull [] bytes) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(bytes);
        }
    }

    public static @NotNull List<File> getFiles(File @NotNull [] files) {
        List<File> list = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                list.addAll(getFiles(file.listFiles()));
            } else {
                list.add(file);
            }
        }
        return list;
    }

    public static void delete(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    delete(f);
                }
                file.delete();
            } else {
                file.delete();
            }
        }
    }

    public static void deleteDir(@NotNull File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (!Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }

    public static String getHomePath() {
        return System.getProperty("user.dir");
    }

    public static @NotNull String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static int readInt(byte @NotNull [] data, int off) {
        return data[off + 3] << 24 | (data[off + 2] & 0xFF) << 16 | (data[off + 1] & 0xFF) << 8
                | data[off] & 0xFF;
    }

    public static boolean isExists(String path) {
        if (path == null) {
            return false;
        }
        File file = new File(path);
        return file != null && file.exists();
    }

    public static void writeFile(String path, String content, boolean append) {
        try {
            File f = new File(path);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }

            if (!f.exists()) {
                f.createNewFile();
                f = new File(path);
            }

            FileWriter fw = new FileWriter(f, append);
            if (content != null && !"".equals(content)) {
                fw.write(content);
                fw.flush();
            }

            fw.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    public static void createDir(String dir) {
        File f = new File(dir);
        if (!f.exists()) {
            f.mkdirs();
        }

    }

    public static boolean copy(String srcFile, String destFile) {
        FileInputStream in = null;
        FileOutputStream out = null;

        boolean var5;
        try {

            if (!isExists(destFile)) {
                File dst = new File(destFile);
                dst.getParentFile().mkdirs();
                dst.createNewFile();
            }

            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] bytes = new byte[1024];

            int c;
            while ((c = in.read(bytes)) != -1) {
                out.write(bytes, 0, c);
            }

            out.flush();
            boolean var6 = true;
            return var6;
        } catch (Exception var20) {
            System.out.println("Error!" + var20);
            var5 = false;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }
            }

            if (null != out) {
                try {
                    out.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

        }
        return var5;
    }
}
