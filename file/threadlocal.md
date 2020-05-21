# Java中的ThreadLocal详解
## 一、ThreadLocal简介
多线程访问同一个共享变量的时候容易出现并发问题，特别是多个线程对一个变量进行写入的时候，为了保证线程安全，一般使用者在访问共享变量的时候需要进行额外的同步措施才能保证线程安全性。ThreadLocal是除了加锁这种同步方式之外的一种保证一种规避多线程访问出现线程不安全的方法，当我们在创建一个变量后，如果每个线程对其进行访问的时候访问的都是线程自己的变量这样就不会存在线程不安全问题。

ThreadLocal是JDK包提供的，它提供线程本地变量，如果创建一乐ThreadLocal变量，那么访问这个变量的每个线程都会有这个变量的一个副本，在实际多线程操作的时候，操作的是自己本地内存中的变量，从而规避了线程安全问题
##二、ThreadLocal简单使用
下面的例子中，开启两个线程，在每个线程内部设置了本地变量的值，然后调用print方法打印当前本地变量的值。如果在打印之后调用本地变量的remove方法会删除本地内存中的变量，代码如下所示
```java
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
```
运行结果：
```
thread1:localVar1
thread2:localVar2
after remove: null
after remove: null
```
##三、ThreadLocal的实现原理
下面是ThreadLocal的类图结构，从图中可知：Thread类中有两个变量threadLocals和inheritableThreadLocals，二者都是ThreadLocal内部类ThreadLocalMap类型的变量，我们通过查看内部内ThreadLocalMap可以发现实际上它类似于一个HashMap。在默认情况下，每个线程中的这两个变量都为null，只有当线程第一次调用ThreadLocal的set或者get方法的时候才会创建他们（后面我们会查看这两个方法的源码）。除此之外，和我所想的不同的是，每个线程的本地变量不是存放在ThreadLocal实例中，而是放在调用线程的ThreadLocals变量里面（前面也说过，该变量是Thread类的变量）。也就是说，ThreadLocal类型的本地变量是存放在具体的线程空间上，其本身相当于一个装载本地变量的工具壳，通过set方法将value添加到调用线程的threadLocals中，当调用线程调用get方法时候能够从它的threadLocals中取出变量。如果调用线程一直不终止，那么这个本地变量将会一直存放在他的threadLocals中，所以不使用本地变量的时候需要调用remove方法将threadLocals中删除不用的本地变量。下面我们通过查看ThreadLocal的set、get以及remove方法来查看ThreadLocal具体实怎样工作的
