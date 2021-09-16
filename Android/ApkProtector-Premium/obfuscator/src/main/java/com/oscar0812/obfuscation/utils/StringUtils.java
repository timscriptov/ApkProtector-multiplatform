package com.oscar0812.obfuscation.utils;

import java.util.ArrayList;

public class StringUtils {
    private static final ArrayList<String> stringPermutations = new ArrayList<>();

    private static void getAllStringsOfKLength(String prefix, int k, ArrayList<String> arrayList, int initMaxLength) {
        // Base case: k is 0,
        // print prefix
        if (k == 0) {
            arrayList.add(prefix);
            return;
        }

        // One by one add all characters
        // from set and recursively
        // call for k equals to k-1
        for (int i = 0; i < initMaxLength; ++i) {

            // Next character of input added
            String newPrefix = prefix + arrayList.get(i);

            // k is decreased, because
            // we have added a new character
            getAllStringsOfKLength(newPrefix, k - 1, arrayList, initMaxLength);
        }
    }

    public static ArrayList<String> getStringPermutations() {
        // int initCh = 0x0001fb00; // boxes
        // int initCh = 0x00000030; // 0
        if(stringPermutations.size() > 0) {
            return stringPermutations;
        }

        int initCh = 0x00000061; // a
        int maxChars = 25;
        int ch = initCh;
        while (ch < initCh + maxChars) {
            stringPermutations.add(new String(Character.toChars(ch)));
            ch += 1;
        }

        for (int x = 2; x < 5; x++) {
            getAllStringsOfKLength("", x, stringPermutations, maxChars);
        }

        return stringPermutations;
    }

    // value = Lcom/oscar0812/sample_navigation/MainActivity;->onCreate(Landroid/os/Bundle;)V
    // => ["Lcom/oscar0812/sample_navigation/MainActivity;", "Landroid/os/Bundle;"]
    public static ArrayList<Substring> getSmaliClassSubstrings(String input) {
        ArrayList<Substring> list = new ArrayList<>();

        if (!input.contains("L") || !input.contains(";")) {
            // avoid headache's, there's nothing here
            return list;
        }

        int from = 0;
        int indL = input.indexOf('L', from);
        while (indL >= 0) {
            // + 1 to include ;
            int indC = input.indexOf(';', indL + 1);
            if (indC > 0) {
                list.add(new Substring(indL, indC + 1, input.substring(indL, indC + 1)));
            }

            from = indL + 1;
            indL = input.indexOf('L', from);
        }

        return list;
    }

    public static String getLeadingWhitespace(String text) {
        StringBuilder wsb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                wsb.append(c);
            } else {
                break;
            }
        }
        return wsb.toString();
    }
}
