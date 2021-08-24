package threaddemo.threadpool;

/**
 *
 */
@FunctionalInterface
public interface ThreadFactory {
    /**
     * 用于创建线程
     * @param runnable
     * @return
     */
    Thread createThread(Runnable runnable);
}
