package net.btnz.pri.java.myjavabasetest.keyword.volatilekey;

/**
 * Created by zhangsongwei on 2016/12/26.
 */

/**
 * 利用volatile关键字的，可见性，即，访问的值能保证是最新的
 */
public class AsFlag {
    private volatile boolean shutdownRequested;

    public void shutdown() {
        this.shutdownRequested = true;
    }

    public void doWork() {
        while (!shutdownRequested) {
            // do stuff
        }
    }
}
