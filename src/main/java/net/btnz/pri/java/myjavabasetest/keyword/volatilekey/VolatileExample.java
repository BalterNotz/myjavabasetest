package net.btnz.pri.java.myjavabasetest.keyword.volatilekey;

/**
 * Created by zhangsongwei on 2016/12/26.
 */

/**
 * volatile的第一条语义是保证线程间变量的可见性，简单地说就是当线程A对变量X进行了修改后，在线程A后面执行的其他线程能看到变量X的变动，更详细地说是要符合以下两个规则：
 * <p>
 * 线程对变量进行修改之后，要立刻回写到主内存。
 * 线程对变量读取的时候，要从主内存中读，而不是缓存。
 */
public class VolatileExample extends Thread {
    private static boolean flag = false;

    public void run() {
        System.out.println("Begin! VolatileExample");
        while (!flag) {
        }
        System.out.println("End!");
    }

    public static void main(String[] args) throws InterruptedException {
        new VolatileExample().start();
        Thread.sleep(100);
        flag = true;
        System.out.println("Finished!");
    }
}
