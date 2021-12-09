package com.mcal.apkprotector.task;

public class StringFog {
    private static String TAG = "StringFog";

    /*public static void doFogJar(String inputJar, String outputJar, String outputMapping) {
        try {
            StringFogMappingPrinter stringFogMappingPrinter = new StringFogMappingPrinter(new File(outputMapping, "stringFogMapping.txt"));

            StringFogClassInjector stringFogClassInjector = new StringFogClassInjector(new String[0], "UTF-8", "com.mcal.stringfog.xor.StringFogImpl", "com.mcal.stringfog.xor.StringFogImpl", stringFogMappingPrinter);
            stringFogMappingPrinter.startMappingOutput();
            stringFogMappingPrinter.ouputInfo("UTF-8", "com.mcal.stringfog.xor.StringFogImpl");
            stringFogClassInjector.doFog2Jar(new File(inputJar), new File(outputJar));
        } catch (IOException e) {
            Log.e(TAG, "doFog()  ", e);
        }
    }

    public static void doFogClasses(String inputClasses, String outputMapping) {
        try {
            StringFogMappingPrinter stringFogMappingPrinter = new StringFogMappingPrinter(new File(outputMapping, "stringFogMapping.txt"));

            StringFogClassInjector stringFogClassInjector = new StringFogClassInjector(new String[0], "UTF-8", "com.mcal.stringfog.xor.StringFogImpl", "com.mcal.stringfog.xor.StringFogImpl", stringFogMappingPrinter);
            stringFogMappingPrinter.startMappingOutput();
            stringFogMappingPrinter.ouputInfo("UTF-8", "com.mcal.stringfog.xor.StringFogImpl");
            stringFogClassInjector.doFog2ClassInDir(new File(inputClasses));
        } catch (IOException e) {
            Log.e(TAG, "doFog()  ", e);
        }
    }*/
}