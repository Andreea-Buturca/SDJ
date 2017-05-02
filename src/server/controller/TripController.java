package server.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import server.Main;
import server.domain.mediator.DataHandler;
import server.domain.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that manages trips.
 *
 * @author IT-1Y-A16 Group 1
 */

public class TripController extends Controller implements Initializable {


    public TextField fieldStartTime;
    public TextField fieldEndTime;
    public ComboBox fieldDestination;
    public ComboBox fieldDeparture;
    public TextField fieldDistance;
    public TextField fieldPrice;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public CheckBox checkPrivateTrip;
    public Button CreateTourBtn;
    public ListView busListview;
    public ChoiceBox busType;
    public ComboBox stopName;
    public Button addStopBtn;
    public Button removeStopBtn;
    public ListView stopsList;
    public TextField stopTimeField;
    public ListView chauffeurList;
    public CheckBox foodCheckBox;
    public CheckBox accommodationCheckBox;
    public CheckBox ticketCheckBox;

    //Add customer
    public TextField fieldCustomerName;
    public TextField fieldCustomerCompany;
    public TextField fieldCustomerAddress;
    public TextField fieldCustomerEmail;
    public TextField fieldCustomerPhone;
    public ListView customerList;
    public Button saveCustomerBtn;
    public Label tourLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (fieldStartTime != null) {
            loadBusList();
            loadChauffeurList();

            ObservableList<Destination> destinationItems = FXCollections.observableArrayList();
            destinationItems.addAll(DataHandler.getInstance().getArrayOfDestinations());
            fieldDestination.setItems(destinationItems);
            fieldDeparture.setItems(destinationItems);
            stopName.setItems(destinationItems);
        }

    }

    public void getDataChoice(ActionEvent actionEvent) {
        loadBusList();
        loadChauffeurList();
    }

    public void getDataFromField(KeyEvent keyEvent) {
        loadBusList();
        loadChauffeurList();
    }


    private void loadChauffeurList() {
        chauffeurList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Chauffeur> items = DataHandler.getInstance().getObservableChauffeursLoadList(fieldDistance.getText(), busType.getValue().toString(), startDatePicker.getValue(), endDatePicker.getValue(), fieldStartTime.getText(), fieldEndTime.getText());

        chauffeurList.setItems(items);
    }

    private void loadBusList() {

        ObservableList<Bus> items = DataHandler.getInstance().loadBuses(busType.getValue().toString(), startDatePicker.getValue(), endDatePicker.getValue(), fieldStartTime.getText(), fieldEndTime.getText());
        busListview.setItems(items);


    }

    private void loadStops() {

    }

    public void handleStops(ActionEvent actionEvent) {

    }

    public void createTour(ActionEvent actionEvent) throws IOException {


        String alert = "There are some mistakes: ";
        int length = alert.length();

        if (!validateEmptyDate(startDatePicker)) alert += "Start Date, ";
        if (!validateEmptyDate(endDatePicker) || validateAdultDate(endDatePicker)) alert += "End Date, ";
        if (!validateEmptyField(fieldStartTime) || !validateTimeField(fieldStartTime)) alert += "Start time, ";
        if (!validateEmptyField(fieldEndTime) || !validateTimeField(fieldEndTime)) alert += "End time, ";
        if (!validateEmptyField(fieldDistance) || !validateNumberField(fieldDistance)) alert += "Distance, ";
        if (!validateEmptyField(fieldPrice) || !validateNumberField(fieldPrice)) alert += "Price, ";
        if (!validateEmptyCombo(fieldDestination)) alert += "Destination, ";
        if (!validateEmptyCombo(fieldDeparture)) alert += "Departure, ";
        if (busListview.getSelectionModel().getSelectedItem() == null) alert += "Bus, ";
        if (chauffeurList.getSelectionModel().getSelectedItem() == null) alert += "Chauffeur, ";

        if (length == alert.length()) {
            //save it DataHandler. .....
            Bus bus = (Bus) busListview.getSelectionModel().getSelectedItem();
            Chauffeur chauffeur = (Chauffeur) chauffeurList.getSelectionModel().getSelectedItem();

            Destination pickUp;
            Destination destination;

            if (DataHandler.getInstance().getDestinationByName(fieldDeparture.getValue().toString()) != null) {
                pickUp = DataHandler.getInstance().getDestinationByName(fieldDeparture.getValue().toString());
            } else {
                pickUp = new Destination(fieldDeparture.getValue().toString());
                DataHandler.getInstance().addDestination(pickUp);
            }

            if (DataHandler.getInstance().getDestinationByName(fieldDestination.getValue().toString()) != null) {
                destination = DataHandler.getInstance().getDestinationByName(fieldDestination.getValue().toString());
            } else {
                destination = new Destination(fieldDestination.getValue().toString());
                DataHandler.getInstance().addDestination(destination);
            }

            int distance = Integer.parseInt(fieldDistance.getText());

            DataHandler.getInstance().addTrip(bus, chauffeur, pickUp, destination, distance, startDatePicker.getValue(), fieldStartTime.getText(), endDatePicker.getValue(), fieldEndTime.getText(), Integer.parseInt(fieldPrice.getText()), foodCheckBox.isSelected(), accommodationCheckBox.isSelected(), ticketCheckBox.isSelected());
            successdisplay("Created", "Trip was created.");
            DataHandler.getInstance().save();
            Parent root = FXMLLoader.load(getClass().getResource("../view/mainScreen.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();

        } else {
            //alert
            alertdisplay("Wrong Input", alert);
        }
    }
}
