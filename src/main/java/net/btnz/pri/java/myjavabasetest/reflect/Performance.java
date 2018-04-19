package net.btnz.pri.java.myjavabasetest.reflect;/**
 * Created by zhangsongwei on 2017/1/20.
 */

/**
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/20 11:14
 * 对Java反射性能的小测试，抄自：http://stackoverflow.com/questions/1392351/java-reflection-why-is-it-so-slow
 */
public class Performance {
    public static long timeDiff(long old) {
        return System.currentTimeMillis() - old;
    }

    public static void main(String[] args) throws Exception {
        long numTrials = (long) Math.pow(10, 7); //指数运算10^7
        long millis;
        millis = System.currentTimeMillis();

        for(int i = 0; i < numTrials; i++) {
            new B();
        }
        System.out.println("Normal instaniation took: " + timeDiff(millis) + "ms");
        millis = System.currentTimeMillis();
        Class<B> c = B.class;
        for(int i = 0; i < numTrials; i++) {
            c.newInstance();
        }
        System.out.println("Reflecting instantiation took: " + timeDiff(millis) + "ms");

        System.out.println("use an array to maintain the created objects");
        B[] bs = new B[(int) numTrials];
        millis = System.currentTimeMillis();
        for(int i = 0; i < numTrials; i++) {
            bs[i] = new B();
        }
        System.out.println("Normal instaniation took: " + timeDiff(millis) + "ms");
        millis = System.currentTimeMillis();
        for(int i = 0; i < numTrials; i++) {
            bs[i] = c.newInstance();
        }
        System.out.println("Reflecting instantiation took: " + timeDiff(millis) + "ms");
    }
}

class B {}