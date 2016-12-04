package net.btnz.pri.java.myjavabasetest.transparams;

import java.util.Vector;

/**
 * Created by BalterNotz on 2016/12/1.
 */
class IntValue {
    int n;
    IntValue(int x){
        n = x;
    }
    public String toString() {
        return Integer.toString(n);
    }
}
public class MutableInteger {
    public static void main(String[] args) {
        Vector v = new Vector();
        for(int i = 0; i < 10; i++){
            v.addElement(new IntValue(i));
        }
        System.out.println(v);
        for(int i = 0; i < v.size(); i++){
            ((IntValue) (v.elementAt(i))).n++;
        }
        System.out.println(v);
    }
}
