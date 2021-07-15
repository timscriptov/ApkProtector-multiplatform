/* Orginal file: ZipUtil.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.multidex*/
package com.mcal.apkprotector.multidex;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

//

//final class ZipUtil {
final class ZipUtil {
    //

    //private static final int ENDHDR = 22;
    private static final int ENDHDR = 22;
    //private static final int ENDSIG = 0x6054b50;
    private static final int ENDSIG = 0x6054b50;
    //

    //private static final int BUFFER_SIZE = 0x4000;
    private static final int BUFFER_SIZE = 0x4000;

    //

    //static long getZipCrc(File apk) throws IOException {
    static long getZipCrc(File apk) throws IOException {
        //RandomAccessFile raf = new RandomAccessFile(apk, "r");
        RandomAccessFile raf = new RandomAccessFile(apk, llIIlJl(147));
        //try {
        try {
            //CentralDirectory dir = findCentralDirectory(raf);
            CentralDirectory dir = findCentralDirectory(raf);
            //return computeCrcOfCentralDir(raf, dir);
            return computeCrcOfCentralDir(raf, dir);
            //} finally {
        } finally {
            //raf.close();
            raf.close();
            //}
        }
        //}
    }

    //

    //static CentralDirectory findCentralDirectory(RandomAccessFile raf) throws IOException,
    static CentralDirectory findCentralDirectory(RandomAccessFile raf) throws IOException
    //ZipException {
    {
        //long scanOffset = raf.length() - ENDHDR;
        long scanOffset = raf.length() - ENDHDR;
        //if (scanOffset < 0) {
        if (scanOffset < 0) {
            //throw new ZipException("File too short to be a zip file: " + raf.length());
            throw new ZipException(llIIlJl(251) + raf.length());
            //}
        }
        //long stopOffset = scanOffset - 0x10000 ;
        long stopOffset = scanOffset - 0x10000;
        //if (stopOffset < 0) {
        if (stopOffset < 0) {
            //stopOffset = 0;
            stopOffset = 0;
            //}
        }
        //int endSig = Integer.reverseBytes(ENDSIG);
        int endSig = Integer.reverseBytes(ENDSIG);
        //while (true) {
        while (true) {
            //raf.seek(scanOffset);
            raf.seek(scanOffset);
            //if (raf.readInt() == endSig) {
            if (raf.readInt() == endSig) {
                //break;
                break;
                //}
            }
            //scanOffset--;
            scanOffset--;
            //if (scanOffset < stopOffset) {
            if (scanOffset < stopOffset) {
                //throw new ZipException("End Of Central Directory signature not found");
                throw new ZipException(llIIlJl(375));
                //}
            }
            //}
        }
        //

        //

        //

        //

        //raf.skipBytes(2); 
        raf.skipBytes(2);
        //raf.skipBytes(2); 
        raf.skipBytes(2);
        //raf.skipBytes(2); 
        raf.skipBytes(2);
        //raf.skipBytes(2); 
        raf.skipBytes(2);
        //CentralDirectory dir = new CentralDirectory();
        CentralDirectory dir = new CentralDirectory();
        //dir.size = Integer.reverseBytes(raf.readInt()) & 0xFFFFFFFFL;
        dir.size = Integer.reverseBytes(raf.readInt()) & 0xFFFFFFFFL;
        //dir.offset = Integer.reverseBytes(raf.readInt()) & 0xFFFFFFFFL;
        dir.offset = Integer.reverseBytes(raf.readInt()) & 0xFFFFFFFFL;
        //return dir;
        return dir;
        //}
    }

    //

    //static long computeCrcOfCentralDir(RandomAccessFile raf, CentralDirectory dir)
    static long computeCrcOfCentralDir(RandomAccessFile raf, CentralDirectory dir)
    //throws IOException {
            throws IOException {
        //CRC32 crc = new CRC32();
        CRC32 crc = new CRC32();
        //long stillToRead = dir.size;
        long stillToRead = dir.size;
        //raf.seek(dir.offset);
        raf.seek(dir.offset);
        //int length = (int) Math.min(BUFFER_SIZE, stillToRead);
        int length = (int) Math.min(BUFFER_SIZE, stillToRead);
        //byte[] buffer = new byte[BUFFER_SIZE];
        byte[] buffer = new byte[BUFFER_SIZE];
        //length = raf.read(buffer, 0, length);
        length = raf.read(buffer, 0, length);
        //while (length != -1) {
        while (length != -1) {
            //crc.update(buffer, 0, length);
            crc.update(buffer, 0, length);
            //stillToRead -= length;
            stillToRead -= length;
            //if (stillToRead == 0) {
            if (stillToRead == 0) {
                //break;
                break;
                //}
            }
            //length = (int) Math.min(BUFFER_SIZE, stillToRead);
            length = (int) Math.min(BUFFER_SIZE, stillToRead);
            //length = raf.read(buffer, 0, length);
            length = raf.read(buffer, 0, length);
            //}
        }
        //return crc.getValue();
        return crc.getValue();
        //}
    }

    static String llIIlJl(int llIIlJ) {
        byte[] llIIlI1 = null;
        try {
            if (llIIlJ == -1) {
                if (llIIlJ == -2) {
                } else if (llIIlJ == -3) {
                } else if (llIIlJ == -4) {
                }
            }
            if (llIIlJ == 147) {
                llIIlI1 = new byte[]{-31};

                for (int llIIlJI = 0; llIIlJI < llIIlI1.length; llIIlJI++) {
                    llIIlI1[llIIlJI] = (byte) (llIIlI1[llIIlJI] ^ llIIlJ);
                }
                {
                    return new String(llIIlI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ == 251) {
                llIIlI1 = new byte[]{-67, -110, -105, -98, -37, -113, -108, -108, -37, -120, -109, -108, -119, -113, -37, -113, -108, -37, -103, -98, -37, -102, -37, -127, -110, -117, -37, -99, -110, -105, -98, -63, -37};

                for (int llIIlJI = 0; llIIlJI < llIIlI1.length; llIIlJI++) {
                    llIIlI1[llIIlJI] = (byte) (llIIlI1[llIIlJI] ^ llIIlJ);
                }
                {
                    return new String(llIIlI1, StandardCharsets.UTF_8);
                }
            }
            if (llIIlJ == 375) {
                llIIlI1 = new byte[]{50, 25, 19, 87, 56, 17, 87, 52, 18, 25, 3, 5, 22, 27, 87, 51, 30, 5, 18, 20, 3, 24, 5, 14, 87, 4, 30, 16, 25, 22, 3, 2, 5, 18, 87, 25, 24, 3, 87, 17, 24, 2, 25, 19};

                for (int llIIlJI = 0; llIIlJI < llIIlI1.length; llIIlJI++) {
                    llIIlI1[llIIlJI] = (byte) (llIIlI1[llIIlJI] ^ llIIlJ);
                }
                {
                    return new String(llIIlI1, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }/*end*/
//}

    //static class CentralDirectory {
    static class CentralDirectory {
        //long offset;
        long offset;
        //long size;
        long size;
        //}
    }
}//
  	