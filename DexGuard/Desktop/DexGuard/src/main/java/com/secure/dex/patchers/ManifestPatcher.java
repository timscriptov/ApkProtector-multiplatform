package com.secure.dex.patchers;

import com.secure.dex.data.Constants;
import com.secure.dex.utils.ManifestModify;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class ManifestPatcher {
    public static boolean customApplication = false;
    public static String customApplicationName = "";
    public static String packageName = "";

    public static void parseManifest() throws IOException {
        FileInputStream fis = new FileInputStream(Constants.MANIFEST_PATH);

        byte[] data = IOUtils.toByteArray(fis);
        ManifestModify mm = new ManifestModify(Constants.PACKAGE_NAME + "." + Constants.PROXY_APP);
        FileUtils.writeByteArrayToFile(new File(Constants.MANIFEST_PATH), mm.modifyAxml(data));
    }
}
