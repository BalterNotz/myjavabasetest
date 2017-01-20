package net.btnz.pri.java.myjavabasetest.designpattern.proxy;/**
 * Created by zhangsongwei on 2017/1/20.
 */

/**
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/20 10:29
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        image.display();
        System.out.println("");
        image.display();
    }
}
