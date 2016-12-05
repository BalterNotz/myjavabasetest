package net.btnz.pri.java.myjavabasetest.net;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by zhangsongwei on 2016/12/5.
 */
interface PerfectTimeI extends Remote {
    long getPerfectTime() throws RemoteException;
}
