package server.controller;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by aykon on 23-Apr-17.
 */
public class ServerController implements Runnable {
    private static final int PORT = 6666;

    public ServerController() {

    }

    public void run() {
        int count = 1;
        try {
            ServerSocket welcomeSocket = new ServerSocket(PORT);
            System.out.println("Server started");
            while (true) {
                Socket connectionSocket = welcomeSocket.accept();
                ServerCommunication c = new ServerCommunication(connectionSocket);
                new Thread(c, "Communication " + count).start();
                System.out.println("Client connected");
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}