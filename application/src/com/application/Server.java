package com.application;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int port = 9090;

    //store threads that's going to create
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws IOException {
        //ServerSocket creation
        ServerSocket listener = new ServerSocket(port);


        while (true) {
            //listening to ServerSocket and make a connection
            //it creates or returns a socket object that correspond to particular connection that it has accepted
            System.out.println("[SERVER] waiting for client connection...");
            //Server call will stop here. It waits and listens on port 9090 for an incoming client connection
            Socket client = listener.accept();
            System.out.println("[SERVER] connected to client!");
            //create a new ClientHandler object
            ClientHandler clientThread = new ClientHandler(client);
            //then add it to the list of clients
            clients.add(clientThread);

            //ask Executor to run
            pool.execute(clientThread);
        }


        //close the socket
        /*System.out.println("[SERVER] sent date. Closing.");
        client.close();
        //close the ServerSocket
        listener.close();*/


    }
}
