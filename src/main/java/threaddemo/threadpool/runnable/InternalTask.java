package threaddemo.threadpool.runnable;

import threaddemo.threadpool.RunnableQueue;

public class InternalTask implements Runnable {

    /**
     * 任务队列
     */
    private final RunnableQueue runnableQueue;

    /**
     * 标记
     */
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue){
        this.runnableQueue =  runnableQueue;
    }

    @Override
    public void run() {
        /**
         * 当前任务为running并且没有被中断，则不从队列获取下一个任务
         */
        while (running && Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.task();
                task.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }

    /**
     * 停止当前线程任务
     */
    public void stop(){
        this.running = false;
    }
}
