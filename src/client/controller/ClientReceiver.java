package client.controller;


import server.model.TripList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;

/**
 * Created by Marek on 26-Apr-17.
 */
public class ClientReceiver implements Runnable{
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;

    public ClientReceiver(ObjectInputStream inFromServer,ObjectOutputStream outToServer ) {
        this.inFromServer=inFromServer;

    }

    @Override
    public void run() {
        while (true) {
            try {
                TripList trips = (TripList) inFromServer.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
