package com.mcal.apkprotector.patchers;

import com.mcal.apkprotector.App;
import com.mcal.apkprotector.BuildConfig;
import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.LoggerUtils;
import com.mcal.apkprotector.utils.file.FileUtils;
import com.mcal.apkprotector.utils.security.SecurityUtils;

import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.writer.builder.DexBuilder;
import org.jf.dexlib2.writer.io.MemoryDataStore;
import org.jf.smali.Smali;
import org.jf.smali.SmaliOptions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import bin.util.StreamUtil;

import static com.mcal.apkprotector.patchers.ManifestPatcher.customApplication;
import static com.mcal.apkprotector.patchers.ManifestPatcher.customApplicationName;
import static com.mcal.apkprotector.patchers.ManifestPatcher.packageName;

public class DexPatcher {
    public static byte[] processDex() throws Exception {
        FileUtils.inputStreamAssets(App.getContext(), "dexloader.dex", Constants.OUTPUT_PATH + File.separator + "dexloader.dex");

        DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new BufferedInputStream(new FileInputStream(Preferences.getDexLoader())));
        DexBuilder dexBuilder = new DexBuilder(Opcodes.getDefault());
        try {
            File smali = new File(Constants.SMALI_PATH + File.separator + "ProtectApplication.smali");
            String src = new String(StreamUtil.readBytes(new FileInputStream(smali)), StandardCharsets.UTF_8);
            if (customApplication) {
                LoggerUtils.writeLog("Custom application detected");
                if (customApplicationName.startsWith(".")) {
                    LoggerUtils.writeLog("Custom application detected");
                    if (packageName == null) {
                        LoggerUtils.writeLog("Package name is null.");
                        throw new NullPointerException("Package name is null.");
                    }
                    customApplicationName = packageName + customApplicationName;
                }
                src = src.replace("android.app.Application", customApplicationName);
            }
            src = src.replace("$PROTECT_KEY", CommonUtils.encryptStrings(Preferences.getProtectKey(), 2))
                        .replace("$DEX_DIR", CommonUtils.encryptStrings(Preferences.getFolderDexesName(), 2))
                        .replace("$DEX_PREFIX", CommonUtils.encryptStrings(Preferences.getPrefixDexesName(), 2))
                        .replace("$DATA", "")
                        .replace("$DEX_SUFIX", CommonUtils.encryptStrings(Preferences.getSuffixDexesName(), 2));
            src = src.replace("com/mcal/apkprotector",
                    Preferences.getPackageName().replace(".", "/"));
            ClassDef classDef = Smali.assembleSmaliFile(src, dexBuilder, new SmaliOptions());
            if (classDef == null) {
                LoggerUtils.writeLog("Parse smali failed");
                throw new Exception("Parse smali failed");
            }
            for (DexBackedClassDef dexBackedClassDef : dex.getClasses()) {
                try {
                    dexBuilder.internClassDef(dexBackedClassDef);
                } catch (org.jf.util.ExceptionWithContext e) {
                    LoggerUtils.writeLog("Error: " + e);
                }
            }
        } catch (IOException e) {
            LoggerUtils.writeLog("Error: " + e);
        }
        MemoryDataStore store = new MemoryDataStore();
        dexBuilder.writeTo(store);
        return Arrays.copyOf(store.getBufferData(), store.getSize());
    }
}
