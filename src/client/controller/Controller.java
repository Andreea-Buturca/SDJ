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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        if (tripListClient!=null) {
            trips.sort();
            ObservableList<Trip> data = FXCollections.observableArrayList();
            for (int i = 0; i < trips.getSize(); i++) {
                if (trips.getArrayTrip().get(i).getDateStart().isEqual(LocalDate.now())) data.add(trips.get(i));
                if (trips.getArrayTrip().get(i).getDateStart().isAfter(LocalDate.now())) data.add(trips.get(i));
            }
            tripListClient.setItems(data);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        showList((ProxyTripList) arg);
    }
}
