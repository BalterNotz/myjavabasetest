package net.btnz.pri.java.myjavabasetest.java.lang.process;/**
 * Created by zhangsongwei on 2017/1/19.
 */

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.IOException;

/**
 * @description 对Java进程Process进行基本练习
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/19 13:45
 */
public class ProcessTest {
    private static Process process = null;

    public static void main(String[] args) throws Exception {
        process = testRuntimeExec();
        System.out.println(JSON.toJSONString(process));
        System.out.println(process.waitFor());
    }

    public static Process testRuntimeExec() throws IOException {
        System.out.println(System.getProperty("user.dir")); //user.dir指定了当前的路径
        System.out.println(ProcessTest.class.getResource("/").getPath());
        System.out.println(ProcessTest.class.getResource("").getPath());
        System.out.println(new File("").getAbsolutePath());
        process = Runtime.getRuntime().exec("D:\\Program Files\\Haskell Platform\\7.10.3\\bin\\runhaskell.exe D:\\msys64\\home\\zhangsongwei\\hello.hs");
        return process;
    }
    /**
     * hello.hs内容如下：
     #!/usr/bin/env runhaskell
     module Main where

     main :: IO()
     main = do
     putStrLn "Hello World!"
     writeFile "./abc.txt" "this is a test of Java runtime exec"
     */


}
