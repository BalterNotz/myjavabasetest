package net.btnz.pri.java.myjavabasetest.javaexplore.dynacompile;

import javax.tools.JavaFileObject;
import java.io.File;

/**
 * Created by zhangsongwei on 2016/12/7.
 */
public class AdvicePrintDemo {
    public static void main(String[] args){
        String demoFilePath = AdvicePrintDemo.class.getClassLoader().getResource("").getPath() + PrintDemo.class.getName().replace('.', File.separatorChar) + JavaFileObject.Kind.CLASS.extension;

        System.out.println(PrintDemo.class.getPackage().getName());
        System.out.println(demoFilePath);
    }
}
