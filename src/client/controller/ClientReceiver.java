package client.controller;


import client.Main;
import server.domain.model.ProxyTripList;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Marek on 26-Apr-17.
 */
public class ClientReceiver implements Runnable {
    private ObjectInputStream inFromServer;

    public ClientReceiver(ObjectInputStream inFromServer) {
        this.inFromServer = inFromServer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ProxyTripList trips = (ProxyTripList) inFromServer.readObject();
                Main.controller.showList(trips);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
