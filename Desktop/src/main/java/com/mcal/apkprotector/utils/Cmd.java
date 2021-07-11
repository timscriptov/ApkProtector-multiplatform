package com.mcal.apkprotector.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Cmd {
    public static int exec(String cmd, boolean waitfor) throws Exception {
        Process process = null;
        process = Runtime.getRuntime().exec(cmd);
        return process(process, waitfor);
    }

    public static int exec(String[] cmdarray, boolean waitfor) throws Exception {
        Process process = null;
        process = Runtime.getRuntime().exec(cmdarray);
        return process(process, waitfor);
    }

    public static String exec(String[] cmdarray) throws Exception {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdarray);
            String logging = getInputContent(process.getInputStream());
            System.out.println(Arrays.asList(cmdarray) + "exec code : [" + process.waitFor() + "]");
            return logging;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return null;
    }

    public static String exec(String cmd) throws Exception {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            String logging = getInputContent(process.getInputStream());
            System.out.println(cmd + "exec code : [" + process.waitFor() + "]");
            return logging;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return null;
    }

    private static String getInputContent(InputStream inputStream) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            sb.append(line);
        }
        return sb.toString();
    }

    private static int process(Process process, boolean waitfor) throws InterruptedException {
        InputStream inputStream = process.getInputStream();
        InputStream errorStream = process.getErrorStream();
        ThreadTool.execute(new LoopInputOrErrorBuffer(inputStream));
        ThreadTool.execute(new LoopInputOrErrorBuffer(errorStream));
        if (waitfor) {
            return process.waitFor();
        }
        return 0;
    }

    static class LoopInputOrErrorBuffer implements Runnable {

        private final InputStream mIOStream;

        public LoopInputOrErrorBuffer(InputStream InputStream) {
            this.mIOStream = InputStream;
        }

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(mIOStream));
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CommonUtils.slientClose(mIOStream);
            }
        }
    }
}