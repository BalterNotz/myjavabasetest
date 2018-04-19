package net.btnz.pri.java.myjavabasetest.concurrent.threadpool.singlethreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangsongwei on 2016/12/21.
 */
public class MySingleThreadPool {
    public static void main(String[] args){
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 10; i++){
            final int index = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("index " + Thread.currentThread().getId());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        singleThreadPool.shutdown();
    }
}
