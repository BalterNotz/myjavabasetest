package net.btnz.pri.java.myjavabasetest.concurrent.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by BalterNotz on 2017/1/10.
 */
public class Main {
    private static Random random = new Random();
    private static long t = System.currentTimeMillis();
    static int getMoreData() {
        System.out.println("Begin to Start compute");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End of Start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return random.nextInt(1000);
    }
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main::getMoreData);
        Future<Integer> f = future.whenComplete((v,e) -> {
            System.out.println(v);
            System.out.println(e);
        });
        System.out.println(f.get());
    }
}
