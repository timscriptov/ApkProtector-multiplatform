package com.mcal.apkprotector.patchers;

import com.mcal.apkprotector.App;
import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.FileCustomUtils;
import com.mcal.apkprotector.utils.LoggerUtils;

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
        String dexPath = Constants.OUTPUT_PATH + File.separator + "dexloader.dex";
        FileCustomUtils.inputStreamAssets(App.getContext(), "dexloader.dex", dexPath);

        DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new BufferedInputStream(new FileInputStream(dexPath)));
        DexBuilder dexBuilder = new DexBuilder(Opcodes.getDefault());
        try {
            /*File[] smaliFiles = new File(Constants.SMALI_PATH).listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return !pathname.isDirectory() && pathname.getAbsolutePath().endsWith(".smali");
                }
            });*/
            //File[] smaliFiles = new File(Constants.SMALI_PATH).listFiles();
            File smali = new File(Constants.SMALI_PATH + File.separator + "ProtectApplication.smali");
            //for (File smali : smaliFiles) {
            String src = new String(StreamUtil.readBytes(new FileInputStream(smali)), StandardCharsets.UTF_8);
            //    switch (smali.getName()) {
            //case "ProtectApplication.smali":
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
                    .replace("$DEX_DIR", CommonUtils.encryptStrings(Preferences.getDexDir(), 2))
                    .replace("$DEX_PREFIX", CommonUtils.encryptStrings(Preferences.getDexPrefix(), 2))
                    .replace("$DEX_SUFIX", CommonUtils.encryptStrings(Preferences.getDexSuffix(), 2));
            //break;
            //}
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
                    continue;
                }
            }
            //}
        } catch (IOException e) {
            LoggerUtils.writeLog(" " + e);
        }
        /*try (InputStream fis = new FileInputStream(Constants.TOOLS_PATH + File.separator + "ProtectApplication.smali")) {
            String src = new String(StreamUtil.readBytes(fis), "utf-8");
            if (customApplication) {
                if (customApplicationName.startsWith(".")) {
                    if (packageName == null)
                        throw new NullPointerException("Package name is null.");
                    customApplicationName = packageName + customApplicationName;
                }
                src = src.replace("android.app.Application", customApplicationName);
            }
            ClassDef classDef = Smali.assembleSmaliFile(src, dexBuilder, new SmaliOptions());

            if (classDef == null)
                throw new Exception("Parse smali failed");
            for (DexBackedClassDef dexBackedClassDef : dex.getClasses()) {
                dexBuilder.internClassDef(dexBackedClassDef);
            }
        }*/
        MemoryDataStore store = new MemoryDataStore();
        dexBuilder.writeTo(store);
        return Arrays.copyOf(store.getBufferData(), store.getSize());
    }
}
