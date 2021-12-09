package mph.trunksku.apps.dexpro.utils;



import java.io.IOException;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class Base64Util {
    public static String decode(String str) {
        try {
            return new String(new BASE64Decoder().decodeBuffer(str.replace("s#ae#s", "\n")));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encode(String str) {
        return new BASE64Encoder().encode(str.getBytes()).replace("\n", "s#ae#s");
    }
}




