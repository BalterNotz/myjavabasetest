package net.btnz.pri.java.myjavabasetest.concurrent.threads;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by zhangsongwei on 2016/12/2.
 * A responsive user interface with threads
 */
class SeparateSubTask extends Thread {
    private int count = 0;
    private Counter2 c2;
    private boolean runFlag = true;

    public SeparateSubTask(Counter2 c2) {
        this.c2 = c2;
        start();
    }

    public void invertFlag() {
        runFlag = !runFlag;
    }

    public void run() {
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
            if (runFlag) {
                c2.t.setText(Integer.toString(count++));
            }
        }
    }
}

public class Counter2 extends Applet {
    TextField t = new TextField(10);
    private SeparateSubTask sp = null;
    private Button onOff = new Button("Toggle");
    private Button start = new Button("Start");

    public void init() {
        start.addActionListener(new StartL());
        onOff.addActionListener(new OnOffL());
        add(t);
        add(start);
        add(onOff);
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (null == sp) {
                sp = new SeparateSubTask(Counter2.this);
            }
        }
    }

    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (null != sp) {
                sp.invertFlag();
            }
        }
    }

    public static void main(String[] args) {
        Counter2 applet = new Counter2();
        Frame frame = new Frame("Counter2");
        frame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        System.exit(0);
                    }
                }
        );
        frame.add(applet, BorderLayout.CENTER);
        frame.setSize(300,200);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }
}
