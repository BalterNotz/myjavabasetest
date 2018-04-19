package net.btnz.pri.java.myjavabasetest.transparams;

/**
 * Created by BalterNotz on 2016/12/1.
 */
public class Stringer {
    static String upcase(String s) {
        return s.toUpperCase();
    }
    static StringBuffer other(StringBuffer sb) {
        sb.append("def");
        return sb;
    }
    public static void main(String[] args) {
        String q = new String("howdy");
        System.out.println(q);
        String qq = upcase(q);
        System.out.println(qq);
        System.out.println(q);

        StringBuffer sb = new StringBuffer();
        sb.append("abc");
        System.out.println(sb);
        other(sb);
        System.out.println(sb);
    }
}
