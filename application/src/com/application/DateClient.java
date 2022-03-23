package com.application;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DateClient {

    public static final String server_ip = "127.0.0.1";
    public static final int server_port = 9090;

    public static void main(String[] args) throws IOException {
        //socket creation
        Socket socket = new Socket(server_ip, server_port);

        //receive info the server going to send the client
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //blocking operation
        String serverResponse = input.readLine();
        //show message sent by server
        JOptionPane.showMessageDialog(null, serverResponse);

        //close socket
        socket.close();
        System.exit(0);
    }
}
