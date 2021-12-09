package mph.trunksku.apps.dexpro.utils;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.text.TextUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import mph.trunksku.apps.dexpro.MyApp;



public class BYProtectUtil {







	public static String readAssetsTxt(String fileName)
    {
        try
        {
            InputStream localInputStream = getStreamFromAssets(fileName);
            byte[] arrayOfByte = new byte[localInputStream.available()];
            localInputStream.read(arrayOfByte);
            localInputStream.close();
            String str = new String(arrayOfByte, "utf-8");
            return str;
        }
        catch (Exception localIOException)
        {
            localIOException.printStackTrace();
        }
        return "";
    }



    public static void copyAssetsFile(String fileName, String desPath) {
        FileUtils.delSingleFile(desPath);
        InputStream in = null;
        OutputStream out = null;
        try {
            in = getStreamFromAssets(fileName);
            out = new FileOutputStream(desPath);
            byte[] bytes = new byte[1024];
            int i;
            while ((i = in.read(bytes)) != -1)
                out.write(bytes, 0 , i);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static InputStream getStreamFromAssets(String fileName)
    {
        try
        {
            InputStream localInputStream = MyApp.app().getAssets().open(getAssetsName(fileName));
//            byte[] data=toByteArray(localInputStream);
//            String  key=SPUtils.getString("by","key");
//            return new ByteArrayInputStream(ByteEncoder.Decrypt(data,key));
            return localInputStream;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public static String getAssetsName(String path){

        if(path.contains("/")) {
            String result = path.subSequence(path.lastIndexOf("/") + 1, path.length()).toString();
            return path.replace(result,encode(result));
        }else{
            return encode(path);
        }

    }


    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

	public static String encode(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

