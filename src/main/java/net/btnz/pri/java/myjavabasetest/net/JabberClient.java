package net.btnz.pri.java.myjavabasetest.net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhangsongwei on 2016/12/5.
 * Very simple client that just sends
 * lines to the server and reads lines
 * that the server sends.
 */
public class JabberClient {
    public static void main(String[] args) throws IOException {
        // Passing null to getByName() produces the special "Local Loopback" IP address, for
        // testing on one machine w/o a network:
        InetAddress address = InetAddress.getByName(null);
        // Alternatively, you can use the address or name:
        // InetAddress address = InetAddress.getByName("127.0.0.1");
        // InetAddress address = InetAddress.getByName("localhost");
        System.out.println("address = " + address);
        Socket socket = new Socket(address, JabberServer.PORT);
        // Guard everything in a try-finally to make sure that socket is closed:
        try{
            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Output is automatically flushed by PrintWriter:
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            for(int i = 0; i < 10; i++){
                out.println("howdy " + i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}
