package com.oscar0812.obfuscation.smali;

import com.oscar0812.obfuscation.APKInfo;
import com.oscar0812.obfuscation.utils.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SmaliFile extends File {
    // chain the lines
    private SmaliLine firstSmaliLine = null;
    private SmaliLine lastSmaliLine = null;

    private final HashMap<String, ArrayList<SmaliLine>> firstWordSmaliLineMap = new HashMap<>(); // first word->list[SmaliLines]: ".field"->[....], "const-string"->[...]
    private SmaliLine lastDescriptiveComment = null;

    // what lines reference/link/use this file
    private final ArrayList<SmaliLine> referencedInlines = new ArrayList<>();
    private String smaliPackage = "";
    private String smaliClass = "";

    // field lines
    private final ArrayList<SmaliField> fieldList = new ArrayList<>();
    private final HashMap<String, SmaliField> fieldMap = new HashMap<>();

    // file lines (parent - child)
    private final HashMap<String, SmaliFile> childFileMap = new HashMap<>();
    private final HashMap<String, SmaliFile> parentFileMap = new HashMap<>();

    // married share a child file
    private final HashMap<String, SmaliFile> marriedFileMap = new HashMap<>();

    // method blocks
    private final ArrayList<SmaliMethod> methodList = new ArrayList<>();

    // getCurrentTrack() -> SmaliMethod
    private final HashMap<String, SmaliMethod> methodMap = new HashMap<>();

    private final HashMap<String, String> methodNameChange = new HashMap<>();
    private final HashMap<String, String> fieldNameChange = new HashMap<>();

    private final HashMap<String, ArrayList<SmaliLine>> methodReferences = new HashMap<>(); // link method name to lines that reference it
    private final HashMap<String, ArrayList<SmaliLine>> fieldReferences = new HashMap<>();  // link field name to lines that reference it

    // public long debugLine = 50;

    public SmaliFile(String pathname) {
        super(pathname);

        setPackageFromPath();
    }

    public SmaliFile(File parent, String child) {
        super(parent, child);

        setPackageFromPath();
    }

    public ArrayList<SmaliLine> getReferencedInSmaliLines() {
        return referencedInlines;
    }

    private void setPackageFromPath() {
        // get package. Bubble up to known parent
        Stack<File> packageStack = new Stack<>();
        HashMap<String, String> pathToPackage = APKInfo.getInstance().getDirPathToPackage();

        File bubbler = this.getParentFile();
        while (!pathToPackage.containsKey(bubbler.getAbsolutePath())) {
            packageStack.add(bubbler);
            bubbler = bubbler.getParentFile();
        }

        // Bubble down to current file and set the trail of paths
        StringBuilder builder = new StringBuilder(pathToPackage.get(bubbler.getAbsolutePath()));
        while (!packageStack.empty()) {
            bubbler = packageStack.pop();
            builder.append(bubbler.getName()).append("/");
            pathToPackage.put(bubbler.getAbsolutePath(), builder.toString());
        }

        int index = this.getName().lastIndexOf(".smali");
        String withoutExt = this.getName().substring(0, index);

        // set package
        this.setSmaliPackage("L" + builder.toString() + withoutExt + ";");
    }

    public void addReferenceSmaliLine(SmaliLine inLine) {
        referencedInlines.add(inLine);

        inLine.getReferenceSmaliFileList().add(this);
        inLine.getReferenceSmaliFileMap().put(this.getSmaliPackage(), this);

        // check if parent - child
        String firstWord = inLine.getParts()[0];
        if (firstWord.equals(".implements") || firstWord.equals(".super")) {
            this.getChildFileMap().put(inLine.getParentSmaliFile().getAbsolutePath(), inLine.getParentSmaliFile());
            inLine.getParentSmaliFile().getParentFileMap().put(this.getAbsolutePath(), this);
        }
    }

    public void appendString(String text) {
        for (String s : text.split("\\r?\\n|\\r")) { // split text by new line
            appendSmaliLine(new SmaliLine(s, this));
        }
    }

    public String getSmaliPackage() {
        return smaliPackage;
    }

    // setting the package, so override the class
    public void setSmaliPackage(String smaliPackage) {
        this.smaliPackage = smaliPackage; // Lcom/oscar0812/sample_navigation/StringUtil;
        this.smaliClass = smaliPackage.substring(smaliPackage.lastIndexOf("/") + 1, smaliPackage.length() - 1); // StringUtil
    }

    public String getSmaliClass() {
        return smaliClass;
    }

    // THIS method starts the process for this file
    public void processLines() {
        // System.out.println("PROCESSING: "+getAbsolutePath());
        // read file line by line
        try (Scanner scanner = new Scanner(new File(getAbsolutePath()))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                appendSmaliLine(new SmaliLine(line, this));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendSmaliLine(SmaliLine sl) {
        if (firstSmaliLine == null) {
            firstSmaliLine = sl;
        }
        if (lastSmaliLine == null) {
            lastSmaliLine = sl;
        } else {
            lastSmaliLine = lastSmaliLine.insertAfter(sl);
        }

        // link the first word to a list of smali lines
        String firstWord = sl.getParts()[0];
        if (!firstWordSmaliLineMap.containsKey(firstWord))
            this.firstWordSmaliLineMap.put(firstWord, new ArrayList<>());
        firstWordSmaliLineMap.get(firstWord).add(sl);

        // keep a track of comments (for virtual and direct method reference)
        if (sl.isComment()) {
            String text = sl.getTextFromParts();
            if (text.contains("direct methods") || text.contains("virtual methods")) {
                lastDescriptiveComment = sl;
            }
        }
    }

    public void addFieldLine(SmaliLine smaliLine) {
        SmaliField sf = null;
        if (smaliLine.getOldParts() != null) {
            String[] parts = smaliLine.getOldParts();
            // get array index that contains :
            int indexOfField = -1;
            for (int x = 0; x < parts.length; x++) {
                if (parts[x].contains(":")) {
                    indexOfField = x;
                    break;
                }
            }
            assert indexOfField >= 0;


            String identifier = parts[indexOfField].substring(0, parts[indexOfField].indexOf(":"));

            if (fieldMap.containsKey(identifier)) {
                // updating, not adding new
                sf = fieldMap.get(identifier);
                fieldMap.remove(identifier);
            }
        }

        if (sf == null) {
            // new
            sf = new SmaliField(smaliLine);
            fieldList.add(sf);
        }

        fieldMap.put(sf.getIdentifier(), sf);
    }

    public void addMethodLine(SmaliLine smaliLine) {
        String[] parts = smaliLine.getParts();
        if (parts[0].equals(".method")) {

            // start of a method
            SmaliMethod sm = null;
            // relink method if rename
            if(smaliLine.getOldParts() != null && smaliLine.getOldParts()[0].equals(".method")) {
                String last = smaliLine.getOldParts()[smaliLine.getOldParts().length - 1];
                String identifier = last.substring(0, last.lastIndexOf(")") + 1);
                if(methodMap.containsKey(identifier)) {
                    // contains old link, remove it
                    sm = methodMap.get(identifier);
                    methodMap.remove(identifier);
                }
            }

            if(sm == null) {
                sm = new SmaliMethod(this, smaliLine);
                if (lastDescriptiveComment != null) {
                    String text = lastDescriptiveComment.getTextFromParts();
                    sm.setMethodType(text.contains("virtual") ? "virtual" : text.contains("direct") ? "direct" : "");
                }
                methodList.add(sm);
            }

            // update the hashmap, to search for method faster by name
            String id = sm.getIdentifier();
            methodMap.put(id.substring(0, id.indexOf(")") + 1), sm);
        } else if (parts[0].equals(".end") && parts[1].equals("method")) {
            // this line is the end of a method
            SmaliMethod sm = methodList.get(methodList.size() - 1);
            smaliLine.setParentMethod(sm);
            sm.setLastLine(smaliLine);
            // loop from start to finish setting the parentMethod var
            sm.updateChildSmaliLines();
        }
    }

    public void saveToDisk(String absolutePath) {
        try {
            FileWriter writer = new FileWriter(absolutePath, false);

            SmaliLine line = firstSmaliLine;
            while (line != null) {
                if (line.getParts()[0].equals(".method") && line.getPrevSmaliLine() != null
                        && !line.getPrevSmaliLine().isEmpty() && !line.getPrevSmaliLine().isComment()) {
                    // we need a space before .method (bothers me)
                    writer.write("\n");
                }

                writer.write(line.getTextFromParts());
                writer.write("\n");

                line = line.getNextSmaliLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // check all files in the same directory, and rename to something that doesnt exist
    private File getNewFile() {
        File parent = SmaliFile.getCreatedSmaliDir(this);
        ArrayList<String> permutations = StringUtils.getStringPermutations();

        for (String perm : permutations) {
            File newFile = new File(parent, perm + ".smali");
            if (!newFile.exists() && !APKInfo.getInstance().getAllSmaliFileMap().containsKey(newFile.getAbsolutePath())
                    && !APKInfo.getInstance().getNewToOldRenamedFilePathMap().containsKey(newFile.getAbsolutePath())) {
                return newFile;
            }
        }
        return null;
    }

    public void rename() {
        File newFile = getNewFile();
        assert newFile != null;
        SmaliFile renameFileTo = new SmaliFile(newFile.getAbsolutePath());

        // rename references in smali
        ArrayList<SmaliLine> referencedInLines = new ArrayList<>(this.getReferencedInSmaliLines());
        for (SmaliLine smaliLine : referencedInLines) {
            String text = smaliLine.getTextFromParts();
            String newText = text.replace(this.getSmaliPackage(), renameFileTo.getSmaliPackage());
            smaliLine.setText(newText);
        }

        // store the file name change. new -> old, old -> new
        HashMap<String, String> newToOldMap = APKInfo.getInstance().getNewToOldRenamedFilePathMap();
        newToOldMap.put(renameFileTo.getAbsolutePath(), this.getAbsolutePath());

        HashMap<String, String> oldSmaliPackageToNew = APKInfo.getInstance().getOldSmaliPackageToNew();
        oldSmaliPackageToNew.put(this.getSmaliPackage(), renameFileTo.getSmaliPackage());

        // rename references in XML
        if (this.getFirstWordSmaliLineMap().containsKey(".source")) {
            // some classes (like Lcom/google/android/gms/cast/VideoInfo; dont have .source)
            SmaliLine source = this.getFirstWordSmaliLineMap().get(".source").get(0);
            source.setText(".source \"WHY_ARE_YOU_HERE.java\"");
        }
    }

    // return the new name for the parent dir (package rename)
    public static File getCreatedSmaliDir(File inFile) {
        File parentDir = inFile.getParentFile();
        HashMap<String, File> oldToNewSmaliDirMap = APKInfo.getInstance().getOldToNewSmaliDirMap();

        if (oldToNewSmaliDirMap.containsKey(parentDir.getAbsolutePath())) {
            return oldToNewSmaliDirMap.get(parentDir.getAbsolutePath());
        }

        return parentDir;
    }

    public ArrayList<SmaliMethod> getMethodList() {
        return methodList;
    }

    public HashMap<String, SmaliMethod> getMethodMap() {
        return methodMap;
    }

    public ArrayList<SmaliField> getFieldList() {
        return fieldList;
    }

    public HashMap<String, SmaliField> getFieldMap() {
        return fieldMap;
    }

    public HashMap<String, ArrayList<SmaliLine>> getMethodReferences() {
        return methodReferences;
    }

    public HashMap<String, ArrayList<SmaliLine>> getFieldReferences() {
        return fieldReferences;
    }

    public HashMap<String, ArrayList<SmaliLine>> getFirstWordSmaliLineMap() {
        return firstWordSmaliLineMap;
    }

    public HashMap<String, SmaliFile> getChildFileMap() {
        return childFileMap;
    }

    public HashMap<String, SmaliFile> getParentFileMap() {
        return parentFileMap;
    }

    public HashMap<String, String> getMethodNameChange() {
        return methodNameChange;
    }

    public HashMap<String, String> getFieldNameChange() {
        return fieldNameChange;
    }

    public HashMap<String, SmaliFile> getMarriedFileMap() {
        return marriedFileMap;
    }

    public boolean isAbstract() {
        return this.getFirstWordSmaliLineMap().get(".class").get(0).getPartsSet().contains("abstract");
    }
}
