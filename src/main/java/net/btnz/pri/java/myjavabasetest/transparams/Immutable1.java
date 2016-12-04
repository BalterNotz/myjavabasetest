package net.btnz.pri.java.myjavabasetest.transparams;

import com.sun.org.apache.bcel.internal.generic.IMUL;

/**
 * Created by BalterNotz on 2016/12/1.
 */
public class Immutable1 {
    private int data;
    public Immutable1(int i) {
        data = i;
    }
    public int read() {
        return data;
    }
    public boolean nonezero() {
        return data != 0;
    }
    public Immutable1 quadruple() {
        return new Immutable1(data *4);
    }
    static void f(Immutable1 i1) {
        Immutable1 quad = i1.quadruple();
        System.out.println("i1 = " + i1.read());
        System.out.println("quad = " + quad.read());
    }
    public static void main(String[] args){
        Immutable1 x = new Immutable1(47);
        System.out.println("x = " + x.read());
        f(x);
        System.out.println("x = " + x.read());
    }
}
