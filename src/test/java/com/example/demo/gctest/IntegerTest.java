package com.example.demo.gctest;

import org.junit.Test;

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
        int[] arr = {1, 2};
        int[] cc = arr;
        int[] aa = {3, 4};
        cc = aa;
        for (int i : arr) {
            System.out.println(i);
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
