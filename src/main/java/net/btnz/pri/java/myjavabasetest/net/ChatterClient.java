package net.btnz.pri.java.myjavabasetest.net;

import java.io.IOException;
import java.net.*;

/**
 * Created by zhangsongwei on 2016/12/5.
 * Tests the ChatterServer by starting multiple clients, each of which sends datagrams.
 */
public class ChatterClient extends Thread {
    // Can listen & send on the same socket:
    private DatagramSocket s;
    private InetAddress hostAddress;
    private byte[] buf = new byte[1000];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
    private int id;

    public ChatterClient(int identifier){
        id = identifier;
        try{
            // Auto-assign port number:
            s = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
        }catch (UnknownHostException e1){
            System.err.println("Cannot find host");
            System.exit(-1);
        } catch (SocketException e2) {
            System.err.println("Can't open socket");
            e2.printStackTrace();
            System.exit(-2);
        }
        System.out.println("ChatterClient starting");
    }
    public void run(){
        try{
            for(int i = 0; i < 25; i++) {
                String outMessage = "Client #" + id + ", message #" + i;
                // Make and send a datagram:
                s.send(Dgram.toDatagram(outMessage, hostAddress, ChatterServer.INPORT));
                // Block until it echoes back:
                s.receive(dp);
                // Print out the echoed contents:
                String rcvd = "Client #" + id + ", rcvd from " + dp.getAddress() + ", " + dp.getPort() + ": " + Dgram.toString(dp);
                System.out.println(rcvd);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public static void main(String[] args){
        for(int i = 0; i < 10; i++){
            new ChatterClient(i).start();
        }
    }
}
