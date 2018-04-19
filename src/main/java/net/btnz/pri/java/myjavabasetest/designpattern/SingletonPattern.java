package net.btnz.pri.java.myjavabasetest.designpattern;

/**
 * Created by BalterNotz on 2016/12/6.
 * The Singleton design pattern: you can never instantiate more than one.
 * Since this isn't inherited from a Cloneable base class and
 * cloneability isn't added, making it final prevents cloneability
 * from being added in any derived classes:
 * 当不重载Object类中的clone方法时，无法在类外访问clone方法，因为不在同一个包下。
 */
final class Singleton {
    private static Singleton s = new Singleton(47);
    private int i;
    private Singleton(int x){i = x;}
    public static Singleton getHandle() {
        return s;
    }
    public int getValue() {return i;}
    public void setValue(int x) {i = x;}
}
public class SingletonPattern {
    public static void main(String[] args){
        Singleton s = Singleton.getHandle();
        System.out.println(s.getValue());
        Singleton s2 = Singleton.getHandle();
        s2.setValue(9);
        System.out.println(s.getValue());
        try{
            // Can't do this: compile-time error.
//             Singleton s3 = (Singleton)s2.clone();
        }catch (Exception e){}
    }
}
