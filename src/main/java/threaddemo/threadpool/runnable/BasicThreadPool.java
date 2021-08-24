package threaddemo.threadpool.runnable;

import threaddemo.threadpool.DenyPolicy;
import threaddemo.threadpool.RunnableQueue;
import threaddemo.threadpool.ThreadFactory;
import threaddemo.threadpool.ThreadPool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicThreadPool extends Thread implements ThreadPool {

    /**
     * 初始化线程数量
     */
    private final int initSize;
    /**
     * 线程池最大线程数量
     */
    private final int maxSize;
    /**
     * 线程池核心线程数量
     */
    private final int coreSize;
    /**
     * 但钱活跃的线程数量
     */
    private int activeCount;

    /**
     * 线程工厂
     */
    private final ThreadFactory threadFactory;

    /**
     * 任务队列
     */
    private final RunnableQueue runnableQueue;
    /**
     * 是否停止线程池
     */
    private volatile boolean isShutdown = false;
    /**
     * 时间
     */
    private final long keepAliveTime;

    /**
     * 睡眠工具类
     */
    private final TimeUnit timeUnit;

    /**
     * 任务队列
     */
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    public BasicThreadPool(int initSize,int maxSize,int coreSize,int queueSize){
        this(initSize,maxSize,coreSize,DEFAULT_THREAD_FACTORY,queueSize,DEFAULT_DENY_POLICY,10,TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory defaultThreadFactory, int queueSize, DenyPolicy defaultDenyPolicy, int keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = defaultThreadFactory;
        this.runnableQueue = new LinkendRunnableQueue(queueSize,defaultDenyPolicy,this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    /**
     * 扩容
     */
    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount ++ ;
        thread.start();
    }

    private void removeThread(){
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount --;
    }


    /**
     *
     * @param runnable
     */
    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this){
            if (isShutdown) {
                return;
            }
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getCoreSize() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.coreSize;
    }

    @Override
    public int getInitSize() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.initSize;
    }

    @Override
    public int getMaxsize() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.maxSize;
    }

    @Override
    public int getQuenSize() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.activeCount;
    }

    @Override
    public boolean isShutdown() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.isShutdown;
    }

    private static class ThreadTask{
        Thread thread;
        InternalTask internalTask;
        public ThreadTask(Thread thread,InternalTask internalTask){
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    /**
     * 默认线程池
     */
    private static class DefaultThreadFactory implements ThreadFactory{

        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

        private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndDecrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);



        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group,runnable,"thread-pool-" + COUNTER.getAndDecrement());
        }
    }
}
