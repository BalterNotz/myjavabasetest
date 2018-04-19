package net.btnz.pri.java.myjavabasetest.concurrent.threads;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by zhangsongwei on 2016/12/5.
 * Adjusting the priorities of threads
 */
class Ticker2 extends Thread {
    private Button b = new Button("Toggle");
    private Button incPriority = new Button("up");
    private Button decPriority = new Button("down");
    private TextField t = new TextField(10);
    private TextField pr = new TextField(3); // Display priority
    private int count = 0;
    private boolean runFlag = true;
    public Ticker2(Container c) {
        b.addActionListener(new ToggleL());
        incPriority.addActionListener(new UpL());
        decPriority.addActionListener(new DownL());
        Panel p = new Panel();
        p.add(t);
        p.add(pr);
        p.add(b);
        p.add(incPriority);
        p.add(decPriority);
        c.add(p);
    }
    class ToggleL implements ActionListener {
        public void actionPerformed(ActionEvent e){
            runFlag = !runFlag;
        }
    }
    class UpL implements ActionListener {
        public void actionPerformed(ActionEvent e){
            int newPrority = getPriority() + 1;
            if(newPrority > Thread.MAX_PRIORITY){
                newPrority = Thread.MAX_PRIORITY;
            }
            setPriority(newPrority);
        }
    }

    class DownL implements ActionListener {
        public void actionPerformed(ActionEvent e){
            int newPriority = getPriority() - 1;
            if(newPriority < Thread.MIN_PRIORITY){
                newPriority = Thread.MIN_PRIORITY;
            }
            setPriority(newPriority);
        }
    }
    public void run(){
        while(true){
            if(runFlag){
                t.setText(Integer.toString(count++));
                pr.setText(Integer.toString(getPriority()));
            }
            yield();
        }
    }
}
public class Counter5 extends Applet {
    private Button start = new Button("Start");
    private Button upMax = new Button("Inc Max Priority");
    private Button downMax = new Button("Dec Max Priority");
    private boolean started = false;
    private static final int SIZE = 3;
    private Ticker2[] s = new Ticker2[SIZE];
    private TextField mp = new TextField(3);
    public void init() {
        for(int i = 0; i < s.length; i++){
            s[i] = new Ticker2(this);
        }
        add(new Label("MAX_PRIORITY = " + Thread.MAX_PRIORITY));
        add(new Label("MIN_PRIORITY = " + Thread.MIN_PRIORITY));
        add(new Label("Group Max Priority = "));
        add(mp);
        add(start);
        add(upMax);
        add(downMax);
        start.addActionListener(new StartL());
        upMax.addActionListener(new UpMaxL());
        downMax.addActionListener(new DownMaxL());
        showMaxPriority();
        // Recursively disply parent thread groups:
        ThreadGroup parent = s[0].getThreadGroup().getParent();
        while(parent != null){
            add(new Label("Parent threadgroup max priority = " + parent.getMaxPriority()));
            parent = parent.getParent();
        }
    }
    public void showMaxPriority(){
        mp.setText(Integer.toString(s[0].getThreadGroup().getMaxPriority()));
    }
    class StartL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(started) return;
            for(int i = 0; i < s.length; i++){
                s[i].start();
            }
        }
    }
    class UpMaxL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int maxp = s[0].getThreadGroup().getMaxPriority();
            if(++maxp > Thread.MAX_PRIORITY){
                maxp = Thread.MAX_PRIORITY;
            }
            s[0].getThreadGroup().setMaxPriority(maxp);
            showMaxPriority();
        }
    }
    class DownMaxL implements ActionListener {
        public void actionPerformed(ActionEvent e){
            int maxp = s[0].getThreadGroup().getMaxPriority();
            if(--maxp < Thread.MIN_PRIORITY){
                maxp = Thread.MIN_PRIORITY;
            }
            s[0].getThreadGroup().setMaxPriority(maxp);
            showMaxPriority();
        }
    }
    public static void main(String[] args){
        Counter5 applet = new Counter5();
        Frame aFrame = new Frame("Counter5");
        aFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(300, 600);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}
