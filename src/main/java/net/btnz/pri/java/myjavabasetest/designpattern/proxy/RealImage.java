package net.btnz.pri.java.myjavabasetest.designpattern.proxy;/**
 * Created by zhangsongwei on 2017/1/20.
 */

/**
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/20 10:20
 */
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }
}
