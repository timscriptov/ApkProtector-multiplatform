package com.oscar0812.obfuscation.smali;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.*;
import java.util.stream.Collectors;

public class SmaliMethod implements SmaliBlock{
    private final SmaliFile parentFile;

    private SmaliLine firstSmaliLine; // .method ...
    private SmaliLine lastSmaliLine = null; // .end method ...

    private String methodName;
    private String methodParameterStr;
    private String[] methodParameterArr; // how to hold parameters? just like this for now
    private String methodReturnType;

    // onCreate(Landroid/os/Bundle;)
    private String identifier = null;

    String methodType = "direct"; // direct or virtual or idk what else

    // parentFile is the file which this method resides: onCreate()'s parentFile is MainActivity.smali
    // firstLine: .method protected onCreate(Landroid/os/Bundle;)V
    public SmaliMethod(SmaliFile parentFile, SmaliLine firstLine) {
        this.parentFile = parentFile;
        setFirstSmaliLine(firstLine);
    }

    public void setFirstSmaliLine(SmaliLine firstSmaliLine) {
        this.firstSmaliLine = firstSmaliLine;
        this.firstSmaliLine.setParentMethod(this);

        String[] parts = firstSmaliLine.getParts();

        // onCreate(Landroid/os/Bundle;)V
        String last = parts[parts.length - 1];
        this.identifier = last.substring(0, last.lastIndexOf(")") + 1);

        // onCreate
        this.methodName = last.substring(0, last.indexOf("("));

        // get string in (...)
        int firstBracket = last.indexOf('(');
        // Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;
        this.methodParameterStr = last.substring(firstBracket + 1, last.indexOf(')', firstBracket));
        // ["Landroid/view/LayoutInflater", "Landroid/view/ViewGroup", "Landroid/os/Bundle"]
        this.methodParameterArr = this.methodParameterStr.split(";");

        // the return is after the )
        // Landroid/view/View
        this.methodReturnType = last.substring(last.indexOf(")") + 1);
    }

    @Override
    public SmaliFile getParentSmaliFile() {
        return parentFile;
    }

    @Override
    public String appendAfterID() {
        return "(" + this.methodParameterStr + ")";
    }

    @Override
    public HashMap<String, String> getNameChangeMap(SmaliFile smaliFile) {
        return smaliFile.getMethodNameChange();
    }

    // return the new identifier

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void rename() {
        String oldMethodID = this.getIdentifier();
        String newMethodID;
        HashMap<String, String> nameChanges = parentNameChanges();

        if (nameChanges.containsKey(this.getIdentifier())) {
            // parent already renamed this
            newMethodID = nameChanges.get(this.getIdentifier());
        } else if (!this.canRename()) {
            // don't rename these
            return;
        } else {
            // get new method name
            newMethodID = getAvailableID(nameChanges);
        }

        assert newMethodID != null;

        String[] parts = this.getFirstSmaliLine().getParts().clone();
        parts[parts.length-1] = newMethodID + this.getMethodReturnType();

        // whitespace followed by parts
        String builder = Arrays.stream(parts).map(part -> part + " ")
                .collect(Collectors.joining("", this.getFirstSmaliLine().getWhitespace(), ""));

        this.getParentSmaliFile().getMethodNameChange().put(oldMethodID, newMethodID);

        String last = parts[parts.length - 1];
        this.identifier = last.substring(0, last.lastIndexOf(")") + 1); // onCreate()
        this.methodName = last.substring(0, last.indexOf("(")); // onCreate
        // rename method start line
        // TODO
        //this.getFirstSmaliLine().setText(builder.stripTrailing());
        this.getFirstSmaliLine().setText(builder.trim());

        relinkAfterRename(oldMethodID, newMethodID);
    }

    // re-link all the lines pointing to this method
    private void relinkAfterRename(String oldMethodID, String newMethodID) {
        HashMap<String, ArrayList<SmaliLine>> references = this.getParentSmaliFile().getMethodReferences();
        for (SmaliFile childFile : this.getParentSmaliFile().getChildFileMap().values()) {
            if(!childFile.getMethodMap().containsKey(oldMethodID) && childFile.getMethodReferences().containsKey(oldMethodID)) {
                // child has references to this method, but doesn't contain the method!
                if(!references.containsKey(oldMethodID)) {
                    references.put(oldMethodID, new ArrayList<>());
                }
                references.get(oldMethodID).addAll(childFile.getMethodReferences().get(oldMethodID));
            }
        }

        if (references.containsKey(oldMethodID)) {
            ArrayList<SmaliLine> copyOfReferences = new ArrayList<>(references.get(oldMethodID));
            for (SmaliLine smaliLine : copyOfReferences) {
                String replaceThis = "->" + oldMethodID + methodReturnType;
                String newText = "->" + newMethodID + methodReturnType;
                String text = smaliLine.getTextFromParts();
                smaliLine.setText(text.replace(replaceThis, newText));
            }

            // TODO: removing references here causes errors
            /*for (SmaliLine smaliLine : copyOfReferences) {
                smaliLine.getParentSmaliFile().getMethodReferences().remove(oldMethodID);
            }*/
        }
    }

    public void setLastLine(SmaliLine lastSmaliLine) {
        this.lastSmaliLine = lastSmaliLine;
        updateChildSmaliLines();

    }

    // call this when child smali lines change (to set the parent method)
    public void updateChildSmaliLines() {
        SmaliLine runner = firstSmaliLine;
        while (runner != null && !runner.equals(lastSmaliLine)) {
            runner.setParentMethod(this);
            runner = runner.getNextSmaliLine();
        }
        if (runner != null) {
            runner.setParentMethod(this);
        }
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getMethodType() {
        return methodType;
    }

    public boolean isDirect() {
        return this.methodType.equals("direct");
    }

    public boolean isVirtual() {
        return this.methodType.equals("virtual");
    }

    public SmaliLine getFirstSmaliLine() {
        return firstSmaliLine;
    }

    public SmaliLine getLastSmaliLine() {
        return lastSmaliLine;
    }

    public boolean isConstructor() {
        return this.getFirstSmaliLine().getPartsSet().contains("constructor");
    }

    public boolean isSynthetic() {
        return this.getFirstSmaliLine().getPartsSet().contains("synthetic");
    }

    public boolean isAbstract() {
        return this.getFirstSmaliLine().getPartsSet().contains("abstract") && this.getParentSmaliFile().isAbstract();
    }

    public boolean canRename() {
        // TODO: make renaming virtual methods possible:
        // renaming parent - child functions messes up stuff, but how do I check all parents???
        return (!isConstructor() && !isSynthetic() && !isVirtual()); //|| isAbstract();
    }

    public String getMethodName() {
        return methodName;
    }

    public String[] getMethodParameterArr() {
        return methodParameterArr;
    }

    public String getMethodReturnType() {
        return methodReturnType;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "SmaliMethod{" +
                ", parentFile=" + parentFile +
                ", firstSmaliLine=" + firstSmaliLine +
                ", lastSmaliLine=" + lastSmaliLine +
                ", methodName='" + methodName + '\'' +
                ", methodParameterStr='" + methodParameterStr + '\'' +
                ", methodParameterArr=" + Arrays.toString(methodParameterArr) +
                ", methodReturnType='" + methodReturnType + '\'' +
                ", methodIdentifier='" + identifier + '\'' +
                '}';
    }
}
