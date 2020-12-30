package com.example.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

public class GenPubKey {

    //加密方式
    public static final String KEY_ALGORITHM = "RSA";
    //公钥
    public static final String PUBLIC_KEY = "RSAPublicKey";
    //私钥
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    public static void main(String[] args) {

        Map<String, Object> map;
        try {
            map=initKey();
            String publicKey = getPublicKey(map);
            System.out.println(publicKey);
            String privateKey = getPrivateKey(map);
            System.out.println(privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取公钥
     * @param keyMap
     * @return
     */
    public static String getPublicKey(Map<String, Object> keyMap){
        //获取map中的公钥转化为key对象
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }


    /**
     * {@link Map}
     * map中存放私钥和公钥
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception{
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator key = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        key.initialize(2048);//这个地方官方文档说的必须初始化 可以参考一下jdk1.8官方文档
        KeyPair generateKeyPair = key.generateKeyPair();
        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) generateKeyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) generateKeyPair.getPrivate();
        Map<String, Object> map =  new HashMap<String, Object>(2);
        map.put(PUBLIC_KEY, publicKey);
        map.put(PRIVATE_KEY, privateKey);
        return map;
    }

    /**
     * 获取私钥
     * @param keyMap
     * @return
     */
    public static String getPrivateKey(Map<String, Object> keyMap){
        //获取map中的公钥转化为key对象
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 解码返回字节数组
     */
    public static byte[] decryptBASE64(String key) throws IOException {
        return new BASE64Decoder().decodeBuffer(key);
    }
    /**
     * 编码返回字符串
     * @param encoded
     * @return
     */
    public static String encryptBASE64(byte[] encoded) {
        return new BASE64Encoder().encodeBuffer(encoded);
    }
}
