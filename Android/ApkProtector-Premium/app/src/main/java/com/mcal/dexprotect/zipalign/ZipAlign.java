package com.mcal.dexprotect.zipalign;

import com.mcal.dexprotect.data.Preferences;

public class ZipAlign {
    static {
        System.loadLibrary("zipalign");
    }

    /**
     * Generate a new, aligned, zip "output" from an "input" zip.
     * <p>
     * <b><em>NOTE:</em></b> If the APK is to be signed with schema v2 or later, the APK must be aligned <em>before</em>
     * signing it, and for v1 schema (AKA jar signing), the APK must be aligned <em>after</em> signing it.
     *
     * @param inZipFile           The zip file to be aligned.
     * @param outZipFile          File where the aligned zip file will be saved.
     * @param alignment           Alignment (in bytes) for uncompressed entries.
     * @param force               Overwrite output if it exists, fail otherwise.
     * @param zopfli              Recompress compressed entries with more efficient algorithm. Copy compressed entries as-is, and unaligned, otherwise.
     * @param pageAlignSharedLibs Align .so files to 4096 and other files to alignment, or all files to alignTo if false..
     * @return {@code true} on success.
     */
    public static native boolean process(String inZipFile, String outZipFile, int alignment, boolean force, boolean zopfli, boolean pageAlignSharedLibs);

    public static boolean runProcess(String inZipFile, String outZipFile) {
        try {
            process(inZipFile, outZipFile, Integer.parseInt(Preferences.getAlignmentString()), Preferences.getForceBoolean(), Preferences.getZopfliBoolean(), Preferences.getPageAlignSharedLibsBoolean());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Verify the alignment of a zip archive.
     *
     * @param inZipFile           The zip file whose alignment has to be verified
     * @param alignment           Alignment (in bytes) for uncompressed entries.
     * @param pageAlignSharedLibs Align .so files to 4096 and other files to alignment, or all files to alignTo if false..
     * @return {@code true} on success.
     */
    public static native boolean verify(String inZipFile, int alignment, boolean verbose, boolean pageAlignSharedLibs);
}