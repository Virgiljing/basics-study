package threaddemo.threadpool;

import threaddemo.threadpool.execption.RunnableDenyPolicyExecption;

/**
 * 当RunnableQueue超出范围是，使用拒绝策略
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable,ThreadPool threadPool);


    /**
     * 直接丢弃任务
     */
    class DiscardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            //TODO
        }
    }

    /**
     * 该拒绝策略会向任务提交者抛出异常
     */
    class AbortDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyPolicyExecption("");
        }
    }

    /**
     * 该拒绝策略会使任务在提交者所在的线程执行
     */
    class RunnerDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }


}
