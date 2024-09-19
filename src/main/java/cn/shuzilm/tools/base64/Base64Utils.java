package cn.shuzilm.tools.base64;

import java.util.Base64;

public class Base64Utils {
    public static String encodeToString(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decodeToString(String data) {
        return Base64.getDecoder().decode(data);
    }
}
