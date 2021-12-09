package com.mcal.apkprotector.fastzip;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FastZipOutputStream extends ZipOutputStream {
    public static final int BUFFER_SIZE = 8192;

    public FastZipOutputStream(OutputStream out) {
        super(out);
    }

    public void copyZipEntry(@NotNull ZipEntry zipEntry, final @NotNull ZipFile zipFile) {
        try {
            ZipEntry newEntry = new ZipEntry(zipEntry.getName());

            putNextEntry(newEntry);
            BufferedInputStream stream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = stream.read(buffer)) > 0) {
                write(buffer, 0, len);
            }
            closeEntry();
        } catch (IOException e) {
            System.out.println(" " + e);
        }
    }
    /*public static final int BUFFER_SIZE = 8192;
    private static final String[] n = {"resources.arsc", ".jpg", ".jpeg", ".png", ".gif", ".wav", ".mp2", ".mp3", ".ogg", ".aac", ".mpg", ".mpeg", ".mid", ".midi", ".smf", ".jet", ".rtttl", ".imy", ".xmf", ".mp4", ".m4a", ".m4v", ".3gp", ".3gpp", ".3g2", ".3gpp2", ".amr", ".awb", ".wma", ".wmv"};

    public FastZipOutputStream(OutputStream out) {
        super(out);
    }

    public static long calcCrc32(File file) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            final byte[] buf = new byte[1024 * 4];
            final CRC32 crc32 = new CRC32();
            int len;
            while ((len = inputStream.read(buf, 0, buf.length)) != -1) {
                crc32.update(buf, 0, len);
            }
            return crc32.getValue();
        }
    }

    public void copyZipEntry(@NonNull ZipEntry zipEntry, final ZipFile zipFile) {
        try {
            ZipEntry newEntry = new ZipEntry(zipEntry.getName());

            for (String endsWith : n) {
                if (zipEntry.equals(endsWith) && !zipEntry.isDirectory()) {
                    final long length = zipEntry.getSize();
                    newEntry.setMethod(ZipOutputStream.STORED);
                    newEntry.setCrc(calcCrc32(new File(zipFile.getName())));
                    newEntry.setSize(length);
                    newEntry.setCompressedSize(length);
                    setLevel(ZipOutputStream.STORED);
                }
            }

            putNextEntry(newEntry);
            BufferedInputStream stream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = stream.read(buffer)) > 0) {
                write(buffer, 0, len);
            }
            closeEntry();
        } catch (IOException e) {
            System.out.println(" " + e);
        }
    }*/
}