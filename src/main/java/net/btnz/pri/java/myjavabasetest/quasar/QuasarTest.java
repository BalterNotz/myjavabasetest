package net.btnz.pri.java.myjavabasetest.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;


/**
 * -javaagent:path-to-quasar-jar.jar
 */
public class QuasarTest {

    public static void main(String... args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread: " + Thread.currentThread());
            }
        }.start();

        new Fiber<Void>() {
            @Override
            protected Void run() throws SuspendExecution, InterruptedException {
                System.out.println("Fiber: " + Fiber.currentFiber());
                return null;
            }
        }.start();
    }
}
