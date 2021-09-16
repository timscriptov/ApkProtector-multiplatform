/* Orginal file: MultiDexExtractor.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.multidex*/
package com.mcal.apkprotector.aA;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.mcal.apkprotector.bB.Const;
import com.mcal.apkprotector.dD.DexCrypto;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

//

final class MultiDexExtractor implements Closeable {
    //static final String DEX_SUFFIX = Const.DEX_SUFFIX;
    static final String DEX_SUFFIX = Const.DEX_SUFFIX;
    //static final String EXTRACTED_SUFFIX = Const.ZIP;
    static final String EXTRACTED_SUFFIX = Const.ZIP;
    //private static final String TAG = MultiDex.TAG;
    private static final String TAG = MultiDex.TAG;
    //private static final String EXTRACTED_NAME_EXT = Const.CLASSES;
    private static final String EXTRACTED_NAME_EXT = Const.CLASSES;
    //private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    //private static final String PREFS_FILE = "multidex.version";
    private static final String PREFS_FILE = llIII1l(344);
    //private static final String KEY_TIME_STAMP = "timestamp";
    private static final String KEY_TIME_STAMP = llIII1l(359);
    //private static final String KEY_CRC = "crc";
    private static final String KEY_CRC = llIII1l(374);
    //private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_NUMBER = llIII1l(389);
    //private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_CRC = llIII1l(404);
    //private static final String KEY_DEX_TIME = "dex.time.";
    private static final String KEY_DEX_TIME = llIII1l(419);
    //

    //

    //private static final long NO_VALUE = -1L;
    private static final long NO_VALUE = -1L;
    //private static final String LOCK_FILENAME = Const.MULTIDEX_LOCK;
    private static final String LOCK_FILENAME = Const.MULTIDEX_LOCK;
    //

    //private static final String PROTECT_KEY = Const.PROTECT_KEY;
    private static final String PROTECT_KEY = Const.PROTECT_KEY;
    //private static final String DEX_DIR = Const.DEX_DIR;
    private static final String DEX_DIR = Const.DEX_DIR;
    //private static final String APK_DEX_DIR = "assets" + File.separator + DEX_DIR + File.separator;
    private static final String APK_DEX_DIR = llIII1l(504) + File.separator + DEX_DIR + File.separator;
    //private static final String DEX_PREFIX = Const.DEX_PREFIX;
    private static final String DEX_PREFIX = Const.DEX_PREFIX;
    //private final File sourceApk;
    private final File sourceApk;
    //private final long sourceCrc;
    private final long sourceCrc;
    //private final File dexDir;
    private final File dexDir;
    //private final RandomAccessFile lockRaf;
    private final RandomAccessFile lockRaf;
    //private final FileChannel lockChannel;
    private final FileChannel lockChannel;
    //private final FileLock cacheLock;
    private final FileLock cacheLock;

    MultiDexExtractor(File sourceApk, File dexDir) throws IOException {
        //Log.i(TAG, "MultiDexExtractor(" + sourceApk.getPath() + ", " + dexDir.getPath() + ")");
        Log.i(TAG, llIII1l(619) + sourceApk.getPath() + llIII1l(631) + dexDir.getPath() + llIII1l(643));
        //this.sourceApk = sourceApk;
        this.sourceApk = sourceApk;
        //this.dexDir = dexDir;
        this.dexDir = dexDir;
        //sourceCrc = getZipCrc(sourceApk);
        sourceCrc = getZipCrc(sourceApk);
        //File lockFile = new File(dexDir, LOCK_FILENAME);
        File lockFile = new File(dexDir, LOCK_FILENAME);
        //lockRaf = new RandomAccessFile(lockFile, "rw");
        lockRaf = new RandomAccessFile(lockFile, llIII1l(703));
        //try {
        try {
            //lockChannel = lockRaf.getChannel();
            lockChannel = lockRaf.getChannel();
            //try {
            try {
                //Log.i(TAG, "Blocking on lock " + lockFile.getPath());
                Log.i(TAG, llIII1l(733) + lockFile.getPath());
                //cacheLock = lockChannel.lock();
                cacheLock = lockChannel.lock();
                //} catch (IOException | RuntimeException | Error e) {
            } catch (IOException | RuntimeException | Error e) {
                //closeQuietly(lockChannel);
                closeQuietly(lockChannel);
                //throw e;
                throw e;
                //}
            }
            //Log.i(TAG, lockFile.getPath() + " locked");
            Log.i(TAG, lockFile.getPath() + llIII1l(804));
            //} catch (IOException | RuntimeException | Error e) {
        } catch (IOException | RuntimeException | Error e) {
            //closeQuietly(lockRaf);
            closeQuietly(lockRaf);
            //throw e;
            throw e;
            //}
        }
        //}
    }

