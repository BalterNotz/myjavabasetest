package net.btnz.pri.java.myjavabasetest.concurrent.failfastorsafe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhangsongwei on 2016/12/23.
 */
public class FailFast {
    public static void main(String[] args){
        Map<String, String> premiumPhone = new HashMap();
        premiumPhone.put("Applo", "iPhone");
        premiumPhone.put("HTC", "HTC one");
        premiumPhone.put("Samsung", "SS");

        try {
            Iterator iterator = premiumPhone.keySet().iterator();
            while (iterator.hasNext()) {
                System.out.println(premiumPhone.get(iterator.next()));
                premiumPhone.put("Sony", "Xperia Z");
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
        for(String key : premiumPhone.keySet()){
            System.out.println(premiumPhone.get(key));
            premiumPhone.put("Two Sony", "Xperia ZZ");
        }
    }
}
