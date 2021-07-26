package com.mcal.resguard.androlib;

import com.mcal.resguard.androlib.res.data.ResPackage;
import com.mcal.resguard.androlib.res.decoder.ARSCDecoder;
import com.mcal.resguard.androlib.res.decoder.RawARSCDecoder;
import com.mcal.resguard.androlib.res.util.ExtFile;
import com.mcal.resguard.directory.DirectoryException;
import com.mcal.resguard.resourceproguard.Configuration;
import com.mcal.resguard.util.FileOperation;
import com.mcal.resguard.util.TypedValue;
import com.mcal.resguard.util.Utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
/*import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;*/
import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * @author shwenzhang
 */
public class ApkDecoder {

    final HashSet<File> mRawResourceFiles = new HashSet<>();
    private final Configuration config;
    private final ExtFile apkFile;
    private File mOutDir;
    private File mOutTempARSCFile;
    private File mOutARSCFile;
    private File mOutResFile;
    private File mRawResFile;
    private File mOutTempDir;
    private File mResMappingFile;
    private File mMergeDuplicatedResMappingFile;
    private HashMap<String, Integer> mCompressData;

    public ApkDecoder(Configuration config, File apkFile) {
        this.config = config;
        this.apkFile = new ExtFile(apkFile);
    }

    private void copyOtherResFiles() {
        if (mRawResourceFiles.isEmpty()) {
            return;
        }
        File resPath = mRawResFile;
        File destPath = mOutResFile;

        for (File path : mRawResourceFiles) {
            System.out.printf("copy res file not in resources.arsc file:%s\n", destPath.toString());
            FileOperation.copyFileStream(path, destPath);
        }
    }

    public static String combine(String path1, String path2)
    {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);
        return file2.getPath();
    }

    public void removeCopiedResFile(File key) {
        mRawResourceFiles.remove(key);
    }

    public Configuration getConfig() {
        return config;
    }

    public boolean hasResources() throws AndrolibException {
        try {
            return apkFile.getDirectory().containsFile("resources.arsc");
        } catch (DirectoryException ex) {
            throw new AndrolibException(ex);
        }
    }

    private void ensureFilePath() throws IOException {
        Utils.cleanDir(mOutDir);

        String unZipDest = new File(mOutDir, TypedValue.UNZIP_FILE_PATH).getAbsolutePath();
        System.out.printf("unziping apk to %s\n", unZipDest);
        mCompressData = FileOperation.unZipAPk(apkFile.getAbsoluteFile().getAbsolutePath(), unZipDest);
        dealWithCompressConfig();
        //将res混淆成r
        if (!config.mKeepRoot) {
            mOutResFile = new File(mOutDir.getAbsolutePath() + File.separator + TypedValue.RES_FILE_PATH);
        } else {
            mOutResFile = new File(mOutDir.getAbsolutePath() + File.separator + "res");
        }

        //这个需要混淆各个文件夹
        mRawResFile = new File(mOutDir.getAbsoluteFile().getAbsolutePath()
                + File.separator
                + TypedValue.UNZIP_FILE_PATH
                + File.separator
                + "res");
        mOutTempDir = new File(mOutDir.getAbsoluteFile().getAbsolutePath() + File.separator + TypedValue.UNZIP_FILE_PATH);

        mRawResFile.listFiles(pathname -> {
            mRawResourceFiles.add(pathname);
            return true;
        });

        if (!mRawResFile.exists() || !mRawResFile.isDirectory()) {
            throw new IOException("can not found res dir in the apk or it is not a dir");
        }

        mOutTempARSCFile = new File(mOutDir.getAbsoluteFile().getAbsolutePath() + File.separator + "resources_temp.arsc");
        mOutARSCFile = new File(mOutDir.getAbsoluteFile().getAbsolutePath() + File.separator + "resources.arsc");

        String basename = apkFile.getName().substring(0, apkFile.getName().indexOf(".apk"));
        mResMappingFile = new File(mOutDir.getAbsoluteFile().getAbsolutePath()
                + File.separator
                + TypedValue.RES_MAPPING_FILE
                + basename
                + TypedValue.TXT_FILE);
        mMergeDuplicatedResMappingFile = new File(mOutDir.getAbsoluteFile().getAbsolutePath()
                + File.separator
                + TypedValue.MERGE_DUPLICATED_RES_MAPPING_FILE
                + basename
                + TypedValue.TXT_FILE);
    }

    /**
     * 根据config来修改压缩的值
     */
    private void dealWithCompressConfig() {
        if (config.mUseCompress) {
            HashSet<Pattern> patterns = config.mCompressPatterns;
            if (!patterns.isEmpty()) {
                for (Entry<String, Integer> entry : mCompressData.entrySet()) {
                    String name = entry.getKey();
                    for (Iterator<Pattern> it = patterns.iterator(); it.hasNext(); ) {
                        Pattern p = it.next();
                        if (p.matcher(name).matches()) {
                            mCompressData.put(name, TypedValue.ZIP_DEFLATED);
                        }
                    }
                }
            }
        }
    }

    public HashMap<String, Integer> getCompressData() {
        return mCompressData;
    }

    public File getOutDir() {
        return mOutDir;
    }

    public void setOutDir(File outDir) {
        mOutDir = outDir;
    }

    public File getOutResFile() {
        return mOutResFile;
    }

    public File getRawResFile() {
        return mRawResFile;
    }

    public File getOutTempARSCFile() {
        return mOutTempARSCFile;
    }

    public File getOutARSCFile() {
        return mOutARSCFile;
    }

    public File getOutTempDir() {
        return mOutTempDir;
    }

    public File getResMappingFile() {
        return mResMappingFile;
    }

    public File getMergeDuplicatedResMappingFile() {
        return mMergeDuplicatedResMappingFile;
    }

    public void decode() throws AndrolibException, IOException, DirectoryException {
        if (hasResources()) {
            ensureFilePath();
            // read the resources.arsc checking for STORED vs DEFLATE compression
            // this will determine whether we compress on rebuild or not.
            System.out.printf("decoding resources.arsc\n");
            RawARSCDecoder.decode(apkFile.getDirectory().getFileInput("resources.arsc"));
            ResPackage[] pkgs = ARSCDecoder.decode(apkFile.getDirectory().getFileInput("resources.arsc"), this);

            //把没有纪录在resources.arsc的资源文件也拷进dest目录
            copyOtherResFiles();

            ARSCDecoder.write(apkFile.getDirectory().getFileInput("resources.arsc"), this, pkgs);
        }
    }
}
