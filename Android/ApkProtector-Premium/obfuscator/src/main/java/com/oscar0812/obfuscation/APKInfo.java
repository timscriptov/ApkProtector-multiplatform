package com.oscar0812.obfuscation;

import com.oscar0812.obfuscation.res.ResourceInfo;
import com.oscar0812.obfuscation.smali.SmaliFile;
import com.oscar0812.obfuscation.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * Single DEX android applications have a method number limit of 65,536
 */
public class APKInfo {
    private static String apkPath;
    private final File apkFile;
    // dir where .apk resides
    private final File apkParentDir;

    private File apkDecompileDir;
    private File smaliDir, resDir, mainProjectDir;

    private final HashMap<String, SmaliFile> RFileMap = new HashMap<>();
    private final HashMap<String, SmaliFile> allSmaliFileMap = new HashMap<>();
    private final HashMap<String, SmaliFile> projectSmaliFileMap = new HashMap<>();
    private final HashMap<String, SmaliFile> createdSmaliFileMap = new HashMap<>();

    private final ArrayList<File> manifestAppFileList = new ArrayList<>();

    // keep track of the smali directories inside smali/
    private final HashMap<String, File> oldToNewSmaliDirMap = new HashMap<>();

    private String manifestPackageStr;

    private final HashMap<String, String> dirPathToPackage = new HashMap<>();

    // store the new file name -> old file name, after rename()
    private final HashMap<String, String> newToOldRenamedFilePathMap = new HashMap<>();
    private final HashMap<String, String> oldSmaliPackageToNew = new HashMap<>();

    private static APKInfo instance = null;

    public static APKInfo getInstance() {
        if (instance == null) {
            instance = new APKInfo();
        }
        return instance;
    }

    public static void setAPKPath(String inPath) {
        apkPath = inPath;
        instance = new APKInfo();
    }

    private APKInfo() {
        apkFile = new File(apkPath);
        apkParentDir = apkFile.getParentFile();

        if (!apkFile.exists()) {
            System.out.println("APK file doesn't exist");
        } else {
            // remove the .apk and make it a directory for output (apktool write)
            apkDecompileDir = new File(apkFile.getAbsolutePath().substring(0, apkFile.getAbsolutePath().lastIndexOf('.')));

            // TODO: what about apks with smali/ AND smali_classes2/
            smaliDir = new File(apkDecompileDir, "smali");
            dirPathToPackage.put(smaliDir.getAbsolutePath(), ""); // base package

            resDir = new File(apkDecompileDir, "res");
        }

    }

    public File getApkParentDir() {
        return apkParentDir;
    }

    public File getApkFile() {
        return apkFile;
    }

    public File getApkDecompileDir() {
        return apkDecompileDir;
    }

    public File getSmaliDir() {
        return smaliDir;
    }

    public File getResDir() {
        return resDir;
    }

    public HashMap<String, SmaliFile> getAllSmaliFileMap() {
        return allSmaliFileMap;
    }

    public HashMap<String, SmaliFile> getProjectSmaliFileMap() {
        return projectSmaliFileMap;
    }

    public HashMap<String, SmaliFile> getCreatedSmaliFileMap() {
        return createdSmaliFileMap;
    }

    public void addSmaliFile(SmaliFile smaliFile) {
        // quick access through path
        if (!smaliFile.exists()) {
            createdSmaliFileMap.put(smaliFile.getAbsolutePath(), smaliFile);
        }
        this.allSmaliFileMap.put(smaliFile.getAbsolutePath(), smaliFile);
    }

    public void fetchDecompiledInfo() {
        manifestFileInfo();
        ResourceInfo.getInstance(); // start a resource info instance
        fetchRSmaliFiles();
        fetchProjectSmaliFiles();
    }

    // get android info from android manifest
    private void manifestFileInfo() {
        File manifestFile = new File(apkDecompileDir, "AndroidManifest.xml");
        Document document;
        try {
            document = new SAXReader().read(manifestFile);
        } catch (DocumentException e) {
            e.printStackTrace();
            return;
        }

        assert document != null;
        Element manifestTag = document.getRootElement();
        this.manifestPackageStr = manifestTag.attributeValue("package");

        Set<String> visitedFiles = new HashSet<>();

        for (Element manifestChildren : manifestTag.elements()) {
            if (manifestChildren.getName().equals("application")) {
                for (Element applicationChildren : manifestChildren.elements()) {
                    // application children (activity, receiver, service, ...)
                    String attrValue = applicationChildren.attributeValue("name"); // com.oscar0812.sample_navigation.MainActivity

                    String sf = attrValue.replace(".", File.separator) + ".smali"; // com\oscar0812\sample_navigation\MainActivity.smali

                    File file = new File(smaliDir, sf);
                    if (!visitedFiles.contains(file.getAbsolutePath())) {
                        if (file.exists()) {
                            manifestAppFileList.add(file);
                        }
                        visitedFiles.add(file.getAbsolutePath());
                    }

                }
            }
        }
    }

