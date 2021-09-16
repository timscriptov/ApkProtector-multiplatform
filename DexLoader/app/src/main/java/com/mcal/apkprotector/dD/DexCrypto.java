/* Orginal file: DexCrypto.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.utils*/
package com.mcal.apkprotector.dD;

//import java.io.File;
//import java.io.IOException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterInputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;

//public class DexCrypto {
public class DexCrypto {
    //public static void decrypt(String protectKey, InputStream input, OutputStream output) throws IOException {
    public static void decrypt(String protectKey, InputStream input, OutputStream output) throws IOException {
        //InflaterInputStream is = new InflaterInputStream(input);
        InflaterInputStream is = new InflaterInputStream(input);
        //InflaterOutputStream os = new InflaterOutputStream(output);
        InflaterOutputStream os = new InflaterOutputStream(output);

        //Xor.exfr(protectKey, is, os);
        Xor.exfr(protectKey, is, os);
        //os.close();
        os.close();
        //is.close();
        is.close();
        //}
    }

    //public static void encrypt(String protectKey, InputStream input, OutputStream output) throws IOException {
    public static void encrypt(String protectKey, InputStream input, OutputStream output) throws IOException {
        //DeflaterInputStream is = new DeflaterInputStream(input);
        DeflaterInputStream is = new DeflaterInputStream(input);
        //DeflaterOutputStream os = new DeflaterOutputStream(output);
        DeflaterOutputStream os = new DeflaterOutputStream(output);

        //Xor.exfr(protectKey, is, os);
        Xor.exfr(protectKey, is, os);
        //os.close();
        os.close();
        //is.close();
        is.close();
        //}
    }
//}
}