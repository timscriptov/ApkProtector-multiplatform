package com.secure.dex.signer;

import com.secure.dex.utils.FileUtils;

import java.io.File;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Monitor extends PrintStream {

    public static final File file = new File("./console.txt");
    private final StringBuilder buffer = new StringBuilder();

    public Monitor() throws Exception {
        super(System.out, true, "GBK");
    }

    public static boolean contains(Monitor monitor, String flag) {
        int index = monitor.buffer.indexOf(flag);
        if (index != -1) {
            return true;
        }
        return false;
    }

    public static String getContent(Monitor monitor) {
        return monitor.buffer.toString();
    }

    @Override
    public void write(int b) {

    }

    @Override
    public void println(int x) {
        super.println(x);
    }

    @Override
    public void write(byte[] b, int off, int len) {
        byte[] chars;
        chars = Arrays.copyOfRange(b, off, len);
        try {
            String msg = new String(chars, StandardCharsets.UTF_8);
            buffer.append(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtils.writeFile(file.getAbsolutePath(), buffer.toString(), false);
        super.write(b, off, len);
    }
}