package net.btnz.pri.java.myjavabasetest.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangsongwei on 2017/1/16.
 * <p>
 * 调用 wait(),notify(),notifyAll()时需要先对调用对象加锁。
 * notify(),notifyAll()方法被调用后，等待线程依然不会从wait()方法返回，而是等调用notify(),notifyAll()的线程释放该锁之后,等待线程才有机会从wait()返回。
 * notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而 notifyAll()方法则是把所有等待线程从等待队列中移到同步队列中，被移动的线程的状态由WAITING变成BLOCKED
 * 从wait()方法返回的前提是获得了调用对象的锁。
 * <p>
 * 等待方的原则:
 * 等待/通知机制依托于同步机制，其目的就是确保等待线程从wait()方法返回时能够感知到通知线程对变量的修改。
 * 1) 获取对象的锁
 * 2)如果条件不满足，那么调用锁的wait()方法，使该线程进入waiting，被通知后依然要检查条件
 * 条件满足则执行对应的逻辑
 * synchronized(对象){
 * while(条件不满足){
 * 对象.wait();
 * }
 * 对应的逻辑处理
 * }
 * <p>
 * 调用 wait(),notify(),notifyAll()时需要先对调用对象加锁。
 * notify(),notifyAll()方法被调用后，等待线程依然不会从wait()方法返回，而是等调用notify(),notifyAll()的线程释放该锁之后,等待线程才有机会从wait()返回。
 * notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而 notifyAll()方法则是把所有等待线程从等待队列中移到同步队列中，被移动的线程的状态由WAITING变成BLOCKED
 * 从wait()方法返回的前提是获得了调用对象的锁。
 * <p>
 * 等待方的原则:
 * 等待/通知机制依托于同步机制，其目的就是确保等待线程从wait()方法返回时能够感知到通知线程对变量的修改。
 * 1) 获取对象的锁
 * 2)如果条件不满足，那么调用锁的wait()方法，使该线程进入waiting，被通知后依然要检查条件
 * 条件满足则执行对应的逻辑
 * synchronized(对象){
 * while(条件不满足){
 * 对象.wait();
 * }
 * 对应的逻辑处理
 * }
 */

/**
 * 调用 wait(),notify(),notifyAll()时需要先对调用对象加锁。
 * notify(),notifyAll()方法被调用后，等待线程依然不会从wait()方法返回，而是等调用notify(),notifyAll()的线程释放该锁之后,等待线程才有机会从wait()返回。
 * notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而 notifyAll()方法则是把所有等待线程从等待队列中移到同步队列中，被移动的线程的状态由WAITING变成BLOCKED
 * 从wait()方法返回的前提是获得了调用对象的锁。
 */
/**
 * 等待方的原则:
 * 等待/通知机制依托于同步机制，其目的就是确保等待线程从wait()方法返回时能够感知到通知线程对变量的修改。
 * 1) 获取对象的锁
 * 2)如果条件不满足，那么调用锁的wait()方法，使该线程进入waiting，被通知后依然要检查条件
 * 条件满足则执行对应的逻辑
 * synchronized(对象){
      while(条件不满足){
      对象.wait();
     }
     对应的逻辑处理
   }
 */

/**
 * 通知方的原则:
 * 1) 获取对象的锁
 * 2)改变条件
 * 3)通知所有等待在该对象上的线程
 *   synchronized(对象){
        改变条件
        对象.notifyAll();
     }
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        public void run() {
            //加锁
            synchronized (lock) {
                //当条件不满足时，进行waitting状态，同时释放lock锁
                while (flag) {
                    System.out.println("flag is true");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //条件满足
                System.out.println("doSomething");
            }
        }
    }

    static class Notify implements Runnable {
        public void run() {
            synchronized (lock) {
                //获取lock的锁，然后进行通知，通知不会释放lock锁
                //直到发出通知的线程执行完毕释放了lock锁，WaitThread线程才能从wait主法返回
                lock.notifyAll();
                System.out.println("flag is false now");
                flag = false;
            }
        }
    }
}
