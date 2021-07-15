/* Orginal file: MultiDex.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.multidex*/
package com.mcal.apkprotector.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;

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
    static final String TAG = llIIllJ(236);
    //private static final String OLD_SECONDARY_FOLDER_NAME = "secondary-dexes";
    private static final String OLD_SECONDARY_FOLDER_NAME = llIIllJ(251);
    //private static final String CODE_CACHE_NAME = "code_cache";
    private static final String CODE_CACHE_NAME = llIIllJ(266);
    //private static final String CODE_CACHE_SECONDARY_FOLDER_NAME = "secondary-dexes";
    private static final String CODE_CACHE_SECONDARY_FOLDER_NAME = llIIllJ(281);
    //private static final int MAX_SUPPORTED_SDK_VERSION = 20;
    private static final int MAX_SUPPORTED_SDK_VERSION = 20;
    //private static final int MIN_SDK_VERSION = 4;
    private static final int MIN_SDK_VERSION = 4;
    //private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    //private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
    //private static final String NO_KEY_PREFIX = "";
    private static final String NO_KEY_PREFIX = llIIllJ(356);
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
        Log.i(TAG, llIIllJ(431));
        //if (IS_VM_MULTIDEX_CAPABLE) {
        if (IS_VM_MULTIDEX_CAPABLE) {
            //Log.i(TAG, "VM has multidex support, MultiDex support library is disabled.");
            Log.i(TAG, llIIllJ(450));
            //return;
            return;
            //}
        }
        //if (Build.VERSION.SDK_INT < MIN_SDK_VERSION) {
        if (Build.VERSION.SDK_INT < MIN_SDK_VERSION) {
            //throw new RuntimeException("MultiDex installation failed. SDK " + Build.VERSION.SDK_INT
            throw new RuntimeException(llIIllJ(481) + Build.VERSION.SDK_INT
                    //+ " is unsupported. Min SDK version is " + MIN_SDK_VERSION + ".");
                    + llIIllJ(493) + MIN_SDK_VERSION + llIIllJ(501));
            //}
        }
        //try {
        try {
            //ApplicationInfo applicationInfo = getApplicationInfo(context);
            ApplicationInfo applicationInfo = getApplicationInfo(context);
            //if (applicationInfo == null) {
            if (applicationInfo == null) {
                //Log.i(TAG, "No ApplicationInfo available, i.e. running on a test Context:"
                Log.i(TAG, llIIllJ(543)
                        //+ " MultiDex support library is disabled.");
                        + llIIllJ(547));
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
            Log.e(TAG, llIIllJ(610), e);
            //throw new RuntimeException("MultiDex installation failed (" + e.getMessage() + ").");
            throw new RuntimeException(llIIllJ(623) + e.getMessage() + llIIllJ(635));
            //}
        }
        //Log.i(TAG, "install done");
        Log.i(TAG, llIIllJ(648));
        //}
    }

    //

    //public static void installInstrumentation(Context instrumentationContext,
    public static void installInstrumentation(Context instrumentationContext,
                                              //Context targetContext) {
                                              Context targetContext) {
        //Log.i(TAG, "Installing instrumentation");
        Log.i(TAG, llIIllJ(682));
        //if (IS_VM_MULTIDEX_CAPABLE) {
        if (IS_VM_MULTIDEX_CAPABLE) {
            //Log.i(TAG, "VM has multidex support, MultiDex support library is disabled.");
            Log.i(TAG, llIIllJ(701));
            //return;
            return;
            //}
        }
        //if (Build.VERSION.SDK_INT < MIN_SDK_VERSION) {
        if (Build.VERSION.SDK_INT < MIN_SDK_VERSION) {
            //throw new RuntimeException("MultiDex installation failed. SDK " + Build.VERSION.SDK_INT
            throw new RuntimeException(llIIllJ(732) + Build.VERSION.SDK_INT
                    //+ " is unsupported. Min SDK version is " + MIN_SDK_VERSION + ".");
                    + llIIllJ(744) + MIN_SDK_VERSION + llIIllJ(752));
            //}
        }
        //try {
        try {
            //ApplicationInfo instrumentationInfo = getApplicationInfo(instrumentationContext);
            ApplicationInfo instrumentationInfo = getApplicationInfo(instrumentationContext);
            //if (instrumentationInfo == null) {
            if (instrumentationInfo == null) {
                //Log.i(TAG, "No ApplicationInfo available for instrumentation, i.e. running on a"
                Log.i(TAG, llIIllJ(794)
                        //+ " test Context: MultiDex support library is disabled.");
                        + llIIllJ(798));
                //return;
                return;
                //}
            }
            //ApplicationInfo applicationInfo = getApplicationInfo(targetContext);
            ApplicationInfo applicationInfo = getApplicationInfo(targetContext);
            //if (applicationInfo == null) {
            if (applicationInfo == null) {
                //Log.i(TAG, "No ApplicationInfo available, i.e. running on a test Context:"
                Log.i(TAG, llIIllJ(839)
                        //+ " MultiDex support library is disabled.");
                        + llIIllJ(843));
                //return;
                return;
                //}
            }
            //String instrumentationPrefix = instrumentationContext.getPackageName() + ".";
            String instrumentationPrefix = instrumentationContext.getPackageName() + llIIllJ(866);
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
            Log.e(TAG, llIIllJ(964), e);
            //throw new RuntimeException("MultiDex installation failed (" + e.getMessage() + ").");
            throw new RuntimeException(llIIllJ(977) + e.getMessage() + llIIllJ(989));
            //}
        }
        //Log.i(TAG, "Installation done");
        Log.i(TAG, llIIllJ(1002));
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
                Log.w(TAG, llIIllJ(1134)
                        //+ Build.VERSION.SDK_INT + ": SDK version higher than "
                        + Build.VERSION.SDK_INT + llIIllJ(1146)
                        //+ MAX_SUPPORTED_SDK_VERSION + " should be backed by "
                        + MAX_SUPPORTED_SDK_VERSION + llIIllJ(1154)
                        //+ "runtime with built-in multidex capabilty but it's not the "
                        + llIIllJ(1158)
                        //+ "case here: java.vm.version=\""
                        + llIIllJ(1162)
                        //+ System.getProperty("java.vm.version") + "\"");
                        + System.getProperty(llIIllJ(1170)) + llIIllJ(1175));
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
                Log.w(TAG, llIIllJ(1222) +
                        //"Must be running in test mode. Skip patching.", e);
                        llIIllJ(1226), e);
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
                        llIIllJ(1259)
                                //+ "Skip patching.");
                                + llIIllJ(1263));
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
                Log.w(TAG, llIIllJ(1301)
                        //+ "continuing without cleaning.", t);
                        + llIIllJ(1305), t);
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
                    Log.w(TAG, llIIllJ(1444)
                            //+ "forced extraction", e);
                            + llIIllJ(1448), e);
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
            Log.w(TAG, llIIllJ(1602) +
                    //"Must be running in test mode. Skip patching.", e);
                    llIIllJ(1606), e);
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
            Matcher matcher = Pattern.compile(llIIllJ(1669)).matcher(versionString);
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
        Log.i(TAG, llIIllJ(1797) + versionString +
                //(isMultidexCapable ?
                (isMultidexCapable ?
                        //" has multidex support" :
                        llIIllJ(1810) :
                        //" does not have multidex support"));
                        llIIllJ(1814)));
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
        throw new NoSuchFieldException(llIIllJ(2119) + name + llIIllJ(2127) + instance.getClass());
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
        throw new NoSuchMethodException(llIIllJ(2289) + name + llIIllJ(2297) +
                //Arrays.asList(parameterTypes) + " not found in " + instance.getClass());
                Arrays.asList(parameterTypes) + llIIllJ(2310) + instance.getClass());
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
            Log.i(TAG, llIIllJ(2559) + dexDir.getPath() + llIIllJ(2571));
            //File[] files = dexDir.listFiles();
            File[] files = dexDir.listFiles();
            //if (files == null) {
            if (files == null) {
                //Log.w(TAG, "Failed to list secondary dex dir content (" + dexDir.getPath() + ").");
                Log.w(TAG, llIIllJ(2610) + dexDir.getPath() + llIIllJ(2622));
                //return;
                return;
                //}
            }
            //for (File oldFile : files) {
            for (File oldFile : files) {
                //Log.i(TAG, "Trying to delete old file " + oldFile.getPath() + " of size "
                Log.i(TAG, llIIllJ(2652) + oldFile.getPath() + llIIllJ(2664)
                        //+ oldFile.length());
                        + oldFile.length());
                //if (!oldFile.delete()) {
                if (!oldFile.delete()) {
                    //Log.w(TAG, "Failed to delete old file " + oldFile.getPath());
                    Log.w(TAG, llIIllJ(2696) + oldFile.getPath());
                    //} else {
                } else {
                    //Log.i(TAG, "Deleted old file " + oldFile.getPath());
                    Log.i(TAG, llIIllJ(2721) + oldFile.getPath());
                    //}
                }
                //}
            }
            //if (!dexDir.delete()) {
            if (!dexDir.delete()) {
                //Log.w(TAG, "Failed to delete secondary dex dir " + dexDir.getPath());
                Log.w(TAG, llIIllJ(2757) + dexDir.getPath());
                //} else {
            } else {
                //Log.i(TAG, "Deleted old secondary dex dir " + dexDir.getPath());
                Log.i(TAG, llIIllJ(2782) + dexDir.getPath());
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
                Log.e(TAG, llIIllJ(2998) + dir.getPath() + llIIllJ(3010));
                //} else {
            } else {
                //Log.e(TAG, "Failed to create dir " + dir.getPath() +
                Log.e(TAG, llIIllJ(3027) + dir.getPath() +
                        //". parent file is a dir " + parent.isDirectory() +
                        llIIllJ(3039) + parent.isDirectory() +
                        //", a file " + parent.isFile() +
                        llIIllJ(3051) + parent.isFile() +
                        //", exists " + parent.exists() +
                        llIIllJ(3063) + parent.exists() +
                        //", readable " + parent.canRead() +
                        llIIllJ(3075) + parent.canRead() +
                        //", writable " + parent.canWrite());
                        llIIllJ(3087) + parent.canWrite());
                //}
            }
            //throw new IOException("Failed to create directory " + dir.getPath());
            throw new IOException(llIIllJ(3107) + dir.getPath());
            //}
        }
        //}
    }

    //

    static String llIIllJ(int llIIllI) {
        byte[] llIIlll = null;
        try {
            if (llIIllI == -1) {
                if (llIIllI == -2) {
                } else if (llIIllI == -3) {
                } else if (llIIllI == -4) {
                }
            }
            if (llIIllI == 236) {
                llIIlll = new byte[]{-95, -103, -128, -104, -123, -88, -119, -108};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 251) {
                llIIlll = new byte[]{-120, -98, -104, -108, -107, -97, -102, -119, -126, -42, -97, -98, -125, -98, -120};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 266) {
                llIIlll = new byte[]{105, 101, 110, 111, 85, 105, 107, 105, 98, 111};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 281) {
                llIIlll = new byte[]{106, 124, 122, 118, 119, 125, 120, 107, 96, 52, 125, 124, 97, 124, 106};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 356) {
                llIIlll = new byte[]{};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 431) {
                llIIlll = new byte[]{-26, -63, -36, -37, -50, -61, -61, -58, -63, -56, -113, -50, -33, -33, -61, -58, -52, -50, -37, -58, -64, -63};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 450) {
                llIIlll = new byte[]{-108, -113, -30, -86, -93, -79, -30, -81, -73, -82, -74, -85, -90, -89, -70, -30, -79, -73, -78, -78, -83, -80, -74, -18, -30, -113, -73, -82, -74, -85, -122, -89, -70, -30, -79, -73, -78, -78, -83, -80, -74, -30, -82, -85, -96, -80, -93, -80, -69, -30, -85, -79, -30, -90, -85, -79, -93, -96, -82, -89, -90, -20};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 481) {
                llIIlll = new byte[]{-84, -108, -115, -107, -120, -91, -124, -103, -63, -120, -113, -110, -107, -128, -115, -115, -128, -107, -120, -114, -113, -63, -121, -128, -120, -115, -124, -123, -49, -63, -78, -91, -86, -63};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 493) {
                llIIlll = new byte[]{-51, -124, -98, -51, -104, -125, -98, -104, -99, -99, -126, -97, -103, -120, -119, -61, -51, -96, -124, -125, -51, -66, -87, -90, -51, -101, -120, -97, -98, -124, -126, -125, -51, -124, -98, -51};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 501) {
                llIIlll = new byte[]{-37};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 543) {
                llIIlll = new byte[]{81, 112, 63, 94, 111, 111, 115, 118, 124, 126, 107, 118, 112, 113, 86, 113, 121, 112, 63, 126, 105, 126, 118, 115, 126, 125, 115, 122, 51, 63, 118, 49, 122, 49, 63, 109, 106, 113, 113, 118, 113, 120, 63, 112, 113, 63, 126, 63, 107, 122, 108, 107, 63, 92, 112, 113, 107, 122, 103, 107, 37};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 547) {
                llIIlll = new byte[]{3, 110, 86, 79, 87, 74, 103, 70, 91, 3, 80, 86, 83, 83, 76, 81, 87, 3, 79, 74, 65, 81, 66, 81, 90, 3, 74, 80, 3, 71, 74, 80, 66, 65, 79, 70, 71, 13};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 610) {
                llIIlll = new byte[]{47, 23, 14, 22, 11, 38, 7, 26, 66, 11, 12, 17, 22, 3, 14, 14, 3, 22, 11, 13, 12, 66, 4, 3, 11, 14, 23, 16, 7};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 623) {
                llIIlll = new byte[]{34, 26, 3, 27, 6, 43, 10, 23, 79, 6, 1, 28, 27, 14, 3, 3, 14, 27, 6, 0, 1, 79, 9, 14, 6, 3, 10, 11, 79, 71};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 635) {
                llIIlll = new byte[]{82, 85};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 648) {
                llIIlll = new byte[]{-31, -26, -5, -4, -23, -28, -28, -88, -20, -25, -26, -19};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 682) {
                llIIlll = new byte[]{-29, -60, -39, -34, -53, -58, -58, -61, -60, -51, -118, -61, -60, -39, -34, -40, -33, -57, -49, -60, -34, -53, -34, -61, -59, -60};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 701) {
                llIIlll = new byte[]{-21, -16, -99, -43, -36, -50, -99, -48, -56, -47, -55, -44, -39, -40, -59, -99, -50, -56, -51, -51, -46, -49, -55, -111, -99, -16, -56, -47, -55, -44, -7, -40, -59, -99, -50, -56, -51, -51, -46, -49, -55, -99, -47, -44, -33, -49, -36, -49, -60, -99, -44, -50, -99, -39, -44, -50, -36, -33, -47, -40, -39, -109};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 732) {
                llIIlll = new byte[]{-111, -87, -80, -88, -75, -104, -71, -92, -4, -75, -78, -81, -88, -67, -80, -80, -67, -88, -75, -77, -78, -4, -70, -67, -75, -80, -71, -72, -14, -4, -113, -104, -105, -4};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 744) {
                llIIlll = new byte[]{-56, -127, -101, -56, -99, -122, -101, -99, -104, -104, -121, -102, -100, -115, -116, -58, -56, -91, -127, -122, -56, -69, -84, -93, -56, -98, -115, -102, -101, -127, -121, -122, -56, -127, -101, -56};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 752) {
                llIIlll = new byte[]{-34};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 794) {
                llIIlll = new byte[]{84, 117, 58, 91, 106, 106, 118, 115, 121, 123, 110, 115, 117, 116, 83, 116, 124, 117, 58, 123, 108, 123, 115, 118, 123, 120, 118, 127, 58, 124, 117, 104, 58, 115, 116, 105, 110, 104, 111, 119, 127, 116, 110, 123, 110, 115, 117, 116, 54, 58, 115, 52, 127, 52, 58, 104, 111, 116, 116, 115, 116, 125, 58, 117, 116, 58, 123};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 798) {
                llIIlll = new byte[]{62, 106, 123, 109, 106, 62, 93, 113, 112, 106, 123, 102, 106, 36, 62, 83, 107, 114, 106, 119, 90, 123, 102, 62, 109, 107, 110, 110, 113, 108, 106, 62, 114, 119, 124, 108, 127, 108, 103, 62, 119, 109, 62, 122, 119, 109, 127, 124, 114, 123, 122, 48};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 839) {
                llIIlll = new byte[]{9, 40, 103, 6, 55, 55, 43, 46, 36, 38, 51, 46, 40, 41, 14, 41, 33, 40, 103, 38, 49, 38, 46, 43, 38, 37, 43, 34, 107, 103, 46, 105, 34, 105, 103, 53, 50, 41, 41, 46, 41, 32, 103, 40, 41, 103, 38, 103, 51, 34, 52, 51, 103, 4, 40, 41, 51, 34, 63, 51, 125};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 843) {
                llIIlll = new byte[]{107, 6, 62, 39, 63, 34, 15, 46, 51, 107, 56, 62, 59, 59, 36, 57, 63, 107, 39, 34, 41, 57, 42, 57, 50, 107, 34, 56, 107, 47, 34, 56, 42, 41, 39, 46, 47, 101};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 866) {
                llIIlll = new byte[]{76};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 964) {
                llIIlll = new byte[]{-119, -79, -88, -80, -83, -128, -95, -68, -28, -83, -86, -73, -80, -91, -88, -88, -91, -80, -83, -85, -86, -28, -94, -91, -83, -88, -79, -74, -95};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 977) {
                llIIlll = new byte[]{-100, -92, -67, -91, -72, -107, -76, -87, -15, -72, -65, -94, -91, -80, -67, -67, -80, -91, -72, -66, -65, -15, -73, -80, -72, -67, -76, -75, -15, -7};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 989) {
                llIIlll = new byte[]{-12, -13};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1002) {
                llIIlll = new byte[]{-93, -124, -103, -98, -117, -122, -122, -117, -98, -125, -123, -124, -54, -114, -123, -124, -113};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1134) {
                llIIlll = new byte[]{35, 27, 2, 26, 7, 42, 11, 22, 78, 7, 29, 78, 0, 1, 26, 78, 9, 27, 15, 28, 15, 0, 26, 11, 11, 10, 78, 26, 1, 78, 25, 1, 28, 5, 78, 7, 0, 78, 61, 42, 37, 78, 24, 11, 28, 29, 7, 1, 0, 78};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1146) {
                llIIlll = new byte[]{64, 90, 41, 62, 49, 90, 12, 31, 8, 9, 19, 21, 20, 90, 18, 19, 29, 18, 31, 8, 90, 14, 18, 27, 20, 90};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1154) {
                llIIlll = new byte[]{-94, -15, -22, -19, -9, -18, -26, -94, -32, -25, -94, -32, -29, -31, -23, -25, -26, -94, -32, -5, -94};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1158) {
                llIIlll = new byte[]{-12, -13, -24, -14, -17, -21, -29, -90, -15, -17, -14, -18, -90, -28, -13, -17, -22, -14, -85, -17, -24, -90, -21, -13, -22, -14, -17, -30, -29, -2, -90, -27, -25, -10, -25, -28, -17, -22, -14, -1, -90, -28, -13, -14, -90, -17, -14, -95, -11, -90, -24, -23, -14, -90, -14, -18, -29, -90};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1162) {
                llIIlll = new byte[]{-23, -21, -7, -17, -86, -30, -17, -8, -17, -80, -86, -32, -21, -4, -21, -92, -4, -25, -92, -4, -17, -8, -7, -29, -27, -28, -73, -88};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1170) {
                llIIlll = new byte[]{-8, -13, -28, -13, -68, -28, -1, -68, -28, -9, -32, -31, -5, -3, -4};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1175) {
                llIIlll = new byte[]{-75};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1222) {
                llIIlll = new byte[]{-128, -89, -81, -86, -77, -76, -93, -26, -79, -82, -81, -86, -93, -26, -78, -76, -65, -81, -88, -95, -26, -78, -87, -26, -87, -92, -78, -89, -81, -88, -26, -123, -87, -88, -78, -93, -66, -78, -26, -91, -86, -89, -75, -75, -26, -86, -87, -89, -94, -93, -76, -24, -26};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1226) {
                llIIlll = new byte[]{-121, -65, -71, -66, -22, -88, -81, -22, -72, -65, -92, -92, -93, -92, -83, -22, -93, -92, -22, -66, -81, -71, -66, -22, -89, -91, -82, -81, -28, -22, -103, -95, -93, -70, -22, -70, -85, -66, -87, -94, -93, -92, -83, -28};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1259) {
                llIIlll = new byte[]{-88, -124, -123, -97, -114, -109, -97, -53, -120, -121, -118, -104, -104, -53, -121, -124, -118, -113, -114, -103, -53, -126, -104, -53, -123, -98, -121, -121, -59, -53, -90, -98, -104, -97, -53, -119, -114, -53, -103, -98, -123, -123, -126, -123, -116, -53, -126, -123, -53, -97, -114, -104, -97, -53, -122, -124, -113, -114, -59, -53};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1263) {
                llIIlll = new byte[]{-68, -124, -122, -97, -49, -97, -114, -101, -116, -121, -122, -127, -120, -63};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1301) {
                llIIlll = new byte[]{70, 122, 120, 112, 97, 125, 124, 123, 114, 53, 98, 112, 123, 97, 53, 98, 103, 122, 123, 114, 53, 98, 125, 112, 123, 53, 97, 103, 108, 124, 123, 114, 53, 97, 122, 53, 118, 121, 112, 116, 103, 53, 122, 121, 113, 53, 88, 96, 121, 97, 124, 81, 112, 109, 53, 112, 109, 97, 103, 116, 118, 97, 124, 122, 123, 57, 53};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1305) {
                llIIlll = new byte[]{122, 118, 119, 109, 112, 119, 108, 112, 119, 126, 57, 110, 112, 109, 113, 118, 108, 109, 57, 122, 117, 124, 120, 119, 112, 119, 126, 55};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1444) {
                llIIlll = new byte[]{-30, -59, -51, -56, -63, -64, -124, -48, -53, -124, -51, -54, -41, -48, -59, -56, -56, -124, -63, -36, -48, -42, -59, -57, -48, -63, -64, -124, -41, -63, -57, -53, -54, -64, -59, -42, -35, -124, -64, -63, -36, -124, -62, -51, -56, -63, -41, -120, -124, -42, -63, -48, -42, -35, -51, -54, -61, -124, -45, -51, -48, -52, -124};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1448) {
                llIIlll = new byte[]{-50, -57, -38, -53, -51, -52, -120, -51, -48, -36, -38, -55, -53, -36, -63, -57, -58};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1602) {
                llIIlll = new byte[]{4, 35, 43, 46, 55, 48, 39, 98, 53, 42, 43, 46, 39, 98, 54, 48, 59, 43, 44, 37, 98, 54, 45, 98, 45, 32, 54, 35, 43, 44, 98, 3, 50, 50, 46, 43, 33, 35, 54, 43, 45, 44, 11, 44, 36, 45, 98, 36, 48, 45, 47, 98, 1, 45, 44, 54, 39, 58, 54, 108, 98};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1606) {
                llIIlll = new byte[]{11, 51, 53, 50, 102, 36, 35, 102, 52, 51, 40, 40, 47, 40, 33, 102, 47, 40, 102, 50, 35, 53, 50, 102, 43, 41, 34, 35, 104, 102, 21, 45, 47, 54, 102, 54, 39, 50, 37, 46, 47, 40, 33, 104};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1669) {
                llIIlll = new byte[]{-83, -39, -31, -82, -84, -39, -85, -83, -39, -31, -82, -84, -83, -39, -85, -39, -31, -82, -84, -70};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1797) {
                llIIlll = new byte[]{83, 72, 37, 114, 108, 113, 109, 37, 115, 96, 119, 118, 108, 106, 107, 37};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1810) {
                llIIlll = new byte[]{50, 122, 115, 97, 50, 127, 103, 126, 102, 123, 118, 119, 106, 50, 97, 103, 98, 98, 125, 96, 102};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 1814) {
                llIIlll = new byte[]{54, 114, 121, 115, 101, 54, 120, 121, 98, 54, 126, 119, 96, 115, 54, 123, 99, 122, 98, 127, 114, 115, 110, 54, 101, 99, 102, 102, 121, 100, 98};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2119) {
                llIIlll = new byte[]{1, 46, 34, 43, 35, 103};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2127) {
                llIIlll = new byte[]{111, 33, 32, 59, 111, 41, 32, 58, 33, 43, 111, 38, 33, 111};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2289) {
                llIIlll = new byte[]{-68, -108, -123, -103, -98, -107, -47};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2297) {
                llIIlll = new byte[]{-39, -114, -112, -115, -111, -39, -119, -104, -117, -104, -108, -100, -115, -100, -117, -118, -39};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2310) {
                llIIlll = new byte[]{38, 104, 105, 114, 38, 96, 105, 115, 104, 98, 38, 111, 104, 38};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2559) {
                llIIlll = new byte[]{-68, -109, -102, -98, -115, -106, -111, -104, -33, -112, -109, -101, -33, -116, -102, -100, -112, -111, -101, -98, -115, -122, -33, -101, -102, -121, -33, -101, -106, -115, -33, -41};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2571) {
                llIIlll = new byte[]{34, 37};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2610) {
                llIIlll = new byte[]{116, 83, 91, 94, 87, 86, 18, 70, 93, 18, 94, 91, 65, 70, 18, 65, 87, 81, 93, 92, 86, 83, 64, 75, 18, 86, 87, 74, 18, 86, 91, 64, 18, 81, 93, 92, 70, 87, 92, 70, 18, 26};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2622) {
                llIIlll = new byte[]{23, 16};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2652) {
                llIIlll = new byte[]{8, 46, 37, 53, 50, 59, 124, 40, 51, 124, 56, 57, 48, 57, 40, 57, 124, 51, 48, 56, 124, 58, 53, 48, 57, 124};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2664) {
                llIIlll = new byte[]{72, 7, 14, 72, 27, 1, 18, 13, 72};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2696) {
                llIIlll = new byte[]{-50, -23, -31, -28, -19, -20, -88, -4, -25, -88, -20, -19, -28, -19, -4, -19, -88, -25, -28, -20, -88, -18, -31, -28, -19, -88};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2721) {
                llIIlll = new byte[]{-27, -60, -51, -60, -43, -60, -59, -127, -50, -51, -59, -127, -57, -56, -51, -60, -127};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2757) {
                llIIlll = new byte[]{-125, -92, -84, -87, -96, -95, -27, -79, -86, -27, -95, -96, -87, -96, -79, -96, -27, -74, -96, -90, -86, -85, -95, -92, -73, -68, -27, -95, -96, -67, -27, -95, -84, -73, -27};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2782) {
                llIIlll = new byte[]{-102, -69, -78, -69, -86, -69, -70, -2, -79, -78, -70, -2, -83, -69, -67, -79, -80, -70, -65, -84, -89, -2, -70, -69, -90, -2, -70, -73, -84, -2};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 2998) {
                llIIlll = new byte[]{-16, -41, -33, -38, -45, -46, -106, -62, -39, -106, -43, -60, -45, -41, -62, -45, -106, -46, -33, -60, -106};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3010) {
                llIIlll = new byte[]{-20, -30, -110, -93, -80, -89, -84, -74, -30, -92, -85, -82, -89, -30, -85, -79, -30, -84, -73, -82, -82, -20};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3027) {
                llIIlll = new byte[]{-107, -78, -70, -65, -74, -73, -13, -89, -68, -13, -80, -95, -74, -78, -89, -74, -13, -73, -70, -95, -13};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3039) {
                llIIlll = new byte[]{-15, -1, -81, -66, -83, -70, -79, -85, -1, -71, -74, -77, -70, -1, -74, -84, -1, -66, -1, -69, -74, -83, -1};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3051) {
                llIIlll = new byte[]{-57, -53, -118, -53, -115, -126, -121, -114, -53};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3063) {
                llIIlll = new byte[]{-37, -41, -110, -113, -98, -124, -125, -124, -41};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3075) {
                llIIlll = new byte[]{47, 35, 113, 102, 98, 103, 98, 97, 111, 102, 35};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3087) {
                llIIlll = new byte[]{35, 47, 120, 125, 102, 123, 110, 109, 99, 106, 47};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3107) {
                llIIlll = new byte[]{101, 66, 74, 79, 70, 71, 3, 87, 76, 3, 64, 81, 70, 66, 87, 70, 3, 71, 74, 81, 70, 64, 87, 76, 81, 90, 3};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3197) {
                llIIlll = new byte[]{13, 28, 9, 21, 49, 20, 14, 9};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3239) {
                llIIlll = new byte[]{-61, -62, -33, -30, -53, -62, -54, -62, -55, -45, -44};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3303) {
                llIIlll = new byte[]{-94, -97, -124, -126, -105, -109, -114, -120, -119, -57, -114, -119, -57, -118, -122, -116, -126, -93, -126, -97, -94, -117, -126, -118, -126, -119, -109};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3323) {
                llIIlll = new byte[]{-97, -98, -125, -66, -105, -98, -106, -98, -107, -113, -120, -88, -114, -117, -117, -119, -98, -120, -120, -98, -97, -66, -125, -104, -98, -117, -113, -110, -108, -107, -120};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3480) {
                llIIlll = new byte[]{-47, -73, -41, -72, -3, -32, -5, -3, -24, -20, -15, -9, -10, -72, -4, -19, -22, -15, -10, -1, -72, -11, -7, -13, -3, -36, -3, -32, -35, -12, -3, -11, -3, -10, -20};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3580) {
                llIIlll = new byte[]{-111, -99, -105, -103, -84, -99, -120, -108, -71, -112, -103, -111, -103, -110, -120, -113};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3605) {
                llIIlll = new byte[]{120, 116, 126, 112, 81, 112, 109, 80, 121, 112, 120, 112, 123, 97, 102};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3732) {
                llIIlll = new byte[]{-16, -11, -8, -30, -3, -1, -70, -25, -19, -25, -32, -15, -7, -70, -48, -15, -20, -60, -11, -32, -4, -40, -3, -25, -32, -80, -47, -8, -15, -7, -15, -6, -32};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3884) {
                llIIlll = new byte[]{92, 77, 88, 68, 96, 69, 95, 88};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3931) {
                llIIlll = new byte[]{63, 62, 35, 30, 55, 62, 54, 62, 53, 47, 40};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3959) {
                llIIlll = new byte[]{49, 22, 30, 27, 18, 19, 87, 17, 30, 25, 19, 87, 17, 30, 18, 27, 19, 87, 80, 19, 18, 15, 50, 27, 18, 26, 18, 25, 3, 4, 80, 87, 22, 3, 3, 18, 26, 7, 3, 30, 25, 16, 87, 80, 7, 22, 3, 31, 50, 27, 18, 26, 18, 25, 3, 4, 80};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 3971) {
                llIIlll = new byte[]{-13, -30, -9, -21, -58, -17, -26, -18, -26, -19, -9, -16};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 4773) {
                llIIlll = new byte[]{-43, -60, -47, -51};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 5003) {
                llIIlll = new byte[]{-91, -17, -18, -13};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 5032) {
                llIIlll = new byte[]{-59, -8, -55, -36, -64, -37};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 5044) {
                llIIlll = new byte[]{-39, -14, -35, -40, -47, -57};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 5056) {
                llIIlll = new byte[]{-83, -102, -87, -80, -77};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
                }
            }
            if (llIIllI == 5068) {
                llIIlll = new byte[]{-95, -120, -87, -76, -65};

                for (int llIIll1 = 0; llIIll1 < llIIlll.length; llIIll1++) {
                    llIIlll[llIIll1] = (byte) (llIIlll[llIIll1] ^ llIIllI);
                }
                {
                    return new String(llIIlll, StandardCharsets.UTF_8);
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
            Field pathListField = findField(loader, llIIllJ(3197));
            //Object dexPathList = pathListField.get(loader);
            Object dexPathList = pathListField.get(loader);
            //ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
            ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
            //expandFieldArray(dexPathList, "dexElements", makeDexElements(dexPathList,
            expandFieldArray(dexPathList, llIIllJ(3239), makeDexElements(dexPathList,
                    //new ArrayList<File>(additionalClassPathEntries), optimizedDirectory,
                    new ArrayList<File>(additionalClassPathEntries), optimizedDirectory,
                    //suppressedExceptions));
                    suppressedExceptions));
            //if (suppressedExceptions.size() > 0) {
            if (suppressedExceptions.size() > 0) {
                //for (IOException e : suppressedExceptions) {
                for (IOException e : suppressedExceptions) {
                    //Log.w(TAG, "Exception in makeDexElement", e);
                    Log.w(TAG, llIIllJ(3303), e);
                    //}
                }
                //Field suppressedExceptionsField =
                Field suppressedExceptionsField =
                        //findField(dexPathList, "dexElementsSuppressedExceptions");
                        findField(dexPathList, llIIllJ(3323));
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
                IOException exception = new IOException(llIIllJ(3480), suppressedExceptions.get(0));
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
                            findMethod(dexPathList, llIIllJ(3580), List.class, File.class, List.class) :
                            //findMethod(dexPathList, "makeDexElements", ArrayList.class, File.class, ArrayList.class);
                            findMethod(dexPathList, llIIllJ(3605), ArrayList.class, File.class, ArrayList.class);
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
            Class<?> elementClass = Class.forName(llIIllJ(3732));
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
            Field pathListField = findField(loader, llIIllJ(3884));
            //Object dexPathList = pathListField.get(loader);
            Object dexPathList = pathListField.get(loader);
            //Object[] elements = new V14().makeDexElements(additionalClassPathEntries);
            Object[] elements = new V14().makeDexElements(additionalClassPathEntries);
            //try {
            try {
                //expandFieldArray(dexPathList, "dexElements", elements);
                expandFieldArray(dexPathList, llIIllJ(3931), elements);
                //} catch (NoSuchFieldException e) {
            } catch (NoSuchFieldException e) {
                //

                //

                //Log.w(TAG, "Failed find field 'dexElements' attempting 'pathElements'", e);
                Log.w(TAG, llIIllJ(3959), e);
                //expandFieldArray(dexPathList, "pathElements", elements);
                expandFieldArray(dexPathList, llIIllJ(3971), elements);
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
            Field pathField = findField(loader, llIIllJ(4773));
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
                extraDexs[index] = DexFile.loadDex(entryPath, entryPath + llIIllJ(5003), 0);
                //}
            }
            //pathField.set(loader, path.toString());
            pathField.set(loader, path.toString());
            //expandFieldArray(loader, "mPaths", extraPaths);
            expandFieldArray(loader, llIIllJ(5032), extraPaths);
            //expandFieldArray(loader, "mFiles", extraFiles);
            expandFieldArray(loader, llIIllJ(5044), extraFiles);
            //expandFieldArray(loader, "mZips", extraZips);
            expandFieldArray(loader, llIIllJ(5056), extraZips);
            //expandFieldArray(loader, "mDexs", extraDexs);
            expandFieldArray(loader, llIIllJ(5068), extraDexs);
            //}
        }
        //}
    }
}//

//
  	