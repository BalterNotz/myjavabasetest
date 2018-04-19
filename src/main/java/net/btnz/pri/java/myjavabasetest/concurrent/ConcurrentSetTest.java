package net.btnz.pri.java.myjavabasetest.concurrent;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentSetTest {

    public static void main(String... args) throws InterruptedException {
        ExecutorService addService = Executors.newFixedThreadPool(1);
        ExecutorService removeService = Executors.newFixedThreadPool(1);
        ExecutorService getService = Executors.newFixedThreadPool(1);

        Set<String> set = new ConcurrentSkipListSet<>();

        for (int i = 0; i < 10; i++) {
            set.add(String.valueOf(i));
        }

        addService.submit(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            while (true) {
                for (int i = 0; i < 10; i++) {
                    set.add(String.valueOf(i));
                }
            }
        });
        removeService.submit(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            while (true) {
                set.clear();
            }
        });
        getService.submit(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            while (true) {
                for (String str : set) {
                    System.out.println(str);
                }
            }
        });

        while (true) {
            Thread.sleep(5000);
        }
    }
}
