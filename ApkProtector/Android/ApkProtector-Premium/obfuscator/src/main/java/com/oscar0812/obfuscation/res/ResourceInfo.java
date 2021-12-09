package com.oscar0812.obfuscation.res;

import com.oscar0812.obfuscation.APKInfo;
import com.oscar0812.obfuscation.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;
import java.util.*;


/**
 * This class reads all the res/values-* files and:
 * gets/changes attributes
 * gets unique xml new name (to rename later)
 *
 */
public class ResourceInfo {
    private static ResourceInfo instance = null;

    public static ResourceInfo getInstance() {
        if (instance == null) {
            instance = new ResourceInfo();
        }

        return instance;
    }

    private final HashMap<String, ArrayList<File>> nameNoExtensionToFile = new HashMap<>();

    // old_attr_name -> new_attr_name
    public HashMap<String, String> XMLNameAttrChangeMap = new HashMap<>();

    // xml files in res/values-* folders
    // {"values"=> [arrays.xml, ...], "values-af"=> ["strings.xml"], ...}
    public HashMap<String, ArrayList<File>> valueXMLFiles = new HashMap<>();

    private final HashMap<String, XMLFile> allXMLFiles = new HashMap<>();

    // Map: renameThis->toThis
    private final HashMap<String, String> renameFilesMap = new HashMap<>();
    private final Set<String> takenRenames = new HashSet<>(); // what file names we already used in renaming

    ArrayList<String> permutations = StringUtils.getStringPermutations();
    int permIndex = 0;

    final String PRE = "r";

    private ResourceInfo() {
        fetchFiles();
    }

    public void parseValuesDir() {
        // we need a copy, don't want to mess around with og hashmap
        HashMap<String, ArrayList<File>> nameNoExtensionToFileCopy = new HashMap<>(nameNoExtensionToFile);

        // first parse public.xml as it contains all elements
        XMLFile publicXMLFile = new XMLFile(valueXMLFiles.get("public").get(0).getAbsolutePath());
        valueXMLFiles.remove("public");
        Document document = parseValueXML(publicXMLFile, nameNoExtensionToFileCopy);
        XMLFile.saveXMLFile(publicXMLFile, document);

        // ignore styles and attributes
        valueXMLFiles.remove("attrs");

        // loop through all res/values-* files
        for (String key : valueXMLFiles.keySet()) {
            for (File valueXMLFile : valueXMLFiles.get(key)) {
                document = parseValueXML(valueXMLFile, nameNoExtensionToFileCopy);
                XMLFile.saveXMLFile(valueXMLFile, document);
            }
        }
    }

    private Document parseValueXML(File valueXMLFile, HashMap<String, ArrayList<File>> nameNoExtensionToFileCopy) {
        Document doc = XMLFile.readXMLFile(valueXMLFile);
        assert doc != null;

        Queue<Element> q = new LinkedList<>();
        q.add(doc.getRootElement());

        while (!q.isEmpty()) {
            Element qElement = q.poll();
            if (qElement.getParent() != null && qElement.getParent().getName().equals("style")) {
                // children of style tags are attributes, which we ignore
                continue;
            }

            String name = qElement.attributeValue("name");

            // public.xml has type attr
            String type = qElement.attributeValue("type");

            if (name != null && !name.isEmpty() && !name.startsWith("android:")) {
                String newName = null;
                if (nameNoExtensionToFileCopy.containsKey(name)) {
                    // We are going to rename files, make sure that the new file name is not taken, otherwise we will override files
                    newName = addToRenameMap(name, nameNoExtensionToFileCopy);
                }
                // bug with attributes
                if (type != null && !type.equals("attr")) {
                    // public.xml
                    if (!XMLNameAttrChangeMap.containsKey(name)) {
                        if (newName == null) {
                            newName = PRE + permutations.get(permIndex++);
                        }
                        if (name.contains(".")) {
                            newName = name.substring(0, name.lastIndexOf(".") + 1) + newName;
                        }
                        XMLNameAttrChangeMap.put(name, newName);
                    }
                }

                if (XMLNameAttrChangeMap.containsKey(name)) {
                    qElement.addAttribute("name", XMLNameAttrChangeMap.get(name));
                }
            }
            q.addAll(qElement.elements());
        }

        return doc;
    }

    // rename xml file
    private String addToRenameMap(String name, HashMap<String, ArrayList<File>> nameNoExtensionToFileCopy) {
        // loop through all the files in the same directory as the files you want to rename.
        // choose a name that is not taken
        HashMap<String, File> allChildrenFiles = new HashMap<>();
        for (File renameChild : nameNoExtensionToFileCopy.get(name)) {
            File[] siblingList = renameChild.getParentFile().listFiles();
            if (siblingList != null) {
                for (File sibling : siblingList) {
                    String nameNoExt = sibling.getName();
                    nameNoExt = nameNoExt.substring(0, nameNoExt.indexOf("."));
                    allChildrenFiles.put(nameNoExt, sibling);
                }
            }
        }
        String newName;
        do {
            newName = PRE + permutations.get(permIndex++);
            // loop until its a new file
        } while (allChildrenFiles.containsKey(newName) || takenRenames.contains(newName));

        takenRenames.add(newName);

        for (File renameThis : nameNoExtensionToFileCopy.get(name)) {
            String ext = renameThis.getName();
            ext = ext.substring(ext.indexOf("."));

            File renameToThis = new File(renameThis.getParentFile(), newName + ext);
            renameFilesMap.put(renameThis.getAbsolutePath(), renameToThis.getAbsolutePath());
        }

        nameNoExtensionToFileCopy.remove(name);
        return newName;
    }

    // go into res/ and fetch all files, keep a hashmap of files without extension since xml refers to some files that way
    private void fetchFiles() {
        Queue<File> q = new LinkedList<>();
        q.add(APKInfo.getInstance().getResDir());

        while (!q.isEmpty()) {
            File parent = q.poll(); // retrieve and remove the first element
            File[] files = parent.listFiles();

            if (files == null) {
                continue;
            }

            for (File file : files) {
                if (file.isFile()) {
                    String fileNameNoExt = file.getName();
                    // some files have more than 1 period!
                    fileNameNoExt = fileNameNoExt.substring(0, fileNameNoExt.indexOf(".")); // remove extension

                    if (!nameNoExtensionToFile.containsKey(fileNameNoExt)) {
                        nameNoExtensionToFile.put(fileNameNoExt, new ArrayList<>());
                    }
                    nameNoExtensionToFile.get(fileNameNoExt).add(file);

                    if (file.getName().endsWith(".xml")) {
                        // check if this is an important value xml file
                        String pName = file.getParentFile().getName();
                        if (pName.equals("values") || pName.startsWith("values-")) {
                            if (!valueXMLFiles.containsKey(fileNameNoExt)) {
                                valueXMLFiles.put(fileNameNoExt, new ArrayList<>());
                            }
                            valueXMLFiles.get(fileNameNoExt).add(new XMLFile(file.getAbsolutePath()));
                        }

                        allXMLFiles.put(file.getAbsolutePath(), new XMLFile(file.getAbsolutePath()));
                    }
                } else if (file.isDirectory()) {
                    // found directory
                    q.add(file);
                }
            }
        }
    }

    public HashMap<String, XMLFile> getAllXMLFiles() {
        return allXMLFiles;
    }

    public HashMap<String, String> getXMLNameAttrChangeMap() {
        return XMLNameAttrChangeMap;
    }

    public HashMap<String, String> getRenameFilesMap() {
        return renameFilesMap;
    }
}
