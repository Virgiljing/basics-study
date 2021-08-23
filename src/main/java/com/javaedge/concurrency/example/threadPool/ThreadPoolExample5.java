package com.javaedge.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExample5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);
        Run run = new Run();
        executorService.submit(run);

        Call call = new Call();
        Future<String> submit = executorService.submit(call);
        System.out.println(submit.get());
        executorService.shutdown();

    }
}

class Run implements Runnable{

    @Override
    public void run() {
        System.out.println("run");
    }
}

class Call implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("call");
        return "chenggong";
    }
}
