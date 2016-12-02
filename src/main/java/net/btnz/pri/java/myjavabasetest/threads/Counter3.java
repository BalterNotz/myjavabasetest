package net.btnz.pri.java.myjavabasetest.threads;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by zhangsongwei on 2016/12/2.
 * Using the Runnable interface to turn the main class into a thread
 */
public class Counter3 extends Applet implements Runnable {
    private int count = 0;
    private boolean runFlag = true;
    private Thread selfThread = null;
    private Button onOff = new Button("Toggle");
    private Button start = new Button("Start");
    private TextField t = new TextField(10);

    public void init() {
        add(t);
        onOff.addActionListener(new OnOff());
        add(onOff);
        start.addActionListener(new StartL());
        add(start);
    }

    @Override
    public void run() {
        while(true){
            try{
                selfThread.sleep(100);
            } catch (InterruptedException e){}
            if(runFlag){
                t.setText(Integer.toString(count++));
            }
        }
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(null == selfThread) {
                selfThread = new Thread(Counter3.this);
                selfThread.start();
            }
        }
    }

    class OnOff implements ActionListener {
        public void actionPerformed(ActionEvent e){
            runFlag = !runFlag;
        }
    }

    public static void main(String[] args){
        Counter3 applet = new Counter3();
        Frame frame = new Frame("Counter3");
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
