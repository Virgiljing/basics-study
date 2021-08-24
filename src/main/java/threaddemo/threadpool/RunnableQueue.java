package threaddemo.threadpool;

/**
 * 主要用于存放提交的Runnable，并且有limit限制
 */
public interface RunnableQueue {

    /**
     * 当前新的任务进来，放到队列的末尾
     * @param offer
     */
    void offer(Runnable offer);

    /**
     * 工作线程通过task获取任务
     * @return
     */
    Runnable task();

    /**
     * 获取线程池缓存任务的数量
     * @return
     */
    int size();


}
