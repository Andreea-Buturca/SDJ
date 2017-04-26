package client.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
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
                FXMLLoader fxmlLoader = new FXMLLoader();
                Pane p = fxmlLoader.load(getClass().getResource("../view/tripList.fxml").openStream());
                Controller controller = (Controller) fxmlLoader.getController();
                controller.showList(trips);
            } catch (IOException e) {
               //
            } catch (ClassNotFoundException e) {
                //
            }
        }
    }
}
