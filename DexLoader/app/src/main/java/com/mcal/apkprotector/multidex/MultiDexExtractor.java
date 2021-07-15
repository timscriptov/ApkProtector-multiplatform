/* Orginal file: MultiDexExtractor.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.multidex*/
package com.mcal.apkprotector.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.mcal.apkprotector.data.Const;
import com.mcal.apkprotector.utils.DexCrypto;

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

//final class MultiDexExtractor implements Closeable {
final class MultiDexExtractor implements Closeable {
    //static final String DEX_SUFFIX = Const.DEX_SUFFIX;
    static final String DEX_SUFFIX = Const.DEX_SUFFIX;
    //static final String EXTRACTED_SUFFIX = ".zip";
    static final String EXTRACTED_SUFFIX = llIIlII(265);
    //private static final String TAG = MultiDex.TAG;
    private static final String TAG = MultiDex.TAG;
    //private static final String EXTRACTED_NAME_EXT = ".classes";
    private static final String EXTRACTED_NAME_EXT = llIIlII(297);
    //private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    //private static final String PREFS_FILE = "multidex.version";
    private static final String PREFS_FILE = llIIlII(327);
    //private static final String KEY_TIME_STAMP = "timestamp";
    private static final String KEY_TIME_STAMP = llIIlII(342);
    //private static final String KEY_CRC = "crc";
    private static final String KEY_CRC = llIIlII(357);
    //private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_NUMBER = llIIlII(372);
    //private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_CRC = llIIlII(387);
    //private static final String KEY_DEX_TIME = "dex.time.";
    private static final String KEY_DEX_TIME = llIIlII(402);
    //

    //

    //private static final long NO_VALUE = -1L;
    private static final long NO_VALUE = -1L;
    //private static final String LOCK_FILENAME = "MultiDex.lock";
    private static final String LOCK_FILENAME = llIIlII(435);
    //

    //private static final String PROTECT_KEY = Const.PROTECT_KEY;
    private static final String PROTECT_KEY = Const.PROTECT_KEY;
    //private static final String DEX_DIR = Const.DEX_DIR;
    private static final String DEX_DIR = Const.DEX_DIR;
    //private static final String APK_DEX_DIR = "assets" + File.separator + DEX_DIR + File.separator;
    private static final String APK_DEX_DIR = llIIlII(485) + File.separator + DEX_DIR + File.separator;
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

