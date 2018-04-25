package net.btnz.pri.java.myjavabasetest.java.lang.enumtest;

public class EnumTest {
    public static void main(String...args) {
        Test t = Test.begin;
        System.out.println("" + t);
    }
}

enum Test {
    begin,end,start
}