package cn.shuzilm.tools.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;

public class AesCFBUtils {

    private static final String ALGORITHM = "AES/CFB/NoPadding";

    public static byte[] aesEncrypt(byte[] data, byte[] secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        byte[] ivbytes = new byte[16];
        Random rand = new Random();
        rand.nextBytes(ivbytes);

        IvParameterSpec ivSpec = new IvParameterSpec(ivbytes);

        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] result = cipher.doFinal(data);

        byte[] finalbytes = new byte[ivbytes.length + result.length];
        System.arraycopy(ivbytes, 0, finalbytes, 0, ivbytes.length);
        System.arraycopy(result, 0, finalbytes, ivbytes.length, result.length);
        return finalbytes;
    }

    public static byte[] aesDecrypt(byte[] data, byte[] secretKey) throws Exception {
        byte[] iv = new byte[16];
        byte[] encryptBytes = new byte[data.length - iv.length];
        System.arraycopy(data, 0, iv, 0, iv.length);
        System.arraycopy(data, iv.length, encryptBytes, 0, encryptBytes.length);
        SecretKeySpec key = new SecretKeySpec(secretKey, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
        byte[] result = cipher.doFinal(encryptBytes);
        return result;
    }
}
