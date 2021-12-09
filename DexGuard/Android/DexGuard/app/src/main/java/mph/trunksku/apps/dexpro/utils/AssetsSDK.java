package mph.trunksku.apps.dexpro.utils;

import android.content.res.AssetManager;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;

public class AssetsSDK {
	
	public static void install(Context context) throws Exception {
        // Assets.copyAssets(context.getAssets(), "sdk", getRootDir(context));
        String aaptName= "";
        if (!new File(getRootDir(context), "proguard.pro").exists()) {
            copyFile(context.getAssets(), "default_proguard.pro", getRootDir(context), "proguard.pro");
        }
		if (!new File(getRootDir(context), "dictionary.txt").exists()) {
            copyFile(context.getAssets(), "default_dictionary.txt", getRootDir(context), "dictionary.txt");
        }
		/*if (!new File(getRootDir(context), "bin/d8.jar").exists()) {
            copyFile(context.getAssets(), "d8.jar", getRootDir(context), "bin/d8.jar");
        }

		if (!new File(getRootDir(context), "bin/android.jar").exists()) {
            Assets.copyFile(context.getAssets(), "android.jar", getRootDir(context), "bin/android.jar");
        }*/

    }
    
	public static File getRootDir(Context context) {
        return context.getFilesDir();
    }
	
    public static void copyAssets(AssetManager assets, String assetPath, File destFolder)
    throws Exception {
        String[] childs = assets.list(assetPath);
        if (childs.length == 0) {
            copyFile(assets, assetPath, destFolder);
        } else {
            copyFolder(assets, assetPath, destFolder);
        }
    }

    private static void copyFolder(AssetManager assets, String assetPath, File destFolder)
    throws Exception {
      //  Log.d(TAG, "copyFolder() called with: assets = [" + assets + "], assetPath = [" + assetPath + "], destFolder = [" + destFolder + "]");
        String[] names = assets.list(assetPath);
        for (String name : names) {
            copyAssets(assets, assetPath + "/" + name, destFolder);
        }
    }

    public static void copyFile(AssetManager assets, String assetPath, File destFolder, String name)
    throws Exception {
       // Log.d(TAG, "copyFile() called with: assets = [" + assets + "], assetPath = [" + assetPath + "], destFolder = [" + destFolder + "]");
        File outFile = new File(destFolder, name);
        outFile.getParentFile().mkdirs();
        InputStream input = assets.open(assetPath);
        OutputStream output = new FileOutputStream(outFile);
        copy(input, output);
        input.close();
        output.close();
    }

    public static void copyFile(AssetManager assets, String assetPath, File destFolder)
    throws Exception {
       // Log.d(TAG, "copyFile() called with: assets = [" + assets + "], assetPath = [" + assetPath + "], destFolder = [" + destFolder + "]");
        File outFile = new File(destFolder, assetPath);
        outFile.getParentFile().mkdirs();
        InputStream input = assets.open(assetPath);
        OutputStream output = new FileOutputStream(outFile);
        copy(input, output);
        input.close();
        output.close();
    }

    private static void copy(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[1024];
        while (true) {
            int readCount = in.read(buffer);
            if (readCount < 0) {
                break;
            }
            out.write(buffer, 0, readCount);
        }
    }

    public static void copy(File file, OutputStream out) throws Exception {
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        try {
            copy(in, out);
        } finally {
            in.close();
        }
    }

    public static void copy(InputStream in, File file) throws Exception {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        try {
            copy(in, out);
        } finally {
            out.close();
        }
    }
    
}
