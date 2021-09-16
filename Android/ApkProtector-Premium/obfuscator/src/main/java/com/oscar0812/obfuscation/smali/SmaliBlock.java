package com.oscar0812.obfuscation.smali;

import com.oscar0812.obfuscation.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface SmaliBlock {

    String getIdentifier();

    SmaliFile getParentSmaliFile();

    default String appendAfterID() {
        return "";
    }


    default String getAvailableID(HashMap<String, String> nameChanges) {
        // generate a new name
        ArrayList<String> permutations = StringUtils.getStringPermutations();
        Set<String> allChanges = new HashSet<>(nameChanges.values());
        for (String perm : permutations) {
            if (!nameChanges.containsKey(perm + appendAfterID()) && !allChanges.contains(perm + appendAfterID())) {
                // new!
                return perm + appendAfterID();
            }
        }
        return null;
    }

    // return field name changes if SmaliField, or return method name changes if SmaliMethod
    HashMap<String, String> getNameChangeMap(SmaliFile smaliFile);

    // get the map between original names and new name:
    // dog -> a, cat -> b, ...
    default HashMap<String, String> parentNameChanges() {
        ArrayList<SmaliFile> allParents = new ArrayList<>(this.getParentSmaliFile().getParentFileMap().values());
        allParents.add(this.getParentSmaliFile());

        HashMap<String, String> nameChanges = new HashMap<>();
        for (SmaliFile parentFile : allParents) {
            nameChanges.putAll(getNameChangeMap(parentFile));
        }
        return nameChanges;
    }

    void rename();
}
