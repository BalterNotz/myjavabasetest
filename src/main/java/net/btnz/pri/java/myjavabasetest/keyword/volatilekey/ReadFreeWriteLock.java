package net.btnz.pri.java.myjavabasetest.keyword.volatilekey;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by zhangsongwei on 2016/12/26.
 */

/**
 * 读操作需要保持可见性，写需要保持原子性
 * 如果超越了该模式的最基本应用，结合这两个竞争的同步机制将变得非常困难。
 */
@ThreadSafe
public class ReadFreeWriteLock {
    // Employs the cheap read-write lock trick
    // All mutative operations MUST be done with the 'this' lock held
    @GuardedBy("this")
    private volatile int value;
    public int getValue(){
        return value;
    }
    public synchronized int increment(){
        return value++;
    }
}
