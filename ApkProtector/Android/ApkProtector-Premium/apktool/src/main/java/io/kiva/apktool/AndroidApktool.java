package io.kiva.apktool;

import android.content.Context;
import android.content.res.AssetManager;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import brut.apktool.Main;
import brut.common.BrutException;

public class AndroidApktool {

    public static void initAndroid(Context context) {
        homeDir = context.getDir("apktool", Context.MODE_PRIVATE);
        sAndroidAssets = context.getAssets();
    }

    public static void run(String[] args) throws InterruptedException, BrutException, IOException {
        Main.main(args);
    }


    public static InputStream open(String fileName) throws IOException {
        return sAndroidAssets.open(fileName);
    }

    public static File extra(String fileName, String outDir) throws IOException {
        String name = new File(fileName).getName();
        File outFile = new File(outDir, name);
        if (!outFile.exists()) {
            InputStream in = open(fileName);
            FileOutputStream fo = new FileOutputStream(outFile);
            IOUtils.copy(in, fo);
            in.close();
            fo.close();
        }
        return outFile;
    }

    public static File extra(String fileName) throws IOException {
        return extra(fileName, getHomeDir().getPath());
    }

    public static File getHomeDir() {
        return homeDir;
    }

    private static AssetManager sAndroidAssets;
    private static File homeDir;
}
