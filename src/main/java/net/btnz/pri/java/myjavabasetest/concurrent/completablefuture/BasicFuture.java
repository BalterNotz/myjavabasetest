package net.btnz.pri.java.myjavabasetest.concurrent.completablefuture;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * Created by BalterNotz on 2017/1/9.
 */
public class BasicFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> f = executorService.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(2000);
            return 100;
        });
        System.out.println("after submit");
        while (!f.isDone()){
            System.out.println("task not finished");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.println(f.get());
    }
}
