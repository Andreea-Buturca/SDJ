package client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import client.Main;
import javafx.scene.control.ListView;
import server.domain.model.ProxyTripList;
import server.domain.model.Trip;
import server.domain.model.TripList;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class Controller implements Observer,Initializable {

    public DatePicker dateFrom;
    public DatePicker dateTo;
    public Button search;
    public ListView tripListClient;

    public Controller() {
        //server.Main.oHandler.adObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controller initialized");

    }

    public void searchMain(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/tripList.fxml"));
        if (root != null) {
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        }
    }

    public void seachTrips(ActionEvent actionEvent) {
        Main.connectionController.sendDatesToServer(dateFrom.getValue(), dateTo.getValue());
    }

    public void showList(ProxyTripList trips) {
        System.out.println("method called");
        if (tripListClient!=null) {
            System.out.println(trips.getSize());
            System.out.println("wennt inside if");
            trips.sort();
            ObservableList<Trip> data = FXCollections.observableArrayList();
            for (int i = 0; i < trips.getSize(); i++) {
                if (trips.getArrayTrip().get(i).getDateStart().isEqual(LocalDate.now())) data.add(trips.get(i));
                if (trips.getArrayTrip().get(i).getDateStart().isAfter(LocalDate.now())) data.add(trips.get(i));
            }

            System.out.println(data);
            System.out.println(data.size());
            //tripListClient.getItems().clear();
            tripListClient.setItems(data);

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        showList((ProxyTripList) arg);
    }
}
