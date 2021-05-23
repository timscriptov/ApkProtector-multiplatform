package com.mcal.fastzip;

import java.io.*;
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
           // newEntry.setMethod(zipEntry.getMethod());
            putNextEntry(newEntry);
            BufferedInputStream stream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while((len = stream.read(buffer)) > 0) {
                write(buffer, 0, len);
            }
            closeEntry();
        } catch (IOException e) {
            System.out.println(" " + e);
        }
    }
}
