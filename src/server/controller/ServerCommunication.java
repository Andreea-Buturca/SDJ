package server.controller;

import server.mediator.DataHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by aykon on 23-Apr-17.
 */
public class ServerCommunication implements Runnable {
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    public ServerCommunication(Socket clientSocket)
            throws IOException
    {
        outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
        inFromClient = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void run() {
        try {
            outToClient.writeObject(DataHandler.getTrips());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
