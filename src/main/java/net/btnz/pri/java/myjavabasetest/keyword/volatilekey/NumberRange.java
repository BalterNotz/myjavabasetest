package net.btnz.pri.java.myjavabasetest.keyword.volatilekey;

import net.jcip.annotations.NotThreadSafe;

/**
 * Created by zhangsongwei on 2016/12/26.
 */

/**
 * 例如，如果初始状态是 (0, 5)，同一时间内，线程 A 调用 setLower(4) 并且线程 B 调用 setUpper(3)，显然这两个操作交叉存入的值是不符合条件的，那么两个线程都会通过用于保护不变式的检查，使得最后的范围值是 (4, 3) —— 一个无效值。至于针对范围的其他操作，我们需要使 setLower() 和 setUpper() 操作原子化 —— 而将字段定义为 volatile 类型是无法实现这一目的的。
 */
@NotThreadSafe
public class NumberRange {
    private int lower, upper;

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public void setLower(int value) {
        if (value > upper)
            throw new IllegalArgumentException("");
        lower = value;
    }

    public void setUpper(int value) {
        if (value < lower)
            throw new IllegalArgumentException("");
        upper = value;
    }
}
