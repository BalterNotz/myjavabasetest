package net.btnz.pri.java.myjavabasetest.proxy;/**
 * Created by zhangsongwei on 2017/1/22.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/22 11:39
 */
public class DynamicProxyDemo01 {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject(); //创建委托对象
        ProxyHandler handler = new ProxyHandler(realSubject); //创建调用处理器对象
        //动态生成代理对象
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(), RealSubject.class.getInterfaces(), handler);

        proxySubject.request(); //通过代理对象调用方法
    }
}

/**
 * 接口
 */
interface Subject {
    void request();
}

/**
 * 委托类
 */
class RealSubject implements Subject {
    public void request() {
        System.out.println("=== RealSubject Request ===");
    }
}

class ProxyHandler implements InvocationHandler {
    private Subject subject;

    ProxyHandler(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("=== before ===");
        Object result = method.invoke(subject, args);
        System.out.println("=== after ===");
        return result;
    }
}