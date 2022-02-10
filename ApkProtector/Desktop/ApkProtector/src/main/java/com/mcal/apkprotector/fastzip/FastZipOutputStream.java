package com.mcal.apkprotector.fastzip;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FastZipOutputStream extends ZipOutputStream {
    public static final int BUFFER_SIZE = 8192;

    public FastZipOutputStream(OutputStream out) {
        super(out);
    }

    public void copyZipEntry(ZipEntry zipEntry, final ZipFile zipFile) {
        try {
            ZipEntry newEntry = new ZipEntry(zipEntry.getName());
            String name = newEntry.getName();
            int len = 0;

            if (CRC32Utils.isNoCompressFileType(name)) {
                newEntry.setMethod(ZipEntry.STORED);
                newEntry.setSize(zipFile.getInputStream(zipEntry).available());
                newEntry.setCrc(CRC32Utils.calculateCrc(zipFile.getInputStream(zipEntry)).getValue());
                newEntry.setCompressedSize(-1);

                len += 30 + name.length();
                int needed = (4 - (len % 4)) % 4;
                if (needed != 0) {
                    newEntry.setExtra(new byte[needed]);
                }
            } else {
                newEntry.setMethod(ZipEntry.DEFLATED);
            }

            putNextEntry(newEntry);
            BufferedInputStream stream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            byte[] buffer = new byte[BUFFER_SIZE];

            while ((len = stream.read(buffer)) > 0) {
                write(buffer, 0, len);
            }
            closeEntry();
        } catch (IOException e) {
            System.out.println(" " + e);
        }
    }
}