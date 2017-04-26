package server.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import server.Main;
import server.mediator.DataHandler;
import server.model.*;

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
        ObservableList<Bus> items = FXCollections.observableArrayList();
        for (Bus bus : DataHandler.getBusList().getArrayBuses()) {
            items.add(bus);
        }
        busListview.setItems(items);
    }

    /**
     * Deletes selected buses from list of buses.
     */

    public void deleteBus(ActionEvent actionEvent) throws FileNotFoundException, ParseException {
        ObservableList<Bus> selected;
        selected = busListview.getSelectionModel().getSelectedItems();
        for (Bus aSelected : selected) {
            DataHandler.getBusList().removeBus(aSelected);
        }

        loadList();
        DataHandler.save();
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
                DataHandler.getBusList().add(new ClassicBus(regplate, seats));
            } else if (typeChoice.getValue().equals("Mini Bus")) {
                DataHandler.getBusList().add(new MiniBus(regplate, seats));
            } else if (typeChoice.getValue().equals("Luxury Bus")) {
                DataHandler.getBusList().add(new LuxuryBus(regplate, seats));
            } else {
                DataHandler.getBusList().add(new PartyBus(regplate, seats));
            }
            DataHandler.save();
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
