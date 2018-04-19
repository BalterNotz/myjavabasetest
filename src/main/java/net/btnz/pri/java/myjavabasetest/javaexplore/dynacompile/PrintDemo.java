package net.btnz.pri.java.myjavabasetest.javaexplore.dynacompile;

/**
 * Created by zhangsongwei on 2016/12/7.
 */
public class PrintDemo {
    public static void main(String[] args) {
        char c = 'A';
        int i = 3;
        double d = 2.718;
        printChar(c);
        printInt(i);
        printDouble(d);
    }

    public static void printChar(char c) {
        System.out.println(c);
    }

    public static void printInt(int i) {
        System.out.println(i);
    }

    public static void printDouble(double d) {
        System.out.println(d);
    }
}
