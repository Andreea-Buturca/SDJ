package server.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import server.Main;
import server.domain.mediator.DataHandler;
import server.domain.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Class that manages window with bus list.
 *
 * @author IT-1Y-A16 Group 1
 */

public class BusController extends Controller {

    public ListView busListview;
    public Button deleteBus;
    public TextField regPlate;
    public TextField seatNumber;
    public ChoiceBox typeChoice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (busListview != null) {  // BusController is shared between buslist and add bus,
            // in add bus there is no listview so i have to use that condition
            loadList();
        }
    }

    /**
     * Loads list of buses to listview.
     */

    private void loadList() {
        busListview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Bus> items = DataHandler.getInstance().getObservableListOfBuses();
        busListview.setItems(items);
    }

    /**
     * Deletes selected buses from list of buses.
     */

    public void deleteBus(ActionEvent actionEvent) throws FileNotFoundException, ParseException {
        ObservableList<Bus> selected;
        selected = busListview.getSelectionModel().getSelectedItems();
        for (Bus aSelected : selected) {
            DataHandler.getInstance().removeFromBuslist(aSelected);
        }

        loadList();
        DataHandler.getInstance().save();
    }

    /**
     * Changes view to add bus window.
     */

    public void addBus(ActionEvent actionEvent) throws IOException, ParseException {

        String alert = "There are some mistakes: \n";
        int length = alert.length();

        if (!validateEmptyField(regPlate) || !validateNumberPlate(regPlate)) alert += "Registration plate \n";
        if (!validateEmptyField(seatNumber) || !validateNumberField(seatNumber)) alert += "Number of seats \n";

        if (length == alert.length()) {
            String regplate = regPlate.getText();
            int seats = Integer.parseInt(seatNumber.getText());

            if (typeChoice.getValue().equals("Classic Bus")) {
                DataHandler.getInstance().addBus(regplate, seats, DataHandler.CLASSIC);
            } else if (typeChoice.getValue().equals("Mini Bus")) {
                DataHandler.getInstance().addBus(regplate, seats, DataHandler.MINI);
            } else if (typeChoice.getValue().equals("Luxury Bus")) {
                DataHandler.getInstance().addBus(regplate, seats, DataHandler.LUXURY);
            } else {
                DataHandler.getInstance().addBus(regplate, seats, DataHandler.PARTY);
            }
            DataHandler.getInstance().save();
            successdisplay("Success", "Bus was created.");
            Parent root = FXMLLoader.load(getClass().getResource("../view/busList.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();

        } else {
            //alert
            alertdisplay("Wrong Input", alert);
        }
    }
}
