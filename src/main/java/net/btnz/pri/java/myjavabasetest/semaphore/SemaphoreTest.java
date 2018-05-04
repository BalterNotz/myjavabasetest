package net.btnz.pri.java.myjavabasetest.semaphore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class SemaphoreTest {
    public static AtomicLong counter;
    private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();


    public static void main(String... args) {
        System.out.println("Hello World!");
    }
}

class Producer extends Thread {

    private String name;
    private BlockingQueue<String> queue;

    Producer(String name, BlockingQueue<String> queue) {
        this.name = name;
        this.queue = queue;
    }

    public void produce() throws InterruptedException {
        String put = String.valueOf(SemaphoreTest.counter.getAndAdd(1));
        queue.put(put);
        System.out.println(name + ": produce " + put);
    }

    @Override
    public void run() {
        try {
            produce();
            Thread.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    private String name;
    private BlockingQueue<String> queue;

    Consumer(String name, BlockingQueue<String> queue) {
        this.name = name;
        this.queue = queue;
    }

    public void consume() throws InterruptedException {
        String take = queue.take();
        System.out.println(name + ": Consumer " + take);
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}