package net.btnz.pri.java.myjavabasetest;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangsongwei on 2016/11/21.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter thread = new Counter();
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10000);

        thread.shutDown();
        System.out.println("10秒钟总结循环了：" + thread.count + " 次");

        System.out.println("Hello World!");
        Vector<Integer> vector = new Vector<Integer>();
        System.out.println("thread status: " + thread.isAlive());
    }
}

class Counter extends Thread {
    volatile private boolean flag = true;
    public long count = 0;

    public void run() {
        while(flag) {
            for(int i = 1; i <= 1000; i++){
                count += 1;
            }
        }
        System.out.println("sun thread end");
    }

    public boolean shutDown() {
        flag = false;
        return flag;
    }
}

class Helper extends Thread {

}