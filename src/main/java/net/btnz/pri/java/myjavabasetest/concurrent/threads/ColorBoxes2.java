package net.btnz.pri.java.myjavabasetest.concurrent.threads;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * Created by zhangsongwei on 2016/12/5.
 * Balancing thread use
 */
class CBox2 extends Canvas {
    private static final Color[] colors = {
            Color.black, Color.blue, Color.cyan,
            Color.darkGray, Color.gray, Color.green,
            Color.lightGray, Color.magenta,
            Color.orange, Color.pink, Color.red,
            Color.white, Color.yellow
    };
    private Color cColor = newColor();
    private static final Color newColor(){
        return colors[(int)(Math.random() * colors.length)];
    }
    void nextColor() {
        cColor = newColor();
        repaint();
    }
    public void paint(Graphics g){
        g.setColor(cColor);
        Dimension s = getSize();
        g.fillRect(0,0,s.width,s.height);
    }
}
class CBoxVector extends Vector implements Runnable {
    private Thread t;
    private int pause;
    public CBoxVector(int pause){
        this.pause = pause;
        t = new Thread(this);
    }
    public void go(){
        t.start();
    }
    public void run(){
        while(true){
            int i = (int)(Math.random()*size());
            ((CBox2)elementAt(i)).nextColor();
            try{
                t.sleep(pause);
            }catch (InterruptedException e){}
        }
    }
}
public class ColorBoxes2 extends Frame {
    private CBoxVector[] v;
    public ColorBoxes2(int pause, int grid){
        setTitle("ColorBoxes2");
        setLayout(new GridLayout(grid, grid));
        v = new CBoxVector[grid];
        for(int i = 0; i < grid; i++){
            v[i] = new CBoxVector(pause);
        }
        for(int i = 0; i < grid * grid; i++){
            v[i%grid].addElement(new CBox2());
            add((CBox2)v[i%grid].lastElement());
        }
        for(int i = 0; i < grid; i++){
            v[i].go();
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }
    public static void main(String[] args){
        // Shorter default pause than ColorBoxes:
        int pause = args.length > 0 ? Integer.parseInt(args[0]) : 5;
        int grid = args.length > 1 ? Integer.parseInt(args[0]) : 8;
        Frame f = new ColorBoxes2(pause, grid);
        f.setSize(500, 400);
        f.setVisible(true);
    }
}
