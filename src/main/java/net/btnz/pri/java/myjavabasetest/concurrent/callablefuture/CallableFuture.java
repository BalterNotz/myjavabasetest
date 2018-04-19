package net.btnz.pri.java.myjavabasetest.concurrent.callablefuture;

import java.util.concurrent.*;

/**
 * Created by zhangsongwei on 2016/12/21.
 */
public class CallableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws InterruptedException {
                Thread.sleep(5000);
                return "callable";
            }
        };
        Future<String> result = (Future<String>) cachedThreadPool.submit(task);
        System.out.println("in main thread");
        System.out.println("task's reuslt is: " + result.get());
        System.out.println("in main thread all task run finished");
    }
}
