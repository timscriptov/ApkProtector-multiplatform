package com.mcal.apkprotector.task;

import android.content.Context;
import android.util.Log;

import com.mcal.apkprotector.R;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.FileCustomUtils;
import com.mcal.apkprotector.utils.FileUtils;
import com.mcal.apkprotector.utils.StringUtils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jf.dexlib2.DexFileFactory;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.DexFile;
import org.jf.dexlib2.immutable.ImmutableDexFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterInputStream;

/**
 * Шифрование дексов
 */

public class DexEncrypt {
    private static String TAG = "ApkProtector";
    private static String xpath;
    private static DeflaterInputStream isx;
    private static final ArrayList<String> ignoredLibs = new ArrayList<>();

    public static boolean enDex(@NotNull Context context) {
        xpath = context.getFilesDir().getAbsolutePath();

        File assets = new File(xpath + "/gen/assets");
        if (!assets.exists()) {
            assets.mkdir();
        }
        try {
            if (Preferences.isOptimizeDexBoolean()) {
                opt_dex(context, "classes.dex");
                new File(xpath + "/gen/classes.dex").delete();
                encDex();
                File directory2 = new File(xpath + "/gen/assets/open-dex");
                File[] files2 = directory2.listFiles();
                for (File file : files2) {
                    if (new File(file.getAbsolutePath()).exists()) {
                        if (file.getName().endsWith(".dex")) {
                            move_dex(file.getName());
                            new File(xpath + "/gen/assets/open-dex/" + file.getName()).delete();
                        }
                    }
                }
            } else {
                File directory = new File(xpath + "/gen");
                File[] files = directory.listFiles();
                for (File file : files) {
                    if (file.getName().endsWith(".dex")) {
                        Log.e("", "Encrypting: " + file.getName());
                        okEnDex(file.getName());
                        new File(xpath + "/gen/" + file.getName()).delete();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "enDex() failed: ", e);
            return false;
        }
    }

    private static void okEnDex(String name) throws Exception {
        File assets = new File(xpath + "/gen/assets");
        if (!assets.exists()) {
            assets.mkdir();
        }
        File folder = new File(xpath + "/gen/assets/" + Preferences.getDexFolderName());
        if (!folder.exists()) {
            folder.mkdir();
        }
        FileInputStream is = new FileInputStream(xpath + (!Preferences.isOptimizeDexBoolean() ? "/gen/" : "/gen/assets/sec-dex/") + name);
        FileOutputStream os = new FileOutputStream(xpath + "/gen/assets/" + Preferences.getDexFolderName() + File.separator + getRandomClassesName(name));
        isx = new DeflaterInputStream(is);
        exfr(isx, os);
        isx.close();
    }

    public static String getRandomClassesName(String name) {
        if (Preferences.getRandomName()) {
            return name.replace("classes", name.equals("classes.dex") ? CommonUtils.getRandomString(8) : CommonUtils.getRandomString(8)).replace("dex", Preferences.getReplaceDexName());
        } else {
            return name.replace("classes", name.equals("classes.dex") ? "classes-v1" : "classes-v").replace("dex", "bin");
        }
    }

    private static void opt_dex(Context context, String name) {
        try {
            File folder = new File(xpath + "/gen/assets/sec-dex");
            if (!folder.exists()) {
                folder.mkdir();
            }
            File folder2 = new File(xpath + "/gen/assets/open-dex");
            if (!folder2.exists()) {
                folder2.mkdir();
            }
            loadIgnoredLibs(context);
            DexFile dexFile = DexFileFactory.loadDexFile(new File(xpath + "/gen/" + name).getAbsolutePath(), Opcodes.forApi(19));

            List<ClassDef> classes = new ArrayList<>();
            List<ClassDef> classes2 = new ArrayList<>();

            for (ClassDef classDef : dexFile.getClasses()) {
                if (Preferences.isOptimizeDexBoolean()) {
                    if (isIgnored(classDef.getType())) {
                        classes2.add(classDef);
                    } else {
                        classes.add(classDef);
                    }
                } else {
                    classes.add(classDef);
                }
            }
            if (classes.size() != 0)
                DexFileFactory.writeDexFile(xpath + "/gen/assets/sec-dex/" + name, new ImmutableDexFile(Opcodes.forApi(19), classes));
            if (classes2.size() != 0) {
                DexFileFactory.writeDexFile(xpath + "/gen/assets/open-dex/" + name, new ImmutableDexFile(Opcodes.forApi(19), classes2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void move_dex(String name) throws Exception {
        File assets = new File(xpath + "/gen/assets");
        if (!assets.exists()) {
            assets.mkdir();
        }
        InputStream inputStream = new FileInputStream(new File(xpath + "/gen/assets/open-dex/" + name));
        FileOutputStream outputStream;
        if (name.equals("classes.dex")) {
            outputStream = new FileOutputStream(xpath + "/gen/classes-op.dex");
        } else {
            outputStream = new FileOutputStream(xpath + "/gen/" + name);
        }
        FileUtils.c(inputStream, outputStream);
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    private static void encDex() {
        try {
            //publishProgress(MyAppInfo.getAppName(), "DEX - Encrypting...");
            File directory = new File(xpath + "/gen/assets/sec-dex");
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.getName().endsWith(".dex")) {
                    okEnDex(file.getName());
                    new File(xpath + "/gen/assets/sec-dex/" + file.getName()).delete();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "encDex() failed: ", e);
        }
    }

    private static void nDnv(@NotNull int[] iArr, @NotNull int[] iArr2) {
        int i = iArr2[0];
        int i2 = iArr2[1];
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[0];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[1];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[2];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[3];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[4];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[5];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[6];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[7];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[8];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[9];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[10];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[11];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[12];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[13];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[14];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[15];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[16];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[17];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[18];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[19];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[20];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[21];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[22];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[23];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[24];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[25];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[26];
        iArr2[0] = ((i << 3) | (i >>> 29)) ^ i2;
        iArr2[1] = i2;
    }

    @NotNull
    @Contract(pure = true)
    private static int[] FxIjsF(@NotNull int[] iArr) {
        int[] iArr2 = new int[27];
        int i = iArr[0];
        iArr2[0] = i;
        int[] iArr3 = new int[]{iArr[1], iArr[2], iArr[3]};
        for (int i2 = 0; i2 < 26; i2++) {
            iArr3[i2 % 3] = (((iArr3[i2 % 3] >>> 8) | (iArr3[i2 % 3] << 24)) + i) ^ i2;
            i = ((i << 3) | (i >>> 29)) ^ iArr3[i2 % 3];
            iArr2[i2 + 1] = i;
        }
        return iArr2;
    }

    private static void exfr(@NotNull InputStream inputStream, OutputStream outputStream) throws Exception {
        //char[] toCharArray = Preferences.getSharedPreferences().getString("DexKey", Utils.sealing(Utils.getBuildSerial(context))).toCharArray();
        char[] toCharArray = Preferences.getProtectKeyString().toCharArray();
        int[] iArr = new int[4];
        int i = 1;
        int i2 = i + 1;
        iArr[0] = toCharArray[0] | (toCharArray[i] << 16);
        i = i2 + 1;
        char c = toCharArray[i2];
        i2 = i + 1;
        iArr[1] = c | (toCharArray[i] << 16);
        i = i2 + 1;
        c = toCharArray[i2];
        i2 = i + 1;
        iArr[2] = c | (toCharArray[i] << 16);
        i = i2 + 1;
        c = toCharArray[i2];
        i2 = i + 1;
        iArr[3] = c | (toCharArray[i] << 16);
        int[] iArr2 = new int[2];
        i = i2 + 1;
        c = toCharArray[i2];
        i2 = i + 1;
        iArr2[0] = c | (toCharArray[i] << 16);
        iArr2[1] = toCharArray[i2] | (toCharArray[i2 + 1] << 16);
        iArr = FxIjsF(iArr);
        byte[] bArr = new byte[8192];
        int i3 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read >= 0) {
                int i4 = i3 + read;
                int i5 = 0;
                while (i3 < i4) {
                    int i6 = i3 % 8;
                    int i7 = i6 / 4;
                    int i8 = i3 % 4;
                    if (i6 == 0) {
                        nDnv(iArr, iArr2);
                    }
                    bArr[i5] = (byte) (((byte) (iArr2[i7] >> (i8 * 8))) ^ bArr[i5]);
                    i3++;
                    i5++;
                }
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    @Contract(pure = true)
    private static boolean isIgnored(String className) {
        for (String ignoredClass : ignoredLibs) {
            if (className.startsWith(ignoredClass)) {
                return true;
            }
        }
        return false;
    }

    private static void loadIgnoredLibs(Context context) {
        String[] ignored = Preferences.isUserIgnoredClasses(ignored_libs(context)).split("\n");
        for (String s : ignored) {
            ignoredLibs.add(StringUtils.toClassName(s));
        }
    }

    @Nullable
    private static String ignored_libs(Context context) {
        try {
            InputStream open = context.getResources().openRawResource(R.raw.ignored_class);
            ByteArrayOutputStream xf = new ByteArrayOutputStream();
            byte[] bArr = new byte[5242880];
            while (true) {
                int read = open.read(bArr);
                if (read == -1) {
                    break;
                }
                xf.write(bArr, 0, read);
            }
            return new String(xf.toByteArray());
        } catch (Exception e) {
            Log.e(TAG, "ignored_libs() ", e);
            return null;
        }
    }
}