package com.oscar0812.obfuscation;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;

public class TestStrObf {
    private final static HashMap<String, String> map = new HashMap<>();
    public static String callMethod(String methodName) {
        try {
            // reflection can get really slow, so cache
            if(map.containsKey(methodName)) {
                return map.get(methodName);
            }
            String sVar = (String) Class.forName("com.oscar0812.obfuscation.TestStrObf").getMethod(methodName).invoke(null);
            map.put(methodName, sVar);
            return sVar;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return "";
        }
    }

    public static String a() {
        float f = 0.1f;
        float f2 = 0.2f;
        float f3 = -0.005f;
        if(f > 0.3f - f2) {
            return "great";
        }
        return "not great";
    }


    public static void main(String[] args) {
        /*
        for(int x = 0; x<100; x++) {
            StringBuilder builder = new StringBuilder();
            for(int y = 0; y<=x; y++) {
                builder.append(StringUtils.getRandomUniqueString());
            }
            System.out.println("String "+StringUtils.getRandomUniqueString()+" = \""+builder.toString()+"\";");
        }
        */

        String myString = "-445c28f6";
        Long i2 = Long.parseLong(myString, 16);
        Float f2 = Float.intBitsToFloat(i2.intValue());
        System.out.println(f2);
        System.out.println(Integer.toHexString(Float.floatToIntBits(f2)));


        if(1 > 0) {
            return;
        }





        String a = a();

        // i is the index. i=24
        // tr is random.                                                00000011001000101000001010100001,   52593313_10
        // f is random from (1, 24)                                     00000000000000000000000000001110,   14_10

        // a1 = (0xff << f) =>          255 << 14                       00000000001111111100000000000000,   4177920_10
        // 0000000011111111 << 14 =>    "left shift 255 by 14"          00000000001111111100000000000000,   4177920_10

        // a1n = ~a =>                  "Complement a", -(a+1)
        // a1n = ~4177920 =>            (4177920 + 1) = -4177921,       11111111110000000011111111111111,  -4177921_10

        // byte = 116, char=t=>         "byte is ascii code of char"    00000000000000000000000001110100,   116_10
        // b1 = b[i] << f               "left shift 116 by 14"          00000000000111010000000000000000,   1900544_10

        // c1 = tr & a1n;                "large random tr AND ~a"       00000011000000000000001010100001,   50332321_10

        // t = c1 | b1;                 "c1 OR b1"                      00000011000111010000001010100001,   52232865_10

        // buf[i] = (byte) (t >>> f);  "Unsigned right shift"           00000000000000000000000001110100,   116_10

        // pretty much shift bytes by f to the right (24 max shift since 32-8 = 24), and mess around with anything around those bytes.
        // for example, f=6, r=random bit, rrrrrrrrrrrrrrrrrr00000000rrrrrr
        // r can change to whatever as long as you don't touch the 0's
        // then override 0's with the char bytes, then shift r to the left
        // only get the last 8, all the other are random and don't matter

        System.out.println(a);
        args = new String[]{"Action"};
        Random r = new Random(System.currentTimeMillis());
        byte[] b = args[0].getBytes();
        int c = b.length;
        PrintStream o = System.out;

        o.println("(new Object() {");
        o.println("\tint t;");
        o.println("\tpublic String toString() {");
        o.print("\t\tbyte[] buf = new byte[");
        o.print(c);
        o.println("];");

        for (int i = 0; i < c; ++i) {
            int tr = r.nextInt();
            int f = r.nextInt(24) + 1; // >>>> max is 32 bits for some reason

            int a1, a1n, b1, c1, t;
            a1 = (0xff << f);
            a1n = ~a1;
            b1 = b[i] << f;
            c1 = tr & a1n;

            t = c1 | b1;

            // System.out.printf("\t\t// f=%d, ff=%d, a1=%d, a1n=%d, byte=%d, char=%s, b1=%d, tr=%d, t=%d, c1=%d\n", f, 0xff, a1, a1n, b[i], args[0].charAt(i), b1, tr, t, c1);
            // System.out.printf("\t\t// f=%s, ff=%s, a1=%s, a1n=%s, byte=%s, char=%s\n\t\t// b1=%s, tr=%s, t=%s, c1=%s\n", Integer.toBinaryString(f), Integer.toBinaryString(0xff), Integer.toBinaryString(a1), Integer.toBinaryString(a1n), Integer.toBinaryString(b[i]), args[0].charAt(i), Integer.toBinaryString(b1), Integer.toBinaryString(tr), Integer.toBinaryString(t), Integer.toBinaryString(c1));
            // System.out.printf("\t\t// %d_10, %s, %d_10, %s_2\n", (t>>>f), Integer.toBinaryString (t>>>f), (byte)(t>>>f), Integer.toBinaryString((byte)(t>>>f)));

            o.print("\t\tt = ");
            o.print(t);
            o.println(";");
            o.print("\t\tbuf[");
            o.print(i);
            o.print("] = (byte) (t >>> ");
            o.print(f);
            o.println(");");
        }

        o.println("\t\treturn new String(buf);");
        o.println("\t}\n}.toString())");
        o.println();


    }
}
