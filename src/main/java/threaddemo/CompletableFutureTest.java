package threaddemo;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author ：zhanghm
 * @date ：Created in 2021/8/24 16:55
 * @description：
 * @modified By：
 * CompletableFuture 是JDK1.8 版本出现的异步编程函数，实现 Future 和 CompletionStage 接口
 */
public class CompletableFutureTest {


    Executor executor = Executors.newCachedThreadPool();
    /**
     * 应用场景1：创建异步对象
     * 1、runXxxx 都是没有返回结果的，supplyXxxx都是可以获取返回结果的
     *
     * 2、提供两个入参的方法可以传入自定义的线程池，否则默认使用公用的ForkJoinPool.commonPool()作为执行异步任务的线程池
     *
     * public static CompletableFuture<Void> runAsync(Runnable runnable)；
     * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)；
     *
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) ；
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)；
     */
    @Test
    public void async() throws ExecutionException, InterruptedException {
        System.out.println("主线程开始执行。。。");
        CompletableFuture<Void> future01 = CompletableFuture.runAsync(() -> System.out.println("无返回值，使用默认线程池"));
        System.out.println(future01.get());


        CompletableFuture<Void> future02 = CompletableFuture.runAsync(() -> System.out.println("无返回值，使用自定义线程池"), executor);
        System.out.println(future02.get());

        CompletableFuture<Long> future03 = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值，使用默认线程池");
            return System.currentTimeMillis();
        });
        System.out.println(future03.get());

        CompletableFuture<Long> future04 = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值，使用自定义线程池");
            return System.currentTimeMillis();
        }, executor);
        System.out.println(future04.get());

        System.out.println("主线程结束。。。");
    }

    /**
     *  应用场景2：异步任务完成时回调方法
     *  当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action。主要是下面的方法：
     *
     * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action);
     * 返回相同的结果或例外，这一阶段的新completionstage，这个阶段完成时，执行特定动作的结果（或 null如果没有）和异常（或 null如果没有）这个阶段。
     *
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action);
     * 返回相同的结果或例外，这一阶段的新completionstage，这个阶段完成时，执行特定动作执行给定的操作这一阶段的默认的异步执行设施，其结果（或 null如果没有）和异常（或 null如果没有）这个阶段作为参数。
     *
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor);
     * 返回相同的结果或例外，这一阶段的新completionstage，这个阶段完成时，执行使用所提供的遗嘱执行人，给出的行动与结果（或 null如果没有）和异常（或 null如果没有）这个阶段作为参数。
     *
     * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn);
     * 返回一个新的completablefuture已经完成与给定值。
     */
    @Test
    public  void demo2() throws ExecutionException, InterruptedException {
        System.out.println("主线程开始执行。。。线程id：" + Thread.currentThread().getId());
        CompletableFuture<Long> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future01执行，线程id：" + Thread.currentThread().getId());
            return System.currentTimeMillis();
        }).whenComplete((res, e) -> {
            System.out.println("future01回调方法执行 ==》" + res + "，线程id：" + Thread.currentThread().getId());
        });
        System.out.println(future01.get());

        CompletableFuture<Long> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future02执行，线程id：" + Thread.currentThread().getId());
            return System.currentTimeMillis();
        }).whenCompleteAsync((res, e) -> {
            System.out.println("future02回调方法执行 ==》" + res + "，线程id：" + Thread.currentThread().getId());
        });
        System.out.println(future02.get());

        CompletableFuture<Long> future03 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future03执行，线程id：" + Thread.currentThread().getId());
            return System.currentTimeMillis();
        }).whenCompleteAsync((res, e) -> {
            System.out.println("future03回调方法执行 ==》" + res + "，线程id：" + Thread.currentThread().getId());
        }, executor);
        System.out.println(future03.get());

        CompletableFuture<Long> future04 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future04执行，线程id：" + Thread.currentThread().getId());
            int i = 10 / 0;
            return System.currentTimeMillis();
        }).whenCompleteAsync((res, e) -> {
            System.out.println("future04回调方法执行 ==》" + res + "，线程id：" + Thread.currentThread().getId());
        }, executor).exceptionally((e) -> {
            System.out.println("异常回调执行，异常原因：" + e.getMessage() + "==> 线程id：" + Thread.currentThread().getId());
            return 0L;
        });
        System.out.println(future04.get());

        System.out.println("主线程结束。。。");
    }

    /**
     * 应用场景3：线程串行化方法
     * thenApply 方法：当一个线程依赖另一个线程时，获取上一个任务返回的结果，并返回当前任务的返回值。
     *
     * thenAccept方法：消费处理结果。接收任务的处理结果，并消费处理，无返回结果。
     *
     * thenRun方法：只要上面的任务执行完成，就开始执行thenRun，只是处理完任务后，执行 thenRun的后续操作
     *
     * 每一个方法都对应了三种操作。带有Async默认是异步执行的。这里所谓的异步指的是不在当前线程内执行。带有参数Executor executor的则用自定义的线程池，不指定的话则用默认ForkJoinPool.commonPool()线程池
     *
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     * 当一个线程依赖另一个线程时，获取上一个任务返回的结果，**并返回当前任务的返回值**。（有返回值）
     *
     * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
     * 消费处理结果。接收任务的处理结果，并消费处理，**无返回结果。
     *
     * public CompletionStage<Void> thenRun(Runnable action);
     * public CompletionStage<Void> thenRunAsync(Runnable action);
     * public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
     * 只要上面的任务执行完成，就开始执行thenRun，**只是处理完任务后，执行 thenRun的后续操作
     */
    @Test
    public  void demo3() throws ExecutionException, InterruptedException {
        System.out.println("主线程开始执行。。。线程id：" + Thread.currentThread().getId());
        CompletableFuture<Void> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future01执行，线程id：" + Thread.currentThread().getId());
            return System.currentTimeMillis();
        }).thenRun(() -> {
            System.out.println("thenRun执行，无法获取上一步执行结果，无返回值");
        });
        System.out.println(future01.get());

        CompletableFuture<Void> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future01执行，线程id：" + Thread.currentThread().getId());
            return System.currentTimeMillis();
        }).thenAccept((res) -> {
            System.out.println("thenAccept，上一步执行结果: " + res +"，无返回值");
        });
        System.out.println(future02.get());

        CompletableFuture<Long> future03 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future03执行，线程id：" + Thread.currentThread().getId());
            return System.currentTimeMillis();
        }).thenApply((res) -> {
            long i = 10;
            System.out.println("thenApply，上一步执行结果: " + res + "，有返回值: " + i);
            return i;
        });
        System.out.println(future03.get());

        System.out.println("主线程结束。。。");
    }
    /**
     * 应用场景4：两任务组合 - 都要完成
     * public <U,V> CompletableFuture<V> thenCombine(
     *     CompletionStage<? extends U> other,
     *     BiFunction<? super T,? super U,? extends V> fn);
     *
     * public <U,V> CompletableFuture<V> thenCombineAsync(
     *     CompletionStage<? extends U> other,
     *     BiFunction<? super T,? super U,? extends V> fn);
     *
     * public <U,V> CompletableFuture<V> thenCombineAsync(
     *     CompletionStage<? extends U> other,
     *     BiFunction<? super T,? super U,? extends V> fn, Executor executor);
     *  组合两个future，获取两个future任务的返回结果，并返回当前任务的返回值
     *
     *
     * public <U> CompletableFuture<Void> thenAcceptBoth(
     *     CompletionStage<? extends U> other,
     *     BiConsumer<? super T, ? super U> action);
     *
     * public <U> CompletableFuture<Void> thenAcceptBothAsync(
     *     CompletionStage<? extends U> other,
     *     BiConsumer<? super T, ? super U> action);
     *
     * public <U> CompletableFuture<Void> thenAcceptBothAsync(
     *     CompletionStage<? extends U> other,
     *     BiConsumer<? super T, ? super U> action, Executor executor);
     * 组合两个future，获取两个future任务的返回结果，然后处理任务，没有返回值。
     *
     *
     * public CompletableFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action);
     *
     * public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action);
     *
     * public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action,
     *                                                  Executor executor);
     * 组合两个future，不需要获取future的结果，只需两个future处理完任务后，处理该任务
     * thenCombine：组合两个future，获取两个future任务的返回结果，并返回当前任务的返回值
     *
     * thenAcceptBoth：组合两个future，获取两个future任务的返回结果，然后处理任务，没有返回值。
     *
     * runAfterBoth：组合两个future，不需要获取future的结果，只需两个future处理完任务后，处理该任务
     *
     */
    @Test
    public  void demo4() throws ExecutionException, InterruptedException {
        System.out.println("主线程开始执行。。。线程id：" + Thread.currentThread().getId());

        CompletableFuture<Long> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future01执行，线程id：" + Thread.currentThread().getId());
            System.out.println("future01结束");
            return System.currentTimeMillis();
        });
        CompletableFuture<Long> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future02执行，线程id：" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future02结束");
            return System.currentTimeMillis();
        });

        CompletableFuture<Void> future03 = future01.runAfterBoth(future02, () -> {
            System.out.println("任务3开始。。。");
        });

        CompletableFuture<Void> future04 = future01.thenAcceptBoth(future02, (f1, f2) -> {
            System.out.println("任务4开始。。。之前的结果 "+f1+"==>"+f2);
        });

        CompletableFuture<Long> future05 = future01.thenCombine(future02, (f1, f2) -> {
            System.out.println("任务5开始。。。之前的结果 "+f1+"==>"+f2);
            return 0L;
        });
        System.out.println(future05.get());


        System.out.println("主线程结束。。。");
    }

    /**
     * 应用场景5：两任务组合 - 任意一个完成
     * public <U> CompletableFuture<U> applyToEither(
     *     CompletionStage<? extends T> other, Function<? super T, U> fn);
     *
     * public <U> CompletableFuture<U> applyToEitherAsync(
     *     CompletionStage<? extends T> other, Function<? super T, U> fn);
     *
     * public <U> CompletableFuture<U> applyToEitherAsync(
     *     CompletionStage<? extends T> other, Function<? super T, U> fn,Executor executor) ;
     *  组合两个future，只需一个future任务的返回结果，并返回当前任务的返回值
     *
     *
     * public <U> CompletableFuture<Void> acceptEither(
     *     CompletionStage<? extends U> other,
     *     BiConsumer<? super T, ? super U> action);
     *
     * public <U> CompletableFuture<Void> acceptEitherAsync(
     *     CompletionStage<? extends U> other,
     *     BiConsumer<? super T, ? super U> action);
     *
     * public <U> CompletableFuture<Void> acceptEitherAsync(
     *     CompletionStage<? extends U> other,
     *     BiConsumer<? super T, ? super U> action, Executor executor);
     * 组合两个future，只需一个future任务的返回结果，然后处理任务，没有返回值。
     *
     *
     * public CompletableFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action);
     *
     * public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action);
     *
     * public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action,
     *                                                  Executor executor);
     * 组合两个future，不需要获取future的结果，只需一个future处理完任务后，处理该任务
     *
     * applyToEither：组合两个future，只需一个future任务的返回结果，并返回当前任务的返回值
     *
     * acceptEither：组合两个future，只需一个future任务的返回结果，然后处理任务，没有返回值。
     *
     * runAfterEither：组合两个future，不需要获取future的结果，只需一个future处理完任务后，处理该任务
     */
    @Test
    public  void demo5() throws ExecutionException, InterruptedException {
        System.out.println("主线程开始执行。。。线程id：" + Thread.currentThread().getId());

        CompletableFuture<Long> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future01执行，线程id：" + Thread.currentThread().getId());
            System.out.println("future01结束");
            return System.currentTimeMillis();
        });
        CompletableFuture<Long> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future02执行，线程id：" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future02结束");
            return System.currentTimeMillis();
        });

        CompletableFuture<Void> future03 = future01.runAfterEither(future02, () -> {
            System.out.println("任务3开始。。。");
        });

        CompletableFuture<Void> future04 = future01.acceptEither(future02, (res) -> {
            System.out.println("任务4开始。。。之前的结果 "+res);
        });

        CompletableFuture<Long> future05 = future01.applyToEither(future02, (res) -> {
            System.out.println("任务5开始。。。之前的结果 "+res);
            return 0L;
        });
        System.out.println(future05.get());


        System.out.println("主线程结束。。。");
    }

    /**
     * 应用场景6：多任务组合
     * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs);
     *
     * public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs);
     *
     * allOf：等待所有任务完成
     *
     * anyOf：只要有一个任务完成，并返回执行结果
     */
    @Test
    public  void demo6() throws ExecutionException, InterruptedException {
        System.out.println("主线程开始执行。。。线程id：" + Thread.currentThread().getId());

        CompletableFuture<Long> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future01执行，线程id：" + Thread.currentThread().getId());
            System.out.println("future01结束");
            return System.currentTimeMillis();
        });
        CompletableFuture<Long> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future02执行，线程id：" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future02结束");
            return System.currentTimeMillis();
        });

        CompletableFuture<Void> future03 = CompletableFuture.allOf(future01, future02);
        CompletableFuture<Object> future04 = CompletableFuture.anyOf(future01, future02);

        System.out.println("anyOf 任意一个异步任务执行完成"+future04.get());
        System.out.println("allOf 等待所有异步任务执行完成"+future03.get());

        System.out.println("主线程结束。。。");
    }

}
