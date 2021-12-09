package com.mcal.dexprotect.data.dto.applist;

import android.graphics.drawable.Drawable;

public class PackageInfoHolder {
    public String packageLabel = "";
    public String packageName = "";
    public String packageVersion = "";
    public String packageFilePath = "";
    public Drawable packageIcon;

    public String getPackageLabel() {
        return packageLabel;
    }
}