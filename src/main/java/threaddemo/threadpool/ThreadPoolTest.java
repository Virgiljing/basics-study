package threaddemo.threadpool;

import threaddemo.threadpool.runnable.BasicThreadPool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        final ThreadPool threadPool = new BasicThreadPool(10, 100, 10, 100);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (;;) {
            System.out.println("getActiveCount:" + threadPool.getActiveCount());
            System.out.println("getCoreSize:" + threadPool.getCoreSize());
            System.out.println("getMaxSize:" + threadPool.getMaxsize());
            System.out.println("getRunnableQueue:" + threadPool.getQuenSize());
            System.out.println("getInitSize:" + threadPool.getInitSize());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
