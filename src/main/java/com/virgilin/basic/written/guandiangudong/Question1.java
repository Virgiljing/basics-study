package com.virgilin.basic.written.guandiangudong;

import java.util.Arrays;

public class Question1 {
    public static void main(String[] args) {
        Integer[] arr =  {6, 4, -3, 5, -2, -1, 0, 1, -9};
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 && index == -1) {
                //找到第一个负数
                index = i;
            }
            if (arr[i] >= 0 && index != -1) {
                //找到负数后边的第一个正数 并和第一个负数交换
                Integer temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
                //回到第一个负数的位置继续寻找
                index = -1;
                i = index;
            }
        }
        System.out.println(Arrays.asList(arr));
    }
}
