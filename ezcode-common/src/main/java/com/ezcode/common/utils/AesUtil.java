package com.ezcode.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author: hc
 * @Date: 2022/3/26 21:14
 */
@Component
public class AesUtil {

    @Value("${pwd.keyValue}")
    private String KEY;

    private String KEY_AES = "AES";

    public String encrypt(String src) throws Exception {
        byte[] raw = KEY.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
        Cipher cipher = Cipher.getInstance(KEY_AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes());
        return byte2hex(encrypted);
    }

    public String decrypt(String src) throws Exception {
        byte[] raw = KEY.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
        Cipher cipher = Cipher.getInstance(KEY_AES);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted = hex2byte(src);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original);
    }

    public byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    public String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (byte value : b) {
            stmp = (Integer.toHexString(value & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            }
            else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    public void main(String[] args) throws Exception {
        String content = "testContext";
        System.out.println("原内容 = " + content);
        String encrypt = new AesUtil().encrypt(content);
        System.out.println("加密后 = " + encrypt);
        String decrypt = new AesUtil().decrypt(encrypt);
        System.out.println("解密后 = " + decrypt);
    }
}
