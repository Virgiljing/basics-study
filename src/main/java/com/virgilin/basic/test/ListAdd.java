package com.virgilin.basic.test;

import java.util.ArrayList;
import java.util.List;

public class ListAdd {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        start = System.currentTimeMillis();
        for (int i = 10000000; i < 20000000; i++) {
            list.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println(end-start);


    }
}
