package client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import client.Main;
import javafx.scene.control.ListView;

import java.io.IOException;

public class Controller {
    public DatePicker dateFromMain;
    public DatePicker dateToMain;
    public Button searchMain;
    public DatePicker dateFrom;
    public DatePicker dateTo;
    public Button search;
    public ListView tripList;

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
}
