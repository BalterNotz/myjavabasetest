package net.btnz.pri.java.myjavabasetest.transparams;

/**
 * Created by zhangsongwei on 2016/11/30.
 */
class MyObject implements Cloneable {
    int i;

    public MyObject(int ii) {
        i = ii;
    }

    public Object clone() {
        Object myObject = null;
        try {
            myObject = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return myObject;
    }

    public String toString() {
        return Integer.toString(i);
    }
}

public class LocalCopy {
    static MyObject g(MyObject v) {
        // Passing a handle, modifies outside object
        v.i++;
        return v;
    }
    static MyObject f(MyObject v) {
        v = (MyObject) v.clone(); // Local copy
        v.i++;
        return v;
    }

    public static void main(String[] args) {
        MyObject a = new MyObject(11);
        MyObject b = g(a);
        //Testing handle equivalence, not object equivalence:
        if (a == b) {
            System.out.println("a == b");
        } else {
            System.out.println("a != b");
        }
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        MyObject c = new MyObject(47);
        MyObject d = f(c);
        if(c == d) {
            System.out.println("c == d");
        } else {
            System.out.println("c != d");
        }
        System.out.println("c = " + c);
        System.out.println("d = " + d);
    }
}
