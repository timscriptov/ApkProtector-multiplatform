package com.mcal.dexprotect.task;

import android.content.Context;

import com.mcal.dexprotect.data.Constants;
import com.mcal.dexprotect.utils.file.FileUtils;
import com.mcal.dexprotect.utils.file.ScopedStorage;

import java.io.File;

public class Environment {
    public Context context;

    public Environment(Context context) {
        this.context = context;
    }

    public void init() {
        installKeys();
        installDexloader();
    }

    public void installKeys() {
        File keyFolder = new File(ScopedStorage.getFilesDir() + File.separator + "key");
        if (!keyFolder.exists()) {
            keyFolder.mkdir();
        }
        FileUtils.inputStreamAssets(context, "key/testkey.pk8", keyFolder + File.separator + "testkey.pk8");
        FileUtils.inputStreamAssets(context, "key/testkey.x509.pem", keyFolder + File.separator + "testkey.x509.pem");
    }

    public void installDexloader() {
        FileUtils.inputStreamAssets(context, "dexloader.dex", Constants.OUTPUT_PATH + File.separator + "dexloader.dex");
    }
}
