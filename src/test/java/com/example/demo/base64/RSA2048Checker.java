package com.example.demo.base64;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RSA2048Checker {

    private static final String CIPHER = "AES/ECB/PKCS5Padding";

    private static final String AES = "AES";

    @Test
    public void test() {
        String content = "HelloWorldqqqqqe";
        String password = "123456";
        try {
            Cipher cipher = Cipher.getInstance(CIPHER);
            byte[] byteContent = content.getBytes();
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            byte[] result = cipher.doFinal(byteContent);//===========
            System.out.println("获取到的加密之后的数据:" + Base64.encodeBase64(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

    }

    private SecretKeySpec getSecretKey(String password) {
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(AES);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kg.init(128, secureRandom);
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), AES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
