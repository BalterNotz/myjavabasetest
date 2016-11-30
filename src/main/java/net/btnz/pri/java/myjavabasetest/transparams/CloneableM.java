package net.btnz.pri.java.myjavabasetest.transparams;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by zhangsongwei on 2016/11/30.
 */
/**
 * 因为Vector clone方法是浅复制，所以这里Vector的clone方法只复制了IntM对象的引用
 * 如果要对Vector对象进行深度复制，需要进行编历Vector对象，并对Vector对象中的元素进行复制
 * HashTable也是这样。
 */
class IntM implements Cloneable {
    private int i;
    public IntM(int ii) {i = ii;}
    public void increment() {i++;}
    public String toString() {
        return Integer.toString(i);
    }
    public IntM clone() throws CloneNotSupportedException {
        IntM intM = (IntM) super.clone();
        intM.i = i;
        return intM;
    }
}

public class CloneableM {
    public static void main(String[] args) {
        Vector v = new Vector();
        for(int i = 0; i < 10; i++){
            v.addElement(new IntM(i));
        }
        System.out.println("v: " + v);
        Vector v2 = (Vector) v.clone();
        for(Enumeration e = v2.elements(); e.hasMoreElements();){
            ((IntM)e.nextElement()).increment();
        }
        //See if it changed v's elements:
        System.out.println("v: " + v);
    }
}