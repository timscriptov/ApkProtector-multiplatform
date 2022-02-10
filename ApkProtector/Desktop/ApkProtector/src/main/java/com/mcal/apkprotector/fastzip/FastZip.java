package com.mcal.apkprotector.fastzip;

import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.patchers.DexPatcher;
import com.mcal.apkprotector.utils.FileUtils;
import com.mcal.apkprotector.utils.LoggerUtils;
import org.jetbrains.annotations.NotNull;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FastZip {

    public static final String[] n = {"resources.arsc", ".jpg", ".jpeg", ".png", ".gif", ".wav", ".mp2", ".mp3", ".ogg", ".aac", ".mpg", ".mpeg", ".mid", ".midi", ".smf", ".jet", ".rtttl", ".imy", ".xmf", ".mp4", ".m4a", ".m4v", ".3gp", ".3gpp", ".3g2", ".3gpp2", ".amr", ".awb", ".wma", ".wmv"};

    public static void extract(File zip, @NotNull File extractDir) throws IOException {
        extractDir.mkdirs();
        ZipFile apk = new ZipFile(zip);
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) apk.entries();
        LoggerUtils.writeLog("----------Apk Extracting----------------");
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
                fos.close();
                LoggerUtils.writeLog("Success extract: " + extractDir.getAbsolutePath() + File.separator + entry.getName());
            }
        }
    }

    public static void extract(String zipPath, String extractDirPath) throws IOException {
        extract(new File(zipPath), new File(extractDirPath));
    }

    public static void repack(File inZip, File outZip) throws Exception {
        ZipFile zipFile = new ZipFile(inZip);
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();
        FastZipOutputStream fzos = new FastZipOutputStream(new BufferedOutputStream(new FileOutputStream(outZip)));
        LoggerUtils.writeLog("----------Apk Repacking----------------");

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
            file = file.replace(Constants.ASSETS_PATH, "assets/protector")
                    .replace("protector\\", "protector/")
                    .replace("protector", Preferences.getDexDir())
                    .replace("classes-v", Preferences.getDexPrefix());
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
                if (name.equals(endsWith) && !entry.isDirectory()) {
                    fzos.setLevel(ZipEntry.STORED);
                } else {
                    fzos.setLevel(ZipEntry.DEFLATED);
                }
            }

            if (name.equals("AndroidManifest.xml")
                    || name.matches("classes\\.dex")
                    || name.matches("classes\\d+\\.dex")
                    && !entry.isDirectory()) {
                continue;
            }

            for (String file1 : files) {
                file1 = file1.replace(FileUtils.getWorkPath() + File.separator, "");
                if (file1.equals(name)) continue;
            }
            LoggerUtils.writeLog("Entry: " + entry.getName());
            fzos.copyZipEntry(entry, zipFile);
        }

        //pack dexloader
        LoggerUtils.writeLog("Entry: classes.dex");
        DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new BufferedInputStream(new FileInputStream(Constants.DEXLOADER_PATH)));
        byte[] dexData = DexPatcher.patchDex(dex);
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

    public static void repack(String inZip, String outZip) throws Exception {
        repack(new File(inZip), new File(outZip));
    }
}