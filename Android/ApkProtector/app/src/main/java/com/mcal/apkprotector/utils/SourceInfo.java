package com.mcal.apkprotector.utils;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class SourceInfo {

    private String packageLabel;
    private String packageName;
    private String packagePath;

    @Contract(pure = true)
    private SourceInfo(String packageLabel, String packageName, String path) {
        this.packageLabel = packageLabel;
        this.packageName = packageName;
        this.packagePath = path;
    }

    public static void initialise(String path, @NotNull MyAppInfo processService) {
        try {
            JSONObject json = new JSONObject();
            json.put("package_label", processService.getAppName());
            json.put("package_name", processService.getPackage());
            String filePath = path + "/" + processService.getPackage();
            FileUtils.writeStringToFile(new File(filePath + "/info.mz"), json.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String path, MyAppInfo processService) {
        try {
            File infoFile = new File(path + "/" + processService.getPackage() + "/info.mz");
            infoFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public static String getLabel(String directory) {
        try {
            File infoFile = new File(directory + "/info.mz");
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("package_label");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static SourceInfo getSourceInfo(File infoFile) {
        try {
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return new SourceInfo(json.getString("package_label"), json.getString("package_name"), infoFile.getAbsolutePath());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPackageLabel() {
        return packageLabel;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPackagePath() {
        return packagePath;
    }
}
