package net.btnz.pri.java.myjavabasetest.transparams;

/**
 * Created by zhangsongwei on 2016/11/30.
 */
//Method calls implicitly alias their arguments
public class Alisa2 {
    int i;
    Alisa2(int ii) {i = ii;}
    static void f(Alisa2 h) {
        h.i++;
    }
    public static void main(String[] args) {
        Alisa2 x = new Alisa2(7);
        System.out.println("x: " + x.i);
        System.out.println("Calling f(x)");
        f(x);
        System.out.println("x: " + x.i);
    }
}
