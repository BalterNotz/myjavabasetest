package net.btnz.pri.java.myjavabasetest.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by BalterNotz on 2017/1/10.
 */

/**
 * future 没有关联任何的 Callback 、线程池、
 * 异步任务等，如果客户端调用 future.get 就会一致傻等下去。
 * 你可以通过下面的代码完成一个计算，触发客户端的等待：
 */
public class BasicMain {
    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }
    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> f = compute();
        class Client extends Thread {
            CompletableFuture<Integer> f;
            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }
            public void run() {
                try{
                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        new Client("Client1", f).start();
        new Client("Client2", f).start();
        System.out.println("Waiting");
        TimeUnit.MILLISECONDS.sleep(2000);
        f.complete(100);
        System.out.println("after");
//        f.completeExceptionally(new Exception());
    }
}
