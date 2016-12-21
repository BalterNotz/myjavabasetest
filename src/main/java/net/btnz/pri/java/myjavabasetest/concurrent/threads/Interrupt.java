package net.btnz.pri.java.myjavabasetest.concurrent.threads;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by BalterNotz on 2016/12/5.
 */
class Blocked extends Thread {
    public synchronized void run() {
        try{
            wait(); // Blocks
        }catch (InterruptedException e){
            System.out.println("InterrupedException");
        }
        System.out.println("Exiting run()");
    }
}
public class Interrupt extends Applet {
    private Button interrupt = new Button("Interrupt");
    private Blocked blocked = new Blocked();
    public void init() {
        add(interrupt);
        interrupt.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Button pressed");
                        if(blocked == null) return;
                        Thread remove = blocked;
                        blocked = null; // to release it
                        remove.interrupt();
                    }
                }
        );
        blocked.start();
    }
    public static void main(String[] args){
        Interrupt applet = new Interrupt();
        Frame aFrame = new Frame("Interrupt");
        aFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        System.exit(0);
                    }
                }
        );
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(200, 100);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}
