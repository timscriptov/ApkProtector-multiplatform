package com.oscar0812.obfuscation.smali;

import com.oscar0812.obfuscation.APKInfo;
import com.oscar0812.obfuscation.utils.StringUtils;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SmaliLineObfuscator {

    private static SmaliLineObfuscator instance = null;

    public static SmaliLineObfuscator getInstance() {
        if (instance == null) {
            instance = new SmaliLineObfuscator();
        }
        return instance;
    }

    private final HashMap<String, SmaliFile> obfFileMap = new HashMap<>();
    private final ArrayList<String> obfFilePaths = new ArrayList<>();
    private final HashMap<String, Integer> methodNumber = new HashMap<>();
    private final HashMap<String, Set<String>> dirFiles = new HashMap<>();

    private static Set<String> smaliFilesInDir(File dir) {
        Set<String> filePaths = new HashSet<>();
        for (File f : dir.listFiles()) {
            if (!f.isDirectory() && f.getName().endsWith(".smali")) {
                filePaths.add(f.getAbsolutePath());
            }
        }

        return filePaths;
    }

    private SmaliFile createNewFile(SmaliFile siblingFile) {
        // create a file with a name that doesn't exist in the same directory as siblingFile
        File parent = SmaliFile.getCreatedSmaliDir(siblingFile);

        if (!dirFiles.containsKey(parent.getAbsolutePath())) {
            dirFiles.put(parent.getAbsolutePath(), smaliFilesInDir(parent));
        }
        Set<String> filePaths = dirFiles.get(parent.getAbsolutePath());

        ArrayList<String> permutations = StringUtils.getStringPermutations();
        String fileClassName;
        int index = 0;
        do {
            fileClassName = permutations.get(index++);
        } while (filePaths.contains(fileClassName + ".smali"));

        filePaths.add(fileClassName + ".smali");

        return new SmaliFile(parent, fileClassName + ".smali");
    }

    // smali of string obfuscator object (look at StringUtil.smali)
    private SmaliFile createObfFile(SmaliLine line) {
        SmaliFile obfFile = createNewFile(line.getParentSmaliFile());

        obfFile.appendString(
                ".class public " + obfFile.getSmaliPackage() + "\n" +
                        ".super Ljava/lang/Object;\n" +
                        ".source \"" + obfFile.getSmaliClass() + ".java\"\n\n\n" +
                        ".method public constructor <init>()V\n" +
                        SmaliLine.SINGLE_SPACE + ".locals 0\n\n" +
                        SmaliLine.SINGLE_SPACE + "invoke-direct {p0}, Ljava/lang/Object;-><init>()V\n\n" +
                        SmaliLine.SINGLE_SPACE + "return-void\n" +
                        ".end method\n\n");

        obfFilePaths.add(obfFile.getAbsolutePath());
        obfFileMap.put(obfFile.getAbsolutePath(), obfFile);

        return obfFile;
    }


    private String getConstTypeForInt(int num) {
        if (num >= -8 && num <= 7) {
            return "const/4";
        } else if ((num >= -32768 && num <= -9) || (num >= 8 && num <= 32767)) {
            return "const/16";
        } else if ((num >= -1073741824 && num <= -65536) || (num >= 32768 && num <= 1073741824)) {
            // return "const/high16";
            return "const";
        } else if (num >= -65535 && num <= -32769) {
            return "const";
        }

        return "const";
    }

    // add a method to the obfuscator file (will return a string from bytes)
    private void appendMethod(SmaliFile obfFile, SmaliLine smaliLine, String methodName) {

        obfFile.appendString(
                ".method public static " + methodName + "()Ljava/lang/String;\n" +
                        SmaliLine.SINGLE_SPACE + ".locals 4\n\n");

        String ogString = smaliLine.getParts()[smaliLine.getParts().length - 1]; // the last index holds the string
        int byteSize = ogString.length();
        String constType = getConstTypeForInt(byteSize);
        String sizeHex = "0x" + Integer.toHexString(byteSize);

        obfFile.appendString(SmaliLine.SINGLE_SPACE + constType + " v0, " + sizeHex + "\n\n" + SmaliLine.SINGLE_SPACE + "new-array v0, v0, [B");

        Random r = new Random(System.currentTimeMillis());
        byte[] b = ogString.getBytes();

        for (int i = 0; i < byteSize; ++i) {
            int tr = r.nextInt();
            int f = r.nextInt(24) + 1;

            int a1, t;
            a1 = (0xff << f);

            t = (tr & ~a1) | (b[i] << f);

            String sign = t < 0 ? "-" : "";
            String tHex = sign + "0x" + Integer.toHexString(Math.abs(t));
            sign = f < 0 ? "-" : "";
            String fHex = sign + "0x" + Integer.toHexString(Math.abs(f));

            String indexHex = "0x" + Integer.toHexString(i);
            obfFile.appendString(
                    "\n" + // SmaliLine.SINGLE_SPACE + ".line " + (obfFile.debugLine++) + "\n" +
                            SmaliLine.SINGLE_SPACE + "const v1, " + tHex + "\n\n" +
                            SmaliLine.SINGLE_SPACE + "ushr-int/lit8 v2, v1, " + fHex + "\n\n" +
                            SmaliLine.SINGLE_SPACE + "int-to-byte v2, v2\n\n" +
                            SmaliLine.SINGLE_SPACE + getConstTypeForInt(i) + " v3, " + indexHex + "\n\n" +
                            SmaliLine.SINGLE_SPACE + "aput-byte v2, v0, v3\n\n");

        }

        obfFile.appendString(// SmaliLine.SINGLE_SPACE + ".line " + obfFile.debugLine + "\n" +
                SmaliLine.SINGLE_SPACE + "new-instance v2, Ljava/lang/String;\n\n" +
                        SmaliLine.SINGLE_SPACE + "invoke-direct {v2, v0}, Ljava/lang/String;-><init>([B)V\n\n" +
                        SmaliLine.SINGLE_SPACE + "return-object v2\n" +
                        ".end method");

        // obfFile.debugLine += 10;
    }

    // const-string v0, "Replace with your own action" =>
    // invoke-static {}, Lcom/oscar0812/sample_navigation/StringUtil;->a()Ljava/lang/String;
    //
    // move-result-object v0
    public void stringToStaticCall(SmaliLine inLine) {
        String register = inLine.getParts()[1].replace(",", ""); // v0, => v0

        int randomNum = ThreadLocalRandom.current().nextInt(0, obfFilePaths.size() + 2);

        SmaliFile obfFile;
        if (randomNum >= obfFilePaths.size()) {
            obfFile = createObfFile(inLine);
        } else {
            obfFile = obfFileMap.get(obfFilePaths.get(randomNum));
        }

        ArrayList<String> permutations = StringUtils.getStringPermutations();
        if (!methodNumber.containsKey(obfFile.getAbsolutePath())) {
            methodNumber.put(obfFile.getAbsolutePath(), 0);
        }

        String methodName = permutations.get(methodNumber.get(obfFile.getAbsolutePath()));
        appendMethod(obfFile, inLine, methodName);

        methodNumber.put(obfFile.getAbsolutePath(), methodNumber.get(obfFile.getAbsolutePath()) + 1);

        // add this to the apk (IT IS PART OF IT NOW!)
        APKInfo.getInstance().addSmaliFile(obfFile);
        inLine.setText(SmaliLine.SINGLE_SPACE + "invoke-static {}, " + obfFile.getSmaliPackage() + "->" + methodName + "()Ljava/lang/String;");
        inLine.insertAfter("").insertAfter(SmaliLine.SINGLE_SPACE + "move-result-object " + register);
    }

    public void obfuscateConstInt(SmaliLine inLine) {
        String[] parts = inLine.getParts();
        // const v3, 0x7f060061
        // 0x7f060061

        String hex = parts[parts.length - 1];
        boolean negHex = hex.startsWith("-0x");
        // 7f060061
        hex = hex.substring(hex.indexOf("x") + 1);
        int dec = Integer.parseInt(hex, 16);

        int randomInt = ThreadLocalRandom.current().nextInt(1, 11); // 11 so it can include 10 (0, 10)

        // v0,
        String register = parts[parts.length - 2];
        // v0
        register = register.substring(0, register.length() - 1);

        // to keep it in bounds (nothing fancy)
        if (negHex) {
            // neg.
            int javaMin32Bit = -2147483648;
            int t = javaMin32Bit + dec;
            if (t >= - -11) {
                return;
            }

            dec += randomInt;
        } else {
            dec -= randomInt;
        }
        // add it back in the next line
        String nextLineNewSmaliText = inLine.getWhitespace() + "add-int/lit8 " + register + ", " + register + ", 0x" + Integer.toHexString(randomInt);

        String newSmaliText = inLine.getTextFromParts().replace("0x" + hex, "0x" + Integer.toHexString(Math.abs(dec)));
        inLine.setText(newSmaliText);
        inLine.insertAfter("").insertAfter(nextLineNewSmaliText);
    }
}