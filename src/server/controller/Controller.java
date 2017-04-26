package server.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import server.Main;
import server.mediator.DataHandler;
import server.model.Trip;
import server.model.TripList;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

/**
 * Class that manages data and files.
 *
 * @author IT-1Y-A16 Group 1
 */

public class Controller implements Initializable {

    public MenuBar menu;
    public MenuItem homeHome;
    public MenuItem homeTour;
    public MenuItem homeReserve;
    public MenuItem homeSearch;
    public MenuItem homeBus;
    public MenuItem homeBusAdd;
    public MenuItem homeDriver;
    public MenuItem homeDriverAdd;

    //server screen
    public Button createTour;
    public Button mkReservation;
    public Button findTrip;
    //tripList
    public ListView tripList;

    //bus list
    public Button addBusView;

    //chauffeur list
    public Button addChauffeur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (tripList != null) {
            showList();
        }
    }

    /**
     * Loads and displays list of trips on the listview.
     */

    private void showList() {
        TripList trips = DataHandler.getInstance().getTrips();
        trips.sort();
        ObservableList<Trip> data = FXCollections.observableArrayList();
        for (int i = 0; i < trips.getSize(); i++) {
            if (trips.getArrayTrip().get(i).getDateStart().isEqual(LocalDate.now())) data.add(trips.get(i));
            if (trips.getArrayTrip().get(i).getDateStart().isAfter(LocalDate.now())) data.add(trips.get(i));
        }
        tripList.setItems(data);
    }

    /**
     * Changes view in GUI.
     */

    public void changeView(MouseEvent mouseEvent) throws IOException {

        Parent root = null;

        //main
        if ((mouseEvent.getSource() == createTour)) {
            root = FXMLLoader.load(getClass().getResource("../view/createTour.fxml"));
        } else if ((mouseEvent.getSource() == mkReservation)) {
            root = FXMLLoader.load(getClass().getResource("../view/makeReservation.fxml"));
        } else if ((mouseEvent.getSource() == findTrip)) {
            root = FXMLLoader.load(getClass().getResource("../view/search.fxml"));
        }

        //bus view
        else if ((mouseEvent.getSource() == addBusView)) {
            root = FXMLLoader.load(getClass().getResource("../view/addBus.fxml"));
        }

        //chauffeur view
        else if ((mouseEvent.getSource() == addChauffeur)) {
            root = FXMLLoader.load(getClass().getResource("../view/AddChauffeur.fxml"));
        }


        if (root != null) {
            Scene scene = new Scene(root);

            Main.stage.setScene(scene);
            Main.stage.show();
        }
    }

    /**
     * Changes view in GUI through menu bar.
     */

    public void changeViewMenu(ActionEvent actionEvent) throws IOException {

        Parent root = null;

        if ((actionEvent.getSource() == homeHome)) {
            root = FXMLLoader.load(getClass().getResource("../view/mainScreen.fxml"));
        } else if ((actionEvent.getSource() == homeTour)) {
            root = FXMLLoader.load(getClass().getResource("../view/createTour.fxml"));
        } else if ((actionEvent.getSource() == homeBus)) {
            root = FXMLLoader.load(getClass().getResource("../view/busList.fxml"));
        } else if ((actionEvent.getSource() == homeBusAdd)) {
            root = FXMLLoader.load(getClass().getResource("../view/addBus.fxml"));
        } else if ((actionEvent.getSource() == homeReserve)) {
            root = FXMLLoader.load(getClass().getResource("../view/makeReservation.fxml"));
        } else if ((actionEvent.getSource() == homeSearch)) {
            root = FXMLLoader.load(getClass().getResource("../view/search.fxml"));
        } else if ((actionEvent.getSource() == homeDriver)) {
            root = FXMLLoader.load(getClass().getResource("../view/chauffeurList.fxml"));
        } else if ((actionEvent.getSource() == homeDriverAdd)) {
            root = FXMLLoader.load(getClass().getResource("../view/addChauffeur.fxml"));
        }

        if (root != null) {
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        }

    }

    /**
     * Validates if textfied is not empty.
     */

    protected boolean validateEmptyField(TextField textField) {
        return !textField.getText().isEmpty();

    }

    /**
     * Validates if textfied contains only numbers.
     */

    protected boolean validateNumberField(TextField textField) {
        return textField.getText().matches("[0-9]+");

    }

    /**
     * Validates if textfied contain double number.
     */

    protected boolean validateDoubleNumberField(TextField textField) {
        return textField.getText().matches("[0-9]+.[0-9]+");

    }

    /**
     * Validates if textfied contains registration plate, two letters and three numbers.
     */

    protected boolean validateNumberPlate(TextField textField) {
        return textField.getText().matches("[A-Z]{2}[0-9]{5}");

    }

    /**
     * Validates if textfield text has given length.
     */

    protected boolean validateLength(TextField textField, int length) {
        if (length < 1) length = length * (-1);
        return textField.getText().length() == length;

    }

    /**
     * Validates if textfied contains time.
     */

    protected boolean validateTimeField(TextField textField) {
        return textField.getText().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");

    }

    /**
     * Validates if datepicker is empty.
     */

    protected boolean validateEmptyDate(DatePicker datePicker) {
        return datePicker.getValue() != null;

    }

    /**
     * Validates if person is adult.
     */

    protected boolean validateAdultDate(DatePicker datePicker) {
        LocalDate birthDate = datePicker.getValue();
        LocalDate now = LocalDate.now();
        if (birthDate != null) {
            int age = Period.between(birthDate, now).getYears();
            return (age >= 18);
        } else {
            return false;
        }

    }

    /**
     * Validates if combobox is empty.
     */

    protected boolean validateEmptyCombo(ComboBox comboBox) {
        return comboBox.getValue() != null;

    }

    /**
     * Validates if email is valid.
     */

    protected boolean validateEmail(TextField textField) {
        return textField.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    }

    /**
     * Displays alert.
     *
     * @param title   title for alert
     * @param message alert's message
     */

    protected void alertdisplay(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays success alert.
     *
     * @param title   tittle for mesage
     * @param message to show
     */

    protected void successdisplay(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

