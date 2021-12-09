package com.secure.dex.patchers;

import com.secure.dex.data.Constants;
import com.secure.dex.data.Preferences;
import com.secure.dex.utils.CommonUtils;
import com.secure.dex.utils.FileUtils;
import com.secure.dex.utils.LoggerUtils;
import org.jetbrains.annotations.NotNull;
import org.jf.baksmali.Baksmali;
import org.jf.baksmali.BaksmaliOptions;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.smali.Smali;
import org.jf.smali.SmaliOptions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DexPatcher {
    public static byte @NotNull [] patchDex(DexBackedDexFile dex) throws IOException {
        File output = new File(Constants.SMALI_PATH);
        if (!Baksmali.disassembleDexFile(dex, output, Runtime.getRuntime().availableProcessors(), new BaksmaliOptions())) {
            System.out.println("Failed dex decompile");
            FileUtils.delete(output);
            System.exit(-1);
        }
        List<File> smalies = FileUtils.getFiles(output.listFiles());
        for (File smali : smalies) {
            String smaliData = new String(Files.readAllBytes(Paths.get(smali.getAbsolutePath())));
            Pattern pattern = Pattern.compile("com.secure.dex".replaceFirst("\\.", "(.)"));
            Matcher matcher = pattern.matcher(smaliData);
            while (matcher.find())
                smaliData = smaliData.replaceFirst(matcher.group(), Preferences.getPackageName().replace(".", matcher.group(1)));
            smaliData = smaliData.replace("$PROTECT_KEY", enc(Preferences.getProtectKey()))
                    .replace("$ASSETS_DIR_DEX", enc(Preferences.getAssetsDirDex()))
                    .replace("$DEX_SUFFIX", enc(Preferences.getDexSuffix()))
                    .replace("ProxyApplication", Preferences.getProxyAppName());
            if (ManifestPatcher.customApplication) {
                LoggerUtils.writeLog("Custom application detected");
                if (ManifestPatcher.customApplicationName.startsWith(".")) {
                    LoggerUtils.writeLog("Custom application detected");
                    if (ManifestPatcher.packageName == null) {
                        LoggerUtils.writeLog("Package name is null.");
                        throw new NullPointerException("Package name is null.");
                    }
                    ManifestPatcher.customApplicationName = ManifestPatcher.packageName + ManifestPatcher.customApplicationName;
                }
                smaliData = smaliData.replace("$REAL_APP", ManifestPatcher.customApplicationName);
            } else {
                smaliData = smaliData.replace("$REAL_APP", "");
            }
            Files.writeString(Paths.get(smali.getAbsolutePath()), smaliData, StandardOpenOption.WRITE);
        }
        File outputDex = new File(Constants.OUTPUT_PATH, "classes.dex");
        SmaliOptions options = new SmaliOptions();
        options.outputDexFile = outputDex.getAbsolutePath();
        if (!Smali.assemble(options, smalies
                .stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList()))) {
            System.out.println("failed assemble smali");
            FileUtils.delete(output);
            FileUtils.delete(outputDex);
            System.exit(-1);
        }
        byte[] dexBytes = Files.readAllBytes(Paths.get(outputDex.getAbsolutePath()));
        FileUtils.delete(output);
        FileUtils.delete(outputDex);
        return dexBytes;
    }

    private static @NotNull String enc(String text) {
        String str = CommonUtils.encryptStrings(text, 2);
        byte[] charset = str.getBytes(StandardCharsets.UTF_8);
        return new String(charset, StandardCharsets.UTF_8);
    }
}