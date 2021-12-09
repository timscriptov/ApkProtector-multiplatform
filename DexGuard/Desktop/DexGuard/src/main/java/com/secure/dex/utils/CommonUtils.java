package com.secure.dex.utils;

import com.secure.dex.data.Preferences;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class CommonUtils {
    @Contract("_ -> new")
    public static @NotNull String generateRandomString(@NotNull String str) {
        Random random = new Random();
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (arr[i] != '.') {
                arr[i] = Preferences.getAlphabet().charAt(random.nextInt(Preferences.getAlphabet().length()));
            }
        }
        return new String(arr);
    }

    /**
     * Метод для шифрования строк
     *
     * @param str - текст для шифрования
     * @param i   - символы
     * @return
     */
    public static @NotNull String encryptStrings(String str, int i) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < str.length(); j++) {
                stringBuilder.append((char) (str.charAt(j) ^ a(i)[j % a(i).length]));
            }
            return stringBuilder.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    @Contract(value = "_ -> new", pure = true)
    public static char @NotNull [] a(int i) {
        switch (i) {
            case 0:
                return new char[]{37469, 12893, 58265, 34626, 61595, 5235, 30980, 36330, 54953, 54553, 35458, 50593};
            case 1:
                return new char[]{12293, 12294};
            case 2:
                return new char[]{24627};
            case 3:
                return new char[]{',', 'w', 65532, 180, 65480, 178, 'K', 131, 'r', 'A', '}', 139, 133, 193, 12, 169, 'O', 172, 139, '8', 17, 'D', ')', 'z', ']', 198, 228, 'W', 179, 142, 65524, 161, 65515, '0', 134, 143, 31, 65526, 172, 203, 'Y', 65519, 203, 't', ' ', 65528, '8', 183, 213, 'U', 'o', 65500, 's', 27, 21, '1', '.', 'z', '*', 141, '+', '\\', 'm', 11, 149, '9', '<', 'r', 220, 144, 181, 194, 169, 'j', 65520};
            default:
                return new char[0];
        }
    }
}