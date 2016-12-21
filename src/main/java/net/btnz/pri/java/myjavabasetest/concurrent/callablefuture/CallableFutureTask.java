package net.btnz.pri.java.myjavabasetest.concurrent.callablefuture;

import java.util.concurrent.*;

/**
 * Created by zhangsongwei on 2016/12/21.
 */
public class CallableFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        第一种方式，使用线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "callable " + Thread.currentThread().getId();
            }
        };
        FutureTask<String> futureTask = new FutureTask<String>(callable);


//        第二种方式，使用new Thread 和 FutureTask
//        FutureTask<String> futureTask = new FutureTask<String>(callable);
//        Thread thread = new Thread(futureTask);
//        thread.start();
        cachedThreadPool.submit(futureTask);
        cachedThreadPool.shutdown();
        System.out.println("in main thread");
        try{
            System.out.println("task's result: " + futureTask.get(10, TimeUnit.SECONDS));
        } catch (TimeoutException e){
            System.out.println("task is time out!!!");
        }
        System.out.println("in main thread all task run finished");
    }
}
