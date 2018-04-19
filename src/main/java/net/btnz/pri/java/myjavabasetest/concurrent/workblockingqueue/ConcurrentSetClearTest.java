package net.btnz.pri.java.myjavabasetest.concurrent.workblockingqueue;

import com.alibaba.fastjson.JSON;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentSetClearTest {


    public static void main(String... args) throws InterruptedException {

        ConcurrentSetClearTest concurrentSetClearTest = new ConcurrentSetClearTest();
        concurrentSetClearTest.test();

        while (true) {
            Thread.sleep(5000);
        }
    }

    public void test() {
        Set<String> set = new ConcurrentSkipListSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(String.valueOf(i));
        }

        ExecutorService clearService = Executors.newSingleThreadExecutor();
        clearService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("before clear: " + JSON.toJSONString(set));
            set.clear();
            System.out.println("after clear: " + JSON.toJSONString(set));
        });

        Executors.newSingleThreadExecutor().submit(() -> {
            for (;;) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Scan: " + set.contains("9"));
            }
        });
    }
}
