import com.mcal.apkprotector.data.Constants;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println(generateRandomString("classes-v"));
    }
    private static String generateRandomString(String str) {
        Random random = new Random();
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (arr[i] != '.') {
                arr[i] = Constants.alphabet.charAt(random.nextInt(Constants.alphabet.length()));
            }
        }
        return new String(arr);
    }
}
