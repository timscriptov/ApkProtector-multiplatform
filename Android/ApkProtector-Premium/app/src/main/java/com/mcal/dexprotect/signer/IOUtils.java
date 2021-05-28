package com.mcal.dexprotect.signer;

import android.content.Context;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IOUtils {
    @AnyThread
    @NonNull
    public static File getTempFile(@NotNull Context context) throws IOException {
        File extDir = new File(context.getFilesDir() + File.separator + "cache");
        if (extDir == null) throw new FileNotFoundException("External storage not available.");
        if (!extDir.exists() && !extDir.mkdirs()) {
            throw new IOException("Cannot create cache directory in the external storage.");
        }
        return File.createTempFile("file_" + System.currentTimeMillis(), ".cached", extDir);
    }
}
