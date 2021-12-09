package com.oscar0812.obfuscation.res;

import com.oscar0812.obfuscation.APKInfo;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;

public class XMLFile extends File {

    // some attributes should not be changed
    Set<String> ignoredAttributes = new HashSet<>(Arrays.asList("format", "quantity", "type"));
    private Document document;

    public XMLFile(String pathname) {
        super(pathname);
    }

    public XMLFile(File file, String subpath) {
        super(file, subpath);
    }

    public void processLines() {
        HashMap<String, String> publicXMLNameMap = ResourceInfo.getInstance().getXMLNameAttrChangeMap();

        document = readXMLFile(this.getAbsoluteFile());
        assert document != null;
        Element element = document.getRootElement();
        Queue<Element> q = new LinkedList<>();
        q.add(element);

        while (!q.isEmpty()) {
            Element qElement = q.poll();

            // [android:textSize="17.0sp", android:id="@id/timelyView11", ....] <=> [name=value, name=value, ...]
            for (Attribute attr : qElement.attributes()) {
                if (ignoredAttributes.contains(attr.getName())) {
                    continue;
                }
                String attrValue = attr.getValue();

                // can reference smali class in attribute
                if (attrValue.contains(APKInfo.getInstance().getManifestPackageStr())) {
                    HashMap<String, String> oldSmaliPackageToNew = APKInfo.getInstance().getOldSmaliPackageToNew();
                    // com.naman14.timber.TimberApp -> Lcom/naman14/timber/TimberApp;
                    String smaliPackage = "L" + attrValue.replace(".", "/") + ";";
                    if (oldSmaliPackageToNew.containsKey(smaliPackage)) {
                        // this attribute references a smali class
                        // Lcom/naman14/timber/activities/x;
                        String newSmaliPackage = oldSmaliPackageToNew.get(smaliPackage);
                        // com.naman14.timber.activities.x
                        String dotPackage = newSmaliPackage.substring(1, newSmaliPackage.length()-1).replace("/", ".");
                        qElement.addAttribute(attr.getName(), dotPackage);
                    }
                } else {
                    int splitIndex = getSplitIndex(attrValue);
                    if (splitIndex > 0) {
                        String checkThis = attrValue.substring(splitIndex);
                        if (publicXMLNameMap.containsKey(checkThis) && !publicXMLNameMap.get(checkThis).isEmpty()) {
                            // can rename this
                            String newAttrValue = attrValue.substring(0, splitIndex) + publicXMLNameMap.get(checkThis);
                            qElement.addAttribute(attr.getName(), newAttrValue);
                        }
                    }
                }
            }

            // can reference in text, such as @id/something
            int splitIndex = getSplitIndex(qElement.getText());
            if (splitIndex > 0) {
                String checkThis = qElement.getText().substring(splitIndex);
                if (publicXMLNameMap.containsKey(checkThis) && !publicXMLNameMap.get(checkThis).isEmpty()) {
                    String newText = qElement.getText().substring(0, splitIndex) + publicXMLNameMap.get(checkThis);
                    qElement.setText(newText);
                }
            }

            // can reference smali classes in name tag <class.name.here ...>
            if(qElement.getName().contains(APKInfo.getInstance().getManifestPackageStr())) {
                HashMap<String, String> oldSmaliPackageToNew = APKInfo.getInstance().getOldSmaliPackageToNew();
                // com.naman14.timber.TimberApp -> Lcom/naman14/timber/TimberApp;
                String smaliPackage = "L" + qElement.getName().replace(".", "/") + ";";
                if (oldSmaliPackageToNew.containsKey(smaliPackage)) {
                    // this attribute references a smali class
                    // Lcom/naman14/timber/activities/x;
                    String newSmaliPackage = oldSmaliPackageToNew.get(smaliPackage);
                    // com.naman14.timber.activities.x
                    String dotPackage = newSmaliPackage.substring(1, newSmaliPackage.length()-1).replace("/", ".");
                    qElement.setName(dotPackage);
                }
            }

            q.addAll(qElement.elements());
        }
    }

    public void saveToDisk() {
        saveXMLFile(this, getDocument());
    }

    public static Document readXMLFile(File XMLFile) {
        try {
            return new SAXReader().read(XMLFile);
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveXMLFile(File file, Document document) {
        XMLWriter xmlWriter;
        try {
            xmlWriter = new XMLWriter(new FileOutputStream(file), OutputFormat.createPrettyPrint());
            xmlWriter.write(document);
            xmlWriter.close(); // close the file or we CANT RENAME
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // if text=@id/something -> return index of / + 1
    private int getSplitIndex(String text) {
        if (text.equals("@null")) {
            return -1;
        }

        // if (text.startsWith("@") && !text.startsWith("@android:")) {
        if (text.startsWith("@") && !text.contains("android:")) {
            // @id/something
            if (!text.startsWith("@attr/")) {
                // ignoring styles and attributes for now
                return text.indexOf("/") + 1;
            }
        } else if (text.startsWith("?") && !text.startsWith("?android:")) {
            // ?something
            // return 1;
            return -1;
        } else if (text.length() > 0) {
            if (!Character.isLetterOrDigit(text.charAt(0)) && !text.startsWith("#") && text.startsWith("-") && text.startsWith("\\")) {
                // doesn't start with alphanum, check
                System.out.println("UNKNOWN ATTR: " + text);
            }
        }

        return -1;
    }

    public Document getDocument() {
        return document;
    }
}