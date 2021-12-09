package com.mcal.apkprotector.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mcal.apkprotector.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ResGuard {
    @SerializedName("keepRoot")
    @Expose
    private Boolean keepRoot;
    @SerializedName("fixedResName")
    @Expose
    private String fixedResName;
    @SerializedName("mappingFile")
    @Expose
    private String mappingFile;
    @SerializedName("whiteList")
    @Expose
    private List<String> whiteList = null;
    @SerializedName("commpressFilePattern")
    @Expose
    private List<String> commpressFilePattern = null;

    /**
     * No args constructor for use in serialization
     */
    public ResGuard() {
    }

    /**
     * @param mappingFile
     * @param keepRoot
     * @param whiteList
     * @param commpressFilePattern
     * @param fixedResName
     */
    public ResGuard(Boolean keepRoot, String fixedResName, String mappingFile, List<String> whiteList, List<String> commpressFilePattern) {
        super();
        this.keepRoot = keepRoot;
        this.fixedResName = fixedResName;
        this.mappingFile = mappingFile;
        this.whiteList = whiteList;
        this.commpressFilePattern = commpressFilePattern;
    }

    public static ResGuard resguardFile(File file) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            String content = FileUtils.readFile(file.getAbsolutePath(), StandardCharsets.UTF_8);
            return gson.fromJson(content, ResGuard.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean getKeepRoot() {
        return keepRoot;
    }

    public void setKeepRoot(Boolean keepRoot) {
        this.keepRoot = keepRoot;
    }

    public String getFixedResName() {
        return fixedResName;
    }

    public void setFixedResName(String fixedResName) {
        this.fixedResName = fixedResName;
    }

    public String getMappingFile() {
        return mappingFile;
    }

    public void setMappingFile(String mappingFile) {
        this.mappingFile = mappingFile;
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    public List<String> getCommpressFilePattern() {
        return commpressFilePattern;
    }

    public void setCommpressFilePattern(List<String> commpressFilePattern) {
        this.commpressFilePattern = commpressFilePattern;
    }
}