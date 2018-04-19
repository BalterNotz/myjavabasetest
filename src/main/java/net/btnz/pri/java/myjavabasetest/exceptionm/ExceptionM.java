package net.btnz.pri.java.myjavabasetest.exceptionm;

/**
 * Created by zhangsongwei on 2016/11/21.
 */
public class ExceptionM {
    public static void main(String[] args) throws Exception {
        System.out.println(test());
    }

    public static String test() throws Exception {
        try {
            print("in try");
            throw new Exception("Exception");
//            return "returnreturn in try";
        } catch (Exception e) {
            throw e;
//            return print(e.getMessage());
        } finally {
//            return print("in finally");
        }
    }

    public static String print(String str) {
        System.out.println(str);
        return str;
    }

}
