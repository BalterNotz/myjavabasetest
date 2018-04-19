package net.btnz.pri.java.myjavabasetest.concurrent.threads;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by zhangsongwei on 2016/12/2.
 * Counter2i using an inner class for the thread
 */
public class Counter2i extends Applet {
    private class SeparateSubTask extends Thread {
        int count = 0;
        boolean runFlag = true;

        SeparateSubTask() {
            start();
        }

        public void run() {
            while (true) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runFlag) {
                    t.setText(Integer.toString(count++));
                }
            }
        }
    }

    private SeparateSubTask sp = null;
    private TextField t = new TextField(10);
    private Button onOff = new Button("Toggle");
    private Button start = new Button("Start");

    public void init() {
        add(t);
        start.addActionListener(new StartL());
        onOff.addActionListener(new OnOffL());
        add(start);
        add(onOff);
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (null == sp) {
                sp = new SeparateSubTask();
            }
        }
    }

    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (null != sp) {
                sp.runFlag = !sp.runFlag;
            }
        }
    }

    public static void main(String[] args) {
        Counter2i applet = new Counter2i();
        Frame frame = new Frame("Counter2i");
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