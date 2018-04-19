package net.btnz.pri.java.myjavabasetest.reflect.changeaccess;

import java.lang.reflect.Field;
import java.security.Permission;

/**
 * Created by zhangsongwei on 2016/12/26.
 */
public class CantChange {
    static{
        try{
            System.setSecurityManager(new MySecurityManager());
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Object prey = new Prey();
        try{
            Field pf = prey.getClass().getDeclaredField("privateString");
            pf.setAccessible(true);
            pf.set(prey, "Test...");
            System.out.println(pf.get(prey));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static class Prey{
        private String privateString = "privateString";
    }

    static class MySecurityManager extends SecurityManager {
        public void checkPermission(Permission permission){
            if("suppressAccessChecks".equals(permission.getName())){
                throw new SecurityException("Can not change the permission dude.!");
            }
        }
    }
}
