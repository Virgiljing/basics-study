package threaddemo.threadpool.runnable;

import playWithDataStructuresRonglexie.queue.LinkedList;
import threaddemo.threadpool.DenyPolicy;
import threaddemo.threadpool.RunnableQueue;
import threaddemo.threadpool.ThreadPool;

public class LinkendRunnableQueue implements RunnableQueue {
    /**
     * 队列最大长度
     */
    private final int limit;

    /**
     * 拒绝策略
     */
    private final DenyPolicy denyPolicy;
    /**
     * 存放任务
     */
    private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();

    /**
     * 线程池
     */
    private final ThreadPool threadPool;

    public LinkendRunnableQueue(int limit,DenyPolicy denyPolicy,ThreadPool threadPool){
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    /**
     *
     * @param runnable
     */
    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableLinkedList){
            if (runnableLinkedList.getSize() >= limit) {
                this.denyPolicy.reject(runnable,threadPool);
            }else {
                runnableLinkedList.addLast(runnable);
                runnableLinkedList.notifyAll();
            }
        }
    }

    @Override
    public Runnable task() {
        synchronized (runnableLinkedList){
            while (runnableLinkedList.isEmpty()){
                try {
                    /**
                     * 如果没有任务进来，阻塞
                     */
                    runnableLinkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return runnableLinkedList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableLinkedList){
            return runnableLinkedList.getSize();
        }
    }
}
