package com.virgilin.basic.threadlocal;

public class ThreadLocalTest {
    static ThreadLocal<String> localVar = new ThreadLocal<>();
    static void print(String str){
        /**
         * 打印当前线程中本地内存中本地变量的值
         */
        System.out.println(str + ":" + localVar.get());
        /**
         * 清除本地内存中的本地变量
         */
        localVar.remove();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            localVar.set("localVar1");
            print("thread1");
            System.out.println("after remove: " + localVar.get());
        });

        Thread t2 = new Thread(() -> {
            localVar.set("localVar2");
            print("thread2");
            System.out.println("after remove: " + localVar.get());
        });
        t1.start();
        t2.start();
    }
}
