package com.sinisiro.StartBoot.util;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


//AES256 CTR 방식. coPilot 와 chatGPT활용
public class AES256CTR {

    /**
     * 주어진 키와 초기화 벡터를 사용하여 주어진 평문을 암호화하는 메서드입니다.
     *
     * @param key        암호화에 사용할 키
     * @param iv         초기화 벡터
     * @param plaintext  암호화할 평문
     * @return           암호화된 결과를 Base64로 인코딩하여 반환
     * @throws Exception 암호화 과정에서 예외가 발생할 경우
     */
    public static String encrypt(String key, String iv, String plaintext) throws Exception {
        // 키와 초기화 벡터를 Base64 디코딩하여 바이트 배열로 변환
        byte[] keyBytes = Base64.getDecoder().decode(key);
        byte[] ivBytes = Base64.getDecoder().decode(iv);

        // SecretKeySpec 객체 생성
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Cipher 객체 생성 및 초기화
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(ivBytes));

        // 평문을 UTF-8 인코딩으로 바이트 배열로 변환하고 암호화 수행
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        // 암호화된 결과를 Base64로 인코딩하여 문자열로 반환
        return Base64.getEncoder().encodeToString(ciphertext);
    }

    public static void main(String[] args) {
        String key = "Rmx0Yx4PzoCIt0w/jHGpzGkSIb2FIF+bsWA0Qv3np0c=";
        String iv = "lIodTJpWGXyW8hMRFnL1jg==";
        String plaintext = "우언진";

        try {
            String encryptedText = encrypt(key, iv, plaintext);
            System.out.println("암호문: " + encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //AES256CTR Test Class 작성
    @Test
    public void AES256CTRTest() throws Exception {
        String key = "Rmx0Yx4PzoCIt0w/jHGpzGkSIb2FIF+bsWA0Qv3np0c=";
        String iv = "lIodTJpWGXyW8hMRFnL1jg==";
        String plaintext = "우언진";

        String encryptedText = AES256CTR.encrypt(key, iv, plaintext);
        System.out.println("암호문: " + encryptedText);
    }

    //AES256CTR 복호화 함수 작성
    public static String decrypt(String key, String iv, String encryptedText) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        byte[] ivBytes = Base64.getDecoder().decode(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivBytes));

        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedText, StandardCharsets.UTF_8);
    }

    @Test
    public void AES256CTRDecryptTest() throws Exception {
        String key = "Rmx0Yx4PzoCIt0w/jHGpzGkSIb2FIF+bsWA0Qv3np0c=";
        String iv = "lIodTJpWGXyW8hMRFnL1jg==";
        String encryptedText = "3mG1S5C9n6gU";

        String decryptedText = AES256CTR.decrypt(key, iv, encryptedText);
        System.out.println("복호문: " + decryptedText);
    }
}
