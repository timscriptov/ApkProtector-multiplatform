/* Orginal file: FileUtils.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.utils*/
package com.mcal.apkprotector.dD;

//import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

//public class FileUtils {
public class FileUtils {
    //public static void writeString(File file, String str) throws IOException {
    public static void writeString(File file, String str) throws IOException {
        //BufferedWriter out = new BufferedWriter(new FileWriter(file));
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        //try {
        try {
            //out.write(str);
            out.write(str);
            //} catch (IOException e) {
        } catch (IOException e) {
            //System.out.println("Exception " + e);
            System.out.println(llII1lJ(186) + e);
            //} finally {
        } finally {
            //out.close();
            out.close();
            //}
        }
        //}
    }

    //public static @NotNull String readFile(String path, Charset encoding) throws IOException {
    public static @NotNull String readFile(String path, Charset encoding) throws IOException {
        //byte[] encoded = readAllBytes(new FileInputStream(path));
        byte[] encoded = readAllBytes(new FileInputStream(path));
        //return new String(encoded, encoding);
        return new String(encoded, encoding);
        //}
    }

    //public static byte @NotNull [] readAllBytes(@NotNull InputStream is) throws IOException {
    public static byte @NotNull [] readAllBytes(@NotNull InputStream is) throws IOException {
        //ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //byte[] buffer = new byte[2048];
        byte[] buffer = new byte[2048];
        //int len = 0;
        int len = 0;
        //while ((len = is.read(buffer)) > 0)
        while ((len = is.read(buffer)) > 0)
            //bos.write(buffer, 0, len);
            bos.write(buffer, 0, len);
        //is.close();
        is.close();
        //return bos.toByteArray();
        return bos.toByteArray();
        //}
    }

    //public static void writeFile(InputStream inputStream, FileOutputStream outputPath) throws IOException {
    public static void writeFile(InputStream inputStream, FileOutputStream outputPath) throws IOException {
        //int size = inputStream.available();
        int size = inputStream.available();
        //byte[] bucket = new byte[size];
        byte[] bucket = new byte[size];

        //inputStream.read(bucket);
        inputStream.read(bucket);

        //try (FileOutputStream outputStream = outputPath) {
        try (FileOutputStream outputStream = outputPath) {
            //outputStream.write(bucket);
            outputStream.write(bucket);
            //}
        }
        //}
    }
//}

    static String llII1lJ(int llII1lI) {
        byte[] llII1ll = null;
        try {
            if (llII1lI == -1) {
                if (llII1lI == -2) {
                } else if (llII1lI == -3) {
                } else if (llII1lI == -4) {
                }
            }
            if (llII1lI == 186) {
                llII1ll = new byte[]{-1, -62, -39, -33, -54, -50, -45, -43, -44, -102};

                for (int llII1l1 = 0; llII1l1 < llII1ll.length; llII1l1++) {
                    llII1ll[llII1l1] = (byte) (llII1ll[llII1l1] ^ llII1lI);
                }
                {
                    return new String(llII1ll, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/
}//

//
  	