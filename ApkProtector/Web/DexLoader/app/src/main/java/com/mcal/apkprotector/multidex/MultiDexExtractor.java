package com.mcal.apkprotector.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.mcal.apkprotector.ProtectApplication;
import com.mcal.apkprotector.data.Const;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.DexCrypto;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Exposes application secondary dex files as files in the application data
 * directory.
 * {@link MultiDexExtractor} is taking the file lock in the dex dir on creation and release it
 * during close.
 */
final class MultiDexExtractor implements Closeable {
    static final String DEX_SUFFIX = Const.DEX_SUFFIX;
    static final String EXTRACTED_SUFFIX = Const.ZIP;
    private static final String TAG = MultiDex.TAG;
    private static final String EXTRACTED_NAME_EXT = Const.CLASSES;
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final String PREFS_FILE = "multidex.version";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String KEY_CRC = "crc";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_TIME = "dex.time.";
    /**
     * Size of reading buffers.
     */
    /* Keep value away from 0 because it is a too probable time stamp value */
    private static final long NO_VALUE = -1L;
    private static final String LOCK_FILENAME = Const.MULTIDEX_LOCK;
    /**
     * We look for additional dex files named {@code classes2.dex},
     * {@code classes3.dex}, etc.
     */
    private static final String PROTECT_KEY = Const.PROTECT_KEY;
    private static final String DEX_DIR = Const.DEX_DIR;
    private static final String APK_DEX_DIR = "assets" + File.separator + DEX_DIR + File.separator;
    private static final String DEX_PREFIX = Const.DEX_PREFIX;
    private final File sourceApk;
    private final long sourceCrc;
    private final File dexDir;
    private final RandomAccessFile lockRaf;
    private final FileChannel lockChannel;
    private final FileLock cacheLock;

    MultiDexExtractor(Context context, File sourceApk, File dexDir) throws IOException {
        Log.i(TAG, "MultiDexExtractor(" + sourceApk.getPath() + ", " + dexDir.getPath() + ")");
        this.sourceApk = new File(context.getFilesDir(), "assets.zip");//sourceApk;
        this.dexDir = dexDir;
        sourceCrc = getZipCrc(sourceApk);
        File lockFile = new File(dexDir, LOCK_FILENAME);
        lockRaf = new RandomAccessFile(lockFile, "rw");
        try {
            lockChannel = lockRaf.getChannel();
            try {
                Log.i(TAG, "Blocking on lock " + lockFile.getPath());
                cacheLock = lockChannel.lock();
            } catch (IOException | RuntimeException | Error e) {
                closeQuietly(lockChannel);
                throw e;
            }
            Log.i(TAG, lockFile.getPath() + " locked");
        } catch (IOException | RuntimeException | Error e) {
            closeQuietly(lockRaf);
            throw e;
        }
    }

    /**
     * Compare current archive and crc with values stored in {@link SharedPreferences}. Should be
     * called only while owning the lock on {@link #LOCK_FILENAME}.
     */
    private static boolean isModified(Context context, File archive, long currentCrc,
                                      String prefsKeyPrefix) {
        SharedPreferences prefs = getMultiDexPreferences(context);
        return (prefs.getLong(prefsKeyPrefix + KEY_TIME_STAMP, NO_VALUE) != getTimeStamp(archive))
                || (prefs.getLong(prefsKeyPrefix + KEY_CRC, NO_VALUE) != currentCrc);
    }

    private static long getTimeStamp(File archive) {
        long timeStamp = archive.lastModified();
        if (timeStamp == NO_VALUE) {
            // never return NO_VALUE
            timeStamp--;
        }
        return timeStamp;
    }

    private static long getZipCrc(File archive) throws IOException {
        long computedValue = ZipUtil.getZipCrc(archive);
        if (computedValue == NO_VALUE) {
            // never return NO_VALUE
            computedValue--;
        }
        return computedValue;
    }

    /**
     * Save {@link SharedPreferences}. Should be called only while owning the lock on
     * {@link #LOCK_FILENAME}.
     */
    private static void putStoredApkInfo(Context context, String keyPrefix, long timeStamp,
                                         long crc, List<ExtractedDex> extractedDexes) {
        SharedPreferences prefs = getMultiDexPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putLong(keyPrefix + KEY_TIME_STAMP, timeStamp);
        edit.putLong(keyPrefix + KEY_CRC, crc);
        edit.putInt(keyPrefix + KEY_DEX_NUMBER, extractedDexes.size() + 1);
        int extractedDexId = 2;
        for (ExtractedDex dex : extractedDexes) {
            edit.putLong(keyPrefix + KEY_DEX_CRC + extractedDexId, dex.crc);
            edit.putLong(keyPrefix + KEY_DEX_TIME + extractedDexId, dex.lastModified());
            extractedDexId++;
        }
        /* Use commit() and not apply() as advised by the doc because we need synchronous writing of
         * the editor content and apply is doing an "asynchronous commit to disk".
         */
        edit.apply();
    }

