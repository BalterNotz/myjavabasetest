package net.btnz.pri.java.myjavabasetest.reflect.changeaccess;

import java.lang.reflect.Field;

/**
 * Created by zhangsongwei on 2016/12/26.
 */
public class CanChange {
    public static void main(String[] args){
        Object prey = new Prey();
        try{
            Field pf = prey.getClass().getDeclaredField("privateString");
            pf.setAccessible(true);
            pf.set(prey, "Test...");
            System.out.println(prey);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static class Prey{
        private String privateString = "privateString";
        @Override
        public String toString(){
            return privateString;
        }
    }
}
