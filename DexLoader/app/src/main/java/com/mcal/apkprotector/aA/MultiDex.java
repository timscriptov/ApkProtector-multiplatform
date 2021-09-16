/* Orginal file: MultiDex.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.multidex*/
package com.mcal.apkprotector.aA;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;

import com.mcal.apkprotector.bB.Const;
import com.mcal.apkprotector.dD.CommonUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

import dalvik.system.DexFile;

//public class MultiDex {
public class MultiDex {
    //static final String TAG = "MultiDex";
    static final String TAG = llIIIJI(262);
    //private static final String OLD_SECONDARY_FOLDER_NAME = Const.SECONDARY_DEXES;
    private static final String OLD_SECONDARY_FOLDER_NAME = Const.SECONDARY_DEXES;
    //private static final String CODE_CACHE_NAME = Const.CODE_CACHE;
    private static final String CODE_CACHE_NAME = Const.CODE_CACHE;
    //private static final String CODE_CACHE_SECONDARY_FOLDER_NAME = Const.SECONDARY_DEXES;
    private static final String CODE_CACHE_SECONDARY_FOLDER_NAME = Const.SECONDARY_DEXES;
    //private static final int MAX_SUPPORTED_SDK_VERSION = 20;
    private static final int MAX_SUPPORTED_SDK_VERSION = 20;
    //private static final int MIN_SDK_VERSION = 4;
    private static final int MIN_SDK_VERSION = 4;
    //private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    //private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
    //private static final String NO_KEY_PREFIX = "";
    private static final String NO_KEY_PREFIX = llIIIJI(388);
    //private static final Set<File> installedApk = new HashSet<>();
    private static final Set<File> installedApk = new HashSet<>();
    //private static final boolean IS_VM_MULTIDEX_CAPABLE =
    private static final boolean IS_VM_MULTIDEX_CAPABLE =
            //false;
            false;

    //private MultiDex() {
    private MultiDex() {
        //}
    }

    //

    //public static void install(Context context) {
    public static void install(Context context) {
        //Log.i(TAG, "Installing application");
        Log.i(TAG, llIIIJI(463));
        //if (IS_VM_MULTIDEX_CAPABLE) {
        if (IS_VM_MULTIDEX_CAPABLE) {
            //Log.i(TAG, "VM has multidex support, MultiDex support library is disabled.");
            Log.i(TAG, llIIIJI(482));
            //return;
            return;
            //}
        }
        //if (Build.VERSION.SDK_INT < MIN_SDK_VERSION) {
        if (Build.VERSION.SDK_INT < MIN_SDK_VERSION) {
            //throw new RuntimeException("MultiDex installation failed. SDK " + Build.VERSION.SDK_INT
            throw new RuntimeException(llIIIJI(513) + Build.VERSION.SDK_INT
                    //+ " is unsupported. Min SDK version is " + MIN_SDK_VERSION + ".");
                    + llIIIJI(525) + MIN_SDK_VERSION + llIIIJI(533));
            //}
        }
        //try {
        try {
            //ApplicationInfo applicationInfo = getApplicationInfo(context);
            ApplicationInfo applicationInfo = getApplicationInfo(context);
            //if (applicationInfo == null) {
            if (applicationInfo == null) {
                //Log.i(TAG, "No ApplicationInfo available, i.e. running on a test Context:"
                Log.i(TAG, llIIIJI(575)
                        //+ " MultiDex support library is disabled.");
                        + llIIIJI(579));
                //return;
                return;
                //}
            }
            //doInstallation(context,
            doInstallation(context,
                    //new File(applicationInfo.sourceDir),
                    new File(applicationInfo.sourceDir),
                    //new File(applicationInfo.dataDir),
                    new File(applicationInfo.dataDir),
                    //CODE_CACHE_SECONDARY_FOLDER_NAME,
                    CODE_CACHE_SECONDARY_FOLDER_NAME,
                    //NO_KEY_PREFIX,
                    NO_KEY_PREFIX,
                    //true);
                    true);
            //} catch (Exception e) {
        } catch (Exception e) {
            //Log.e(TAG, "MultiDex installation failure", e);
            Log.e(TAG, llIIIJI(642), e);
            //throw new RuntimeException("MultiDex installation failed (" + e.getMessage() + ").");
            throw new RuntimeException(llIIIJI(655) + e.getMessage() + llIIIJI(667));
            //}
        }
        //Log.i(TAG, "install done");
        Log.i(TAG, llIIIJI(680));
        //}
    }

    //

    //public static void installInstrumentation(Context instrumentationContext,
    public static void installInstrumentation(Context instrumentationContext,
                                              //Context targetContext) {
                                              Context targetContext) {
        //Log.i(TAG, "Installing instrumentation");
        Log.i(TAG, llIIIJI(714));
        //if (IS_VM_MULTIDEX_CAPABLE) {
        if (IS_VM_MULTIDEX_CAPABLE) {
            //Log.i(TAG, "VM has multidex support, MultiDex support library is disabled.");
            Log.i(TAG, llIIIJI(733));
            //return;
            return;
            //}
        }
        //if (Build.VERSION.SDK_INT < MIN_SDK_VERSION) {
        if (Build.VERSION.SDK_INT < MIN_SDK_VERSION) {
            //throw new RuntimeException("MultiDex installation failed. SDK " + Build.VERSION.SDK_INT
            throw new RuntimeException(llIIIJI(764) + Build.VERSION.SDK_INT
                    //+ " is unsupported. Min SDK version is " + MIN_SDK_VERSION + ".");
                    + llIIIJI(776) + MIN_SDK_VERSION + llIIIJI(784));
            //}
        }
        //try {
        try {
            //ApplicationInfo instrumentationInfo = getApplicationInfo(instrumentationContext);
            ApplicationInfo instrumentationInfo = getApplicationInfo(instrumentationContext);
            //if (instrumentationInfo == null) {
            if (instrumentationInfo == null) {
                //Log.i(TAG, "No ApplicationInfo available for instrumentation, i.e. running on a"
                Log.i(TAG, llIIIJI(826)
                        //+ " test Context: MultiDex support library is disabled.");
                        + llIIIJI(830));
                //return;
                return;
                //}
            }
            //ApplicationInfo applicationInfo = getApplicationInfo(targetContext);
            ApplicationInfo applicationInfo = getApplicationInfo(targetContext);
            //if (applicationInfo == null) {
            if (applicationInfo == null) {
                //Log.i(TAG, "No ApplicationInfo available, i.e. running on a test Context:"
                Log.i(TAG, llIIIJI(871)
                        //+ " MultiDex support library is disabled.");
                        + llIIIJI(875));
                //return;
                return;
                //}
            }
            //String instrumentationPrefix = instrumentationContext.getPackageName() + ".";
            String instrumentationPrefix = instrumentationContext.getPackageName() + llIIIJI(898);
            //File dataDir = new File(applicationInfo.dataDir);
            File dataDir = new File(applicationInfo.dataDir);
            //doInstallation(targetContext,
            doInstallation(targetContext,
                    //new File(instrumentationInfo.sourceDir),
                    new File(instrumentationInfo.sourceDir),
                    //dataDir,
                    dataDir,
                    //instrumentationPrefix + CODE_CACHE_SECONDARY_FOLDER_NAME,
                    instrumentationPrefix + CODE_CACHE_SECONDARY_FOLDER_NAME,
                    //instrumentationPrefix,
                    instrumentationPrefix,
                    //false);
                    false);
            //doInstallation(targetContext,
            doInstallation(targetContext,
                    //new File(applicationInfo.sourceDir),
                    new File(applicationInfo.sourceDir),
                    //dataDir,
                    dataDir,
                    //CODE_CACHE_SECONDARY_FOLDER_NAME,
                    CODE_CACHE_SECONDARY_FOLDER_NAME,
                    //NO_KEY_PREFIX,
                    NO_KEY_PREFIX,
                    //false);
                    false);
            //} catch (Exception e) {
        } catch (Exception e) {
            //Log.e(TAG, "MultiDex installation failure", e);
            Log.e(TAG, llIIIJI(996), e);
            //throw new RuntimeException("MultiDex installation failed (" + e.getMessage() + ").");
            throw new RuntimeException(llIIIJI(1009) + e.getMessage() + llIIIJI(1021));
            //}
        }
        //Log.i(TAG, "Installation done");
        Log.i(TAG, llIIIJI(1034));
        //}
    }

