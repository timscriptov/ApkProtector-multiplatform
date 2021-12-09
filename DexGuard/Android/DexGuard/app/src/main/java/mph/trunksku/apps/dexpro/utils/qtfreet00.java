package mph.trunksku.apps.dexpro.utils;

import java.io.ByteArrayOutputStream;

public class qtfreet00 {
    private static final String hexString = "0123456789ABCDEF";
    private static final String KEY = "qtfreet";

    /**
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) {
        //根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        int len = bytes.length;
        int keyLen = KEY.length();
        for (int i = 0; i < len; i++) {
            //对每个字节进行异或
            bytes[i] = (byte) (bytes[i] ^ KEY.charAt(i % keyLen));
        }
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        //将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    public static String encode(String str, String key) {
        //根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        int len = bytes.length;
        int keyLen = key.length();
        for (int i = 0; i < len; i++) {
            //对每个字节进行异或
            bytes[i] = (byte) (bytes[i] ^ key.charAt(i % keyLen));
        }
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        //将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    /**
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String decode(String str) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(str.length() / 2);
        //将每2位16进制整数组装成一个字节
        for (int i = 0; i < str.length(); i += 2)
            baos.write((hexString.indexOf(str.charAt(i)) << 4 | hexString.indexOf(str.charAt(i + 1))));
        byte[] b = baos.toByteArray();
        int len = b.length;
        int keyLen = KEY.length();
        for (int i = 0; i < len; i++) {
            b[i] = (byte) (b[i] ^ KEY.charAt(i % keyLen));
        }
        return new String(b);
    }

	public static String a(String input) {
		boolean qwerty21345hjdnjd = false;

		while (qwerty21345hjdnjd) {
            switch (1) {
                case 1:
                    while (qwerty21345hjdnjd) {
                        try {
                            Throwable throwable=new Throwable();
                            Throwable cause = throwable.getCause();
                        } catch (NullPointerException e) {
                        } finally {
                        }
                    }
                    break;
            }
        }
		char[] key = new char[]{'\uffec', '\u00ac', '<', 'o', '\b', '\u0087', '\u00ac', '\u00c2', '+', '\uffe6', '\u00cf', '\u00cb', '\u00e6', '\u00b0', '\uffe6', '\u0083', '\f', 's', '\u00f0', 't', '\u00cc', '\ufffa', '\u00d0', '\u00a0', 'Y', '\u00b2', '<', '\u00a8', '\u008b', '\u00cf', '\u00bb', 'j', 'j', '\ufffc', 'H', 'q', '\uffec', '\u00be', '\u0003', 'N', '#', '\u00c3', '\u00b5', '\u00b2', '\u009e', '\u00e0', '\b', '\u0099', '\uffe3', '=', '\u0018', '\ufff3', 'B', '\u0080', 'c', '\ufff1', '\u0015', 'k', '\f', '\u00b3', '\ufff0', '\u00a5', '\u00cc', '\u00cf', '\uffe0', '\u000f', '\u0095', '\u00dc', '\u00e0', '\u009c', ']', 's', 'D', '\uffd0', '`', '\u00b5', '\u00ab', 's', 'V', '\uffe1', '\u00c9', '\u0016', '0', 'X', 'H', '\u0006', '8', '\u0082', '\u008a', '\uffcf', '\ufff0', ':', '\u00e3', 'A', '=', '\u00c0', '\f', 'x', '^', '\u0084', '\u001a', '\u000b', 'r', '\u0089', '\uffe0', '\u00b0', '\u00c4', '\ufffe', '\u008b', '\u00d5', '\\', '\u0007', '\u00a1', 'w', '\u0084', '\u0013', 'u', '\uffea', 'J', '\u00d6', '3', '\u0003', '>', '>', '\u00b8', '\u009f', '\u001e', 'E', '\uffb5', '\u00a9', '9', '\uffcd', ',', '\u0005', 'Q', 'a', '(', '8', '\u00bd', '\u00bc', '.', '\u009c', '\u00c2', ';', 'u', '^', '\u00d4', 'E', '\u0012', '\u00a7', 'l', '\u00bf', '\ufffd', '\ufff7', '\u0086', '\u0017', '\b', '\u00cf', '\u0096', 'w', 'v', '\u00a5', 'y', '\uffe4', '\u0085', '\u00e0', '\u0013', '\u0088', '\u0012', '\u0085', '\ufff6', '\t', '\u0083', 'Q', '\u00c1', '\u00cd', 'v', '\u009e', '\u00a7', 'F', '\u0081', '{', '\ufffe', '\u0090', '\u0082', 'A', '\uffcc', '\'', '!', '=', '\u00af', '\u00bf', '\uffec', '\u0012', 'Z', '\ufffa', '\u00ac', '\u000e', '\u0084', 't', 'p', '\u001b', '\uffc9', '\u00db', '\u00bf', 'L', 'e', '\ufff6', '\ufff1', 'g', '\u0083', '\ufff3', '\ufff3', '{', '\u00b7', '\u0090', '?', 'a', '\u0000', '\u009b', 'G', '\ufff4', '`', '\u00c7', '\u00b2', '\u0015', '\uffcf', '\u0085', '\u0092', 'e', '\u0092', '7', '\u00b5', '\u00ae', '\u008d', '\u0004', '\u00b4', '\u000e', '<', '\u0095', '4', '\u0014', 'x', '\t', 'l', '\u0092', '\u0084', 'k', 'z', '\u001d', '\u00b6', '\u0091', '.', '\u000b', '\u00e0', 't', '\uffb0', '\u00c1', '+', '\u0080', 'M', '\u0084', '\u00ba', '\u00c2', '\ufff6'};

			StringBuilder output = new StringBuilder();

													  for (int i = 0; i < input.length(); i++)
			{
				output.append((char) (input.charAt(i) ^ key[i % key.length]));
			}

			return output.toString();
    }
	
}
