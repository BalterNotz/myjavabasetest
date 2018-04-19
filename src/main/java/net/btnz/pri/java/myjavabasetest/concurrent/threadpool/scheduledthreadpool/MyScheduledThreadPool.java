package net.btnz.pri.java.myjavabasetest.concurrent.threadpool.scheduledthreadpool;

import java.util.concurrent.*;

/**
 * Created by zhangsongwei on 2016/12/21.
 */
public class MyScheduledThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("in runnable " + Thread.currentThread().getId());
            }
        }, 1, 1, TimeUnit.SECONDS);

        Thread.sleep(5000);
        System.out.println("in main thread finished");
        scheduledThreadPool.shutdown();
    }
}
