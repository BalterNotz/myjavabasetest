package net.btnz.pri.java.myjavabasetest.transparams;

import java.util.Vector;

/**
 * Created by zhangsongwei on 2016/12/1.
 * The Integer class cannot be changed
 */
public class ImmutableInteger {
    public static void main(String[] args){
        Vector v = new Vector();
        for (int i = 0; i < 10; i++){
            v.addElement(new Integer(i));
        }
        // But how do you change the int
        // inside the Integer?
    }
}
