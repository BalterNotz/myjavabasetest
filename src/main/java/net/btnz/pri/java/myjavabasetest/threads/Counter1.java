package net.btnz.pri.java.myjavabasetest.threads;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by zhangsongwei on 2016/12/2.
 * A non-responsive user interface
 */
public class Counter1 extends Applet {
    private int count = 0;
    private Button onOff = new Button("Toggle");
    private Button start = new Button("Start");
    private TextField t = new TextField(10);
    private boolean runFlag = true;

    public void init(){
        add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
    }

    public void go(){
        while(true){
            try{
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {}
            if(runFlag){
                t.setText(Integer.toString(count++));
            }
        }
    }

    class StartL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }

    class OnOffL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }

    public static void main(String[] args){
        Counter1 applet = new Counter1();
        Frame aFrame = new Frame("Counter1");
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
        aFrame.setSize(300,200);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}
