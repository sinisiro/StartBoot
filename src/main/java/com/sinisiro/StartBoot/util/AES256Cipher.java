package com.sinisiro.StartBoot.util;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

// AES 256 테스트
public class AES256Cipher {

    private static volatile AES256Cipher INSTANCE;

    //    final static String secretKey = "2gdd7c49ef427e0d20g0g6bf95264627"; //32bit
//    final static String secretKey = "DongbuMobileDM15880100!qaz2wsx3e"; //32bit
//    final static String secretKey = "01234567890123456789012345678912"; //32bit 모바일포털 서버
    final static String secretKey = "mobilePortalDBSecuSystemPrivateW"; //32bit 모바일포털 비밀번호 초기화
    static String IV = ""; //16bit

    public static AES256Cipher getInstance() {
        if (INSTANCE == null) {
            synchronized (AES256Cipher.class) {
                if (INSTANCE == null)
                    INSTANCE = new AES256Cipher();
            }
        }
        return INSTANCE;
    }


    private AES256Cipher() {
        // 21.04.29 MS . IV를 key의 절반으로 설정
        IV = secretKey.substring(0, 16);
    }

    //암호화
    public static String AES_Encode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] keyData = secretKey.getBytes();

        SecretKey secureKey = new SecretKeySpec(keyData, "AES");

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));

        byte[] encrypted = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
        String enStr = new String(Base64.encodeBase64(encrypted));

        return enStr;
    }
    //복호화
    public static String AES_Decode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] keyData = secretKey.getBytes();
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));

        byte[] byteStr = Base64.decodeBase64(str.getBytes());

        return new String(c.doFinal(byteStr), StandardCharsets.UTF_8);
    }
}
