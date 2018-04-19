package net.btnz.pri.java.myjavabasetest.concurrent.threads;

/**
 * Created by zhangsongwei on 2016/12/5.
 * How threads can access other threads
 * in a parent thread group
 */
public class TestAccess {
    public static void main(String[] args){
        ThreadGroup x = new ThreadGroup("x");
        ThreadGroup y = new ThreadGroup(x, "y");
        ThreadGroup z = new ThreadGroup(y, "z");
        Thread one = new TestThread1(x, "one");
        Thread two = new TestThread2(z, "two");
    }
}

class TestThread1 extends Thread {
    private int i;
    TestThread1(ThreadGroup g, String name){
        super(g,name);
    }
    void f(){
        i++; // modify this thread
        System.out.println(getName() + " f()");
    }
}

class TestThread2 extends TestThread1 {
    TestThread2(ThreadGroup g, String name){
        super(g, name);
        start();
    }
    public void run(){
        ThreadGroup g = getThreadGroup().getParent().getParent();
        g.list();
        Thread[] gAll = new Thread[g.activeCount()];
        g.enumerate(gAll);
        for(int i = 0; i < gAll.length; i++){
            gAll[i].setPriority(Thread.MIN_PRIORITY);
            ((TestThread1)gAll[i]).f();
        }
        g.list();
    }
}