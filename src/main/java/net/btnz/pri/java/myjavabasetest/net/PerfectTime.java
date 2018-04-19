package net.btnz.pri.java.myjavabasetest.net;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by zhangsongwei on 2016/12/5.
 */
public class PerfectTime extends UnicastRemoteObject implements PerfectTimeI {
    protected PerfectTime() throws RemoteException {
        super();
    }

    @Override
    public long getPerfectTime() throws RemoteException {
        return System.currentTimeMillis();
    }
    // Registration for RMI serving:
    public static void main(String[] args){
        try{
            LocateRegistry.createRegistry(2225);
            PerfectTime pt = new PerfectTime();
            Naming.bind("//localhost:2225/PerfectTime", pt);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
//                Naming.unbind("//localhost:2005/PerfectTime");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
