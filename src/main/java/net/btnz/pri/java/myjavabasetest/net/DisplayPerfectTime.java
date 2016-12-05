package net.btnz.pri.java.myjavabasetest.net;

import java.rmi.Naming;

/**
 * Created by zhangsongwei on 2016/12/5.
 */
public class DisplayPerfectTime {
    public static void main(String[] args){
        try{
            PerfectTimeI t = (PerfectTimeI) Naming.lookup("//localhost:2225/PerfectTime");
            for(int i = 0; i < 10; i++){
                System.out.println("Perfect time = " + t.getPerfectTime());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
