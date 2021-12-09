package com.tencent.mm.androlib;

import com.tencent.mm.androlib.res.decoder.ARSCDecoder;
import com.tencent.mm.resourceproguard.Configuration;
import com.tencent.mm.util.FileOperation;
import com.tencent.mm.util.TypedValue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author shwenzhang
 * modified:
 * @author jonychina162
 * 为了使用v2签名，引入了google v2sign 模块
 * 由于使用v2签名，会对整个包除了签名块验证完整性，即除了签名块的内容在签名之后包其他内容不允许再改动，因此修改了原有的签名逻辑，
 * 现有逻辑：1 zipalign 2.sign 。具体请参考buildApkV2sign
 */
public class ResourceApkBuilder {

    private final Configuration config;
    private File mOutDir;

    private File mUnSignedApk;

    private String mApkName;

    public ResourceApkBuilder(Configuration config) {
        this.config = config;
    }

    public void setOutDir(File outDir, String apkName) {
        this.mOutDir = outDir;
        this.mApkName = apkName;
    }

    public void buildApk(HashMap<String, Integer> compressData) throws IOException {
        insureFileName();
        generalUnsignApk(compressData);
    }


    private void insureFileName() {
        mUnSignedApk = new File(mOutDir.getAbsolutePath(), mApkName + "_unsigned.apk");
    }

    private void generalUnsignApk(HashMap<String, Integer> compressData) throws IOException {
        System.out.printf("General unsigned apk: %s\n", mUnSignedApk.getName());
        File tempOutDir = new File(mOutDir.getAbsolutePath(), TypedValue.UNZIP_FILE_PATH);
        if (!tempOutDir.exists()) {
            System.err.printf("Missing apk unzip files, path=%s\n", tempOutDir.getAbsolutePath());
            System.exit(-1);
        }

        File[] unzipFiles = tempOutDir.listFiles();
        assert unzipFiles != null;
        List<File> collectFiles = new ArrayList<>();
        for (File f : unzipFiles) {
            String name = f.getName();
            if (name.equals("res") || name.equals("resources.arsc")) {
                continue;
            } else if (name.equals(config.mMetaName)) {
                addNonSignatureFiles(collectFiles, f);
                continue;
            }
            collectFiles.add(f);
        }

        File destResDir = new File(mOutDir.getAbsolutePath(), "res");
        //添加修改后的res文件
        if (!config.mKeepRoot && FileOperation.getlist(destResDir) == 0) {
            destResDir = new File(mOutDir.getAbsolutePath(), TypedValue.RES_FILE_PATH);
        }

        /*
         * NOTE:文件数量应该是一样的，如果不一样肯定有问题
         */
        File rawResDir = new File(tempOutDir.getAbsolutePath() + File.separator + "res");
        System.out.printf("DestResDir %d rawResDir %d\n",
                FileOperation.getlist(destResDir),
                FileOperation.getlist(rawResDir)
        );
        if (FileOperation.getlist(destResDir) != (FileOperation.getlist(rawResDir) - ARSCDecoder.mMergeDuplicatedResCount)) {
            throw new IOException(String.format(
                    "the file count of %s, and the file count of %s is not equal, there must be some problem\n",
                    rawResDir.getAbsolutePath(),
                    destResDir.getAbsolutePath()
            ));
        }
        if (!destResDir.exists()) {
            System.err.printf("Missing res files, path=%s\n", destResDir.getAbsolutePath());
            System.exit(-1);
        }
        //这个需要检查混淆前混淆后，两个res的文件数量是否相等
        collectFiles.add(destResDir);
        File rawARSCFile = new File(mOutDir.getAbsolutePath() + File.separator + "resources.arsc");
        if (!rawARSCFile.exists()) {
            System.err.printf("Missing resources.arsc files, path=%s\n", rawARSCFile.getAbsolutePath());
            System.exit(-1);
        }
        collectFiles.add(rawARSCFile);
        FileOperation.zipFiles(collectFiles, tempOutDir, mUnSignedApk, compressData);

        if (!mUnSignedApk.exists()) {
            throw new IOException(String.format("can not found the unsign apk file path=%s", mUnSignedApk.getAbsolutePath()));
        }
    }

    private void addNonSignatureFiles(List<File> collectFiles, File metaFolder) {
        File[] metaFiles = metaFolder.listFiles();
        if (metaFiles != null) {
            for (File metaFile : metaFiles) {
                String metaFileName = metaFile.getName();
                // Ignore signature files
                if (!metaFileName.endsWith(".MF") && !metaFileName.endsWith(".RSA") && !metaFileName.endsWith(".SF")) {
                    System.out.printf("add meta file %s%n", metaFile.getAbsolutePath());
                    collectFiles.add(metaFile);
                }
            }
        }
    }
}
