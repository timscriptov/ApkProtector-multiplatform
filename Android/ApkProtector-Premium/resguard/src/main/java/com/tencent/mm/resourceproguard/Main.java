package com.tencent.mm.resourceproguard;

import com.tencent.mm.androlib.AndrolibException;
import com.tencent.mm.androlib.ApkDecoder;
import com.tencent.mm.androlib.ResourceApkBuilder;
import com.tencent.mm.androlib.res.decoder.ARSCDecoder;
import com.tencent.mm.directory.DirectoryException;
import com.tencent.mm.util.FileOperation;

import java.io.File;
import java.io.IOException;

/**
 * @author shwenzhang
 * @author simsun
 */
public class Main {

    public static final int ERRNO_USAGE = 2;
    protected static long mRawApkSize;
    protected static String mRunningLocation;
    protected static long mBeginTime;

    /**
     * 是否通过命令行方式设置
     **/
    public boolean mSetMappingThroughCmd;
    public String mFinalApkBackPath;

    protected Configuration config;
    protected File mOutDir;

    public static void gradleRun(InputParam inputParam) {
        Main m = new Main();
        m.run(inputParam);
    }

    private void run(InputParam inputParam) {
        synchronized (Main.class) {
            loadConfigFromGradle(inputParam);
            this.mFinalApkBackPath = inputParam.finalApkBackupPath;
            Thread currentThread = Thread.currentThread();
            System.out.printf(
                    "\n-->AndResGuard starting! Current thread# id: %d, name: %s\n",
                    currentThread.getId(),
                    currentThread.getName()
            );

            resourceProguard(
                    new File(inputParam.outFolder),
                    inputParam.apkPath
            );
            System.out.printf("<--AndResGuard Done! You can find the output in %s\n", mOutDir.getAbsolutePath());
            clean();
        }
    }

    protected void clean() {
        config = null;
        ARSCDecoder.mTableStringsResguard.clear();
        ARSCDecoder.mMergeDuplicatedResCount = 0;
    }

    private void loadConfigFromGradle(InputParam inputParam) {
        try {
            config = new Configuration(inputParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void resourceProguard(File outputDir, String apkFilePath) {
        File apkFile = new File(apkFilePath);
        if (!apkFile.exists()) {
            System.err.printf("The input apk %s does not exist", apkFile.getAbsolutePath());
            goToError();
        }
        mRawApkSize = FileOperation.getFileSizes(apkFile);
        try {
            ApkDecoder decoder = new ApkDecoder(config, apkFile);
            decodeResource(outputDir, decoder, apkFile);
            buildApk(decoder, apkFile);
        } catch (Exception e) {
            e.printStackTrace();
            goToError();
        }
    }

    private void decodeResource(File outputFile, ApkDecoder decoder, File apkFile)
            throws AndrolibException, IOException, DirectoryException {
        if (outputFile == null) {
            mOutDir = new File(mRunningLocation, apkFile.getName().substring(0, apkFile.getName().indexOf(".apk")));
        } else {
            mOutDir = outputFile;
        }
        decoder.setOutDir(mOutDir.getAbsoluteFile());
        decoder.decode();
    }

    private void buildApk(
            ApkDecoder decoder, File apkFile)
            throws Exception {
        ResourceApkBuilder builder = new ResourceApkBuilder(config);
        String apkBasename = apkFile.getName();
        apkBasename = apkBasename.substring(0, apkBasename.indexOf(".apk"));
        builder.setOutDir(mOutDir, apkBasename);
        builder.buildApk(decoder.getCompressData());
    }

    protected void goToError() {
        System.exit(ERRNO_USAGE);
    }
}