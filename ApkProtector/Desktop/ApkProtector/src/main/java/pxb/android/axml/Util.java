package pxb.android.axml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Util {
    public static byte[] readFile(File in) throws IOException {
        InputStream is = new FileInputStream(in);
        byte[] xml = new byte[is.available()];
        is.read(xml);
        is.close();
        return xml;
    }

    public static byte[] readIs(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        copy(is, os);
        return os.toByteArray();
    }

    public static void writeFile(byte[] data, File out) throws IOException {
        FileOutputStream fos = new FileOutputStream(out);
        fos.write(data);
        fos.close();
    }

    public static Map<String, String> readProguardConfig(File config) throws IOException {
        Map<String, String> clzMap = new HashMap<String, String>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(config), StandardCharsets.UTF_8))) {
            for (String ln = r.readLine(); ln != null; ln = r.readLine()) {
                if (ln.startsWith("#") || ln.startsWith(" ")) {
                    continue;
                }
                // format a.pt.Main -> a.a.a:
                int i = ln.indexOf("->");
                if (i > 0) {
                    clzMap.put(ln.substring(0, i).trim(), ln.substring(i + 2, ln.length() - 1).trim());
                }
            }
        }
        return clzMap;
    }

    public static void copy(InputStream is, OutputStream os) throws IOException {
        byte[] xml = new byte[10 * 1024];
        for (int c = is.read(xml); c > 0; c = is.read(xml)) {
            os.write(xml, 0, c);
        }
    }
}