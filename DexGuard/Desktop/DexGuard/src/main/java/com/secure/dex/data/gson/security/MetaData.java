package com.secure.dex.data.gson.security;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Package")
    @Expose
    public String _package;
    @SerializedName("VName")
    @Expose
    public String vName;
    @SerializedName("Signature")
    @Expose
    public String signature;
    @SerializedName("VCode")
    @Expose
    public Integer vCode;
    @SerializedName("AntiMod")
    @Expose
    public Boolean antiMod;
    @SerializedName("HookChecker")
    @Expose
    public Boolean hookChecker;
    @SerializedName("SignChecker")
    @Expose
    public Boolean signChecker;
    @SerializedName("Welcome")
    @Expose
    public Boolean welcome;
    @SerializedName("WelcomeMode")
    @Expose
    public String welcomeMode;
    @SerializedName("WelcomeMsg")
    @Expose
    public String welcomeMsg;
    @SerializedName("DeviceLock")
    @Expose
    public Boolean deviceLock;
    @SerializedName("DeviceID")
    @Expose
    public String deviceID;
    @SerializedName("CrashCatcher")
    @Expose
    public Boolean crashCatcher;
    @SerializedName("InstallerChecker")
    @Expose
    public Boolean installerChecker;
    @SerializedName("EmuChecker")
    @Expose
    public Boolean emuChecker;
    @SerializedName("RootChecker")
    @Expose
    public Boolean rootChecker;

    public MetaData(String name, String _package, String vName, String signature, Integer vCode, Boolean antiMod, Boolean hookChecker, Boolean signChecker, Boolean welcome, String welcomeMode, String welcomeMsg, Boolean deviceLock, String deviceID, Boolean crashCatcher, Boolean installerChecker, Boolean emuChecker, Boolean rootChecker) {
        super();
        this.name = name;
        this._package = _package;
        this.vName = vName;
        this.signature = signature;
        this.vCode = vCode;
        this.antiMod = antiMod;
        this.hookChecker = hookChecker;
        this.signChecker = signChecker;
        this.welcome = welcome;
        this.welcomeMode = welcomeMode;
        this.welcomeMsg = welcomeMsg;
        this.deviceLock = deviceLock;
        this.deviceID = deviceID;
        this.crashCatcher = crashCatcher;
        this.installerChecker = installerChecker;
        this.emuChecker = emuChecker;
        this.rootChecker = rootChecker;
    }
}