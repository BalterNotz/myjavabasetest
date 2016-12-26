package net.btnz.pri.java.myjavabasetest.keyword.volatilekey;

/**
 * Created by zhangsongwei on 2016/12/26.
 */
public class VolatileExample2 extends Thread {
    private static volatile boolean flag = false;
    public void run() {
        System.out.println("Begin! VolatileExample2");
        while (!flag) {
        }
        System.out.println("End!");
    }

    public static void main(String[] args) throws InterruptedException {
        new VolatileExample2().start();
        Thread.sleep(100);
        flag = true;
        System.out.println("Finished!");
    }
}
