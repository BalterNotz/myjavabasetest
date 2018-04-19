package net.btnz.pri.java.myjavabasetest.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangsongwei on 2017/1/16.
 */

/**
 * 当程序执行到m.interrupt()方法后，线程m将被中断并进入消亡状态。
 * 当线myThread线程处于阻塞状态时，调用myThread.interrupt()方法，将使myThread阻塞的代码抛出InterruptedException异常，使myThread线程退出阻塞代码。
 */
public class InterrupThead {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println("Starting thread...");
        myThread.start();
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.println("Interrupt thread...");
        myThread.interrupt();
        TimeUnit.MILLISECONDS.sleep(5000);
        System.out.println("Stopping application...");
    }
}

class MyThread extends Thread {
    public void run() {
        while(true){
            System.out.println(getName() + " is running");
            try{
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}