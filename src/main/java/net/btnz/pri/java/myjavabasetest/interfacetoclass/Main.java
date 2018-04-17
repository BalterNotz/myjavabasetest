package net.btnz.pri.java.myjavabasetest.interfacetoclass;

import java.lang.reflect.Field;
import java.util.Vector;

public class Main {
    public static void main(String... args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Hello World!");
        Class clazz = Class.forName("net.btnz.pri.java.myjavabasetest.interfacetoclass.InterfaceDemo");
        System.out.println(clazz);
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println(classLoader);
        Class[] classs = clazz.getDeclaredClasses();
        System.out.println(classs);
        ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(threadClassLoader);
        Class threadClassLoaderClass = threadClassLoader.getClass();
        Class classLoaderClass = classLoader.getClass();
        System.out.println(classLoaderClass);
        while (classLoaderClass != ClassLoader.class) {
            classLoaderClass = classLoaderClass.getSuperclass();
            System.out.println(classLoaderClass);
        }
        while (threadClassLoaderClass != ClassLoader.class) {
            threadClassLoaderClass = threadClassLoaderClass.getSuperclass();
            System.out.println(threadClassLoaderClass);
        }

        Field field = ClassLoader.class.getDeclaredField("classes");
        System.out.println(field);
        field.setAccessible(true);

        Vector vector = (Vector) field.get(Main.class.getClassLoader());
    }
}