    // get R.smali files in ALL directories under smali/
    private void fetchRSmaliFiles() {
        Queue<File> q = new LinkedList<>();
        q.add(smaliDir);

        while (!q.isEmpty()) {
            File parent = q.poll(); // retrieve and remove the first element
            File[] files = parent.listFiles();

            if (files == null) {
                continue;
            }

            for (File childFile : files) {
                if (childFile.isFile()) {
                    String name = childFile.getName();
                    if (name.endsWith(".smali")) {
                        SmaliFile smaliFile = new SmaliFile(childFile.getAbsolutePath());
                        allSmaliFileMap.put(childFile.getAbsolutePath(), smaliFile);

                        if (name.equals("R.smali") || name.startsWith("R$")) {
                            // append this smali childFile
                            File r = new File(parent, "R.smali");
                            File rID = new File(parent, "R$id.smali");

                            if (r.exists() && rID.exists()) {
                                // this is an R file
                                RFileMap.put(smaliFile.getAbsolutePath(), smaliFile);
                            }
                        }
                    }
                } else if (childFile.isDirectory()) {
                    // found directory
                    q.add(childFile);
                }
            }
        }
    }

    private File fetchProjectMainDir() {
        // ok got the main files, now search for R.smali, that should tell us what the root directory of the apk is
        HashMap<String, ArrayList<SmaliFile>> dirToRFiles = new HashMap<>();
        String packageToDir = manifestPackageStr.replace(".", File.separator);
        for (SmaliFile rSmaliFile : this.getRFileMap().values()) {
            if (!rSmaliFile.getAbsolutePath().contains(packageToDir)) {
                continue;
            }

            File rParent = rSmaliFile.getParentFile();
            if (!dirToRFiles.containsKey(rParent.getAbsolutePath())) {
                dirToRFiles.put(rParent.getAbsolutePath(), new ArrayList<>());
            }
            dirToRFiles.get(rParent.getAbsolutePath()).add(rSmaliFile);
        }

        for (File manifestFile : manifestAppFileList) {
            File bubbler = manifestFile;
            while (!bubbler.getAbsolutePath().equals(smaliDir.getAbsolutePath())) {
                if (dirToRFiles.containsKey(bubbler.getAbsolutePath())) {
                    return bubbler;
                }
                bubbler = bubbler.getParentFile();
            }
        }
        return null;
    }

    static void ensureFoldersExist(File folder) {
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                ensureFoldersExist(folder.getParentFile());
            }
        }
    }

    private void fetchProjectSmaliFiles() {
        mainProjectDir = fetchProjectMainDir();
        assert mainProjectDir != null;

        // got R.smali, now get all files in smali/main_package directory
        // meh recursion, use queue
        Queue<File> q = new LinkedList<>();
        q.add(mainProjectDir);

        while (!q.isEmpty()) {
            File parent = q.poll(); // retrieve and remove the first element
            File[] listFiles = parent.listFiles();

            if (listFiles == null || listFiles.length == 0) {
                continue;
            }

            for (File file : listFiles) {
                if (file.isFile()) {
                    if (allSmaliFileMap.containsKey(file.getAbsolutePath())) {
                        projectSmaliFileMap.put(file.getAbsolutePath(), allSmaliFileMap.get(file.getAbsolutePath()));
                    }
                } else if (file.isDirectory()) {
                    // found directory

                    File newParent = parent;
                    // to rename packages we will first create the directories and set a link between the old
                    // dir and the new one
                    if (oldToNewSmaliDirMap.containsKey(file.getParentFile().getAbsolutePath())) {
                        // contains the parent
                        newParent = oldToNewSmaliDirMap.get(file.getParentFile().getAbsolutePath());
                    }

                    for (String perm : StringUtils.getStringPermutations()) {
                        File newDir = new File(newParent, perm);
                        if (!newDir.exists()) {
                            ensureFoldersExist(newDir);
                            oldToNewSmaliDirMap.put(file.getAbsolutePath(), newDir);
                            break;
                        }
                    }
                    q.add(file);
                }
            }
        }
    }

    public String getManifestPackageStr() {
        return manifestPackageStr;
    }

    public File getMainProjectDir() {
        return mainProjectDir;
    }

    public HashMap<String, SmaliFile> getRFileMap() {
        return RFileMap;
    }

    public HashMap<String, String> getDirPathToPackage() {
        return dirPathToPackage;
    }

    public HashMap<String, String> getNewToOldRenamedFilePathMap() {
        return newToOldRenamedFilePathMap;
    }

    public HashMap<String, String> getOldSmaliPackageToNew() {
        return oldSmaliPackageToNew;
    }

    public HashMap<String, File> getOldToNewSmaliDirMap() {
        return oldToNewSmaliDirMap;
    }
}