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
import server.model.Trip;
import server.model.TripList;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class Controller implements Observer,Initializable {
    public DatePicker dateFromMain;
    public DatePicker dateToMain;
    public Button searchMain;
    public DatePicker dateFrom;
    public DatePicker dateTo;
    public Button search;
    public ListView tripList;

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


        System.out.println("searching");
    }

    public void showList(TripList trips) {
        System.out.println("method called");
        if (tripList!=null) {
            System.out.println(trips.getSize());
            System.out.println("wennt inside if");
            trips.sort();
            ObservableList<Trip> data = FXCollections.observableArrayList();
            for (int i = 0; i < trips.getSize(); i++) {
                data.add(trips.get(i));
                /*if (trips.getArrayTrip().get(i).getDateStart().isEqual(LocalDate.now())) data.add(trips.get(i));
                if (trips.getArrayTrip().get(i).getDateStart().isAfter(LocalDate.now())) data.add(trips.get(i));*/
            }
            System.out.println(data);
            System.out.println(data.size());
            tripList.setItems(data);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        showList((TripList)arg);
    }
}