    //

    private static boolean isModified(Context context, File archive, long currentCrc,
                                      String prefsKeyPrefix) {
        //SharedPreferences prefs = getMultiDexPreferences(context);
        SharedPreferences prefs = getMultiDexPreferences(context);
        //return (prefs.getLong(prefsKeyPrefix + KEY_TIME_STAMP, NO_VALUE) != getTimeStamp(archive))
        return (prefs.getLong(prefsKeyPrefix + KEY_TIME_STAMP, NO_VALUE) != getTimeStamp(archive))
                //|| (prefs.getLong(prefsKeyPrefix + KEY_CRC, NO_VALUE) != currentCrc);
                || (prefs.getLong(prefsKeyPrefix + KEY_CRC, NO_VALUE) != currentCrc);
        //}
    }

    //private static long getTimeStamp(File archive) {
    private static long getTimeStamp(File archive) {
        long timeStamp = archive.lastModified();
        //if (timeStamp == NO_VALUE) {
        if (timeStamp == NO_VALUE) {
            //

            //timeStamp--;
            timeStamp--;
            //}
        }
        //return timeStamp;
        return timeStamp;
        //}
    }

    //private static long getZipCrc(File archive) throws IOException {
    private static long getZipCrc(File archive) throws IOException {
        //long computedValue = ZipUtil.getZipCrc(archive);
        long computedValue = ZipUtil.getZipCrc(archive);
        //if (computedValue == NO_VALUE) {
        if (computedValue == NO_VALUE) {
            //

            //computedValue--;
            computedValue--;
            //}
        }
        //return computedValue;
        return computedValue;
        //}
    }

    //

    //private static void putStoredApkInfo(Context context, String keyPrefix, long timeStamp,
    private static void putStoredApkInfo(Context context, String keyPrefix, long timeStamp,
                                         long crc, List<ExtractedDex> extractedDexes) {
        //SharedPreferences prefs = getMultiDexPreferences(context);
        SharedPreferences prefs = getMultiDexPreferences(context);
        //SharedPreferences.Editor edit = prefs.edit();
        SharedPreferences.Editor edit = prefs.edit();
        //edit.putLong(keyPrefix + KEY_TIME_STAMP, timeStamp);
        edit.putLong(keyPrefix + KEY_TIME_STAMP, timeStamp);
        //edit.putLong(keyPrefix + KEY_CRC, crc);
        edit.putLong(keyPrefix + KEY_CRC, crc);
        //edit.putInt(keyPrefix + KEY_DEX_NUMBER, extractedDexes.size() + 1);
        edit.putInt(keyPrefix + KEY_DEX_NUMBER, extractedDexes.size() + 1);
        //int extractedDexId = 2;
        int extractedDexId = 2;
        //for (ExtractedDex dex : extractedDexes) {
        for (ExtractedDex dex : extractedDexes) {
            //edit.putLong(keyPrefix + KEY_DEX_CRC + extractedDexId, dex.crc);
            edit.putLong(keyPrefix + KEY_DEX_CRC + extractedDexId, dex.crc);
            //edit.putLong(keyPrefix + KEY_DEX_TIME + extractedDexId, dex.lastModified());
            edit.putLong(keyPrefix + KEY_DEX_TIME + extractedDexId, dex.lastModified());
            //extractedDexId++;
            extractedDexId++;
            //}
        }
        //

        //edit.apply();
        edit.apply();
        //}
    }

