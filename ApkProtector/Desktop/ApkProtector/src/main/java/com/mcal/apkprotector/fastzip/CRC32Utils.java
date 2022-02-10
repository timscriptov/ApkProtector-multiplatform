package com.mcal.apkprotector.fastzip;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

public class CRC32Utils {
    public static final String[] noCompressExt = {"resources.arsc", ".jpg", ".jpeg", ".png",
            ".gif", ".wav", ".mp2", ".mp3", ".ogg", ".aac", ".mpg", ".mpeg", ".mid", ".midi",
            ".smf", ".jet", ".rtttl", ".imy", ".xmf", ".mp4", ".m4a", ".m4v", ".3gp", ".3gpp",
            ".3g2", ".3gpp2", ".amr", ".awb", ".wma", ".wmv"};

    public static boolean isNoCompressFileType(String entryName) {
        for (int i = 0; i < noCompressExt.length; i++) {
            if (entryName.endsWith(noCompressExt[i])) {
                //Log.d("DEBUG", entryName + " not compress.");
                return true;
            }
        }
        return false;
    }

    public static @NotNull CRC32 calculateCrc(@NotNull InputStream input) throws IOException {
        CRC32 crc = new CRC32();
        int bytesRead;
        byte[] buffer = new byte[8192];
        while((bytesRead = input.read(buffer)) != -1) {
            crc.update(buffer, 0, bytesRead);
        }
        return crc;
    }
}