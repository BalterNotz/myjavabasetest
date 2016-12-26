package net.btnz.pri.java.myjavabasetest.reflect;

/**
 * Created by zhangsongwei on 2016/11/29.
 */
interface HasBatteries {}
interface Waterproof {}
interface ShootsThings {}
class Toy {
    //Comment out the following default
    //constructor to see
    //NoSuchMethodError from (*1*)
    Toy() {}
    Toy(int i) {}
}
class FancyToy extends Toy implements HasBatteries, Waterproof, ShootsThings {
    FancyToy() {
        super(1);
    }
}
public class ToyTest {
    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("net.btnz.pri.java.myjavabasetest.rtti.FancyToy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        printInfo(c);
        Class[] faces = c.getInterfaces();
        for(int i = 0; i < faces.length; i++) {
            printInfo(faces[i]);
        }
        Class cy = c.getSuperclass();
        Object o = null;
        try {
            //Requires default constructor
            o = cy.newInstance();
        }catch (InstantiationException e ){
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        printInfo(o.getClass());
    }

    static void printInfo(Class cc) {
        System.out.println("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
    }
}
