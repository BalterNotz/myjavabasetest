package net.btnz.pri.java.myjavabasetest.keyword.volatilekey;

/**
 * Created by zhangsongwei on 2016/12/26.
 */
public class VolatileExample1 extends Thread {
    private static boolean flag = false;

    public void run() {
        System.out.println("Begin! VolatileExample1");
        while (!flag) {
            System.out.println("volatile");
        }
        System.out.println("End!");
    }

    public static void main(String[] args) throws InterruptedException {
        new VolatileExample1().start();
        Thread.sleep(100);
        flag = true;
        System.out.println("Finished!");
    }
}
