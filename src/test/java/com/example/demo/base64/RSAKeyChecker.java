package com.example.demo.base64;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RSAKeyChecker {

    @Test
    public void test() {
        String str = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxE4LyzFqtqtE7+Skze8R\n" +
                "tLne8u0CUJ9XcW4xz/+y7F3bZyw3scFi2/Jdbw1tfwdGj38lHUuv5J6eVUmDpI+x\n" +
                "Vg3wNYxbxlnU/aNhddr6DOiqJ9CWnh2W1+KZ3HF+QN/xfD5yPffGQ+KzublG5frg\n" +
                "Lpw3hWzEMF16qF1FIVsLzXrgyauty4Ditx3o3DwFHpM0qi2Z5UPpmpGKxZjbqreW\n" +
                "UX6VRMplgBfwCSXNxOKuTMpkEdfFbAL1l9GuNS/x6YO4dm4Rhl/DXeklDP0JPPKk\n" +
                "ZlPBuF1naaJWvX5OuMIfSqPqLIFmeMLRpLKGLhRXaPcYqFRIoQYxxu02fB4TRNmZ\n" +
                "fwIDAQAB";
        boolean helloworld = checkPublicKey(str);
        System.out.println("校验结果:" + helloworld);
    }
    /** 日志 */

    /**
     * 检查公钥的合法性
     *
     * @param key 经过base64编码的公钥key
     * @return 生成公钥未抛异常，则返回<code>true</code>，否则返回<code>false</code>
     */
    public boolean checkPublicKey(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        try {
            getPublicKey(key);
            return true;
        } catch (Exception e) {
            log.error("RSA公钥合法性校验失败", e);
            return false;
        }

    }

    /**
     * 生成RSA公钥
     *
     * @param key 经过base64编码的公钥key
     * @return rsa公钥
     * @throws Exception key不合法，则抛异常
     */
    public PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = (byte[]) Base64.decodeBase64(key.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
}
