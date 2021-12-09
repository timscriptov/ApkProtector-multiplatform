package com.mcal.dexprotect.fastzip;

import android.content.Context;

import com.mcal.dexprotect.data.Constants;
import com.mcal.dexprotect.data.Preferences;
import com.mcal.dexprotect.patchers.DexPatcher;
import com.mcal.dexprotect.utils.LoggerUtils;
import com.mcal.dexprotect.utils.file.ScopedStorage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FastZip {
    private static final String[] n = {"resources.arsc", ".jpg", ".jpeg", ".png", ".gif", ".wav", ".mp2", ".mp3", ".ogg", ".aac", ".mpg", ".mpeg", ".mid", ".midi", ".smf", ".jet", ".rtttl", ".imy", ".xmf", ".mp4", ".m4a", ".m4v", ".3gp", ".3gpp", ".3g2", ".3gpp2", ".amr", ".awb", ".wma", ".wmv"};

    public static void extract(File zip, File extractDir) throws IOException {
        extractDir.mkdirs();
        ZipFile apk = new ZipFile(zip);
        Enumeration<? extends ZipEntry> entries = apk.entries();
        LoggerUtils.writeLog("\n************ APK EXTRACTING ***********");
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            LoggerUtils.writeLog("Entry: " + entry.getName());
            String name = entry.getName();
            //extract only manifest and dex files
            if (name.equals("AndroidManifest.xml")
                    || name.matches("classes\\.dex")
                    || name.matches("classes\\d+\\.dex")
                    && !entry.isDirectory()) {

                BufferedInputStream bis = new BufferedInputStream(apk.getInputStream(entry));
                File f = new File(extractDir, entry.getName());
                if (!f.exists()) f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);
                byte[] buffer = new byte[2048];
                int len = 0;
                while ((len = bis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                LoggerUtils.writeLog("Success extract: " + extractDir.getAbsolutePath() + File.separator + entry.getName());
            }
        }
    }

    public static void extract(String zipPath, String extractDirPath) throws IOException {
        extract(new File(zipPath), new File(extractDirPath));
    }

    public static void repack(Context context, File inZip, File outZip) throws Exception {
        ZipFile zipFile = new ZipFile(inZip);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        FastZipOutputStream fzos = new FastZipOutputStream(new BufferedOutputStream(new FileOutputStream(outZip)));
        LoggerUtils.writeLog("\n************ APK REPACKING ***********");

        //pack other patched files
        String[] files = new File(Constants.OUTPUT_PATH).list();

        for (String file : files) {
            if (file.endsWith("assets") || file.endsWith(".dex")) continue;
            file = file.replace(Constants.OUTPUT_PATH + File.separator, "");
            LoggerUtils.writeLog("Entry: " + file);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(Constants.OUTPUT_PATH + File.separator + file));
            byte[] buffer = new byte[2048];
            int len = 0;
            ZipEntry newEntry =
                    new ZipEntry(file);
            fzos.putNextEntry(newEntry);

            while ((len = bis.read(buffer)) > 0) {
                fzos.write(buffer, 0, len);
            }
            fzos.closeEntry();
        }

        //pack dexes
        File[] dexes = new File(Constants.ASSETS_PATH).listFiles();
        for (File f : dexes) {
            if (f.isDirectory()) continue;
            String file = f.getAbsolutePath();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            file = file.replace(Constants.ASSETS_PATH, "assets/apkprotector_dex")
                    .replace("apkprotector_dex\\", "apkprotector_dex/")
                    .replace("apkprotector_dex", Preferences.getFolderDexesName())
                    .replace("classes-v", Preferences.getPrefixDexesName());
            LoggerUtils.writeLog("Entry: " + file);
            byte[] buffer = new byte[2048];
            int len = 0;
            ZipEntry newEntry = new ZipEntry(file);
            fzos.putNextEntry(newEntry);

            while ((len = bis.read(buffer)) > 0) {
                fzos.write(buffer, 0, len);
            }
            fzos.closeEntry();
        }

        //repack files from original apk
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            String name = entry.getName();

            if (name.startsWith("META-INF/")) continue;
            for (String endsWith : n) {
                if (name.equals(endsWith) || name.equals("resources.arsc") && !entry.isDirectory()) {
                    fzos.setLevel(ZipOutputStream.STORED);
                } else {
                    fzos.setLevel(ZipOutputStream.DEFLATED);
                }
            }

            if (name.equals("AndroidManifest.xml")
                    || name.matches("classes\\.dex")
                    || name.matches("classes\\d+\\.dex")
                    && !entry.isDirectory()) {
                continue;
            }

            for (String file1 : files) {
                file1 = file1.replace(ScopedStorage.getFilesDir() + File.separator, "");
                if (file1.equals(name)) continue;
            }
            LoggerUtils.writeLog("Entry: " + entry.getName());
            fzos.copyZipEntry(entry, zipFile);
        }

        //pack dexloader
        LoggerUtils.writeLog("Entry: classes.dex");

        byte[] dexData = DexPatcher.processDex(context);
        ByteArrayInputStream bis = new ByteArrayInputStream(dexData);
        byte[] buffer = new byte[2048];
        int len = 0;
        fzos.putNextEntry(new ZipEntry("classes.dex"));
        while ((len = bis.read(buffer)) > 0) {
            fzos.write(buffer, 0, len);
        }
        fzos.closeEntry();

        fzos.close();
    }

    public static void repack(Context context, String inZip, String outZip) throws Exception {
        repack(context, new File(inZip), new File(outZip));
    }
}