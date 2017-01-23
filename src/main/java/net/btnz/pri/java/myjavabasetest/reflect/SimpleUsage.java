package net.btnz.pri.java.myjavabasetest.reflect;/**
 * Created by zhangsongwei on 2017/1/22.
 *
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/22 15:16
 */

/**
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/22 15:16
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * source: http://www.infoq.com/cn/articles/cf-java-reflection-dynamic-proxy
 */
public class SimpleUsage {
    public static void main(String[] args) {
        MyClass myClass = new MyClass(0); //一般做法
        myClass.increase(2);
        System.out.println("Normal -> " + myClass.count);

        try {
            Constructor constructor = MyClass.class.getConstructor(int.class); //获取构造函数
            MyClass myClassReflect = (MyClass) constructor.newInstance(0); //创建对象
            Method method = MyClass.class.getMethod("increase", int.class); //获取方法
            method.invoke(myClassReflect, 5); //调用方法
            Field field = MyClass.class.getField("count"); //获取域
            System.out.println("Reflect -> " + field.getInt(myClassReflect)); //获取域的值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyClass {
    public int count;

    public MyClass(int start) {
        count = start;
    }

    public void increase(int step) {
        count += step;
    }
}

interface MyIntfs {
    int get();

    int set(int i);
}

class MyClassIntfs implements MyIntfs {
    private int count;

    @Override
    public int get() {
        return count;
    }

    public int set(int i) {
        return this.count = i;
    }
}