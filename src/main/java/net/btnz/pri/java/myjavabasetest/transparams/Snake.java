package net.btnz.pri.java.myjavabasetest.transparams;

/**
 * Created by zhangsongwei on 2016/11/30.
 */
//Tests cloning to see if destination of handles are also cloned.
//Java的super.clone是浅复制
public class Snake implements Cloneable {
    private Snake next;
    private char c;

    //Value of i == number of segments
    Snake(int i, char x) {
        c = x;
        if (--i > 0) {
            next = new Snake(i, (char) (x + 1));
        }
    }

    void increment() {
        c++;
        if (next != null) {
            next.increment();
        }
    }

    public String toString() {
        String s = ":" + c;
        if (next != null) {
            s += next.toString();
        }
        return s;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static void main(String[] args) {
        Snake s = new Snake(5, 'a');
        System.out.println("s = " + s);
        Snake s2 = (Snake)s.clone();
        System.out.println("s2 = " + s2);
        s.increment();
        System.out.println("after s.increment, s2 = " + s2);
    }
}
