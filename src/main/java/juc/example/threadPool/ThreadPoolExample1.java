package juc.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2.7 java自从5.0以来，提供了线程池，线程的目标执行对象可以共享线程池中有限数目的线程对象。
 * 线程池对象 ExecutorService 的三种实现方式
 * 单一线程数ExecutorService executorService1 = Executors.newSingleThreadExecutor();  
 * 固定线程数ExecutorService executorService2 = Executors.newFixedThreadPool(10);  
 * 缓存线程数ExecutorService executorService3 = Executors.newScheduledThreadPool(10);
 *
 * 1.单一线程数：就是只有一个线程开启，如果多长执行的话就是，这个线程一直轮询执行下去
 * 2.固定线程数：在程序初始化的时候，虚拟机初始化10个线程
 * 3.缓存线程数：在程序初始化的时候创建 10线程线程，并随着系统的需求增加线程数量，线程数量的上限是系统资源的上线
 */
@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", index);
                }
            });
        }
        executorService.shutdown();
    }
}
