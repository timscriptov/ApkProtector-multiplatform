/* Orginal file: Xor.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.utils*/
package com.mcal.apkprotector.dD;

//import java.io.IOException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//public class Xor {
public class Xor {
    //public static void exfr(String protectKey, InputStream inputStream, OutputStream outputStream) throws IOException {
    public static void exfr(String protectKey, InputStream inputStream, OutputStream outputStream) throws IOException {
        //char[] key = protectKey.toCharArray();
        char[] key = protectKey.toCharArray();
        //int[] iArr = new int[4];
        int[] iArr = new int[4];
        //int i = 1;
        int i = 1;
        //int i2 = i + 1;
        int i2 = i + 1;
        //iArr[0] = key[0] | (key[i] << 16);
        iArr[0] = key[0] | (key[i] << 16);
        //i = i2 + 1;
        i = i2 + 1;
        //char c = key[i2];
        char c = key[i2];
        //i2 = i + 1;
        i2 = i + 1;
        //iArr[1] = c | (key[i] << 16);
        iArr[1] = c | (key[i] << 16);
        //i = i2 + 1;
        i = i2 + 1;
        //c = key[i2];
        c = key[i2];
        //i2 = i + 1;
        i2 = i + 1;
        //iArr[2] = c | (key[i] << 16);
        iArr[2] = c | (key[i] << 16);
        //i = i2 + 1;
        i = i2 + 1;
        //c = key[i2];
        c = key[i2];
        //i2 = i + 1;
        i2 = i + 1;
        //iArr[3] = c | (key[i] << 16);
        iArr[3] = c | (key[i] << 16);
        //int[] iArr2 = new int[2];
        int[] iArr2 = new int[2];
        //i = i2 + 1;
        i = i2 + 1;
        //c = key[i2];
        c = key[i2];
        //i2 = i + 1;
        i2 = i + 1;
        //iArr2[0] = c | (key[i] << 16);
        iArr2[0] = c | (key[i] << 16);
        //iArr2[1] = key[i2] | (key[i2 + 1] << 16);
        iArr2[1] = key[i2] | (key[i2 + 1] << 16);
        //iArr = FxIjsF(iArr);
        iArr = FxIjsF(iArr);
        //byte[] bArr = new byte[8192];
        byte[] bArr = new byte[8192];
        //int i3 = 0;
        int i3 = 0;
        //while (true) {
        while (true) {
            //int read = inputStream.read(bArr);
            int read = inputStream.read(bArr);
            //if (read >= 0) {
            if (read >= 0) {
                //int i4 = i3 + read;
                int i4 = i3 + read;
                //int i5 = 0;
                int i5 = 0;
                //while (i3 < i4) {
                while (i3 < i4) {
                    //int i6 = i3 % 8;
                    int i6 = i3 % 8;
                    //int i7 = i6 / 4;
                    int i7 = i6 / 4;
                    //int i8 = i3 % 4;
                    int i8 = i3 % 4;
                    //bArr[i5] = (byte) (((byte) (iArr2[i7] >> (i8 * 8))) ^ bArr[i5]);
                    bArr[i5] = (byte) (((byte) (iArr2[i7] >> (i8 * 8))) ^ bArr[i5]);
                    //i3++;
                    i3++;
                    //i5++;
                    i5++;
                    //}
                }
                //outputStream.write(bArr, 0, read);
                outputStream.write(bArr, 0, read);
                //} else {
            } else {
                //return;
                return;
                //}
            }
            //}
        }
        //}
    }

    //private static int[] FxIjsF(int[] iArr) {
    private static int[] FxIjsF(int[] iArr) {
        //int[] iArr2 = new int[27];
        int[] iArr2 = new int[27];
        //int i = iArr[0];
        int i = iArr[0];
        //iArr2[0] = i;
        iArr2[0] = i;
        //int[] iArr3 = new int[]{iArr[1], iArr[2], iArr[3]};
        int[] iArr3 = new int[]{iArr[1], iArr[2], iArr[3]};
        //for (int i2 = 0; i2 < 26; i2++) {
        for (int i2 = 0; i2 < 26; i2++) {
            //iArr3[i2 % 3] = (((iArr3[i2 % 3] >>> 8) | (iArr3[i2 % 3] << 24)) + i) ^ i2;
            iArr3[i2 % 3] = (((iArr3[i2 % 3] >>> 8) | (iArr3[i2 % 3] << 24)) + i) ^ i2;
            //i = ((i << 3) | (i >>> 29)) ^ iArr3[i2 % 3];
            i = ((i << 3) | (i >>> 29)) ^ iArr3[i2 % 3];
            //iArr2[i2 + 1] = i;
            iArr2[i2 + 1] = i;
            //}
        }
        //return iArr2;
        return iArr2;
        //}
    }
//}
}
//
  