    //

    //private static void doInstallation(Context mainContext, File sourceApk, File dataDir,
    private static void doInstallation(Context mainContext, File sourceApk, File dataDir,
                                       //String secondaryFolderName, String prefsKeyPrefix,
                                       String secondaryFolderName, String prefsKeyPrefix,
                                       //boolean reinstallOnPatchRecoverableException) throws IOException,
                                       boolean reinstallOnPatchRecoverableException) throws IOException,
            //IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
            //InvocationTargetException, NoSuchMethodException, SecurityException,
            InvocationTargetException, NoSuchMethodException, SecurityException,
            //ClassNotFoundException, InstantiationException {
            ClassNotFoundException, InstantiationException {
        //synchronized (installedApk) {
        synchronized (installedApk) {
            //if (installedApk.contains(sourceApk)) {
            if (installedApk.contains(sourceApk)) {
                //return;
                return;
                //}
            }
            //installedApk.add(sourceApk);
            installedApk.add(sourceApk);
            //if (Build.VERSION.SDK_INT > MAX_SUPPORTED_SDK_VERSION) {
            if (Build.VERSION.SDK_INT > MAX_SUPPORTED_SDK_VERSION) {
                //Log.w(TAG, "MultiDex is not guaranteed to work in SDK version "
                Log.w(TAG, llIIIJI(1166)
                        //+ Build.VERSION.SDK_INT + ": SDK version higher than "
                        + Build.VERSION.SDK_INT + llIIIJI(1178)
                        //+ MAX_SUPPORTED_SDK_VERSION + " should be backed by "
                        + MAX_SUPPORTED_SDK_VERSION + llIIIJI(1186)
                        //+ "runtime with built-in multidex capabilty but it's not the "
                        + llIIIJI(1190)
                        //+ "case here: java.vm.version=\""
                        + llIIIJI(1194)
                        //+ System.getProperty("java.vm.version") + "\"");
                        + System.getProperty(llIIIJI(1202)) + llIIIJI(1207));
                //}
            }
            //

            //ClassLoader loader;
            ClassLoader loader;
            //try {
            try {
                //loader = mainContext.getClassLoader();
                loader = mainContext.getClassLoader();
                //} catch (RuntimeException e) {
            } catch (RuntimeException e) {
                //

                //Log.w(TAG, "Failure while trying to obtain Context class loader. " +
                Log.w(TAG, llIIIJI(1254) +
                        //"Must be running in test mode. Skip patching.", e);
                        llIIIJI(1258), e);
                //return;
                return;
                //}
            }
            //if (loader == null) {
            if (loader == null) {
                //

                //Log.e(TAG,
                Log.e(TAG,
                        //"Context class loader is null. Must be running in test mode. "
                        llIIIJI(1291)
                                //+ "Skip patching.");
                                + llIIIJI(1295));
                //return;
                return;
                //}
            }
            //try {
            try {
                //clearOldDexDir(mainContext);
                clearOldDexDir(mainContext);
                //} catch (Throwable t) {
            } catch (Throwable t) {
                //Log.w(TAG, "Something went wrong when trying to clear old MultiDex extraction, "
                Log.w(TAG, llIIIJI(1333)
                        //+ "continuing without cleaning.", t);
                        + llIIIJI(1337), t);
                //}
            }
            //File dexDir = getDexDir(mainContext, dataDir, secondaryFolderName);
            File dexDir = getDexDir(mainContext, dataDir, secondaryFolderName);
            //

            //

            //

            //MultiDexExtractor extractor = new MultiDexExtractor(sourceApk, dexDir);
            MultiDexExtractor extractor = new MultiDexExtractor(sourceApk, dexDir);
            //IOException closeException = null;
            IOException closeException = null;
            //try {
            try {
                //List<? extends File> files =
                List<? extends File> files =
                        //extractor.load(mainContext, prefsKeyPrefix, false);
                        extractor.load(mainContext, prefsKeyPrefix, false);
                //try {
                try {
                    //installSecondaryDexes(loader, dexDir, files);
                    installSecondaryDexes(loader, dexDir, files);
                    //

                    //} catch (IOException e) {
                } catch (IOException e) {
                    //if (!reinstallOnPatchRecoverableException) {
                    if (!reinstallOnPatchRecoverableException) {
                        //throw e;
                        throw e;
                        //}
                    }
                    //Log.w(TAG, "Failed to install extracted secondary dex files, retrying with "
                    Log.w(TAG, llIIIJI(1476)
                            //+ "forced extraction", e);
                            + llIIIJI(1480), e);
                    //files = extractor.load(mainContext, prefsKeyPrefix, true);
                    files = extractor.load(mainContext, prefsKeyPrefix, true);
                    //installSecondaryDexes(loader, dexDir, files);
                    installSecondaryDexes(loader, dexDir, files);
                    //}
                }
                //} finally {
            } finally {
                //try {
                try {
                    //extractor.close();
                    extractor.close();
                    //CommonUtils.deleteDirectory(dexDir);
                    CommonUtils.deleteDirectory(dexDir);
                    //} catch (IOException e) {
                } catch (IOException e) {
                    //

                    //

                    //closeException = e;
                    closeException = e;
                    //}
                }
                //}
            }
            //if (closeException != null) {
            if (closeException != null) {
                //throw closeException;
                throw closeException;
                //}
            }
            //}
        }
        //}
    }

    //private static ApplicationInfo getApplicationInfo(Context context) {
    private static ApplicationInfo getApplicationInfo(Context context) {
        //try {
        try {
            //

            //return context.getApplicationInfo();
            return context.getApplicationInfo();
            //} catch (RuntimeException e) {
        } catch (RuntimeException e) {
            //

            //Log.w(TAG, "Failure while trying to obtain ApplicationInfo from Context. " +
            Log.w(TAG, llIIIJI(1642) +
                    //"Must be running in test mode. Skip patching.", e);
                    llIIIJI(1646), e);
            //return null;
            return null;
            //}
        }
        //}
    }

    //

    //

    //static boolean isVMMultidexCapable(String versionString) {
    static boolean isVMMultidexCapable(String versionString) {
        //boolean isMultidexCapable = false;
        boolean isMultidexCapable = false;
        //if (versionString != null) {
        if (versionString != null) {
            //Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(versionString);
            Matcher matcher = Pattern.compile(llIIIJI(1709)).matcher(versionString);
            //if (matcher.matches()) {
            if (matcher.matches()) {
                //try {
                try {
                    //int major = Integer.parseInt(matcher.group(1));
                    int major = Integer.parseInt(matcher.group(1));
                    //int minor = Integer.parseInt(matcher.group(2));
                    int minor = Integer.parseInt(matcher.group(2));
                    //isMultidexCapable = (major > VM_WITH_MULTIDEX_VERSION_MAJOR)
                    isMultidexCapable = (major > VM_WITH_MULTIDEX_VERSION_MAJOR)
                            //|| ((major == VM_WITH_MULTIDEX_VERSION_MAJOR)
                            || ((major == VM_WITH_MULTIDEX_VERSION_MAJOR)
                            //&& (minor >= VM_WITH_MULTIDEX_VERSION_MINOR));
                            && (minor >= VM_WITH_MULTIDEX_VERSION_MINOR));
                    //} catch (NumberFormatException e) {
                } catch (NumberFormatException e) {
                    //

                    //}
                }
                //}
            }
            //}
        }
        //Log.i(TAG, "VM with version " + versionString +
        Log.i(TAG, llIIIJI(1837) + versionString +
                //(isMultidexCapable ?
                (isMultidexCapable ?
                        //" has multidex support" :
                        llIIIJI(1850) :
                        //" does not have multidex support"));
                        llIIIJI(1854)));
        //return isMultidexCapable;
        return isMultidexCapable;
        //}
    }

