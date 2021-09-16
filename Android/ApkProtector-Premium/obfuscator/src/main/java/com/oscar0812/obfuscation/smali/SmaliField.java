package com.oscar0812.obfuscation.smali;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.oscar0812.obfuscation.APKInfo;

import java.util.*;
import java.util.stream.Collectors;

/***
 #static fields
 .field <access rights> static [modification keyword]<field name>:<field type> [= value]

 #instance fields
 .field <access rights> [modification keyword]<field name>:<field type> [= value]
 */

public class SmaliField implements SmaliBlock{
    private SmaliLine smaliLine;

    private String identifier; // field name
    private String fieldType;
    private int indexOfField = -1;  // <field name>:<field type>
    private String value = null;

    public SmaliField(SmaliLine smaliLine) {
        setSmaliLine(smaliLine);
    }

    public void setSmaliLine(SmaliLine smaliLine) {
        this.smaliLine = smaliLine;
        String[] parts = smaliLine.getParts();

        for (int x = 0; x < parts.length; x++) {
            if (parts[x].contains(":")) {
                indexOfField = x;
                break;
            }
        }

        assert indexOfField >= 0;
        int cIndex = parts[indexOfField].indexOf(":");
        identifier = parts[indexOfField].substring(0, cIndex);
        fieldType = parts[indexOfField].substring(cIndex + 1);

        if (indexOfField + 1 < parts.length) {
            // has a value
            this.value = parts[parts.length - 1];
        }
    }

    @Override
    public SmaliFile getParentSmaliFile() {
        return this.getSmaliLine().getParentSmaliFile();
    }


    @Override
    public HashMap<String, String> getNameChangeMap(SmaliFile smaliFile) {
        return smaliFile.getFieldNameChange();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void rename() {
        HashMap<String, String> nameChanges = parentNameChanges();
        String newName;
        if (nameChanges.containsKey(this.getIdentifier())) {
            newName = nameChanges.get(this.getIdentifier());
        } else {
            // generate a new name
            newName = getAvailableID(nameChanges);
        }

        assert newName != null;

        rename(newName);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void rename(String newFieldName) {
        // get a new field name
        String[] parts = this.getSmaliLine().getParts().clone();

        // mAppBarConfiguration:Landroidx/navigation/ui/AppBarConfiguration; -> a:Landroidx/navigation/ui/AppBarConfiguration;
        parts[indexOfField] = newFieldName + ":" + this.fieldType;

        // whitespace followed by parts joined by space
        String builder = Arrays.stream(parts).map(part -> part + " ")
                .collect(Collectors.joining("", this.getSmaliLine().getWhitespace(), ""));

        String oldFieldName = this.getIdentifier();

        this.getSmaliLine().getParentSmaliFile().getFieldNameChange().put(oldFieldName, newFieldName);

        // rename field line
        // TODO
        //this.getSmaliLine().setText(builder.stripTrailing());
        this.getSmaliLine().setText(builder.trim());
        this.setIdentifier(newFieldName); // update the identifier

        // change all lines that called this method by the old name
        ArrayList<SmaliLine> smaliLinesPointingToThisField = this.getSmaliLine().getParentSmaliFile().getFieldReferences().get(oldFieldName);

        if (smaliLinesPointingToThisField == null) {
            smaliLinesPointingToThisField = new ArrayList<>();
        }

        // child files may refer to parent file fields
        for (SmaliFile childFile : this.getSmaliLine().getParentSmaliFile().getChildFileMap().values()) {
            if (childFile.getFieldReferences().containsKey(oldFieldName)) {
                smaliLinesPointingToThisField.addAll(childFile.getFieldReferences().get(oldFieldName));
            }
        }

        for (SmaliLine smaliLine : smaliLinesPointingToThisField) {
            String replaceThis = "->" + oldFieldName + ":" + this.getFieldType();
            String newText = "->" + newFieldName + ":" + this.getFieldType();
            String text = smaliLine.getTextFromParts();
            smaliLine.setText(text.replace(replaceThis, newText));
        }

        // move all references to the new key
        HashMap<String, ArrayList<SmaliLine>> referenceMap = this.getSmaliLine().getParentSmaliFile().getFieldReferences();
        referenceMap.remove(oldFieldName);
    }

    public boolean canRename() {
        // if its in an R.smali file dont rename
        return !APKInfo.getInstance().getRFileMap().containsKey(this.getSmaliLine().getParentSmaliFile().getAbsolutePath());
    }

    public SmaliLine getSmaliLine() {
        return smaliLine;
    }

    public String getFullField() {
        return smaliLine.getParts()[indexOfField];
    }

    public int getIndexOfField() {
        return indexOfField;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    private void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmaliField that = (SmaliField) o;
        return Objects.equals(smaliLine, that.smaliLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smaliLine);
    }
}
