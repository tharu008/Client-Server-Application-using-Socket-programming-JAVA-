package com.application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateServer {

    private static final int port = 9090;

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(port);

        //listening serversocket making a connection
        //it creates or returns a socket object that correspond to particular connection that it has accepted
        System.out.println("[SERVER] waiting for client connection...");
        Socket client = listener.accept();
        System.out.println("[SERVER] connected to client!");


        //sending info to the client using a PrintWriter
        //autoflush true means it will send (date) as soon as you run the println statement at line 28.
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        String date = (new Date()).toString();
        System.out.println("[SERVER] sending date " +date);
        out.println(date);

        //close the socket
        System.out.println("[SERVER] sent date. Closing.");
        client.close();
        //close the serversocket(listener)
        listener.close();
    }
}
