package net.btnz.pri.java.myjavabasetest.transparams;

/**
 * Created by zhangsongwei on 2016/11/30.
 */
//Aliasing two handles to one object
public class Alisa1 {
    int i;
    Alisa1(int ii){i = ii;}
    public static void main(String[] args){
        Alisa1 x = new Alisa1(7);
        Alisa1 y = x; //Assign the handle
        System.out.println("x: " + x.i);
        System.out.println("y: " + y.i);
        System.out.println("Incrementing x");
        x.i++;
        System.out.println("x: " + x.i);
        System.out.println("y: " + y.i);
    }
}
