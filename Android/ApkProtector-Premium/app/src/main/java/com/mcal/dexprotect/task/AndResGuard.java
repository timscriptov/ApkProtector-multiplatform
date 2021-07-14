package com.mcal.dexprotect.task;

import com.mcal.dexprotect.data.Constants;
import com.mcal.dexprotect.data.resguard.ResGuard;
import com.mcal.dexprotect.utils.file.FileUtils;
import com.mcal.resguard.resourceproguard.InputParam;
import com.mcal.resguard.resourceproguard.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AndResGuard {
    public static void proguard2(File input, File output, File file, String packageName) {
        InputParam.Builder builder = new InputParam.Builder();
        builder.setWhiteList(new ArrayList<>());
        builder.setCompressFilePattern(new ArrayList<>());
        File rules = new File(file, "proguard-resources.json");
        if (rules.exists() && rules.isFile()) {
            try {
                builder.setKeepRoot(ResGuard.resguardFile(rules).getKeepRoot());
                String fixedResName = ResGuard.resguardFile(rules).getFixedResName();
                if (fixedResName != null && fixedResName.length() > 0) {
                    builder.setFixedResName(ResGuard.resguardFile(rules).getFixedResName());
                }
                String mappingFile = ResGuard.resguardFile(rules).getMappingFile();
                if (mappingFile != null) {
                    File file3 = new File(file, mappingFile);
                    if (file3.exists() && file3.isFile()) {
                        builder.setMappingFile(new File(ResGuard.resguardFile(rules).getMappingFile()));
                    }
                }
                List<String> whiteList = ResGuard.resguardFile(rules).getWhiteList();
                if (whiteList != null) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (int i = 0; i < whiteList.get(0).length(); i++) {
                        arrayList.add(packageName + "." + whiteList.get(i));
                    }
                    builder.setWhiteList(arrayList);
                }
                List<String> commpressFilePattern = ResGuard.resguardFile(rules).getCommpressFilePattern();
                if (commpressFilePattern != null) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (int i = 0; i < commpressFilePattern.get(0).length(); i++) {
                        arrayList.add(commpressFilePattern.get(i));
                    }
                    builder.setCompressFilePattern(arrayList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File tmpApk = new File(Constants.RELEASE_PATH + File.separator + "app-temp.apk");
        if (FileUtils.copyFileStream(input, tmpApk)) {
            builder.setApkPath(tmpApk.getAbsolutePath());
            File folderMapping = new File(output, "andresguard");
            builder.setOutBuilder(folderMapping.getAbsolutePath());
            Main.gradleRun(builder.create());
            File outputApk = new File(folderMapping, "app-temp_unsigned.apk");
            if (outputApk.exists()) {
                outputApk.renameTo(new File(Constants.RELEASE_PATH + File.separator + "app-temp-encrypted.apk"));
            }
            for(File f : folderMapping.listFiles()) {
                if(f.getName().endsWith(".txt")) continue;
                FileUtils.delete(f);
            }
        }
    }
}