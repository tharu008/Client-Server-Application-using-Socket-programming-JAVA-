package com.application;

import java.io.*;
import java.net.Socket;

public class Client {

    public static final String server_ip = "127.0.0.1";
    public static final int server_port = 9090;

    public static void main(String[] args) throws IOException {
        //socket creation
        Socket socket = new Socket(server_ip, server_port);

        //receive info the server going to send the client
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //to read from the keyboard
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter that goes out to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while(true) {
            System.out.println("\n> Enter the website URL: \t");
            //readline from the keyboard input
            String url = keyboard.readLine();
            //sending the url to the server
            out.println(url);

            //blocking operation
            //get response from the server
            String ip = input.readLine();
            //show message sent by server
            System.out.println("IP of the given URL:\t" + ip);

            //Time consumed by the client requests with the received IP address
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String executionTime = (String) dis.readUTF();
            System.out.println("---------Time taken by the server--------- \n" + executionTime);
        }

        //close socket
        //socket.close();
        //System.exit(0);

    }
}
