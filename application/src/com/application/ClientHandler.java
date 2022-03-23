package com.application;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader input;
    private PrintWriter out;


    //constructor
    //we take the socket as an input (client socket)
    //here we handle the socket that got created by a separate thread
    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        //we are getting an input stream from the client input stream
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //sending info to the client using a PrintWriter
        //AutoFlush 'true' make sure buffered output is flushed automatically when the buffer is filled
        //PrintWriter connects to the client's output stream.
        out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        //logic to finding the ipaddress of the given website URL
        try {
            //read the url client send to the variable request in server
            String request = input.readLine();

            //start computing time
            long startTime = System.nanoTime();

            InetAddress ip = InetAddress.getByName(request);
            out.println(ip.getHostAddress());

            //end computing time
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("Execution time in nanoseconds: " + timeElapsed + "ns");
            System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000 + "ms");

            //send client the time consumed by the server.
            DataOutputStream dout = new DataOutputStream(client.getOutputStream());
            dout.writeUTF("Time consumed in nanoseconds: "+ timeElapsed +"ns \nTime consumed in milliseconds: "+ timeElapsed / 1000000 +"ms");

            //timestamp
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());

            //logging
            try {
                Log server_log = new Log("server_log.txt");
                server_log.logger.setLevel(Level.INFO);
                server_log.logger.info(timeStamp + " | " + request + " | " + ip + "\n");

            } catch (Exception e) {
                //logger
            }


        } catch (UnknownHostException e) {
            out.println("Invalid website URL!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
