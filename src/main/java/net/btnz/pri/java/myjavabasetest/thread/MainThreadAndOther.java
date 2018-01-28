package net.btnz.pri.java.myjavabasetest.thread;

/**
 stackoverflow上有个类似的问题：When does the main thread stop in Java?，把里面的答案总结一下，基本上就这么三句话：

 JVM会在所有的非守护线程（用户线程）执行完毕后退出；
 main线程是用户线程；
 仅有main线程一个用户线程执行完毕，不能决定JVM是否退出，也即是说main线程并不一定是最后一个退出的线程。
 */

public class MainThreadAndOther {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("ping-1");
                    }
                }
        );
        thread1.start();
        System.out.print("pong-1");
    }
}
