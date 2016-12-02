package net.btnz.pri.java.myjavabasetest.threads;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by zhangsongwei on 2016/12/2.
 * If you separate your thread from the main class, you can have as many threads as you want.
 */
class Ticker extends Thread {
    private Button b = new Button("Toggle");
    private TextField t = new TextField(10);
    private int count = 0;
    private boolean runFlag = true;

    public Ticker(Container c) {
        b.addActionListener(new ToggleL());
        Panel p = new Panel();
        p.add(t);
        p.add(b);
        c.add(p);
    }

    class ToggleL implements ActionListener {
        public void actionPerformed(ActionEvent e){
            runFlag = !runFlag;
        }
    }

    public void run() {
        while(true){
            if(runFlag){
                t.setText(Integer.toString(count++));
            }
            try{
                sleep(100);
            } catch (InterruptedException e){}
        }
    }
}
public class Counter4 extends Applet {
    private Button start = new Button("Start");
    private boolean started = false;
    private Ticker[] s;
    private boolean isApplet = true;
    private int size;

    public void init() {
        // Get parameter "size" from Web page:
        if(isApplet){
            size = Integer.parseInt(getParameter("size"));
        }
        s = new Ticker[size];
        for(int i = 0; i < s.length; i++){
            s[i] = new Ticker(this);
        }
        start.addActionListener(new StartL());
        add(start);
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(!started){
                started = true;
                for (int i = 0; i < s.length; i++){
                    s[i].start();
                }
            }
        }
    }

    public static void main(String[] args){
        Counter4 applet = new Counter4();
        // This isn't an applet, so set the flag and
        // produce the parameter values from args:
        applet.isApplet = false;
        applet.size = args.length == 0 ? 5 :Integer.parseInt(args[0]);
        Frame frame = new Frame("Counter4");
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
        frame.setSize(200, applet.size * 50);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }
}
