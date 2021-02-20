package com.example.demo.leetcode;

import com.jayway.jsonpath.internal.filter.ValueNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 滑动窗口中位数
 */
public class LeetCodeTest {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 87, 3, 5, 7, 43, 23, 56, 3, 67, 3, 24};
        int k = 4;
//        int[] ints = medianSlidingWindow(nums, k);
//        for (int anInt : ints) {
//            System.out.println(anInt);
//        }
//        twoSum(nums, k);
        String s = "sajhfdkj";
    }

    public static int[] medianSlidingWindow(int[] nums, int k) {
        int[] results = new int[nums.length - k + 1];
        int start = 0;
        int first = (start + k) / 2;
        if (first % 2 == 0) {
            for (int i = 0; i < results.length; i++) {
                int result = (nums[first] + nums[first - 1]) / 2;
                results[i] = result;
                first++;
            }
        } else {
            for (int i = 0; i < results.length; i++) {
                System.out.println("数组中相应位置(" + first + ")元素:" + nums[first]);
                results[i] = nums[first];
                first++;
            }
        }
        return results;
    }

    public static void twoSum(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        first:
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break first;
                }
            }
        }
        for (int i : result) {
            System.out.println(i);
        }
    }

    //polopkjh
    public static void getLonger(String target) {
        String longerStr = "";
        int maxLonger = 0;
        if (target == null || target.length() == 0) {
            return;
        }
        // 结束递归标识
        boolean out = false;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < target.length(); i++) {
            String chat = target.charAt(i) + "";
            if (!sb.toString().contains(chat)) {
                sb.append(chat);
                //判断是否已经遍历完整个字符串
                if (i == target.length() - 1) {
                    longerStr = sb.toString();
                    maxLonger = sb.length();
                    out = true;
                }
            } else {
                if (maxLonger < sb.length()) {
                    longerStr = sb.toString();
                    maxLonger = sb.length();
                }
                // 每次递归去掉首字母
                target = target.substring(1);
                // 不可能获得更长的子字符串了
                if (maxLonger == target.length()) {
                    out = true;
                }
                break;
            }
        }
        if (!out) {
            getLonger(target);
        }
    }


}