    //MultiDexExtractor(File sourceApk, File dexDir) throws IOException {
    MultiDexExtractor(File sourceApk, File dexDir) throws IOException {
        //Log.i(TAG, "MultiDexExtractor(" + sourceApk.getPath() + ", " + dexDir.getPath() + ")");
        Log.i(TAG, llIIlII(600) + sourceApk.getPath() + llIIlII(612) + dexDir.getPath() + llIIlII(624));
        //this.sourceApk = sourceApk;
        this.sourceApk = sourceApk;
        //this.dexDir = dexDir;
        this.dexDir = dexDir;
        //sourceCrc = getZipCrc(sourceApk);
        sourceCrc = getZipCrc(sourceApk);
        //File lockFile = new File(dexDir, LOCK_FILENAME);
        File lockFile = new File(dexDir, LOCK_FILENAME);
        //lockRaf = new RandomAccessFile(lockFile, "rw");
        lockRaf = new RandomAccessFile(lockFile, llIIlII(684));
        //try {
        try {
            //lockChannel = lockRaf.getChannel();
            lockChannel = lockRaf.getChannel();
            //try {
            try {
                //Log.i(TAG, "Blocking on lock " + lockFile.getPath());
                Log.i(TAG, llIIlII(714) + lockFile.getPath());
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
            Log.i(TAG, lockFile.getPath() + llIIlII(785));
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

    //private static boolean isModified(Context context, File archive, long currentCrc,
    private static boolean isModified(Context context, File archive, long currentCrc,
                                      //String prefsKeyPrefix) {
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
        //long timeStamp = archive.lastModified();
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
                                         //long crc, List<ExtractedDex> extractedDexes) {
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

    //private static SharedPreferences getMultiDexPreferences(Context context) {
    private static SharedPreferences getMultiDexPreferences(Context context) {
        //return context.getSharedPreferences(PREFS_FILE,
        return context.getSharedPreferences(PREFS_FILE,
                //Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                        //? Context.MODE_PRIVATE
                        ? Context.MODE_PRIVATE
                        //: Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
                        : Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        //}
    }

    //private static void extract(ZipFile apk, ZipEntry dexFile, File extractTo,
    private static void extract(ZipFile apk, ZipEntry dexFile, File extractTo,
                                //String extractedFilePrefix) throws IOException, FileNotFoundException {
                                String extractedFilePrefix) throws IOException {

        //InputStream in = apk.getInputStream(dexFile);
        InputStream in = apk.getInputStream(dexFile);

        //ZipOutputStream out = null;
        ZipOutputStream out = null;
        //

        //File tmp = File.createTempFile("tmp-" + extractedFilePrefix, EXTRACTED_SUFFIX,
        File tmp = File.createTempFile(llIIlII(1372) + extractedFilePrefix, EXTRACTED_SUFFIX,
                //extractTo.getParentFile());
                extractTo.getParentFile());
        //Log.i(TAG, "Extracting " + tmp.getPath());
        Log.i(TAG, llIIlII(1397) + tmp.getPath());
        //try {
        try {
            //out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmp)));
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmp)));
            //try {
            try {
                //ZipEntry classesDex = new ZipEntry("classes.dex");
                ZipEntry classesDex = new ZipEntry(llIIlII(1449));
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
                throw new IOException(llIIlII(1549) + tmp.getAbsolutePath() +
                        //"\" (tmp of \"" + extractTo.getAbsolutePath() + "\")");
                        llIIlII(1561) + extractTo.getAbsolutePath() + llIIlII(1573));
                //}
            }
            //Log.i(TAG, "Renaming to " + extractTo.getPath());
            Log.i(TAG, llIIlII(1586) + extractTo.getPath());
            //if (!tmp.renameTo(extractTo)) {
            if (!tmp.renameTo(extractTo)) {
                //throw new IOException("Failed to rename \"" + tmp.getAbsolutePath() +
                throw new IOException(llIIlII(1618) + tmp.getAbsolutePath() +
                        //"\" to \"" + extractTo.getAbsolutePath() + "\"");
                        llIIlII(1630) + extractTo.getAbsolutePath() + llIIlII(1642));
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

    //private static void closeQuietly(Closeable closeable) {
    private static void closeQuietly(Closeable closeable) {
        //try {
        try {
            //closeable.close();
            closeable.close();
            //} catch (IOException e) {
        } catch (IOException e) {
            //Log.w(TAG, "Failed to close resource", e);
            Log.w(TAG, llIIlII(1718), e);
            //}
        }
        //}
    }

    //

    static String llIIlII(int llIIlIl) {
        byte[] llIIlI = null;
        try {
            if (llIIlIl == -1) {
                if (llIIlIl == -2) {
                } else if (llIIlIl == -3) {
                } else if (llIIlIl == -4) {
                }
            }
            if (llIIlIl == 265) {
                llIIlI = new byte[]{39, 115, 96, 121};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 297) {
                llIIlI = new byte[]{7, 74, 69, 72, 90, 90, 76, 90};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 327) {
                llIIlI = new byte[]{42, 50, 43, 51, 46, 35, 34, 63, 105, 49, 34, 53, 52, 46, 40, 41};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 342) {
                llIIlI = new byte[]{34, 63, 59, 51, 37, 34, 55, 59, 38};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 357) {
                llIIlI = new byte[]{6, 23, 6};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 372) {
                llIIlI = new byte[]{16, 17, 12, 90, 26, 1, 25, 22, 17, 6};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 387) {
                llIIlI = new byte[]{-25, -26, -5, -83, -32, -15, -32, -83};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 402) {
                llIIlI = new byte[]{-10, -9, -22, -68, -26, -5, -1, -9, -68};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 435) {
                llIIlI = new byte[]{-2, -58, -33, -57, -38, -9, -42, -53, -99, -33, -36, -48, -40};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 485) {
                llIIlI = new byte[]{-124, -106, -106, -128, -111, -106};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 600) {
                llIIlI = new byte[]{21, 45, 52, 44, 49, 28, 61, 32, 29, 32, 44, 42, 57, 59, 44, 55, 42, 112};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 612) {
                llIIlI = new byte[]{72, 68};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 624) {
                llIIlI = new byte[]{89};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 684) {
                llIIlI = new byte[]{-34, -37};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 714) {
                llIIlI = new byte[]{-120, -90, -91, -87, -95, -93, -92, -83, -22, -91, -92, -22, -90, -91, -87, -95, -22};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 785) {
                llIIlI = new byte[]{49, 125, 126, 114, 122, 116, 117};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1372) {
                llIIlI = new byte[]{40, 49, 44, 113};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1397) {
                llIIlI = new byte[]{48, 13, 1, 7, 20, 22, 1, 28, 27, 18, 85};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1449) {
                llIIlI = new byte[]{-54, -59, -56, -38, -38, -52, -38, -121, -51, -52, -47};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1549) {
                llIIlI = new byte[]{75, 108, 100, 97, 104, 105, 45, 121, 98, 45, 96, 108, 127, 102, 45, 127, 104, 108, 105, 98, 99, 97, 116, 45, 47};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1561) {
                llIIlI = new byte[]{59, 57, 49, 109, 116, 105, 57, 118, 127, 57, 59};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1573) {
                llIIlI = new byte[]{7, 12};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1586) {
                llIIlI = new byte[]{96, 87, 92, 83, 95, 91, 92, 85, 18, 70, 93, 18};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1618) {
                llIIlI = new byte[]{20, 51, 59, 62, 55, 54, 114, 38, 61, 114, 32, 55, 60, 51, 63, 55, 114, 112};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1630) {
                llIIlI = new byte[]{124, 126, 42, 49, 126, 124};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1642) {
                llIIlI = new byte[]{72};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1718) {
                llIIlI = new byte[]{-16, -41, -33, -38, -45, -46, -106, -62, -39, -106, -43, -38, -39, -59, -45, -106, -60, -45, -59, -39, -61, -60, -43, -45};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1769) {
                llIIlI = new byte[]{-92, -100, -123, -99, -128, -83, -116, -111, -84, -111, -99, -101, -120, -118, -99, -122, -101, -57, -123, -122, -120, -115, -63};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1781) {
                llIIlI = new byte[]{-39, -43};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1789) {
                llIIlI = new byte[]{-47, -35};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1797) {
                llIIlI = new byte[]{44};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1820) {
                llIIlI = new byte[]{81, 105, 112, 104, 117, 88, 121, 100, 89, 100, 104, 110, 125, 127, 104, 115, 110, 60, 107, 125, 111, 60, 127, 112, 115, 111, 121, 120};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1897) {
                llIIlI = new byte[]{47, 8, 0, 5, 12, 13, 73, 29, 6, 73, 27, 12, 5, 6, 8, 13, 73, 12, 17, 0, 26, 29, 0, 7, 14, 73, 12, 17, 29, 27, 8, 10, 29, 12, 13, 73, 26, 12, 10, 6, 7, 13, 8, 27, 16, 73, 13, 12, 17, 73, 15, 0, 5, 12, 26, 69};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1901) {
                llIIlI = new byte[]{77, 11, 12, 1, 1, 4, 3, 10, 77, 15, 12, 14, 6, 77, 25, 2, 77, 11, 31, 8, 30, 5, 77, 8, 21, 25, 31, 12, 14, 25, 4, 2, 3};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1961) {
                llIIlI = new byte[]{-17, -58, -37, -54, -52, -51, -119, -52, -47, -35, -37, -56, -54, -35, -64, -58, -57, -119, -60, -36, -38, -35, -119, -53, -52, -119, -39, -52, -37, -49, -58, -37, -60, -52, -51, -121};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 1978) {
                llIIlI = new byte[]{-2, -33, -50, -33, -39, -50, -33, -34, -102, -50, -46, -37, -50, -102, -33, -62, -50, -56, -37, -39, -50, -45, -43, -44, -102, -41, -49, -55, -50, -102, -40, -33, -102, -54, -33, -56, -36, -43, -56, -41, -33, -34, -108};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2023) {
                llIIlI = new byte[]{-117, -120, -122, -125, -57, -127, -120, -110, -119, -125, -57};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2035) {
                llIIlI = new byte[]{-45, -128, -106, -112, -100, -99, -105, -110, -127, -118, -45, -105, -106, -117, -45, -107, -102, -97, -106, -128};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2119) {
                llIIlI = new byte[]{43, 40, 38, 35, 46, 41, 32, 103, 34, 63, 46, 52, 51, 46, 41, 32, 103, 52, 34, 36, 40, 41, 35, 38, 53, 62, 103, 35, 34, 63, 103, 33, 46, 43, 34, 52};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2386) {
                llIIlI = new byte[]{27, 60, 36, 51, 62, 59, 54, 114, 55, 42, 38, 32, 51, 49, 38, 55, 54, 114, 54, 55, 42, 104, 114};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2394) {
                llIIlI = new byte[]{122, 114, 49, 63, 35, 122, 120};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2402) {
                llIIlI = new byte[]{64, 75, 78, 66, 7, 26, 18, 7, 1, 22, 7, 6, 66, 15, 13, 6, 11, 4, 11, 1, 3, 22, 11, 13, 12, 66, 22, 11, 15, 7, 88, 66};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2410) {
                llIIlI = new byte[]{70, 74, 7, 5, 14, 3, 12, 3, 9, 11, 30, 3, 5, 4, 74, 30, 3, 7, 15, 80, 74};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2418) {
                llIIlI = new byte[]{94, 82, 23, 10, 2, 23, 17, 6, 23, 22, 82, 17, 0, 17, 72, 82};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2426) {
                llIIlI = new byte[]{86, 90, 28, 19, 22, 31, 90, 25, 8, 25, 64, 90};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2458) {
                llIIlI = new byte[]{-41, -13, -23, -23, -13, -12, -3, -70, -1, -30, -18, -24, -5, -7, -18, -1, -2, -70, -23, -1, -7, -11, -12, -2, -5, -24, -29, -70, -2, -1, -30, -70, -4, -13, -10, -1, -70, -67};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2470) {
                llIIlI = new byte[]{-127};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2665) {
                llIIlI = new byte[]{44, 17, 29, 27, 8, 10, 29, 0, 6, 7, 73, 0, 26, 73, 7, 12, 12, 13, 12, 13, 73, 15, 6, 27, 73, 15, 0, 5, 12, 73};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2781) {
                llIIlI = new byte[]{-101, -68, -76, -79, -72, -71, -3, -87, -78, -3, -81, -72, -68, -71, -3, -66, -81, -66, -3, -69, -81, -78, -80, -3};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2806) {
                llIIlI = new byte[]{-77, -114, -126, -124, -105, -107, -126, -97, -103, -104, -42};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2815) {
                llIIlI = new byte[]{-116, -118, -100, -100, -102, -102, -101, -102, -101};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2819) {
                llIIlI = new byte[]{101, 98, 106, 111, 102, 103};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2824) {
                llIIlI = new byte[]{40, 47};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2836) {
                llIIlI = new byte[]{51, 46, 52, 120, 113, 122, 115, 96, 124, 52};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2848) {
                llIIlI = new byte[]{0, 13, 0, 67, 82, 67, 26, 0};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2894) {
                llIIlI = new byte[]{8, 47, 39, 34, 43, 42, 110, 58, 33, 110, 42, 43, 34, 43, 58, 43, 110, 45, 33, 60, 60, 59, 62, 58, 43, 42, 110, 61, 43, 45, 33, 32, 42, 47, 60, 55, 110, 42, 43, 54, 110, 105};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2906) {
                llIIlI = new byte[]{125};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2931) {
                llIIlI = new byte[]{48, 28, 6, 31, 23, 83, 29, 28, 7, 83, 16, 1, 22, 18, 7, 22, 83, 9, 26, 3, 83, 21, 26, 31, 22, 83};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2943) {
                llIIlI = new byte[]{95, 25, 16, 13, 95, 12, 26, 28, 16, 17, 27, 30, 13, 6, 95, 27, 26, 7, 95, 87};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 2951) {
                llIIlI = new byte[]{-82};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 3024) {
                llIIlI = new byte[]{-106, -79, -71, -68, -75, -76, -16, -92, -65, -16, -77, -68, -65, -93, -75, -16, -94, -75, -93, -65, -91, -94, -77, -75};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 3129) {
                llIIlI = new byte[]{127, 88, 80, 85, 92, 93, 25, 77, 86, 25, 85, 80, 74, 77, 25, 74, 92, 90, 86, 87, 93, 88, 75, 64, 25, 93, 92, 65, 25, 93, 80, 75, 25, 90, 86, 87, 77, 92, 87, 77, 25, 17};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 3141) {
                llIIlI = new byte[]{108, 107};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 3171) {
                llIIlI = new byte[]{55, 17, 26, 10, 13, 4, 67, 23, 12, 67, 7, 6, 15, 6, 23, 6, 67, 12, 15, 7, 67, 5, 10, 15, 6, 67};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 3183) {
                llIIlI = new byte[]{79, 0, 9, 79, 28, 6, 21, 10, 79};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 3215) {
                llIIlI = new byte[]{-55, -18, -26, -29, -22, -21, -81, -5, -32, -81, -21, -22, -29, -22, -5, -22, -81, -32, -29, -21, -81, -23, -26, -29, -22, -81};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
                }
            }
            if (llIIlIl == 3240) {
                llIIlI = new byte[]{-20, -51, -60, -51, -36, -51, -52, -120, -57, -60, -52, -120, -50, -63, -60, -51, -120};

                for (int llIIlIJ = 0; llIIlIJ < llIIlI.length; llIIlIJ++) {
                    llIIlI[llIIlIJ] = (byte) (llIIlI[llIIlIJ] ^ llIIlIl);
                }
                {
                    return new String(llIIlI, StandardCharsets.UTF_8);
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
        Log.i(TAG, llIIlII(1769) + sourceApk.getPath() + llIIlII(1781) + forceReload + llIIlII(1789) +
                //prefsKeyPrefix + ")");
                prefsKeyPrefix + llIIlII(1797));
        //if (!cacheLock.isValid()) {
        if (!cacheLock.isValid()) {
            //throw new IllegalStateException("MultiDexExtractor was closed");
            throw new IllegalStateException(llIIlII(1820));
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
                Log.w(TAG, llIIlII(1897)
                        //+ " falling back to fresh extraction", ioe);
                        + llIIlII(1901), ioe);
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
                Log.i(TAG, llIIlII(1961));
                //} else {
            } else {
                //Log.i(TAG, "Detected that extraction must be performed.");
                Log.i(TAG, llIIlII(1978));
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
        Log.i(TAG, llIIlII(2023) + files.size() + llIIlII(2035));
        //return files;
        return files;
        //}
    }

    //

    //@Override
    @Override
    //public void close() throws IOException {
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
        Log.i(TAG, llIIlII(2119));
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
                    throw new IOException(llIIlII(2386) + extractedFile +
                            //" (key \"" + prefsKeyPrefix + "\"), expected modification time: "
                            llIIlII(2394) + prefsKeyPrefix + llIIlII(2402)
                            //+ expectedModTime + ", modification time: "
                            + expectedModTime + llIIlII(2410)
                            //+ lastModified + ", expected crc: "
                            + lastModified + llIIlII(2418)
                            //+ expectedCrc + ", file crc: " + extractedFile.crc);
                            + expectedCrc + llIIlII(2426) + extractedFile.crc);
                    //}
                }
                //files.add(extractedFile);
                files.add(extractedFile);
                //} else {
            } else {
                //throw new IOException("Missing extracted secondary dex file '" +
                throw new IOException(llIIlII(2458) +
                        //extractedFile.getPath() + "'");
                        extractedFile.getPath() + llIIlII(2470));
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
                Log.i(TAG, llIIlII(2665) + extractedFile);
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
                        Log.w(TAG, llIIlII(2781) + extractedFile.getAbsolutePath(), e);
                        //}
                    }
                    //

                    //Log.i(TAG, "Extraction " + (isExtractionSuccessful ? "succeeded" : "failed")
                    Log.i(TAG, llIIlII(2806) + (isExtractionSuccessful ? llIIlII(2815) : llIIlII(2819))
                            //+ " '" + extractedFile.getAbsolutePath() + "': length "
                            + llIIlII(2824) + extractedFile.getAbsolutePath() + llIIlII(2836)
                            //+ extractedFile.length() + " - crc: " + extractedFile.crc);
                            + extractedFile.length() + llIIlII(2848) + extractedFile.crc);
                    //if (!isExtractionSuccessful) {
                    if (!isExtractionSuccessful) {
                        //

                        //extractedFile.delete();
                        extractedFile.delete();
                        //if (extractedFile.exists()) {
                        if (extractedFile.exists()) {
                            //Log.w(TAG, "Failed to delete corrupted secondary dex '" +
                            Log.w(TAG, llIIlII(2894) +
                                    //extractedFile.getPath() + "'");
                                    extractedFile.getPath() + llIIlII(2906));
                            //}
                        }
                        //}
                    }
                    //}
                }
                //if (!isExtractionSuccessful) {
                if (!isExtractionSuccessful) {
                    //throw new IOException("Could not create zip file " +
                    throw new IOException(llIIlII(2931) +
                            //extractedFile.getAbsolutePath() + " for secondary dex (" +
                            extractedFile.getAbsolutePath() + llIIlII(2943) +
                            //secondaryNumber + ")");
                            secondaryNumber + llIIlII(2951));
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
                Log.w(TAG, llIIlII(3024), e);
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
            Log.w(TAG, llIIlII(3129) + dexDir.getPath() + llIIlII(3141));
            //return;
            return;
            //}
        }
        //for (File oldFile : files) {
        for (File oldFile : files) {
            //Log.i(TAG, "Trying to delete old file " + oldFile.getPath() + " of size " +
            Log.i(TAG, llIIlII(3171) + oldFile.getPath() + llIIlII(3183) +
                    //oldFile.length());
                    oldFile.length());
            //if (!oldFile.delete()) {
            if (!oldFile.delete()) {
                //Log.w(TAG, "Failed to delete old file " + oldFile.getPath());
                Log.w(TAG, llIIlII(3215) + oldFile.getPath());
                //} else {
            } else {
                //Log.i(TAG, "Deleted old file " + oldFile.getPath());
                Log.i(TAG, llIIlII(3240) + oldFile.getPath());
                //}
            }
            //}
        }
        //}
    }
//}

    //private static class ExtractedDex extends File {
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
  	