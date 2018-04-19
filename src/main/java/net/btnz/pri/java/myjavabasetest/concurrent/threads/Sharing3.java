package net.btnz.pri.java.myjavabasetest.concurrent.threads;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by BalterNotz on 2016/12/3.
 * Problems with resource sharing while threading
 */
class TwoCounter3 extends Thread {
    private boolean started = false;
    private TextField t1 = new TextField(5);
    private TextField t2 = new TextField(5);
    private Label l = new Label("count1 == count2");
    private int count1 = 0;
    private int count2 = 0;

    // Add the display components as a panel to the given container:
    public TwoCounter3(Container c) {
        Panel p = new Panel();
        p.add(t1);
        p.add(t2);
        p.add(l);
        c.add(p);
    }

    public void start() {
        if (started) {
            return;
        }
        started = true;
        super.start();
    }

    public void run() {
        while (true) {
            synchronized (this) {
                t1.setText(Integer.toString(count1++));
                t2.setText(Integer.toString(count2++));
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void synchTest() {
        Sharing3.incrementAccess();
        if (count1 != count2) {
            l.setText("Unsynched");
        }
    }
}

class Watcher3 extends Thread {
    private Sharing3 p;

    public Watcher3(Sharing3 p) {
        this.p = p;
        start();
    }

    public void run() {
        while (true) {
            for (int i = 0; i < p.s.length; i++) {
                p.s[i].synchTest();
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }
}

public class Sharing3 extends Applet {
    TwoCounter3[] s;
    private static int accessCount = 0;
    private static TextField aCount = new TextField("0", 10);

    public static void incrementAccess() {
        accessCount++;
        aCount.setText(Integer.toString(accessCount));
    }

    private Button start = new Button("Start");
    private Button observer = new Button("Observe");
    private boolean isApplet = true;
    private int numCounters = 0;
    private int numObservers = 0;

    public void init() {
        if (isApplet) {
            numCounters = Integer.parseInt(getParameter("size"));
            numObservers = Integer.parseInt(getParameter("observers"));
        }
        s = new TwoCounter3[numCounters];
        for (int i = 0; i < s.length; i++) {
            s[i] = new TwoCounter3(this);
        }
        Panel p = new Panel();
        start.addActionListener(new StartL());
        p.add(start);
        observer.addActionListener(new ObserverL());
        p.add(observer);
        p.add(new Label("Access Count"));
        p.add(aCount);
        add(p);
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < s.length; i++) {
                s[i].start();
            }
        }
    }

    class ObserverL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < numObservers; i++) {
                new Watcher3(Sharing3.this);
            }
        }
    }

    public static void main(String[] args) {
        Sharing3 applet = new Sharing3();
        // This isn't an applet, so set the flag and
        // produce the parameter values form args:
        applet.isApplet = false;
        applet.numCounters = args.length == 0 ? 5 : Integer.parseInt(args[0]);
        applet.numObservers = args.length < 2 ? 5 : Integer.parseInt(args[1]);
        Frame aFrame = new Frame("Sharing3");
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
        aFrame.setSize(350, applet.numCounters * 100);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}
