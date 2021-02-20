package com.example.demo.gctest;

import org.junit.Test;

import java.security.MessageDigest;

public class IntegerTest {

    @Test
    public void test() {
        String[] str = {"a", "b"};
        change(str);
        for (String s : str) {
            System.out.println(s);
        }
    }

    private void change(String[] arr) {
        arr[0] = "c";
    }

    @Test
    public void test1() {
        String key = "你好";
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            System.out.println("获取到MD5加密串:" + new String(str));
        } catch (Exception e) {
        }
    }

    private void changeInt(int[] cc) {
        System.out.println("Change方法中arr初始地址:" + cc);
        int[] b ={3,4};
        System.out.println("Change方法中b初始地址:" + b);
        cc= b;
        System.out.println("Change方法中改变后的arr地址:" + cc);
    }

}
