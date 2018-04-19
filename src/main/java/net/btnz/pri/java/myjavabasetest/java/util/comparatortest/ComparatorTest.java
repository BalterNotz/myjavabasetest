package net.btnz.pri.java.myjavabasetest.java.util.comparatortest;

import java.util.*;

public class ComparatorTest {
    public static void main(String...args) {
        List l = Arrays.asList(1,2,3);
        Comparator<Integer> comp = (c1, c2) -> c1 > c2 ? 1 : (c1 < c2 ? -1 : 0);
        Collections.sort(l, comp);
        System.out.println(l);
        Collections.sort(l, comp.reversed());
        System.out.println(l);
    }
}
