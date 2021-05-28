package com.mcal.dexprotect.patchers;

import android.content.Context;

import com.mcal.dexprotect.App;
import com.mcal.dexprotect.BuildConfig;
import com.mcal.dexprotect.R;
import com.mcal.dexprotect.data.Constants;
import com.mcal.dexprotect.data.Preferences;
import com.mcal.dexprotect.task.Security;
import com.mcal.dexprotect.utils.CommonUtils;
import com.mcal.dexprotect.utils.FileCustomUtils;
import com.mcal.dexprotect.utils.LoggerUtils;
import com.mcal.dexprotect.utils.SecurityUtils;
import com.mcal.dexprotect.utils.Utils;

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

import static com.mcal.dexprotect.patchers.ManifestPatcher.customApplication;
import static com.mcal.dexprotect.patchers.ManifestPatcher.customApplicationName;
import static com.mcal.dexprotect.patchers.ManifestPatcher.packageName;

public class DexPatcher {

    public static byte[] processDex() throws Exception {
        String dexPath = Constants.OUTPUT_PATH + File.separator + "dexloader.dex";
        FileCustomUtils.inputStreamAssets(App.getContext(), "dexloader.dex", dexPath);

        DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new BufferedInputStream(new FileInputStream(dexPath)));
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
            if (SecurityUtils.isVerifyInstaller() || BuildConfig.DEBUG) {
                src = src.replace("$PROTECT_KEY", CommonUtils.encryptStrings(Preferences.getProtectKey(), 2))
                        .replace("$DEX_DIR", CommonUtils.encryptStrings(Preferences.getDexDir(), 2))
                        .replace("$DEX_PREFIX", CommonUtils.encryptStrings(Preferences.getDexPrefix(), 2))
                        .replace("$DATA", CommonUtils.encryptStrings(Security.write(Constants.RELEASE_PATH + File.separator + "app-temp.apk"), 2))
                        .replace("$DEX_SUFIX", CommonUtils.encryptStrings(Preferences.getDexSuffix(), 2));
            }
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
