package net.btnz.pri.java.myjavabasetest.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by zhangsongwei on 2016/12/5.
 */
public class ChatterServer {
    static final int INPORT = 1711;
    private byte[] buf = new byte[1000];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
    // Can listen & send on the same socket:
    private DatagramSocket socket;

    public ChatterServer() {
        try{
            socket = new DatagramSocket(INPORT);
            System.out.println("Server started");
            while(true){
                // Block until a datagram appears:
                socket.receive(dp);
                String rcvd = Dgram.toString(dp) + ", from address: " + dp.getAddress() + ", port: " + dp.getPort();
                System.out.println(rcvd);
                String echoString = "Echoed: " + rcvd;
                // Extract the address and port from the received datagram to find out where to send it back:
                DatagramPacket echo = Dgram.toDatagram(echoString, dp.getAddress(), dp.getPort());
                socket.send(echo);
            }
        }catch (SocketException e1) {
            System.err.println("Can't open socket");
            System.exit(-1);
        }catch (IOException e){
            System.err.println("Communication error");
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new ChatterServer();
    }
}
