package com.mcal.apkprotector.task;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.JsonUtils;
import com.mcal.resguard.resourceproguard.InputParam;
import com.mcal.resguard.resourceproguard.Main;
import com.mcal.resguard.util.TypedValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class AndResGuard {
    public static void proguard2(File input, File output, File file, String packageName) throws Throwable {
        String readFile2String;
        InputParam.Builder builder = new InputParam.Builder();
        builder.setWhiteList(new ArrayList<>());
        builder.setCompressFilePattern(new ArrayList<>());
        File rules = new File(file, "proguard-resources.json");
        if (rules.exists() && rules.isFile() && (readFile2String = FileIOUtils.readFile2String(rules)) != null && readFile2String.trim().length() != 0) {
            try {
                JSONObject jSONObject = new JSONObject(readFile2String);
                builder.setKeepRoot(JsonUtils.getBoolean(jSONObject, "keepRoot", false));
                String string = JsonUtils.getString(jSONObject, "fixedResName", null);
                if (string != null && string.length() > 0) {
                    builder.setFixedResName(string);
                }
                String string2 = JsonUtils.getString(jSONObject, "mappingFile", null);
                if (string2 != null) {
                    File file3 = new File(file, string2);
                    if (file3.exists() && file3.isFile()) {
                        builder.setMappingFile(file3);
                    }
                }
                JSONArray jSONArray = JsonUtils.getJSONArray(jSONObject, "whiteList", null);
                if (jSONArray != null) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(packageName + "." + jSONArray.getString(i));
                    }
                    builder.setWhiteList(arrayList);
                }
                JSONArray jSONArray2 = JsonUtils.getJSONArray(jSONObject, "compressFilePattern", null);
                if (jSONArray2 != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        arrayList2.add(jSONArray2.getString(i2));
                    }
                    builder.setCompressFilePattern(arrayList2);
                }
            } catch (JSONException e) {
                throw new JSONException("Error parsing JSON, please check if JSON is wrong!пјљ\n" + e.getMessage());
            }
        }
        File file4 = new File(output, "resources.apk");
        FileUtils.copy(input, file4);
        builder.setApkPath(file4.getAbsolutePath());
        File file5 = new File(output, "andresguard");// РџР°РїРєР° СЃ mapping
        builder.setOutBuilder(file5.getAbsolutePath());
        Main.gradleRun(builder.create());
        File file6 = new File(file5, "resources_unsigned.apk");
        if (file6.exists()) {
            FileUtils.move(file6.getAbsolutePath(), input.getAbsolutePath());
        }
        FileUtils.delete(file4);
        FileUtils.deleteFilesInDirWithFilter(file5, file1 -> !file1.getName().endsWith(TypedValue.TXT_FILE));
    }
}