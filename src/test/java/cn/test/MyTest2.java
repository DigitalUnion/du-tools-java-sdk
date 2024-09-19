package cn.test;

import cn.shuzilm.tools.aes.AesCFBUtils;
import cn.shuzilm.tools.base64.Base64Utils;
import cn.shuzilm.tools.cdid.CDID;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyTest2 {
    public static void main(String[] args) throws Exception {
        String src = "rb4KDKcdFBNMEs/1JtLvbml6qDpzIji54p5/QSzxCwCCz5da5LnYBcDJeUHXO8go/mWx+JRDv6zBIl+6XW8vQAt0LWKSFfulx83ZS7Ue252B3iQhj0uj6ZIsa7DZj7b0lmZ0M298hQy4zKM+K1cS4VpsXn3nyf+ylC2x/tL+mtrHgjHa/PdiqZvHZ6OU7TAJlUJr8s1Vi7DC3vjkIh4VLdWmVK/QzmMKBzWmIsonhMPsIH6s1l2w3uxl/nJVuZeYRmQTJXsOnYlxDt+kCpheAOuT1ro7JQl1KHuxDYO1lIDCa3WCJ0bDduqSRkKI/AIGWLJbxYzuqdtMOWhj+kTVinervIxPh3yT6DVr0bAfpHQkC/Wx+PsC7YMroDQH5Ls2HhtHZuM7Ze4AyAt5xut3NEW353JSzDwPmRNFKocuJw==";
        String key = "xxxx";
        byte[] res = AesCFBUtils.aesDecrypt(Base64Utils.decodeToString(src), key.getBytes());
        System.out.println(new String(res));
    }
}