    /**
     * Get the MuliDex {@link SharedPreferences} for the current application. Should be called only
     * while owning the lock on {@link #LOCK_FILENAME}.
     */
    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE,
                Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                        ? Context.MODE_PRIVATE
                        : Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
    }

    private static void extract(Context context, ZipFile apk, ZipEntry dexFile, File extractTo,
                                String extractedFilePrefix) throws IOException, FileNotFoundException {

        ZipEntry dex = new ZipEntry(context.getFilesDir() + "/assets/dexes/classes-v1.bin");

        InputStream in = apk.getInputStream(dexFile);

        ZipOutputStream out = null;
        // Temp files must not start with extractedFilePrefix to get cleaned up in prepareDexDir()
        File tmp = File.createTempFile("tmp-" + extractedFilePrefix, EXTRACTED_SUFFIX,
                extractTo.getParentFile());
        Log.i(TAG, "Extracting " + tmp.getPath());
        try {
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmp)));
            try {
                ZipEntry classesDex = new ZipEntry("classes.dex");
                // keep zip entry time since it is the criteria used by Dalvik
                classesDex.setTime(dexFile.getTime());
                out.putNextEntry(classesDex);
                DexCrypto.decrypt(PROTECT_KEY, in, out);
                out.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
            if (!tmp.setReadOnly()) {
                throw new IOException("Failed to mark readonly \"" + tmp.getAbsolutePath() +
                        "\" (tmp of \"" + extractTo.getAbsolutePath() + "\")");
            }
            Log.i(TAG, "Renaming to " + extractTo.getPath());
            if (!tmp.renameTo(extractTo)) {
                throw new IOException("Failed to rename \"" + tmp.getAbsolutePath() +
                        "\" to \"" + extractTo.getAbsolutePath() + "\"");
            }

        } finally {
            closeQuietly(in);
            tmp.delete(); // return status ignored
        }
    }

    /**
     * Closes the given {@code Closeable}. Suppresses any IO exceptions.
     */
    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "Failed to close resource", e);
        }
    }

    /**
     * Extracts application secondary dexes into files in the application data
     * directory.
     *
     * @return a list of files that were created. The list may be empty if there
     * are no secondary dex files. Never return null.
     * @throws IOException if encounters a problem while reading or writing
     *                     secondary dex files
     */
    List<? extends File> load(Context context, String prefsKeyPrefix, boolean forceReload)
            throws IOException {
        Log.i(TAG, "MultiDexExtractor.load(" + sourceApk.getPath() + ", " + forceReload + ", " +
                prefsKeyPrefix + ")");
        if (!cacheLock.isValid()) {
            throw new IllegalStateException("MultiDexExtractor was closed");
        }
        List<ExtractedDex> files;
        if (!forceReload && !isModified(context, sourceApk, sourceCrc, prefsKeyPrefix)) {
            try {
                files = loadExistingExtractions(context, prefsKeyPrefix);
            } catch (IOException ioe) {
                Log.w(TAG, "Failed to reload existing extracted secondary dex files,"
                        + " falling back to fresh extraction", ioe);
                files = performExtractions(context);
                putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(sourceApk), sourceCrc,
                        files);
            }
        } else {
            if (forceReload) {
                Log.i(TAG, "Forced extraction must be performed.");
            } else {
                Log.i(TAG, "Detected that extraction must be performed.");
            }
            files = performExtractions(context);
            putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(sourceApk), sourceCrc,
                    files);
        }
        Log.i(TAG, "load found " + files.size() + " secondary dex files");
        return files;
    }

    @Override
    public void close() throws IOException {
        cacheLock.release();
        lockChannel.close();
        lockRaf.close();
    }

    /**
     * Load previously extracted secondary dex files. Should be called only while owning the lock on
     * {@link #LOCK_FILENAME}.
     */
    private List<ExtractedDex> loadExistingExtractions(
            Context context,
            String prefsKeyPrefix)
            throws IOException {
        Log.i(TAG, "loading existing secondary dex files");
        final String extractedFilePrefix = sourceApk.getName() + EXTRACTED_NAME_EXT;
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        int totalDexNumber = multiDexPreferences.getInt(prefsKeyPrefix + KEY_DEX_NUMBER, 1);
        final List<ExtractedDex> files = new ArrayList<ExtractedDex>(totalDexNumber - 1);
        for (int secondaryNumber = 2; secondaryNumber <= totalDexNumber; secondaryNumber++) {
            String fileName = extractedFilePrefix + secondaryNumber + EXTRACTED_SUFFIX;
            ExtractedDex extractedFile = new ExtractedDex(dexDir, fileName);
            if (extractedFile.isFile()) {
                extractedFile.crc = getZipCrc(extractedFile);
                long expectedCrc = multiDexPreferences.getLong(
                        prefsKeyPrefix + KEY_DEX_CRC + secondaryNumber, NO_VALUE);
                long expectedModTime = multiDexPreferences.getLong(
                        prefsKeyPrefix + KEY_DEX_TIME + secondaryNumber, NO_VALUE);
                long lastModified = extractedFile.lastModified();
                if ((expectedModTime != lastModified)
                        || (expectedCrc != extractedFile.crc)) {
                    throw new IOException("Invalid extracted dex: " + extractedFile +
                            " (key \"" + prefsKeyPrefix + "\"), expected modification time: "
                            + expectedModTime + ", modification time: "
                            + lastModified + ", expected crc: "
                            + expectedCrc + ", file crc: " + extractedFile.crc);
                }
                files.add(extractedFile);
            } else {
                throw new IOException("Missing extracted secondary dex file '" +
                        extractedFile.getPath() + "'");
            }
        }
        return files;
    }

    private List<ExtractedDex> performExtractions(Context context) throws IOException {
        final String extractedFilePrefix = sourceApk.getName() + EXTRACTED_NAME_EXT;
        // It is safe to fully clear the dex dir because we own the file lock so no other process is
        // extracting or running optimizing dexopt. It may cause crash of already running
        // applications if for whatever reason we end up extracting again over a valid extraction.
        clearDexDir();
        List<ExtractedDex> files = new ArrayList<ExtractedDex>();
        final ZipFile apk = new ZipFile(sourceApk);
        try {
            int secondaryNumber = 1;
            ZipEntry dexFile = apk.getEntry(APK_DEX_DIR + DEX_PREFIX + secondaryNumber + DEX_SUFFIX);
            while (dexFile != null) {
                String fileName = extractedFilePrefix + secondaryNumber + EXTRACTED_SUFFIX;
                ExtractedDex extractedFile = new ExtractedDex(dexDir, fileName);
                files.add(extractedFile);
                Log.i(TAG, "Extraction is needed for file " + extractedFile);
                int numAttempts = 0;
                boolean isExtractionSuccessful = false;
                while (numAttempts < MAX_EXTRACT_ATTEMPTS && !isExtractionSuccessful) {
                    numAttempts++;
                    // Create a zip file (extractedFile) containing only the secondary dex file
                    // (dexFile) from the apk.
                    extract(context, apk, dexFile, extractedFile, extractedFilePrefix);
                    // Read zip crc of extracted dex
                    try {
                        extractedFile.crc = getZipCrc(extractedFile);
                        isExtractionSuccessful = true;
                    } catch (IOException e) {
                        isExtractionSuccessful = false;
                        Log.w(TAG, "Failed to read crc from " + extractedFile.getAbsolutePath(), e);
                    }
                    // Log size and crc of the extracted zip file
                    Log.i(TAG, "Extraction " + (isExtractionSuccessful ? "succeeded" : "failed")
                            + " '" + extractedFile.getAbsolutePath() + "': length "
                            + extractedFile.length() + " - crc: " + extractedFile.crc);
                    if (!isExtractionSuccessful) {
                        // Delete the extracted file
                        extractedFile.delete();
                        if (extractedFile.exists()) {
                            Log.w(TAG, "Failed to delete corrupted secondary dex '" +
                                    extractedFile.getPath() + "'");
                        }
                    }
                }
                if (!isExtractionSuccessful) {
                    throw new IOException("Could not create zip file " +
                            extractedFile.getAbsolutePath() + " for secondary dex (" +
                            secondaryNumber + ")");
                }
                secondaryNumber++;
                dexFile = apk.getEntry(APK_DEX_DIR + DEX_PREFIX + secondaryNumber + DEX_SUFFIX);
            }
        } finally {
            try {
                apk.close();
            } catch (IOException e) {
                Log.w(TAG, "Failed to close resource", e);
            }
        }
        return files;
    }

    /**
     * Clear the dex dir from all files but the lock.
     */
    private void clearDexDir() {
        File[] files = dexDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.getName().equals(LOCK_FILENAME);
            }
        });
        if (files == null) {
            Log.w(TAG, "Failed to list secondary dex dir content (" + dexDir.getPath() + ").");
            return;
        }
        for (File oldFile : files) {
            Log.i(TAG, "Trying to delete old file " + oldFile.getPath() + " of size " +
                    oldFile.length());
            if (!oldFile.delete()) {
                Log.w(TAG, "Failed to delete old file " + oldFile.getPath());
            } else {
                Log.i(TAG, "Deleted old file " + oldFile.getPath());
            }
        }
    }

    /**
     * Zip file containing one secondary dex file.
     */
    private static class ExtractedDex extends File {
        public long crc = NO_VALUE;

        public ExtractedDex(File dexDir, String fileName) {
            super(dexDir, fileName);
        }
    }
}
