package com.example.demo.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {
    /**
     * sun.misc方式Base64编码
     * @param str
     * @return
     */
    public static String encodeBySunMisc(String str) {
        try {
            return new BASE64Encoder().encode(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * sun.misc方式Base64解码
     * @param str
     * @return
     */
    public static String decodeBySunMisc(String str) {
        try {
            byte[] result = new BASE64Decoder().decodeBuffer(str);
            return new String(result);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * commons-code方式Base64编码
     * @param str
     * @return
     */
    public static String encodeByCommonsCode(String str) {
        byte[] result;
        try {
            result = org.apache.commons.codec.binary.Base64.encodeBase64(str.getBytes("UTF-8"));
            return new String(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * commons-code方式Base64解码
     * @param str
     * @return
     */
    public static String decodeByCommonsCode(String str) {
        byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes());
        return new String(result);
    }

    /**
     * xerces方式Base64解码
     * @param str
     * @return
     */
    public static String decodeByXerces(String str) {
        try {
            byte[] result = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(str);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Java8中的Base64编码
     * @param str
     * @return
     */
    public static String encodeByJava8(String str) {
        try {
            return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Java8中的Base64解码
     * @param str
     * @return
     */
    public static String decodeByJava8(String str) {
        byte[] result = Base64.getDecoder().decode(str.getBytes());
        return new String(result);
    }
}
