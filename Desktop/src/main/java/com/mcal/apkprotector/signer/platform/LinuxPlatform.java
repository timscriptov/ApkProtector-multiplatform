package com.mcal.apkprotector.signer.platform;

import com.mcal.apkprotector.utils.Cmd;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.StringUtils;

import java.io.File;
import java.io.IOException;

public class LinuxPlatform implements IPlatform {
    @Override
    public String zipalign(String inputPath) throws Exception {
        final String zipalignPath = CommonUtils.exportMetaFile("linux" + File.separator + "zipalign", new File("." + File.separator + "zipalign").getAbsolutePath());
        CommonUtils.exportMetaFile("linux" + File.separator + "lib64" + File.separator + "libbcinfo.so", new File("." + File.separator + "lib64" + File.separator + "libbcinfo.so").getAbsolutePath());
        CommonUtils.exportMetaFile("linux" + File.separator + "lib64" + File.separator + "libc++.so", new File("." + File.separator + "lib64" + File.separator + "libc++.so").getAbsolutePath());
        CommonUtils.exportMetaFile("linux" + File.separator + "lib64" + File.separator + "libclang.so", new File("." + File.separator + "lib64" + File.separator + "libclang.so").getAbsolutePath());
        CommonUtils.exportMetaFile("linux" + File.separator + "lib64" + File.separator + "libLLVM.so", new File("." + File.separator + "lib64" + File.separator + "libLLVM.so").getAbsolutePath());
        final String tempFilePath = inputPath.replace(".apk", "-aligned.apk");

        Cmd.exec("chmod a+x " + zipalignPath);

        String[] cmdarray = new String[]{/*"/bin/sh", "-c",*/ zipalignPath, "-v", "-f", "4", inputPath, tempFilePath};

        boolean zipalignResult = false;
        Process process = null;
        try {
            String content = Cmd.exec(cmdarray);
            if (!StringUtils.isEmpty(content) && content.contains("Verification succesful")) {
                zipalignResult = true;
            }
            System.out.println("Task zip align result : " + zipalignResult);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }

        return (zipalignResult) ? tempFilePath : null;
    }

    @Override
    public boolean checkZipaligned(String src) throws Exception {
        final String zipalignPath = CommonUtils.exportMetaFile("linux" + File.separator + "zipalign", new File("." + File.separator + "zipalign").getAbsolutePath());
        CommonUtils.exportMetaFile("linux" + File.separator + "lib64" + File.separator + "libbcc.so", new File("." + File.separator + "lib64" + File.separator + "libbcc.so").getAbsolutePath());
        CommonUtils.exportMetaFile("linux" + File.separator + "lib64" + File.separator + "libbcinfo.so", new File("." + File.separator + "lib64" + File.separator + "libbcinfo.so").getAbsolutePath());
        CommonUtils.exportMetaFile("linux" + File.separator + "lib64" + File.separator + "libc++.so", new File("." + File.separator + "lib64" + File.separator + "libc++.so").getAbsolutePath());
        CommonUtils.exportMetaFile("linux" + File.separator + "lib64" + File.separator + "libclang.so", new File("." + File.separator + "lib64" + File.separator + "libclang.so").getAbsolutePath());
        CommonUtils.exportMetaFile("linux" + File.separator + "lib64" + File.separator + "libLLVM.so", new File("." + File.separator + "lib64" + File.separator + "libLLVM.so").getAbsolutePath());

        Cmd.exec("chmod a+x " + zipalignPath);

        String[] cmdarray = new String[]{/*"/bin/sh", "-c",*/ zipalignPath, "-c", "-v", "4", src};
        boolean zipalignResult = false;
        Process process = null;
        try {
            String content = Cmd.exec(cmdarray);
            if (!StringUtils.isEmpty(content) && content.contains("Verification succesful")) {
                zipalignResult = true;
            }
            System.out.println(src + " is zip aligned ? " + zipalignResult);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }

        return (zipalignResult) ? true : false;
    }
}