package net.btnz.pri.java.myjavabasetest.temp;

import java.util.HashSet;

public class InitObject {
    public static void main(String... args) {
        Pojo pojo = new Pojo();
        System.out.println("POJO: " + pojo.strRef);
        System.out.println("POJO: " + pojo.setRef);
        System.out.println("POJO: " + pojo.i);
        System.out.println("POJO: " + pojo.ii);
        System.out.println("POJO: " + pojo.iii);

        pojo.setRef.set(new HashSet<>());
        System.out.println("POJO: " + pojo.setRef);
        pojo.setRef.getAndSet(new HashSet<>());
        System.out.println("POJO: " + pojo.setRef);
        pojo.setRef.get().add("abc");
        System.out.println("POJO: " + pojo.setRef);
    }
}