    //

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE,
                //Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                        //? Context.MODE_PRIVATE
                        ? Context.MODE_PRIVATE
                        //: Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
                        : Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        //}
    }

    private static void extract(ZipFile apk, ZipEntry dexFile, File extractTo,
                                String extractedFilePrefix) throws IOException {

        //InputStream in = apk.getInputStream(dexFile);
        InputStream in = apk.getInputStream(dexFile);

        //ZipOutputStream out = null;
        ZipOutputStream out = null;
        //

        //File tmp = File.createTempFile("tmp-" + extractedFilePrefix, EXTRACTED_SUFFIX,
        File tmp = File.createTempFile(llIII1l(1391) + extractedFilePrefix, EXTRACTED_SUFFIX,
                //extractTo.getParentFile());
                extractTo.getParentFile());
        //Log.i(TAG, "Extracting " + tmp.getPath());
        Log.i(TAG, llIII1l(1416) + tmp.getPath());
        //try {
        try {
            //out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmp)));
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmp)));
            //try {
            try {
                //ZipEntry classesDex = new ZipEntry("classes.dex");
                ZipEntry classesDex = new ZipEntry(llIII1l(1468));
                //

                //classesDex.setTime(dexFile.getTime());
                classesDex.setTime(dexFile.getTime());
                //out.putNextEntry(classesDex);
                out.putNextEntry(classesDex);
                //

                //DexCrypto.decrypt(PROTECT_KEY, in, out);
                DexCrypto.decrypt(PROTECT_KEY, in, out);
                //out.closeEntry();
                out.closeEntry();
                //} catch (Exception e) {
            } catch (Exception e) {
                //e.printStackTrace();
                e.printStackTrace();
                //} finally {
            } finally {
                //out.close();
                out.close();
                //}
            }
            //if (!tmp.setReadOnly()) {
            if (!tmp.setReadOnly()) {
                //throw new IOException("Failed to mark readonly \"" + tmp.getAbsolutePath() +
                throw new IOException(llIII1l(1568) + tmp.getAbsolutePath() +
                        //"\" (tmp of \"" + extractTo.getAbsolutePath() + "\")");
                        llIII1l(1580) + extractTo.getAbsolutePath() + llIII1l(1592));
                //}
            }
            //Log.i(TAG, "Renaming to " + extractTo.getPath());
            Log.i(TAG, llIII1l(1605) + extractTo.getPath());
            //if (!tmp.renameTo(extractTo)) {
            if (!tmp.renameTo(extractTo)) {
                //throw new IOException("Failed to rename \"" + tmp.getAbsolutePath() +
                throw new IOException(llIII1l(1637) + tmp.getAbsolutePath() +
                        //"\" to \"" + extractTo.getAbsolutePath() + "\"");
                        llIII1l(1649) + extractTo.getAbsolutePath() + llIII1l(1661));
                //}
            }

            //} finally {
        } finally {
            //closeQuietly(in);
            closeQuietly(in);
            //tmp.delete(); 
            tmp.delete();
            //}
        }
        //}
    }

    //

    private static void closeQuietly(Closeable closeable) {
        //try {
        try {
            //closeable.close();
            closeable.close();
            //} catch (IOException e) {
        } catch (IOException e) {
            //Log.w(TAG, "Failed to close resource", e);
            Log.w(TAG, llIII1l(1737), e);
            //}
        }
        //}
    }

    //

    static String llIII1l(int llIII1) {
        byte[] llIIIJ1 = null;
        try {
            if (llIII1 == -1) {
                if (llIII1 == -2) {
                } else if (llIII1 == -3) {
                } else if (llIII1 == -4) {
                }
            }
            if (llIII1 == 344) {
                llIIIJ1 = new byte[]{53, 45, 52, 44, 49, 60, 61, 32, 118, 46, 61, 42, 43, 49, 55, 54};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 359) {
                llIIIJ1 = new byte[]{19, 14, 10, 2, 20, 19, 6, 10, 23};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 374) {
                llIIIJ1 = new byte[]{21, 4, 21};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 389) {
                llIIIJ1 = new byte[]{-31, -32, -3, -85, -21, -16, -24, -25, -32, -9};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 404) {
                llIIIJ1 = new byte[]{-16, -15, -20, -70, -9, -26, -9, -70};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 419) {
                llIIIJ1 = new byte[]{-57, -58, -37, -115, -41, -54, -50, -58, -115};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 504) {
                llIIIJ1 = new byte[]{-103, -117, -117, -99, -116, -117};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 619) {
                llIIIJ1 = new byte[]{38, 30, 7, 31, 2, 47, 14, 19, 46, 19, 31, 25, 10, 8, 31, 4, 25, 67};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 631) {
                llIIIJ1 = new byte[]{91, 87};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 643) {
                llIIIJ1 = new byte[]{-86};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 703) {
                llIIIJ1 = new byte[]{-51, -56};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 733) {
                llIIIJ1 = new byte[]{-97, -79, -78, -66, -74, -76, -77, -70, -3, -78, -77, -3, -79, -78, -66, -74, -3};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 804) {
                llIIIJ1 = new byte[]{4, 72, 75, 71, 79, 65, 64};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1391) {
                llIIIJ1 = new byte[]{27, 2, 31, 66};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1416) {
                llIIIJ1 = new byte[]{-51, -16, -4, -6, -23, -21, -4, -31, -26, -17, -88};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1468) {
                llIIIJ1 = new byte[]{-33, -48, -35, -49, -49, -39, -49, -110, -40, -39, -60};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1568) {
                llIIIJ1 = new byte[]{102, 65, 73, 76, 69, 68, 0, 84, 79, 0, 77, 65, 82, 75, 0, 82, 69, 65, 68, 79, 78, 76, 89, 0, 2};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1580) {
                llIIIJ1 = new byte[]{14, 12, 4, 88, 65, 92, 12, 67, 74, 12, 14};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1592) {
                llIIIJ1 = new byte[]{26, 17};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1605) {
                llIIIJ1 = new byte[]{23, 32, 43, 36, 40, 44, 43, 34, 101, 49, 42, 101};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1637) {
                llIIIJ1 = new byte[]{35, 4, 12, 9, 0, 1, 69, 17, 10, 69, 23, 0, 11, 4, 8, 0, 69, 71};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1649) {
                llIIIJ1 = new byte[]{83, 81, 5, 30, 81, 83};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1661) {
                llIIIJ1 = new byte[]{95};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1737) {
                llIIIJ1 = new byte[]{-113, -88, -96, -91, -84, -83, -23, -67, -90, -23, -86, -91, -90, -70, -84, -23, -69, -84, -70, -90, -68, -69, -86, -84};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1788) {
                llIIIJ1 = new byte[]{-79, -119, -112, -120, -107, -72, -103, -124, -71, -124, -120, -114, -99, -97, -120, -109, -114, -46, -112, -109, -99, -104, -44};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1800) {
                llIIIJ1 = new byte[]{36, 40};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1808) {
                llIIIJ1 = new byte[]{60, 48};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1816) {
                llIIIJ1 = new byte[]{49};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1839) {
                llIIIJ1 = new byte[]{98, 90, 67, 91, 70, 107, 74, 87, 106, 87, 91, 93, 78, 76, 91, 64, 93, 15, 88, 78, 92, 15, 76, 67, 64, 92, 74, 75};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1916) {
                llIIIJ1 = new byte[]{58, 29, 21, 16, 25, 24, 92, 8, 19, 92, 14, 25, 16, 19, 29, 24, 92, 25, 4, 21, 15, 8, 21, 18, 27, 92, 25, 4, 8, 14, 29, 31, 8, 25, 24, 92, 15, 25, 31, 19, 18, 24, 29, 14, 5, 92, 24, 25, 4, 92, 26, 21, 16, 25, 15, 80};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1920) {
                llIIIJ1 = new byte[]{-96, -26, -31, -20, -20, -23, -18, -25, -96, -30, -31, -29, -21, -96, -12, -17, -96, -26, -14, -27, -13, -24, -96, -27, -8, -12, -14, -31, -29, -12, -23, -17, -18};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1980) {
                llIIIJ1 = new byte[]{-6, -45, -50, -33, -39, -40, -100, -39, -60, -56, -50, -35, -33, -56, -43, -45, -46, -100, -47, -55, -49, -56, -100, -34, -39, -100, -52, -39, -50, -38, -45, -50, -47, -39, -40, -110};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 1997) {
                llIIIJ1 = new byte[]{-119, -88, -71, -88, -82, -71, -88, -87, -19, -71, -91, -84, -71, -19, -88, -75, -71, -65, -84, -82, -71, -92, -94, -93, -19, -96, -72, -66, -71, -19, -81, -88, -19, -67, -88, -65, -85, -94, -65, -96, -88, -87, -29};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2042) {
                llIIIJ1 = new byte[]{-106, -107, -101, -98, -38, -100, -107, -113, -108, -98, -38};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2054) {
                llIIIJ1 = new byte[]{38, 117, 99, 101, 105, 104, 98, 103, 116, 127, 38, 98, 99, 126, 38, 96, 111, 106, 99, 117};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2138) {
                llIIIJ1 = new byte[]{54, 53, 59, 62, 51, 52, 61, 122, 63, 34, 51, 41, 46, 51, 52, 61, 122, 41, 63, 57, 53, 52, 62, 59, 40, 35, 122, 62, 63, 34, 122, 60, 51, 54, 63, 41};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2405) {
                llIIIJ1 = new byte[]{44, 11, 19, 4, 9, 12, 1, 69, 0, 29, 17, 23, 4, 6, 17, 0, 1, 69, 1, 0, 29, 95, 69};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2413) {
                llIIIJ1 = new byte[]{77, 69, 6, 8, 20, 77, 79};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2421) {
                llIIIJ1 = new byte[]{87, 92, 89, 85, 16, 13, 5, 16, 22, 1, 16, 17, 85, 24, 26, 17, 28, 19, 28, 22, 20, 1, 28, 26, 27, 85, 1, 28, 24, 16, 79, 85};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2429) {
                llIIIJ1 = new byte[]{81, 93, 16, 18, 25, 20, 27, 20, 30, 28, 9, 20, 18, 19, 93, 9, 20, 16, 24, 71, 93};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2437) {
                llIIIJ1 = new byte[]{-87, -91, -32, -3, -11, -32, -26, -15, -32, -31, -91, -26, -9, -26, -65, -91};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2445) {
                llIIIJ1 = new byte[]{-95, -83, -21, -28, -31, -24, -83, -18, -1, -18, -73, -83};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2477) {
                llIIIJ1 = new byte[]{-32, -60, -34, -34, -60, -61, -54, -115, -56, -43, -39, -33, -52, -50, -39, -56, -55, -115, -34, -56, -50, -62, -61, -55, -52, -33, -44, -115, -55, -56, -43, -115, -53, -60, -63, -56, -115, -118};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2489) {
                llIIIJ1 = new byte[]{-98};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2684) {
                llIIIJ1 = new byte[]{57, 4, 8, 14, 29, 31, 8, 21, 19, 18, 92, 21, 15, 92, 18, 25, 25, 24, 25, 24, 92, 26, 19, 14, 92, 26, 21, 16, 25, 92};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2800) {
                llIIIJ1 = new byte[]{-74, -111, -103, -100, -107, -108, -48, -124, -97, -48, -126, -107, -111, -108, -48, -109, -126, -109, -48, -106, -126, -97, -99, -48};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2825) {
                llIIIJ1 = new byte[]{76, 113, 125, 123, 104, 106, 125, 96, 102, 103, 41};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2834) {
                llIIIJ1 = new byte[]{97, 103, 113, 113, 119, 119, 118, 119, 118};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2838) {
                llIIIJ1 = new byte[]{112, 119, 127, 122, 115, 114};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2843) {
                llIIIJ1 = new byte[]{59, 60};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2855) {
                llIIIJ1 = new byte[]{0, 29, 7, 75, 66, 73, 64, 83, 79, 7};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2867) {
                llIIIJ1 = new byte[]{19, 30, 19, 80, 65, 80, 9, 19};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2913) {
                llIIIJ1 = new byte[]{39, 0, 8, 13, 4, 5, 65, 21, 14, 65, 5, 4, 13, 4, 21, 4, 65, 2, 14, 19, 19, 20, 17, 21, 4, 5, 65, 18, 4, 2, 14, 15, 5, 0, 19, 24, 65, 5, 4, 25, 65, 70};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2925) {
                llIIIJ1 = new byte[]{74};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2950) {
                llIIIJ1 = new byte[]{-59, -23, -13, -22, -30, -90, -24, -23, -14, -90, -27, -12, -29, -25, -14, -29, -90, -4, -17, -10, -90, -32, -17, -22, -29, -90};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2962) {
                llIIIJ1 = new byte[]{-78, -12, -3, -32, -78, -31, -9, -15, -3, -4, -10, -13, -32, -21, -78, -10, -9, -22, -78, -70};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 2970) {
                llIIIJ1 = new byte[]{-77};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 3043) {
                llIIIJ1 = new byte[]{-91, -126, -118, -113, -122, -121, -61, -105, -116, -61, -128, -113, -116, -112, -122, -61, -111, -122, -112, -116, -106, -111, -128, -122};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 3148) {
                llIIIJ1 = new byte[]{10, 45, 37, 32, 41, 40, 108, 56, 35, 108, 32, 37, 63, 56, 108, 63, 41, 47, 35, 34, 40, 45, 62, 53, 108, 40, 41, 52, 108, 40, 37, 62, 108, 47, 35, 34, 56, 41, 34, 56, 108, 100};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 3160) {
                llIIIJ1 = new byte[]{113, 118};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 3190) {
                llIIIJ1 = new byte[]{34, 4, 15, 31, 24, 17, 86, 2, 25, 86, 18, 19, 26, 19, 2, 19, 86, 25, 26, 18, 86, 16, 31, 26, 19, 86};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 3202) {
                llIIIJ1 = new byte[]{-94, -19, -28, -94, -15, -21, -8, -25, -94};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 3234) {
                llIIIJ1 = new byte[]{-28, -61, -53, -50, -57, -58, -126, -42, -51, -126, -58, -57, -50, -57, -42, -57, -126, -51, -50, -58, -126, -60, -53, -50, -57, -126};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
            if (llIII1 == 3259) {
                llIIIJ1 = new byte[]{-1, -34, -41, -34, -49, -34, -33, -101, -44, -41, -33, -101, -35, -46, -41, -34, -101};

                for (int llIII1I = 0; llIII1I < llIIIJ1.length; llIII1I++) {
                    llIIIJ1[llIII1I] = (byte) (llIIIJ1[llIII1I] ^ llIII1);
                }
                {
                    return new String(llIIIJ1, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/

    //List<? extends File> load(Context context, String prefsKeyPrefix, boolean forceReload)
    List<? extends File> load(Context context, String prefsKeyPrefix, boolean forceReload)
    //throws IOException {
            throws IOException {
        //Log.i(TAG, "MultiDexExtractor.load(" + sourceApk.getPath() + ", " + forceReload + ", " +
        Log.i(TAG, llIII1l(1788) + sourceApk.getPath() + llIII1l(1800) + forceReload + llIII1l(1808) +
                //prefsKeyPrefix + ")");
                prefsKeyPrefix + llIII1l(1816));
        //if (!cacheLock.isValid()) {
        if (!cacheLock.isValid()) {
            //throw new IllegalStateException("MultiDexExtractor was closed");
            throw new IllegalStateException(llIII1l(1839));
            //}
        }
        //List<ExtractedDex> files;
        List<ExtractedDex> files;
        //if (!forceReload && !isModified(context, sourceApk, sourceCrc, prefsKeyPrefix)) {
        if (!forceReload && !isModified(context, sourceApk, sourceCrc, prefsKeyPrefix)) {
            //try {
            try {
                //files = loadExistingExtractions(context, prefsKeyPrefix);
                files = loadExistingExtractions(context, prefsKeyPrefix);
                //} catch (IOException ioe) {
            } catch (IOException ioe) {
                //Log.w(TAG, "Failed to reload existing extracted secondary dex files,"
                Log.w(TAG, llIII1l(1916)
                        //+ " falling back to fresh extraction", ioe);
                        + llIII1l(1920), ioe);
                //files = performExtractions();
                files = performExtractions();
                //putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(sourceApk), sourceCrc,
                putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(sourceApk), sourceCrc,
                        //files);
                        files);
                //}
            }
            //} else {
        } else {
            //if (forceReload) {
            if (forceReload) {
                //Log.i(TAG, "Forced extraction must be performed.");
                Log.i(TAG, llIII1l(1980));
                //} else {
            } else {
                //Log.i(TAG, "Detected that extraction must be performed.");
                Log.i(TAG, llIII1l(1997));
                //}
            }
            //files = performExtractions();
            files = performExtractions();
            //putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(sourceApk), sourceCrc,
            putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(sourceApk), sourceCrc,
                    //files);
                    files);
            //}
        }
        //Log.i(TAG, "load found " + files.size() + " secondary dex files");
        Log.i(TAG, llIII1l(2042) + files.size() + llIII1l(2054));
        //return files;
        return files;
        //}
    }

    //

    //@Override
    @Override
    public void close() throws IOException {
        //cacheLock.release();
        cacheLock.release();
        //lockChannel.close();
        lockChannel.close();
        //lockRaf.close();
        lockRaf.close();
        //}
    }

    //private List<ExtractedDex> loadExistingExtractions(
    private List<ExtractedDex> loadExistingExtractions(
            //Context context,
            Context context,
            //String prefsKeyPrefix)
            String prefsKeyPrefix)
    //throws IOException {
            throws IOException {
        //Log.i(TAG, "loading existing secondary dex files");
        Log.i(TAG, llIII1l(2138));
        //final String extractedFilePrefix = sourceApk.getName() + EXTRACTED_NAME_EXT;
        final String extractedFilePrefix = sourceApk.getName() + EXTRACTED_NAME_EXT;
        //SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        //int totalDexNumber = multiDexPreferences.getInt(prefsKeyPrefix + KEY_DEX_NUMBER, 1);
        int totalDexNumber = multiDexPreferences.getInt(prefsKeyPrefix + KEY_DEX_NUMBER, 1);
        //final List<ExtractedDex> files = new ArrayList<ExtractedDex>(totalDexNumber - 1);
        final List<ExtractedDex> files = new ArrayList<ExtractedDex>(totalDexNumber - 1);
        //for (int secondaryNumber = 2; secondaryNumber <= totalDexNumber; secondaryNumber++) {
        for (int secondaryNumber = 2; secondaryNumber <= totalDexNumber; secondaryNumber++) {
            //String fileName = extractedFilePrefix + secondaryNumber + EXTRACTED_SUFFIX;
            String fileName = extractedFilePrefix + secondaryNumber + EXTRACTED_SUFFIX;
            //ExtractedDex extractedFile = new ExtractedDex(dexDir, fileName);
            ExtractedDex extractedFile = new ExtractedDex(dexDir, fileName);
            //if (extractedFile.isFile()) {
            if (extractedFile.isFile()) {
                //extractedFile.crc = getZipCrc(extractedFile);
                extractedFile.crc = getZipCrc(extractedFile);
                //long expectedCrc = multiDexPreferences.getLong(
                long expectedCrc = multiDexPreferences.getLong(
                        //prefsKeyPrefix + KEY_DEX_CRC + secondaryNumber, NO_VALUE);
                        prefsKeyPrefix + KEY_DEX_CRC + secondaryNumber, NO_VALUE);
                //long expectedModTime = multiDexPreferences.getLong(
                long expectedModTime = multiDexPreferences.getLong(
                        //prefsKeyPrefix + KEY_DEX_TIME + secondaryNumber, NO_VALUE);
                        prefsKeyPrefix + KEY_DEX_TIME + secondaryNumber, NO_VALUE);
                //long lastModified = extractedFile.lastModified();
                long lastModified = extractedFile.lastModified();
                //if ((expectedModTime != lastModified)
                if ((expectedModTime != lastModified)
                        //|| (expectedCrc != extractedFile.crc)) {
                        || (expectedCrc != extractedFile.crc)) {
                    //throw new IOException("Invalid extracted dex: " + extractedFile +
                    throw new IOException(llIII1l(2405) + extractedFile +
                            //" (key \"" + prefsKeyPrefix + "\"), expected modification time: "
                            llIII1l(2413) + prefsKeyPrefix + llIII1l(2421)
                            //+ expectedModTime + ", modification time: "
                            + expectedModTime + llIII1l(2429)
                            //+ lastModified + ", expected crc: "
                            + lastModified + llIII1l(2437)
                            //+ expectedCrc + ", file crc: " + extractedFile.crc);
                            + expectedCrc + llIII1l(2445) + extractedFile.crc);
                    //}
                }
                //files.add(extractedFile);
                files.add(extractedFile);
                //} else {
            } else {
                //throw new IOException("Missing extracted secondary dex file '" +
                throw new IOException(llIII1l(2477) +
                        //extractedFile.getPath() + "'");
                        extractedFile.getPath() + llIII1l(2489));
                //}
            }
            //}
        }
        //return files;
        return files;
        //}
    }

    //

    //private List<ExtractedDex> performExtractions() throws IOException {
    private List<ExtractedDex> performExtractions() throws IOException {
        //final String extractedFilePrefix = sourceApk.getName() + EXTRACTED_NAME_EXT;
        final String extractedFilePrefix = sourceApk.getName() + EXTRACTED_NAME_EXT;
        //

        //

        //

        //clearDexDir();
        clearDexDir();
        //List<ExtractedDex> files = new ArrayList<ExtractedDex>();
        List<ExtractedDex> files = new ArrayList<ExtractedDex>();
        //final ZipFile apk = new ZipFile(sourceApk);
        final ZipFile apk = new ZipFile(sourceApk);
        //try {
        try {
            //int secondaryNumber = 1;
            int secondaryNumber = 1;
            //ZipEntry dexFile = apk.getEntry(APK_DEX_DIR + DEX_PREFIX + secondaryNumber + DEX_SUFFIX);
            ZipEntry dexFile = apk.getEntry(APK_DEX_DIR + DEX_PREFIX + secondaryNumber + DEX_SUFFIX);
            //while (dexFile != null) {
            while (dexFile != null) {
                //String fileName = extractedFilePrefix + secondaryNumber + EXTRACTED_SUFFIX;
                String fileName = extractedFilePrefix + secondaryNumber + EXTRACTED_SUFFIX;
                //ExtractedDex extractedFile = new ExtractedDex(dexDir, fileName);
                ExtractedDex extractedFile = new ExtractedDex(dexDir, fileName);
                //files.add(extractedFile);
                files.add(extractedFile);
                //Log.i(TAG, "Extraction is needed for file " + extractedFile);
                Log.i(TAG, llIII1l(2684) + extractedFile);
                //int numAttempts = 0;
                int numAttempts = 0;
                //boolean isExtractionSuccessful = false;
                boolean isExtractionSuccessful = false;
                //while (numAttempts < MAX_EXTRACT_ATTEMPTS && !isExtractionSuccessful) {
                while (numAttempts < MAX_EXTRACT_ATTEMPTS && !isExtractionSuccessful) {
                    //numAttempts++;
                    numAttempts++;
                    //

                    //

                    //extract(apk, dexFile, extractedFile, extractedFilePrefix);
                    extract(apk, dexFile, extractedFile, extractedFilePrefix);
                    //

                    //try {
                    try {
                        //extractedFile.crc = getZipCrc(extractedFile);
                        extractedFile.crc = getZipCrc(extractedFile);
                        //isExtractionSuccessful = true;
                        isExtractionSuccessful = true;
                        //} catch (IOException e) {
                    } catch (IOException e) {
                        //isExtractionSuccessful = false;
                        isExtractionSuccessful = false;
                        //Log.w(TAG, "Failed to read crc from " + extractedFile.getAbsolutePath(), e);
                        Log.w(TAG, llIII1l(2800) + extractedFile.getAbsolutePath(), e);
                        //}
                    }
                    //

                    //Log.i(TAG, "Extraction " + (isExtractionSuccessful ? "succeeded" : "failed")
                    Log.i(TAG, llIII1l(2825) + (isExtractionSuccessful ? llIII1l(2834) : llIII1l(2838))
                            //+ " '" + extractedFile.getAbsolutePath() + "': length "
                            + llIII1l(2843) + extractedFile.getAbsolutePath() + llIII1l(2855)
                            //+ extractedFile.length() + " - crc: " + extractedFile.crc);
                            + extractedFile.length() + llIII1l(2867) + extractedFile.crc);
                    //if (!isExtractionSuccessful) {
                    if (!isExtractionSuccessful) {
                        //

                        //extractedFile.delete();
                        extractedFile.delete();
                        //if (extractedFile.exists()) {
                        if (extractedFile.exists()) {
                            //Log.w(TAG, "Failed to delete corrupted secondary dex '" +
                            Log.w(TAG, llIII1l(2913) +
                                    //extractedFile.getPath() + "'");
                                    extractedFile.getPath() + llIII1l(2925));
                            //}
                        }
                        //}
                    }
                    //}
                }
                //if (!isExtractionSuccessful) {
                if (!isExtractionSuccessful) {
                    //throw new IOException("Could not create zip file " +
                    throw new IOException(llIII1l(2950) +
                            //extractedFile.getAbsolutePath() + " for secondary dex (" +
                            extractedFile.getAbsolutePath() + llIII1l(2962) +
                            //secondaryNumber + ")");
                            secondaryNumber + llIII1l(2970));
                    //}
                }
                //secondaryNumber++;
                secondaryNumber++;
                //dexFile = apk.getEntry(APK_DEX_DIR + DEX_PREFIX + secondaryNumber + DEX_SUFFIX);
                dexFile = apk.getEntry(APK_DEX_DIR + DEX_PREFIX + secondaryNumber + DEX_SUFFIX);
                //}
            }
            //} finally {
        } finally {
            //try {
            try {
                //apk.close();
                apk.close();
                //} catch (IOException e) {
            } catch (IOException e) {
                //Log.w(TAG, "Failed to close resource", e);
                Log.w(TAG, llIII1l(3043), e);
                //}
            }
            //}
        }
        //return files;
        return files;
        //}
    }

    //

    //private void clearDexDir() {
    private void clearDexDir() {
        //File[] files = dexDir.listFiles(new FileFilter() {
        File[] files = dexDir.listFiles(new FileFilter() {
            //@Override
            @Override
            //public boolean accept(File pathname) {
            public boolean accept(File pathname) {
                //return !pathname.getName().equals(LOCK_FILENAME);
                return !pathname.getName().equals(LOCK_FILENAME);
                //}
            }
            //});
        });
        //if (files == null) {
        if (files == null) {
            //Log.w(TAG, "Failed to list secondary dex dir content (" + dexDir.getPath() + ").");
            Log.w(TAG, llIII1l(3148) + dexDir.getPath() + llIII1l(3160));
            //return;
            return;
            //}
        }
        //for (File oldFile : files) {
        for (File oldFile : files) {
            //Log.i(TAG, "Trying to delete old file " + oldFile.getPath() + " of size " +
            Log.i(TAG, llIII1l(3190) + oldFile.getPath() + llIII1l(3202) +
                    //oldFile.length());
                    oldFile.length());
            //if (!oldFile.delete()) {
            if (!oldFile.delete()) {
                //Log.w(TAG, "Failed to delete old file " + oldFile.getPath());
                Log.w(TAG, llIII1l(3234) + oldFile.getPath());
                //} else {
            } else {
                //Log.i(TAG, "Deleted old file " + oldFile.getPath());
                Log.i(TAG, llIII1l(3259) + oldFile.getPath());
                //}
            }
            //}
        }
        //}
    }
//}

    private static class ExtractedDex extends File {
        //public long crc = NO_VALUE;
        public long crc = NO_VALUE;

        //public ExtractedDex(File dexDir, String fileName) {
        public ExtractedDex(File dexDir, String fileName) {
            //super(dexDir, fileName);
            super(dexDir, fileName);
            //}
        }
        //}
    }
}//

//
  	