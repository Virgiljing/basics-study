package com.virgilin.basic.threadlocal;

public class ThreadLocalTest2 {
    //(1)创建ThreadLocal变量
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        //在main线程中添加main线程的本地变量
        threadLocal.set("mainVar");
        Thread thread = new Thread(() -> {
            System.out.println("子线程中的本地变量值：" + threadLocal.get());
        });

        thread.start();
        System.out.println("main线程中的本地变量：" + threadLocal.get());
    }
}
