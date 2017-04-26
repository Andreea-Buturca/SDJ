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
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.mediator.DataHandler;
import server.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Class that searching window.
 *
 * @author IT-1Y-A16 Group 1
 */

public class SearchController extends Controller implements Initializable {


    public ListView matchingReservations;
    public TextField name;
    public TextField companyName;
    public TextField address;
    public TextField email;
    public TextField phone;
    public TextField destination;
    public TextField departure;
    public DatePicker date;
    public ListView matchingTrips;
    public Button removeReservation;
    public Button removeTrip;
    public RadioButton tourTypeAll;
    public RadioButton tourTypeStandard;
    public RadioButton tourTypePrivate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        findReservations();
        findTrips();
    }

    public void searchCustomer(KeyEvent keyEvent) throws FileNotFoundException, ParseException {
        findReservations();
    }

    private void findReservations() {
        if (name != null) {
            if (companyName != null) {
                if (address != null) {
                    if (email != null) {
                        if (phone != null) {
                            if (matchingReservations != null) {
                                CustomerList matching = DataHandler.getCustomerList();
                                if (name.getText() != null && (!name.getText().equals("")))
                                    matching = matching.findAllByName(name.getText());
                                if (address.getText() != null && (!address.getText().equals("")))
                                    matching = matching.findAllByAddress(address.getText());
                                if (email.getText() != null && (!email.getText().equals("")))
                                    matching = matching.findAllByEmail(email.getText());
                                if (phone.getText() != null && (!phone.getText().equals("")))
                                    matching = matching.findAllByPhone(phone.getText());
                                ReservationList reservations = DataHandler.getReservationList();
                                ObservableList<Reservation> items = FXCollections.observableArrayList();
                                for (int i = 0; i < matching.getSize(); i++) {
                                    for (int j = 0; j < reservations.getSize(); j++) {
                                        if (reservations.getReservation(j).getCustomer().equals(matching.getCustomer(i))) {
                                            items.add(reservations.getReservation(j));
                                        }
                                    }

                                }
                                matchingReservations.setItems(items);
                            }
                        }
                    }
                }
            }
        }
    }

    public void searchTripRadio(ActionEvent actionEvent) {
        findTrips();
    }

    public void searchTrip(KeyEvent keyEvent) throws FileNotFoundException, ParseException {
        findTrips();
    }

    private void findTrips() {
        if (matchingTrips != null) {
            TripList matching = DataHandler.getTrips();
            if (destination.getText() != null && (!destination.getText().equals("")))
                matching = matching.findAllByDestination(destination.getText());
            if (departure.getText() != null && (!departure.getText().equals("")))
                matching = matching.findAllByDeparture(departure.getText());
            if (date.getValue() != null)
                matching = matching.findAllByDate(date.getValue());
            if (tourTypePrivate.isSelected()) matching = matching.findAllPrivate();
            if (tourTypeStandard.isSelected()) matching = matching.findAllStandard();

            ObservableList<Trip> items = FXCollections.observableArrayList();
            for (int i = 0; i < matching.getSize(); i++) {
                items.add(matching.get(i));
            }
            matchingTrips.setItems(items);
        }
    }

    public void removeTrip(ActionEvent actionEvent) {
        ObservableList<Trip> selected;
        selected = matchingTrips.getSelectionModel().getSelectedItems();
        for (Trip aSelected : selected) {
            DataHandler.getTrips().getArrayTrip().remove(aSelected);
        }
        findTrips();
    }

    public void removeReservation(ActionEvent actionEvent) {
        ObservableList<Reservation> selected;
        selected = matchingReservations.getSelectionModel().getSelectedItems();
        for (Reservation aSelected : selected) {
            DataHandler.getReservationList().remove(aSelected);
        }
        findReservations();
    }

    public void editReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/makeReservationData.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1000, 600);
        Stage window = new Stage();
        ReservationController reservationController = fxmlLoader.getController();
        reservationController.setEditData(DataHandler.getReservationList().getArrayReservation().get(0));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit trip");
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }

    public void editTrip(ActionEvent actionEvent) throws IOException {
        if (matchingTrips.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/createTour.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1000, 600);
            Stage window = new Stage();
            TripController tripController = fxmlLoader.getController();
            tripController.setEditData((Trip) matchingTrips.getSelectionModel().getSelectedItem());
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Edit trip");
            window.setScene(scene);
            window.setResizable(false);
            window.showAndWait();
        }
    }
}