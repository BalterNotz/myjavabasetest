package net.btnz.pri.java.myjavabasetest.asm;/**
 * Created by zhangsongwei on 2017/2/3.
 */

import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/2/3 15:29
 */
public class MyClassLoader extends ClassLoader implements Opcodes {
    public MyClassLoader() {
        super();
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    public Class<?> defineClass(String name, byte[] b) {
        return super.defineClass(name, b, 0, b.length);
    }
}
