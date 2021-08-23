package com.javaedge.concurrency.example.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * OutInputThread
 * volatile关键字具有可见性，有序性，不具有原子性
 * @author virgilin
 * @date 2019/4/15
 */
class ResLock{
    public String userName;
    public String sex;
    public volatile boolean flag = false;
    public Lock lock = new ReentrantLock();
}

class OutLock extends Thread{
    ResLock res;
    Condition condition;

    public OutLock(ResLock res,Condition condition) {
        this.res = res;
        this.condition = condition;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            res.lock.lock();
            if (!res.flag){
                try {
                  condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(res.userName + ":" + res.sex);
            res.flag = false;
            condition.signal();
            res.lock.unlock();
        }
    }
}

class InputLock extends Thread{
    ResLock res;
    Condition condition;

    public InputLock(ResLock res,Condition condition) {
        this.res = res;
        this.condition = condition;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            res.lock.lock();
                if (res.flag){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count == 0){
                    res.userName = "小红";
                    res.sex = "女";
                }else {
                    res.userName = "小明";
                    res.sex = "男";
                }

                count = (count +1 ) % 2;
                res.flag = true;
            condition.signal();
            res.lock.unlock();

        }
    }
}

public class OutInputThreadLock {
    public static void main(String[] args) {
        ResLock res = new ResLock();
        Condition condition = res.lock.newCondition();
        OutLock out = new OutLock(res,condition);
        InputLock input = new InputLock(res,condition);
        out.start();
        input.start();

    }
}
