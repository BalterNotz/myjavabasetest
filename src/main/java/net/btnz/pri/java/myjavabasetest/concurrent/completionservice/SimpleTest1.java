package net.btnz.pri.java.myjavabasetest.concurrent.completionservice;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by BalterNotz on 2017/1/7.
 */
public class SimpleTest1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);

        for(int i = 0; i < 5; i++) {
            completionService.submit(new Task());
        }
        executorService.shutdown();
        int count = 0, index = 1;
        while(count < 5) {
            Future<Integer> f = completionService.poll();
            if(f == null) {
                System.out.println(index + " 没有发现完成的任务");
            }else{
                System.out.println(index + " 产生了一个随机数：" + f.get());
                count++;
            }
            index++;
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        TimeUnit.SECONDS.sleep(random.nextInt(7));
        return random.nextInt();
    }
}