package com.mcal.apkprotector.signer;

import com.mcal.apkprotector.utils.FileUtils;

import java.io.File;

public class SignerTool {
    public static boolean sign(String input, String output) {
        File inputFile = new File(input);
        File outputFile = new File(output);

        try {
            SignerUtils signerUtils = SignerUtils.getInstance(FileUtils.getTempFile());
            signerUtils.sign(inputFile, outputFile, -1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean verify(String output) {
        File outputFile = new File(output);

        try {
            SignerUtils signerUtils = SignerUtils.getInstance(FileUtils.getTempFile());
            signerUtils.verify(outputFile);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
