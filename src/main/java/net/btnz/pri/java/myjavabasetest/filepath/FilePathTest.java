package net.btnz.pri.java.myjavabasetest.filepath;/**
 * Created by zhangsongwei on 2017/1/19.
 */

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 对文件路径的获取与改变等进行练习
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/19 13:56
 * <p>
 * Properties props=System.getProperties(); //系统属性
 * System.out.println("Java的运行环境版本："+props.getProperty("java.version"));
 * System.out.println("Java的运行环境供应商："+props.getProperty("java.vendor"));
 * System.out.println("Java供应商的URL："+props.getProperty("java.vendor.url"));
 * System.out.println("Java的安装路径："+props.getProperty("java.home"));
 * System.out.println("Java的虚拟机规范版本："+props.getProperty("java.vm.specification.version"));
 * System.out.println("Java的虚拟机规范供应商："+props.getProperty("java.vm.specification.vendor"));
 * System.out.println("Java的虚拟机规范名称："+props.getProperty("java.vm.specification.name"));
 * System.out.println("Java的虚拟机实现版本："+props.getProperty("java.vm.version"));
 * System.out.println("Java的虚拟机实现供应商："+props.getProperty("java.vm.vendor"));
 * System.out.println("Java的虚拟机实现名称："+props.getProperty("java.vm.name"));
 * System.out.println("Java运行时环境规范版本："+props.getProperty("java.specification.version"));
 * System.out.println("Java运行时环境规范供应商："+props.getProperty("java.specification.vender"));
 * System.out.println("Java运行时环境规范名称："+props.getProperty("java.specification.name"));
 * System.out.println("Java的类格式版本号："+props.getProperty("java.class.version"));
 * System.out.println("Java的类路径："+props.getProperty("java.class.path"));
 * System.out.println("加载库时搜索的路径列表："+props.getProperty("java.library.path"));
 * System.out.println("默认的临时文件路径："+props.getProperty("java.io.tmpdir"));
 * System.out.println("一个或多个扩展目录的路径："+props.getProperty("java.ext.dirs"));
 * System.out.println("操作系统的名称："+props.getProperty("os.name"));
 * System.out.println("操作系统的构架："+props.getProperty("os.arch"));
 * System.out.println("操作系统的版本："+props.getProperty("os.version"));
 * System.out.println("文件分隔符："+props.getProperty("file.separator"));   //在 unix 系统中是＂／＂
 * System.out.println("路径分隔符："+props.getProperty("path.separator"));   //在 unix 系统中是＂:＂
 * System.out.println("行分隔符："+props.getProperty("line.separator"));   //在 unix 系统中是＂/n＂
 * System.out.println("用户的账户名称："+props.getProperty("user.name"));
 * System.out.println("用户的主目录："+props.getProperty("user.home"));
 * System.out.println("用户的当前工作目录："+props.getProperty("user.dir"));
 */
public class FilePathTest {
    public static void main(String[] args) throws Exception {
        //利用System.getProperty()函数获取当前路径
        System.out.println(System.getProperty("user.dir")); //user.dir指定了当前的路径 默认当前程序工作目录
        /**
         * 通过System.setProperty("user.dir", "***")的方式，不能改变当前进程的工作目录。
         * 需要自己拼接路径。。。去创建文件或目录。
         */
        System.setProperty("user.dir", System.getProperty("user.dir") + "\\abc\\");
        System.out.println(System.getProperty("user.dir"));
        File file = new File("def.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        fileWriter.close();
        System.out.println(file.getAbsolutePath());

        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(FilePathTest.class.getResource("/").getPath());
        System.out.println(FilePathTest.class.getResource(""));

        //使用File提供的函数获取当前路径
        File dir = new File("");
        System.out.println(dir.getCanonicalPath()); //获取标准的路径
        System.out.println(dir.getAbsolutePath()); //获取绝对路径
    }
}
