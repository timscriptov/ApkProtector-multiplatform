package com.secure.dex.signer.platform;

import com.secure.dex.utils.Cmd;
import com.secure.dex.utils.FileUtils;
import com.secure.dex.utils.StringUtils;

import java.io.File;
import java.io.IOException;

public class WindowsPlatform implements IPlatform {
    @Override
    public String zipalign(String inputPath) throws Exception {
        final String zipalignPath = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "zipalign.exe";
        final String tempFilePath = inputPath.replace(".apk", "-aligned.apk");

        String[] cmdarray = new String[]{zipalignPath, "-v", "-f", "4", inputPath, tempFilePath};

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
        final String zipalignPath = FileUtils.getWorkPath() + File.separator + "tools" + File.separator + "zipalign.exe";

        String[] cmdarray = new String[]{zipalignPath, "-p", "-c", "-v", "4", src};

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