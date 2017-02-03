package net.btnz.pri.java.myjavabasetest.asm;/**
 * Created by zhangsongwei on 2017/2/3.
 */

/**
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/2/3 18:35
 */
public class AopInteceptor {
    public static void before() {
        System.out.println(".......before().......");
    }

    public static void after() {
        System.out.println(".......after().......");
    }
}