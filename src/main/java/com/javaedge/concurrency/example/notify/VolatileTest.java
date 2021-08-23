package com.javaedge.concurrency.example.notify;

/**
 * VolatileTest
 *
 * @author virgilin
 * @date 2019/4/15
 */
public class VolatileTest extends Thread{
    public  boolean flag = false;

    @Override
    public void run() {
        System.out.println("线程开始");
        while (true){
            if (flag){
                break;
            }
        }
        System.out.println("线程结束");

    }

    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        volatileTest.flag = true;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
/**
 wait 与 Sleep区别是什么？
 wait用于同步中，可以释放锁的资源。
 sleep不会释放锁的资源。
 wait需要nofity才能从休眠状态变为运行状态
 sleep时间到期，从休眠状态变为运行状态
 相同都是休眠

 */