package com.javaedge.concurrency.example.notify;

/**
 * ThreadLocalDemo
 *
 * @author virgilin
 * @date 2019/4/15
 */
class ResNumber{
    public int count = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int getCount() {
        count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count;
    }
}

class LocalThreadDome extends Thread{
    private ResNumber resNumber;

    public LocalThreadDome(ResNumber resNumber) {
        this.resNumber = resNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i <3; i++) {
            System.out.println(getName() + ",number:" + resNumber.getCount());
        }
    }
}
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ResNumber resNumber = new ResNumber();
        LocalThreadDome t1 = new LocalThreadDome(resNumber);
        LocalThreadDome t2 = new LocalThreadDome(resNumber);
        LocalThreadDome t3 = new LocalThreadDome(resNumber);
        t1.start();
        t2.start();
        t3.start();
    }
}
