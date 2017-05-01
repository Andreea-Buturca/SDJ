package client.controller;


import client.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.domain.model.ProxyTripList;
import server.domain.model.TripList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Marek on 26-Apr-17.
 */
public class ClientReceiver implements Runnable {
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;

    public ClientReceiver(ObjectInputStream inFromServer,ObjectOutputStream outToServer ) {
        this.inFromServer=inFromServer;
    }

    @Override
    public void run() {
        while (Main.controller == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            try {
                ProxyTripList trips = (ProxyTripList) inFromServer.readObject();
                Main.controller.showList(trips);
                inFromServer.reset();
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
                //
            }
        }
    }
}
