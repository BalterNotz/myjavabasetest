package net.btnz.pri.java.myjavabasetest.designpattern;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by BalterNotz on 2016/12/6.
 */
abstract class Trash {
    private double weight;
    Trash(double wt) {weight = wt;}
    // Sums the value of Trash in a bin:
    static void sumValue(Vector bin){
        Enumeration e = bin.elements();
        double val = 0.0f;
        while(e.hasMoreElements()){
            // One kind of RTTI:
            // A dynamically-checked cast

        }
    }
}
public class RecycleA {
}