    //private static void installSecondaryDexes(ClassLoader loader, File dexDir,
    private static void installSecondaryDexes(ClassLoader loader, File dexDir,
                                              //List<? extends File> files)
                                              List<? extends File> files)
    //throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
            //InvocationTargetException, NoSuchMethodException, IOException, SecurityException,
            InvocationTargetException, NoSuchMethodException, IOException, SecurityException,
            //ClassNotFoundException, InstantiationException {
            ClassNotFoundException, InstantiationException {
        //if (!files.isEmpty()) {
        if (!files.isEmpty()) {
            //if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= 19) {
                //V19.install(loader, files, dexDir);
                V19.install(loader, files, dexDir);
                //} else if (Build.VERSION.SDK_INT >= 14) {
            } else if (Build.VERSION.SDK_INT >= 14) {
                //V14.install(loader, files);
                V14.install(loader, files);
                //} else {
            } else {
                //V4.install(loader, files);
                V4.install(loader, files);
                //}
            }
            //}
        }
        //}
    }

    //

    //private static Field findField(Object instance, String name) throws NoSuchFieldException {
    private static Field findField(Object instance, String name) throws NoSuchFieldException {
        //for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            //try {
            try {
                //Field field = clazz.getDeclaredField(name);
                Field field = clazz.getDeclaredField(name);
                //if (!field.isAccessible()) {
                if (!field.isAccessible()) {
                    //field.setAccessible(true);
                    field.setAccessible(true);
                    //}
                }
                //return field;
                return field;
                //} catch (NoSuchFieldException e) {
            } catch (NoSuchFieldException e) {
                //

                //}
            }
            //}
        }
        //throw new NoSuchFieldException("Field " + name + " not found in " + instance.getClass());
        throw new NoSuchFieldException(llIIIJI(2159) + name + llIIIJI(2167) + instance.getClass());
        //}
    }

    //

    //private static Method findMethod(Object instance, String name, Class<?>... parameterTypes)
    private static Method findMethod(Object instance, String name, Class<?>... parameterTypes)
    //throws NoSuchMethodException {
            throws NoSuchMethodException {
        //for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            //try {
            try {
                //Method method = clazz.getDeclaredMethod(name, parameterTypes);
                Method method = clazz.getDeclaredMethod(name, parameterTypes);
                //if (!method.isAccessible()) {
                if (!method.isAccessible()) {
                    //method.setAccessible(true);
                    method.setAccessible(true);
                    //}
                }
                //return method;
                return method;
                //} catch (NoSuchMethodException e) {
            } catch (NoSuchMethodException e) {
                //

                //}
            }
            //}
        }
        //throw new NoSuchMethodException("Method " + name + " with parameters " +
        throw new NoSuchMethodException(llIIIJI(2329) + name + llIIIJI(2337) +
                //Arrays.asList(parameterTypes) + " not found in " + instance.getClass());
                Arrays.asList(parameterTypes) + llIIIJI(2350) + instance.getClass());
        //}
    }

    //

    //private static void expandFieldArray(Object instance, String fieldName,
    private static void expandFieldArray(Object instance, String fieldName,
                                         //Object[] extraElements) throws NoSuchFieldException, IllegalArgumentException,
                                         Object[] extraElements) throws NoSuchFieldException, IllegalArgumentException,
            //IllegalAccessException {
            IllegalAccessException {
        //Field jlrField = findField(instance, fieldName);
        Field jlrField = findField(instance, fieldName);
        //Object[] original = (Object[]) jlrField.get(instance);
        Object[] original = (Object[]) jlrField.get(instance);
        //Object[] combined = (Object[]) Array.newInstance(
        Object[] combined = (Object[]) Array.newInstance(
                //original.getClass().getComponentType(), original.length + extraElements.length);
                original.getClass().getComponentType(), original.length + extraElements.length);
        //System.arraycopy(original, 0, combined, 0, original.length);
        System.arraycopy(original, 0, combined, 0, original.length);
        //System.arraycopy(extraElements, 0, combined, original.length, extraElements.length);
        System.arraycopy(extraElements, 0, combined, original.length, extraElements.length);
        //jlrField.set(instance, combined);
        jlrField.set(instance, combined);
        //}
    }

    //private static void clearOldDexDir(Context context) throws Exception {
    private static void clearOldDexDir(Context context) throws Exception {
        //File dexDir = new File(context.getFilesDir(), OLD_SECONDARY_FOLDER_NAME);
        File dexDir = new File(context.getFilesDir(), OLD_SECONDARY_FOLDER_NAME);
        //if (dexDir.isDirectory()) {
        if (dexDir.isDirectory()) {
            //Log.i(TAG, "Clearing old secondary dex dir (" + dexDir.getPath() + ").");
            Log.i(TAG, llIIIJI(2599) + dexDir.getPath() + llIIIJI(2611));
            //File[] files = dexDir.listFiles();
            File[] files = dexDir.listFiles();
            //if (files == null) {
            if (files == null) {
                //Log.w(TAG, "Failed to list secondary dex dir content (" + dexDir.getPath() + ").");
                Log.w(TAG, llIIIJI(2650) + dexDir.getPath() + llIIIJI(2662));
                //return;
                return;
                //}
            }
            //for (File oldFile : files) {
            for (File oldFile : files) {
                //Log.i(TAG, "Trying to delete old file " + oldFile.getPath() + " of size "
                Log.i(TAG, llIIIJI(2692) + oldFile.getPath() + llIIIJI(2704)
                        //+ oldFile.length());
                        + oldFile.length());
                //if (!oldFile.delete()) {
                if (!oldFile.delete()) {
                    //Log.w(TAG, "Failed to delete old file " + oldFile.getPath());
                    Log.w(TAG, llIIIJI(2736) + oldFile.getPath());
                    //} else {
                } else {
                    //Log.i(TAG, "Deleted old file " + oldFile.getPath());
                    Log.i(TAG, llIIIJI(2761) + oldFile.getPath());
                    //}
                }
                //}
            }
            //if (!dexDir.delete()) {
            if (!dexDir.delete()) {
                //Log.w(TAG, "Failed to delete secondary dex dir " + dexDir.getPath());
                Log.w(TAG, llIIIJI(2797) + dexDir.getPath());
                //} else {
            } else {
                //Log.i(TAG, "Deleted old secondary dex dir " + dexDir.getPath());
                Log.i(TAG, llIIIJI(2822) + dexDir.getPath());
                //}
            }
            //}
        }
        //}
    }

    //public static File getDexDir(Context context, File dataDir, String secondaryFolderName)
    public static File getDexDir(Context context, File dataDir, String secondaryFolderName)
    //throws IOException {
            throws IOException {
        //File cache = new File(dataDir, CODE_CACHE_NAME);
        File cache = new File(dataDir, CODE_CACHE_NAME);
        //try {
        try {
            //mkdirChecked(cache);
            mkdirChecked(cache);
            //} catch (IOException e) {
        } catch (IOException e) {
            //

            //cache = new File(context.getFilesDir(), CODE_CACHE_NAME);
            cache = new File(context.getFilesDir(), CODE_CACHE_NAME);
            //mkdirChecked(cache);
            mkdirChecked(cache);
            //}
        }
        //File dexDir = new File(cache, secondaryFolderName);
        File dexDir = new File(cache, secondaryFolderName);
        //mkdirChecked(dexDir);
        mkdirChecked(dexDir);
        //return dexDir;
        return dexDir;
        //}
    }

    //private static void mkdirChecked(File dir) throws IOException {
    private static void mkdirChecked(File dir) throws IOException {
        //dir.mkdir();
        dir.mkdir();
        //if (!dir.isDirectory()) {
        if (!dir.isDirectory()) {
            //File parent = dir.getParentFile();
            File parent = dir.getParentFile();
            //if (parent == null) {
            if (parent == null) {
                //Log.e(TAG, "Failed to create dir " + dir.getPath() + ". Parent file is null.");
                Log.e(TAG, llIIIJI(3038) + dir.getPath() + llIIIJI(3050));
                //} else {
            } else {
                //Log.e(TAG, "Failed to create dir " + dir.getPath() +
                Log.e(TAG, llIIIJI(3067) + dir.getPath() +
                        //". parent file is a dir " + parent.isDirectory() +
                        llIIIJI(3079) + parent.isDirectory() +
                        //", a file " + parent.isFile() +
                        llIIIJI(3091) + parent.isFile() +
                        //", exists " + parent.exists() +
                        llIIIJI(3103) + parent.exists() +
                        //", readable " + parent.canRead() +
                        llIIIJI(3115) + parent.canRead() +
                        //", writable " + parent.canWrite());
                        llIIIJI(3127) + parent.canWrite());
                //}
            }
            //throw new IOException("Failed to create directory " + dir.getPath());
            throw new IOException(llIIIJI(3147) + dir.getPath());
            //}
        }
        //}
    }

    //

    static String llIIIJI(int llIIIJl) {
        byte[] llIIIJ = null;
        try {
            if (llIIIJl == -1) {
                if (llIIIJl == -2) {
                } else if (llIIIJl == -3) {
                } else if (llIIIJl == -4) {
                }
            }
            if (llIIIJl == 262) {
                llIIIJ = new byte[]{75, 115, 106, 114, 111, 66, 99, 126};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 388) {
                llIIIJ = new byte[]{};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 463) {
                llIIIJ = new byte[]{-122, -95, -68, -69, -82, -93, -93, -90, -95, -88, -17, -82, -65, -65, -93, -90, -84, -82, -69, -90, -96, -95};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 482) {
                llIIIJ = new byte[]{-76, -81, -62, -118, -125, -111, -62, -113, -105, -114, -106, -117, -122, -121, -102, -62, -111, -105, -110, -110, -115, -112, -106, -50, -62, -81, -105, -114, -106, -117, -90, -121, -102, -62, -111, -105, -110, -110, -115, -112, -106, -62, -114, -117, -128, -112, -125, -112, -101, -62, -117, -111, -62, -122, -117, -111, -125, -128, -114, -121, -122, -52};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 513) {
                llIIIJ = new byte[]{76, 116, 109, 117, 104, 69, 100, 121, 33, 104, 111, 114, 117, 96, 109, 109, 96, 117, 104, 110, 111, 33, 103, 96, 104, 109, 100, 101, 47, 33, 82, 69, 74, 33};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 525) {
                llIIIJ = new byte[]{45, 100, 126, 45, 120, 99, 126, 120, 125, 125, 98, 127, 121, 104, 105, 35, 45, 64, 100, 99, 45, 94, 73, 70, 45, 123, 104, 127, 126, 100, 98, 99, 45, 100, 126, 45};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 533) {
                llIIIJ = new byte[]{59};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 575) {
                llIIIJ = new byte[]{113, 80, 31, 126, 79, 79, 83, 86, 92, 94, 75, 86, 80, 81, 118, 81, 89, 80, 31, 94, 73, 94, 86, 83, 94, 93, 83, 90, 19, 31, 86, 17, 90, 17, 31, 77, 74, 81, 81, 86, 81, 88, 31, 80, 81, 31, 94, 31, 75, 90, 76, 75, 31, 124, 80, 81, 75, 90, 71, 75, 5};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 579) {
                llIIIJ = new byte[]{99, 14, 54, 47, 55, 42, 7, 38, 59, 99, 48, 54, 51, 51, 44, 49, 55, 99, 47, 42, 33, 49, 34, 49, 58, 99, 42, 48, 99, 39, 42, 48, 34, 33, 47, 38, 39, 109};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 642) {
                llIIIJ = new byte[]{-49, -9, -18, -10, -21, -58, -25, -6, -94, -21, -20, -15, -10, -29, -18, -18, -29, -10, -21, -19, -20, -94, -28, -29, -21, -18, -9, -16, -25};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 655) {
                llIIIJ = new byte[]{-62, -6, -29, -5, -26, -53, -22, -9, -81, -26, -31, -4, -5, -18, -29, -29, -18, -5, -26, -32, -31, -81, -23, -18, -26, -29, -22, -21, -81, -89};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 667) {
                llIIIJ = new byte[]{-78, -75};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 680) {
                llIIIJ = new byte[]{-63, -58, -37, -36, -55, -60, -60, -120, -52, -57, -58, -51};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 714) {
                llIIIJ = new byte[]{-125, -92, -71, -66, -85, -90, -90, -93, -92, -83, -22, -93, -92, -71, -66, -72, -65, -89, -81, -92, -66, -85, -66, -93, -91, -92};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 733) {
                llIIIJ = new byte[]{-117, -112, -3, -75, -68, -82, -3, -80, -88, -79, -87, -76, -71, -72, -91, -3, -82, -88, -83, -83, -78, -81, -87, -15, -3, -112, -88, -79, -87, -76, -103, -72, -91, -3, -82, -88, -83, -83, -78, -81, -87, -3, -79, -76, -65, -81, -68, -81, -92, -3, -76, -82, -3, -71, -76, -82, -68, -65, -79, -72, -71, -13};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 764) {
                llIIIJ = new byte[]{-79, -119, -112, -120, -107, -72, -103, -124, -36, -107, -110, -113, -120, -99, -112, -112, -99, -120, -107, -109, -110, -36, -102, -99, -107, -112, -103, -104, -46, -36, -81, -72, -73, -36};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 776) {
                llIIIJ = new byte[]{40, 97, 123, 40, 125, 102, 123, 125, 120, 120, 103, 122, 124, 109, 108, 38, 40, 69, 97, 102, 40, 91, 76, 67, 40, 126, 109, 122, 123, 97, 103, 102, 40, 97, 123, 40};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 784) {
                llIIIJ = new byte[]{62};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 826) {
                llIIIJ = new byte[]{116, 85, 26, 123, 74, 74, 86, 83, 89, 91, 78, 83, 85, 84, 115, 84, 92, 85, 26, 91, 76, 91, 83, 86, 91, 88, 86, 95, 26, 92, 85, 72, 26, 83, 84, 73, 78, 72, 79, 87, 95, 84, 78, 91, 78, 83, 85, 84, 22, 26, 83, 20, 95, 20, 26, 72, 79, 84, 84, 83, 84, 93, 26, 85, 84, 26, 91};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 830) {
                llIIIJ = new byte[]{30, 74, 91, 77, 74, 30, 125, 81, 80, 74, 91, 70, 74, 4, 30, 115, 75, 82, 74, 87, 122, 91, 70, 30, 77, 75, 78, 78, 81, 76, 74, 30, 82, 87, 92, 76, 95, 76, 71, 30, 87, 77, 30, 90, 87, 77, 95, 92, 82, 91, 90, 16};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 871) {
                llIIIJ = new byte[]{41, 8, 71, 38, 23, 23, 11, 14, 4, 6, 19, 14, 8, 9, 46, 9, 1, 8, 71, 6, 17, 6, 14, 11, 6, 5, 11, 2, 75, 71, 14, 73, 2, 73, 71, 21, 18, 9, 9, 14, 9, 0, 71, 8, 9, 71, 6, 71, 19, 2, 20, 19, 71, 36, 8, 9, 19, 2, 31, 19, 93};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 875) {
                llIIIJ = new byte[]{75, 38, 30, 7, 31, 2, 47, 14, 19, 75, 24, 30, 27, 27, 4, 25, 31, 75, 7, 2, 9, 25, 10, 25, 18, 75, 2, 24, 75, 15, 2, 24, 10, 9, 7, 14, 15, 69};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 898) {
                llIIIJ = new byte[]{-84};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 996) {
                llIIIJ = new byte[]{-87, -111, -120, -112, -115, -96, -127, -100, -60, -115, -118, -105, -112, -123, -120, -120, -123, -112, -115, -117, -118, -60, -126, -123, -115, -120, -111, -106, -127};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1009) {
                llIIIJ = new byte[]{-68, -124, -99, -123, -104, -75, -108, -119, -47, -104, -97, -126, -123, -112, -99, -99, -112, -123, -104, -98, -97, -47, -105, -112, -104, -99, -108, -107, -47, -39};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1021) {
                llIIIJ = new byte[]{-44, -45};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1034) {
                llIIIJ = new byte[]{67, 100, 121, 126, 107, 102, 102, 107, 126, 99, 101, 100, 42, 110, 101, 100, 111};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1166) {
                llIIIJ = new byte[]{-61, -5, -30, -6, -25, -54, -21, -10, -82, -25, -3, -82, -32, -31, -6, -82, -23, -5, -17, -4, -17, -32, -6, -21, -21, -22, -82, -6, -31, -82, -7, -31, -4, -27, -82, -25, -32, -82, -35, -54, -59, -82, -8, -21, -4, -3, -25, -31, -32, -82};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1178) {
                llIIIJ = new byte[]{-96, -70, -55, -34, -47, -70, -20, -1, -24, -23, -13, -11, -12, -70, -14, -13, -3, -14, -1, -24, -70, -18, -14, -5, -12, -70};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1186) {
                llIIIJ = new byte[]{-126, -47, -54, -51, -41, -50, -58, -126, -64, -57, -126, -64, -61, -63, -55, -57, -58, -126, -64, -37, -126};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1190) {
                llIIIJ = new byte[]{-44, -45, -56, -46, -49, -53, -61, -122, -47, -49, -46, -50, -122, -60, -45, -49, -54, -46, -117, -49, -56, -122, -53, -45, -54, -46, -49, -62, -61, -34, -122, -59, -57, -42, -57, -60, -49, -54, -46, -33, -122, -60, -45, -46, -122, -49, -46, -127, -43, -122, -56, -55, -46, -122, -46, -50, -61, -122};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1194) {
                llIIIJ = new byte[]{-55, -53, -39, -49, -118, -62, -49, -40, -49, -112, -118, -64, -53, -36, -53, -124, -36, -57, -124, -36, -49, -40, -39, -61, -59, -60, -105, -120};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1202) {
                llIIIJ = new byte[]{-40, -45, -60, -45, -100, -60, -33, -100, -60, -41, -64, -63, -37, -35, -36};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1207) {
                llIIIJ = new byte[]{-107};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1254) {
                llIIIJ = new byte[]{-96, -121, -113, -118, -109, -108, -125, -58, -111, -114, -113, -118, -125, -58, -110, -108, -97, -113, -120, -127, -58, -110, -119, -58, -119, -124, -110, -121, -113, -120, -58, -91, -119, -120, -110, -125, -98, -110, -58, -123, -118, -121, -107, -107, -58, -118, -119, -121, -126, -125, -108, -56, -58};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1258) {
                llIIIJ = new byte[]{-89, -97, -103, -98, -54, -120, -113, -54, -104, -97, -124, -124, -125, -124, -115, -54, -125, -124, -54, -98, -113, -103, -98, -54, -121, -123, -114, -113, -60, -54, -71, -127, -125, -102, -54, -102, -117, -98, -119, -126, -125, -124, -115, -60};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1291) {
                llIIIJ = new byte[]{72, 100, 101, 127, 110, 115, 127, 43, 104, 103, 106, 120, 120, 43, 103, 100, 106, 111, 110, 121, 43, 98, 120, 43, 101, 126, 103, 103, 37, 43, 70, 126, 120, 127, 43, 105, 110, 43, 121, 126, 101, 101, 98, 101, 108, 43, 98, 101, 43, 127, 110, 120, 127, 43, 102, 100, 111, 110, 37, 43};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1295) {
                llIIIJ = new byte[]{92, 100, 102, 127, 47, 127, 110, 123, 108, 103, 102, 97, 104, 33};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1333) {
                llIIIJ = new byte[]{102, 90, 88, 80, 65, 93, 92, 91, 82, 21, 66, 80, 91, 65, 21, 66, 71, 90, 91, 82, 21, 66, 93, 80, 91, 21, 65, 71, 76, 92, 91, 82, 21, 65, 90, 21, 86, 89, 80, 84, 71, 21, 90, 89, 81, 21, 120, 64, 89, 65, 92, 113, 80, 77, 21, 80, 77, 65, 71, 84, 86, 65, 92, 90, 91, 25, 21};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1337) {
                llIIIJ = new byte[]{90, 86, 87, 77, 80, 87, 76, 80, 87, 94, 25, 78, 80, 77, 81, 86, 76, 77, 25, 90, 85, 92, 88, 87, 80, 87, 94, 23};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1476) {
                llIIIJ = new byte[]{-126, -91, -83, -88, -95, -96, -28, -80, -85, -28, -83, -86, -73, -80, -91, -88, -88, -28, -95, -68, -80, -74, -91, -89, -80, -95, -96, -28, -73, -95, -89, -85, -86, -96, -91, -74, -67, -28, -96, -95, -68, -28, -94, -83, -88, -95, -73, -24, -28, -74, -95, -80, -74, -67, -83, -86, -93, -28, -77, -83, -80, -84, -28};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1480) {
                llIIIJ = new byte[]{-82, -89, -70, -85, -83, -84, -24, -83, -80, -68, -70, -87, -85, -68, -95, -89, -90};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1642) {
                llIIIJ = new byte[]{44, 11, 3, 6, 31, 24, 15, 74, 29, 2, 3, 6, 15, 74, 30, 24, 19, 3, 4, 13, 74, 30, 5, 74, 5, 8, 30, 11, 3, 4, 74, 43, 26, 26, 6, 3, 9, 11, 30, 3, 5, 4, 35, 4, 12, 5, 74, 12, 24, 5, 7, 74, 41, 5, 4, 30, 15, 18, 30, 68, 74};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1646) {
                llIIIJ = new byte[]{35, 27, 29, 26, 78, 12, 11, 78, 28, 27, 0, 0, 7, 0, 9, 78, 7, 0, 78, 26, 11, 29, 26, 78, 3, 1, 10, 11, 64, 78, 61, 5, 7, 30, 78, 30, 15, 26, 13, 6, 7, 0, 9, 64};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1709) {
                llIIIJ = new byte[]{-123, -15, -55, -122, -124, -15, -125, -123, -15, -55, -122, -124, -123, -15, -125, -15, -55, -122, -124, -110};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1837) {
                llIIIJ = new byte[]{123, 96, 13, 90, 68, 89, 69, 13, 91, 72, 95, 94, 68, 66, 67, 13};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1850) {
                llIIIJ = new byte[]{26, 82, 91, 73, 26, 87, 79, 86, 78, 83, 94, 95, 66, 26, 73, 79, 74, 74, 85, 72, 78};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 1854) {
                llIIIJ = new byte[]{30, 90, 81, 91, 77, 30, 80, 81, 74, 30, 86, 95, 72, 91, 30, 83, 75, 82, 74, 87, 90, 91, 70, 30, 77, 75, 78, 78, 81, 76, 74};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2159) {
                llIIIJ = new byte[]{41, 6, 10, 3, 11, 79};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2167) {
                llIIIJ = new byte[]{87, 25, 24, 3, 87, 17, 24, 2, 25, 19, 87, 30, 25, 87};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2329) {
                llIIIJ = new byte[]{84, 124, 109, 113, 118, 125, 57};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2337) {
                llIIIJ = new byte[]{1, 86, 72, 85, 73, 1, 81, 64, 83, 64, 76, 68, 85, 68, 83, 82, 1};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2350) {
                llIIIJ = new byte[]{14, 64, 65, 90, 14, 72, 65, 91, 64, 74, 14, 71, 64, 14};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2599) {
                llIIIJ = new byte[]{100, 75, 66, 70, 85, 78, 73, 64, 7, 72, 75, 67, 7, 84, 66, 68, 72, 73, 67, 70, 85, 94, 7, 67, 66, 95, 7, 67, 78, 85, 7, 15};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2611) {
                llIIIJ = new byte[]{26, 29};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2650) {
                llIIIJ = new byte[]{28, 59, 51, 54, 63, 62, 122, 46, 53, 122, 54, 51, 41, 46, 122, 41, 63, 57, 53, 52, 62, 59, 40, 35, 122, 62, 63, 34, 122, 62, 51, 40, 122, 57, 53, 52, 46, 63, 52, 46, 122, 114};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2662) {
                llIIIJ = new byte[]{79, 72};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2692) {
                llIIIJ = new byte[]{-48, -10, -3, -19, -22, -29, -92, -16, -21, -92, -32, -31, -24, -31, -16, -31, -92, -21, -24, -32, -92, -30, -19, -24, -31, -92};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2704) {
                llIIIJ = new byte[]{-80, -1, -10, -80, -29, -7, -22, -11, -80};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2736) {
                llIIIJ = new byte[]{-10, -47, -39, -36, -43, -44, -112, -60, -33, -112, -44, -43, -36, -43, -60, -43, -112, -33, -36, -44, -112, -42, -39, -36, -43, -112};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2761) {
                llIIIJ = new byte[]{-115, -84, -91, -84, -67, -84, -83, -23, -90, -91, -83, -23, -81, -96, -91, -84, -23};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2797) {
                llIIIJ = new byte[]{-85, -116, -124, -127, -120, -119, -51, -103, -126, -51, -119, -120, -127, -120, -103, -120, -51, -98, -120, -114, -126, -125, -119, -116, -97, -108, -51, -119, -120, -107, -51, -119, -124, -97, -51};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 2822) {
                llIIIJ = new byte[]{66, 99, 106, 99, 114, 99, 98, 38, 105, 106, 98, 38, 117, 99, 101, 105, 104, 98, 103, 116, 127, 38, 98, 99, 126, 38, 98, 111, 116, 38};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3038) {
                llIIIJ = new byte[]{-104, -65, -73, -78, -69, -70, -2, -86, -79, -2, -67, -84, -69, -65, -86, -69, -2, -70, -73, -84, -2};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3050) {
                llIIIJ = new byte[]{-60, -54, -70, -117, -104, -113, -124, -98, -54, -116, -125, -122, -113, -54, -125, -103, -54, -124, -97, -122, -122, -60};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3067) {
                llIIIJ = new byte[]{-67, -102, -110, -105, -98, -97, -37, -113, -108, -37, -104, -119, -98, -102, -113, -98, -37, -97, -110, -119, -37};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3079) {
                llIIIJ = new byte[]{41, 39, 119, 102, 117, 98, 105, 115, 39, 97, 110, 107, 98, 39, 110, 116, 39, 102, 39, 99, 110, 117, 39};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3091) {
                llIIIJ = new byte[]{63, 51, 114, 51, 117, 122, 127, 118, 51};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3103) {
                llIIIJ = new byte[]{51, 63, 122, 103, 118, 108, 107, 108, 63};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3115) {
                llIIIJ = new byte[]{7, 11, 89, 78, 74, 79, 74, 73, 71, 78, 11};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3127) {
                llIIIJ = new byte[]{27, 23, 64, 69, 94, 67, 86, 85, 91, 82, 23};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3147) {
                llIIIJ = new byte[]{13, 42, 34, 39, 46, 47, 107, 63, 36, 107, 40, 57, 46, 42, 63, 46, 107, 47, 34, 57, 46, 40, 63, 36, 57, 50, 107};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3237) {
                llIIIJ = new byte[]{-43, -60, -47, -51, -23, -52, -42, -47};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3279) {
                llIIIJ = new byte[]{-85, -86, -73, -118, -93, -86, -94, -86, -95, -69, -68};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3343) {
                llIIIJ = new byte[]{74, 119, 108, 106, 127, 123, 102, 96, 97, 47, 102, 97, 47, 98, 110, 100, 106, 75, 106, 119, 74, 99, 106, 98, 106, 97, 123};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3363) {
                llIIIJ = new byte[]{71, 70, 91, 102, 79, 70, 78, 70, 77, 87, 80, 112, 86, 83, 83, 81, 70, 80, 80, 70, 71, 102, 91, 64, 70, 83, 87, 74, 76, 77, 80};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3520) {
                llIIIJ = new byte[]{-119, -17, -113, -32, -91, -72, -93, -91, -80, -76, -87, -81, -82, -32, -92, -75, -78, -87, -82, -89, -32, -83, -95, -85, -91, -124, -91, -72, -123, -84, -91, -83, -91, -82, -76};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3620) {
                llIIIJ = new byte[]{73, 69, 79, 65, 116, 69, 80, 76, 97, 72, 65, 73, 65, 74, 80, 87};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3645) {
                llIIIJ = new byte[]{80, 92, 86, 88, 121, 88, 69, 120, 81, 88, 80, 88, 83, 73, 78};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3772) {
                llIIIJ = new byte[]{-40, -35, -48, -54, -43, -41, -110, -49, -59, -49, -56, -39, -47, -110, -8, -39, -60, -20, -35, -56, -44, -16, -43, -49, -56, -104, -7, -48, -39, -47, -39, -46, -56};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3924) {
                llIIIJ = new byte[]{36, 53, 32, 60, 24, 61, 39, 32};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3971) {
                llIIIJ = new byte[]{-25, -26, -5, -58, -17, -26, -18, -26, -19, -9, -16};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 3999) {
                llIIIJ = new byte[]{-39, -2, -10, -13, -6, -5, -65, -7, -10, -15, -5, -65, -7, -10, -6, -13, -5, -65, -72, -5, -6, -25, -38, -13, -6, -14, -6, -15, -21, -20, -72, -65, -2, -21, -21, -6, -14, -17, -21, -10, -15, -8, -65, -72, -17, -2, -21, -9, -38, -13, -6, -14, -6, -15, -21, -20, -72};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 4011) {
                llIIIJ = new byte[]{-37, -54, -33, -61, -18, -57, -50, -58, -50, -59, -33, -40};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 4813) {
                llIIIJ = new byte[]{-67, -84, -71, -91};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 5043) {
                llIIIJ = new byte[]{-99, -41, -42, -53};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 5072) {
                llIIIJ = new byte[]{-67, -128, -79, -92, -72, -93};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 5084) {
                llIIIJ = new byte[]{-79, -102, -75, -80, -71, -81};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 5096) {
                llIIIJ = new byte[]{-123, -78, -127, -104, -101};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
            if (llIIIJl == 5108) {
                llIIIJ = new byte[]{-103, -80, -111, -116, -121};

                for (int llIIIJJ = 0; llIIIJJ < llIIIJ.length; llIIIJJ++) {
                    llIIIJ[llIIIJJ] = (byte) (llIIIJ[llIIIJJ] ^ llIIIJl);
                }
                {
                    return new String(llIIIJ, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/

    //

    //private static final class V19 {
    private static final class V19 {
        //static void install(ClassLoader loader,
        static void install(ClassLoader loader,
                            //List<? extends File> additionalClassPathEntries,
                            List<? extends File> additionalClassPathEntries,
                            //File optimizedDirectory)
                            File optimizedDirectory)
        //throws IllegalArgumentException, IllegalAccessException,
                throws IllegalArgumentException, IllegalAccessException,
                //NoSuchFieldException, InvocationTargetException, NoSuchMethodException,
                NoSuchFieldException, InvocationTargetException, NoSuchMethodException,
                //IOException {
                IOException {
            //

            //Field pathListField = findField(loader, "pathList");
            Field pathListField = findField(loader, llIIIJI(3237));
            //Object dexPathList = pathListField.get(loader);
            Object dexPathList = pathListField.get(loader);
            //ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
            ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
            //expandFieldArray(dexPathList, "dexElements", makeDexElements(dexPathList,
            expandFieldArray(dexPathList, llIIIJI(3279), makeDexElements(dexPathList,
                    //new ArrayList<File>(additionalClassPathEntries), optimizedDirectory,
                    new ArrayList<File>(additionalClassPathEntries), optimizedDirectory,
                    //suppressedExceptions));
                    suppressedExceptions));
            //if (suppressedExceptions.size() > 0) {
            if (suppressedExceptions.size() > 0) {
                //for (IOException e : suppressedExceptions) {
                for (IOException e : suppressedExceptions) {
                    //Log.w(TAG, "Exception in makeDexElement", e);
                    Log.w(TAG, llIIIJI(3343), e);
                    //}
                }
                //Field suppressedExceptionsField =
                Field suppressedExceptionsField =
                        //findField(dexPathList, "dexElementsSuppressedExceptions");
                        findField(dexPathList, llIIIJI(3363));
                //IOException[] dexElementsSuppressedExceptions =
                IOException[] dexElementsSuppressedExceptions =
                        //(IOException[]) suppressedExceptionsField.get(dexPathList);
                        (IOException[]) suppressedExceptionsField.get(dexPathList);
                //if (dexElementsSuppressedExceptions == null) {
                if (dexElementsSuppressedExceptions == null) {
                    //dexElementsSuppressedExceptions =
                    dexElementsSuppressedExceptions =
                            //suppressedExceptions.toArray(
                            suppressedExceptions.toArray(
                                    //new IOException[suppressedExceptions.size()]);
                                    new IOException[suppressedExceptions.size()]);
                    //} else {
                } else {
                    //IOException[] combined =
                    IOException[] combined =
                            //new IOException[suppressedExceptions.size() +
                            new IOException[suppressedExceptions.size() +
                                    //dexElementsSuppressedExceptions.length];
                                    dexElementsSuppressedExceptions.length];
                    //suppressedExceptions.toArray(combined);
                    suppressedExceptions.toArray(combined);
                    //System.arraycopy(dexElementsSuppressedExceptions, 0, combined,
                    System.arraycopy(dexElementsSuppressedExceptions, 0, combined,
                            //suppressedExceptions.size(), dexElementsSuppressedExceptions.length);
                            suppressedExceptions.size(), dexElementsSuppressedExceptions.length);
                    //dexElementsSuppressedExceptions = combined;
                    dexElementsSuppressedExceptions = combined;
                    //}
                }
                //suppressedExceptionsField.set(dexPathList, dexElementsSuppressedExceptions);
                suppressedExceptionsField.set(dexPathList, dexElementsSuppressedExceptions);
                //IOException exception = new IOException("I/O exception during makeDexElement");
                IOException exception = new IOException(llIIIJI(3520), suppressedExceptions.get(0));
                //exception.initCause(suppressedExceptions.get(0));
                //throw exception;
                throw exception;
                //}
            }
            //}
        }

        //

        //private static Object[] makeDexElements(
        private static Object[] makeDexElements(
                //Object dexPathList, ArrayList<File> files, File optimizedDirectory,
                Object dexPathList, ArrayList<File> files, File optimizedDirectory,
                //ArrayList<IOException> suppressedExceptions)
                ArrayList<IOException> suppressedExceptions)
        //throws IllegalAccessException, InvocationTargetException,
                throws IllegalAccessException, InvocationTargetException,
                //NoSuchMethodException {
                NoSuchMethodException {
            //Method makeDexElements =
            Method makeDexElements =
                    //Build.VERSION.SDK_INT >= 23 ?
                    Build.VERSION.SDK_INT >= 23 ?
                            //findMethod(dexPathList, "makePathElements", List.class, File.class, List.class) :
                            findMethod(dexPathList, llIIIJI(3620), List.class, File.class, List.class) :
                            //findMethod(dexPathList, "makeDexElements", ArrayList.class, File.class, ArrayList.class);
                            findMethod(dexPathList, llIIIJI(3645), ArrayList.class, File.class, ArrayList.class);
            //return (Object[]) makeDexElements.invoke(dexPathList, files, optimizedDirectory,
            return (Object[]) makeDexElements.invoke(dexPathList, files, optimizedDirectory,
                    //suppressedExceptions);
                    suppressedExceptions);
            //}
        }
        //}
    }

    //

    //private static final class V14 {
    private static final class V14 {
        //private static final int EXTRACTED_SUFFIX_LENGTH =
        private static final int EXTRACTED_SUFFIX_LENGTH =
                //MultiDexExtractor.EXTRACTED_SUFFIX.length();
                MultiDexExtractor.EXTRACTED_SUFFIX.length();
        //private final ElementConstructor elementConstructor;
        private final ElementConstructor elementConstructor;

        //private V14() throws ClassNotFoundException, SecurityException, NoSuchMethodException {
        private V14() throws ClassNotFoundException, SecurityException, NoSuchMethodException {
            //ElementConstructor constructor;
            ElementConstructor constructor;
            //Class<?> elementClass = Class.forName("dalvik.system.DexPathList$Element");
            Class<?> elementClass = Class.forName(llIIIJI(3772));
            //try {
            try {
                //constructor = new ICSElementConstructor(elementClass);
                constructor = new ICSElementConstructor(elementClass);
                //} catch (NoSuchMethodException e1) {
            } catch (NoSuchMethodException e1) {
                //try {
                try {
                    //constructor = new JBMR11ElementConstructor(elementClass);
                    constructor = new JBMR11ElementConstructor(elementClass);
                    //} catch (NoSuchMethodException e2) {
                } catch (NoSuchMethodException e2) {
                    //constructor = new JBMR2ElementConstructor(elementClass);
                    constructor = new JBMR2ElementConstructor(elementClass);
                    //}
                }
                //}
            }
            //this.elementConstructor = constructor;
            this.elementConstructor = constructor;
            //}
        }

        //static void install(ClassLoader loader,
        static void install(ClassLoader loader,
                            //List<? extends File> additionalClassPathEntries)
                            List<? extends File> additionalClassPathEntries)
        //throws IOException, SecurityException, IllegalArgumentException,
                throws IOException, SecurityException, IllegalArgumentException,
                //ClassNotFoundException, NoSuchMethodException, InstantiationException,
                ClassNotFoundException, NoSuchMethodException, InstantiationException,
                //IllegalAccessException, InvocationTargetException, NoSuchFieldException {
                IllegalAccessException, InvocationTargetException, NoSuchFieldException {
            //

            //Field pathListField = findField(loader, "pathList");
            Field pathListField = findField(loader, llIIIJI(3924));
            //Object dexPathList = pathListField.get(loader);
            Object dexPathList = pathListField.get(loader);
            //Object[] elements = new V14().makeDexElements(additionalClassPathEntries);
            Object[] elements = new V14().makeDexElements(additionalClassPathEntries);
            //try {
            try {
                //expandFieldArray(dexPathList, "dexElements", elements);
                expandFieldArray(dexPathList, llIIIJI(3971), elements);
                //} catch (NoSuchFieldException e) {
            } catch (NoSuchFieldException e) {
                //

                //

                //Log.w(TAG, "Failed find field 'dexElements' attempting 'pathElements'", e);
                Log.w(TAG, llIIIJI(3999), e);
                //expandFieldArray(dexPathList, "pathElements", elements);
                expandFieldArray(dexPathList, llIIIJI(4011), elements);
                //}
            }
            //}
        }

        //

        //private static String optimizedPathFor(File path) {
        private static String optimizedPathFor(File path) {
            //

            //

            //File optimizedDirectory = path.getParentFile();
            File optimizedDirectory = path.getParentFile();
            //String fileName = path.getName();
            String fileName = path.getName();
            //String optimizedFileName =
            String optimizedFileName =
                    //fileName.substring(0, fileName.length() - EXTRACTED_SUFFIX_LENGTH)
                    fileName.substring(0, fileName.length() - EXTRACTED_SUFFIX_LENGTH)
                            //+ MultiDexExtractor.DEX_SUFFIX;
                            + MultiDexExtractor.DEX_SUFFIX;
            //File result = new File(optimizedDirectory, optimizedFileName);
            File result = new File(optimizedDirectory, optimizedFileName);
            //return result.getPath();
            return result.getPath();
            //}
        }

        //

        //private Object[] makeDexElements(List<? extends File> files)
        private Object[] makeDexElements(List<? extends File> files)
        //throws IOException, SecurityException, IllegalArgumentException,
                throws IOException, SecurityException, IllegalArgumentException,
                //InstantiationException, IllegalAccessException, InvocationTargetException {
                InstantiationException, IllegalAccessException, InvocationTargetException {
            //Object[] elements = new Object[files.size()];
            Object[] elements = new Object[files.size()];
            //for (int i = 0; i < elements.length; i++) {
            for (int i = 0; i < elements.length; i++) {
                //File file = files.get(i);
                File file = files.get(i);
                //elements[i] = elementConstructor.newInstance(
                elements[i] = elementConstructor.newInstance(
                        //file,
                        file,
                        //DexFile.loadDex(file.getPath(), optimizedPathFor(file), 0));
                        DexFile.loadDex(file.getPath(), optimizedPathFor(file), 0));
                //}
            }
            //return elements;
            return elements;
            //}
        }

        //private interface ElementConstructor {
        private interface ElementConstructor {
            //Object newInstance(File file, DexFile dex)
            Object newInstance(File file, DexFile dex)
            //throws IllegalArgumentException, InstantiationException,
                    throws IllegalArgumentException, InstantiationException,
                    //IllegalAccessException, InvocationTargetException, IOException;
                    IllegalAccessException, InvocationTargetException, IOException;
            //}
        }

        //

        //private static class ICSElementConstructor implements ElementConstructor {
        private static class ICSElementConstructor implements ElementConstructor {
            //private final Constructor<?> elementConstructor;
            private final Constructor<?> elementConstructor;

            //ICSElementConstructor(Class<?> elementClass)
            ICSElementConstructor(Class<?> elementClass)
            //throws SecurityException, NoSuchMethodException {
                    throws SecurityException, NoSuchMethodException {
                //elementConstructor =
                elementConstructor =
                        //elementClass.getConstructor(File.class, ZipFile.class, DexFile.class);
                        elementClass.getConstructor(File.class, ZipFile.class, DexFile.class);
                //elementConstructor.setAccessible(true);
                elementConstructor.setAccessible(true);
                //}
            }

            //@Override
            @Override
            //public Object newInstance(File file, DexFile dex)
            public Object newInstance(File file, DexFile dex)
            //throws IllegalArgumentException, InstantiationException,
                    throws IllegalArgumentException, InstantiationException,
                    //IllegalAccessException, InvocationTargetException, IOException {
                    IllegalAccessException, InvocationTargetException, IOException {
                //return elementConstructor.newInstance(file, new ZipFile(file), dex);
                return elementConstructor.newInstance(file, new ZipFile(file), dex);
                //}
            }
            //}
        }

        //

        //private static class JBMR11ElementConstructor implements ElementConstructor {
        private static class JBMR11ElementConstructor implements ElementConstructor {
            //private final Constructor<?> elementConstructor;
            private final Constructor<?> elementConstructor;

            //JBMR11ElementConstructor(Class<?> elementClass)
            JBMR11ElementConstructor(Class<?> elementClass)
            //throws SecurityException, NoSuchMethodException {
                    throws SecurityException, NoSuchMethodException {
                //elementConstructor = elementClass
                elementConstructor = elementClass
                        //.getConstructor(File.class, File.class, DexFile.class);
                        .getConstructor(File.class, File.class, DexFile.class);
                //elementConstructor.setAccessible(true);
                elementConstructor.setAccessible(true);
                //}
            }

            //@Override
            @Override
            //public Object newInstance(File file, DexFile dex)
            public Object newInstance(File file, DexFile dex)
            //throws IllegalArgumentException, InstantiationException,
                    throws IllegalArgumentException, InstantiationException,
                    //IllegalAccessException, InvocationTargetException {
                    IllegalAccessException, InvocationTargetException {
                //return elementConstructor.newInstance(file, file, dex);
                return elementConstructor.newInstance(file, file, dex);
                //}
            }
            //}
        }

        //

        //private static class JBMR2ElementConstructor implements ElementConstructor {
        private static class JBMR2ElementConstructor implements ElementConstructor {
            //private final Constructor<?> elementConstructor;
            private final Constructor<?> elementConstructor;

            //JBMR2ElementConstructor(Class<?> elementClass)
            JBMR2ElementConstructor(Class<?> elementClass)
            //throws SecurityException, NoSuchMethodException {
                    throws SecurityException, NoSuchMethodException {
                //elementConstructor = elementClass
                elementConstructor = elementClass
                        //.getConstructor(File.class, Boolean.TYPE, File.class, DexFile.class);
                        .getConstructor(File.class, Boolean.TYPE, File.class, DexFile.class);
                //elementConstructor.setAccessible(true);
                elementConstructor.setAccessible(true);
                //}
            }

            //@Override
            @Override
            //public Object newInstance(File file, DexFile dex)
            public Object newInstance(File file, DexFile dex)
            //throws IllegalArgumentException, InstantiationException,
                    throws IllegalArgumentException, InstantiationException,
                    //IllegalAccessException, InvocationTargetException {
                    IllegalAccessException, InvocationTargetException {
                //return elementConstructor.newInstance(file, Boolean.FALSE, file, dex);
                return elementConstructor.newInstance(file, Boolean.FALSE, file, dex);
                //}
            }
            //}
        }
        //}
    }
//}

    //private static final class V4 {
    private static final class V4 {
        //static void install(ClassLoader loader,
        static void install(ClassLoader loader,
                            //List<? extends File> additionalClassPathEntries)
                            List<? extends File> additionalClassPathEntries)
        //throws IllegalArgumentException, IllegalAccessException,
                throws IllegalArgumentException, IllegalAccessException,
                //NoSuchFieldException, IOException {
                NoSuchFieldException, IOException {
            //

            //int extraSize = additionalClassPathEntries.size();
            int extraSize = additionalClassPathEntries.size();
            //Field pathField = findField(loader, "path");
            Field pathField = findField(loader, llIIIJI(4813));
            //StringBuilder path = new StringBuilder((String) pathField.get(loader));
            StringBuilder path = new StringBuilder((String) pathField.get(loader));
            //String[] extraPaths = new String[extraSize];
            String[] extraPaths = new String[extraSize];
            //File[] extraFiles = new File[extraSize];
            File[] extraFiles = new File[extraSize];
            //ZipFile[] extraZips = new ZipFile[extraSize];
            ZipFile[] extraZips = new ZipFile[extraSize];
            //DexFile[] extraDexs = new DexFile[extraSize];
            DexFile[] extraDexs = new DexFile[extraSize];
            //for (ListIterator<? extends File> iterator = additionalClassPathEntries.listIterator();
            for (ListIterator<? extends File> iterator = additionalClassPathEntries.listIterator();
                //iterator.hasNext(); ) {
                 iterator.hasNext(); ) {
                //File additionalEntry = iterator.next();
                File additionalEntry = iterator.next();
                //String entryPath = additionalEntry.getAbsolutePath();
                String entryPath = additionalEntry.getAbsolutePath();
                //path.append(':').append(entryPath);
                path.append(':').append(entryPath);
                //int index = iterator.previousIndex();
                int index = iterator.previousIndex();
                //extraPaths[index] = entryPath;
                extraPaths[index] = entryPath;
                //extraFiles[index] = additionalEntry;
                extraFiles[index] = additionalEntry;
                //extraZips[index] = new ZipFile(additionalEntry);
                extraZips[index] = new ZipFile(additionalEntry);
                //extraDexs[index] = DexFile.loadDex(entryPath, entryPath + ".dex", 0);
                extraDexs[index] = DexFile.loadDex(entryPath, entryPath + llIIIJI(5043), 0);
                //}
            }
            //pathField.set(loader, path.toString());
            pathField.set(loader, path.toString());
            //expandFieldArray(loader, "mPaths", extraPaths);
            expandFieldArray(loader, llIIIJI(5072), extraPaths);
            //expandFieldArray(loader, "mFiles", extraFiles);
            expandFieldArray(loader, llIIIJI(5084), extraFiles);
            //expandFieldArray(loader, "mZips", extraZips);
            expandFieldArray(loader, llIIIJI(5096), extraZips);
            //expandFieldArray(loader, "mDexs", extraDexs);
            expandFieldArray(loader, llIIIJI(5108), extraDexs);
            //}
        }
        //}
    }
}//

//
  	