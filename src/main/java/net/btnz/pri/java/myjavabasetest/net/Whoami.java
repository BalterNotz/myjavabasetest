package net.btnz.pri.java.myjavabasetest.net;

import java.net.InetAddress;

/**
 * Created by zhangsongwei on 2016/12/5.
 */
public class Whoami {
    public static void main(String[] args) throws Exception {
        if(args.length != 1){
            System.err.println("Usage: Whoami MachineName");
            System.exit(-1);
        }
        InetAddress a = InetAddress.getByName(args[0]);
        System.out.println(a);
    }
}
