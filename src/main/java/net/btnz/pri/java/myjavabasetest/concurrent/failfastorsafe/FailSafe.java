package net.btnz.pri.java.myjavabasetest.concurrent.failfastorsafe;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangsongwei on 2016/12/23.
 */
public class FailSafe {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> premiumPhone = new ConcurrentHashMap<String, String>();
        premiumPhone.put("Apple", "iPhone");
        premiumPhone.put("HTC", "HTC one");
        premiumPhone.put("Samsung", "SS");

        try {
            Iterator iterator = premiumPhone.keySet().iterator();
            while (iterator.hasNext()) {
                System.out.println(premiumPhone.get(iterator.next()));
                premiumPhone.put("Samsung 2", "Samsung Z");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.out.println("\n");
        for (String key : premiumPhone.keySet()) {
            System.out.println(premiumPhone.get(key));
            premiumPhone.put("AA", "Xperia ZZ");
        }
        System.out.println("\n");
        for (String key : premiumPhone.keySet()) {
            System.out.println(premiumPhone.get(key));
        }
    }
}
