package cn.shuzilm.tools.serial;

import cn.shuzilm.tools.aes.AesCBCUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.zip.CRC32;

public class Serial {

    private static final String BASE62_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static SerialResp Decode(String sid,String secret){
        SerialResp resp = new SerialResp();
        try {
            sid = sid.substring(0,sid.length()-2);
            byte[] decode = Base64.getUrlDecoder().decode(sid);
            byte[] data = AesCBCUtils.aesDecrypt(decode, secret.getBytes());
            if (data == null) {
                resp.isLegal = false;
                return resp;
            }
            sid = new String(data);
            if (!validSerial(sid)) {
                resp.isLegal = false;
                return resp;
            }
            resp.isLegal = true;
            resp.timestamp = getTimestamp(sid.substring(12,20));
            resp.rules = getRules(sid.substring(20,sid.length()-8));
            return resp;
        }catch (Exception e) {
            resp.isLegal = false;
            return resp;
        }
    }

    private static long getTimestamp(String timestampEncoded) {
        return base62ToDecimal(timestampEncoded).longValue();
    }
    private static ArrayList<String> getRules(String ruleStr) {
        ArrayList<String> rules = new ArrayList<>();
        StringBuilder binaryRule = new StringBuilder();
        for (int i = 0; i < ruleStr.length(); i++) {
            char currentChar = ruleStr.charAt(i);
            int value = Character.digit(currentChar, 32);
            String binaryString = String.format("%5s", Integer.toBinaryString(value)).replace(' ', '0');
            binaryRule.append(binaryString);
        }
        for (int i = binaryRule.length()-1; i >= 0; i--) {
            char c = binaryRule.charAt(i);
            if (c == '1') {
                rules.add(Integer.toString(binaryRule.length()-i));
            }
        }
        return rules;
    }

    private static boolean validSerial(String sid){
        String crc = crc32sum(sid.substring(0, sid.length()-8));
        return crc.equals(sid.substring(sid.length()-8));
    }

    public static String getVersion(String sid){
        int value = Character.digit(sid.charAt(sid.length()-1), 32);
        return Integer.toString(value);
    }
    private static BigInteger base62ToDecimal(String base62Number) {
        BigInteger result = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(62);

        for (int i = 0; i < base62Number.length(); i++) {
            int digit = BASE62_ALPHABET.indexOf(base62Number.charAt(i));
            if (digit == -1) {
                throw new IllegalArgumentException("Invalid character found in base62 number: " + base62Number.charAt(i));
            }
            BigInteger value = base.pow(base62Number.length() - i - 1).multiply(BigInteger.valueOf(digit));
            result = result.add(value);
        }
        return result;
    }

    private static String crc32sum(String data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data.getBytes(StandardCharsets.UTF_8));
        return String.format("%08x", crc32.getValue());
    }

}
