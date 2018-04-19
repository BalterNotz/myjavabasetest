package net.btnz.pri.java.myjavabasetest.designpattern.proxy;/**
 * Created by zhangsongwei on 2017/1/20.
 */

/**
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/20 10:26
 */
public class ProxyImage implements Image {

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(null == realImage) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
