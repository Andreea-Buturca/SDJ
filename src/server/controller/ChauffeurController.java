package server.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import server.Main;
import server.model.Chauffeur;
import server.mediator.DataHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Class that manages window with chauffeur list.
 *
 * @author IT-1Y-A16 Group 1
 */

public class ChauffeurController extends Controller implements Initializable {

    public TextField fieldChauffeurAddAddress;
    public TextField fieldChauffeurAddEmail;
    public TextField fieldChauffeurAddName;
    public TextField fieldChauffeurAddPhone;
    public TextField fieldChauffeurAddId;
    public Button buttonAddChauffeur;
    public DatePicker birthdayPicker;
    public Button buttonDeleteChauffeur;
    public ListView listViewChauffeurList;
    public CheckBox checkBoxVikar;
    public CheckBox checkBoxDistanceLong;
    public CheckBox checkBoxDistanceMedium;
    public CheckBox checkBoxDistanceShort;
    public CheckBox checkBoxClasicBus;
    public CheckBox checkBoxMiniBus;
    public CheckBox checkBoxPartyBus;
    public CheckBox checkboxLuxuryBus;

    /**
     * Loads list of chauffeurs into listview.
     */

    private void loadList() {
        listViewChauffeurList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Chauffeur> items = FXCollections.observableArrayList();
        for (Chauffeur chauffeur : DataHandler.getDataHandler().getChauffeurList().getArrayChauffeur()) {
            items.add(chauffeur);
        }
        listViewChauffeurList.setItems(items);
    }

    public void initialize(URL location, ResourceBundle resources) {
        if (listViewChauffeurList != null) {
            loadList();
        }
    }

    /**
     * Changes view to window to add chauffeur.
     */

    public void addChauffeur(ActionEvent actionEvent) throws IOException, ParseException {
        String alert = "There are some mistakes: \n";
        int length = alert.length();

        if (!validateEmptyField(fieldChauffeurAddName)) alert += "Name \n";
        if (!validateEmptyField(fieldChauffeurAddAddress)) alert += "Address \n";
        if (!validateEmptyField(fieldChauffeurAddEmail) || !validateEmail(fieldChauffeurAddEmail)) alert += "Email \n";
        if (!validateEmptyField(fieldChauffeurAddPhone) || !validateLength(fieldChauffeurAddPhone, 8))
            alert += "Phone \n";
        if (!validateEmptyField(fieldChauffeurAddId) || !validateLength(fieldChauffeurAddId, 5))
            alert += "Employee ID(5 digit nr) \n";
        if (!validateEmptyDate(birthdayPicker) || !validateAdultDate(birthdayPicker)) alert += "Birthday \n";

        if (length == alert.length()) {

            //save it DataHandler.
            String name = fieldChauffeurAddName.getText();
            String address = fieldChauffeurAddAddress.getText();
            String email = fieldChauffeurAddEmail.getText();
            String phone = fieldChauffeurAddPhone.getText();
            LocalDate birthday = birthdayPicker.getValue();
            int chauffeurID = Integer.parseInt(fieldChauffeurAddId.getText());
            boolean isVikar = false;

            if (!checkBoxVikar.isSelected()) {
                DataHandler.getDataHandler().getChauffeurList().add(new Chauffeur(name, address, email, phone, birthday, chauffeurID, isVikar));
                Chauffeur chauffeur = DataHandler.getDataHandler().getChauffeurList().getByName(name);
                if (checkBoxDistanceShort.isSelected()) {
                    chauffeur.setPreferredShortDistance(400);
                }
                if (checkBoxDistanceMedium.isSelected()) {
                    chauffeur.setPreferredMediumDistance(1000);
                }
                if (checkBoxDistanceLong.isSelected()) {
                    chauffeur.setPreferredLongDistance(2500);
                }
                if (checkBoxClasicBus.isSelected()) {
                    chauffeur.setPreferredBusType("Classic Bus");
                }
                if (checkboxLuxuryBus.isSelected()) {
                    chauffeur.setPreferredBusType("Luxury Bus");
                }
                if (checkBoxPartyBus.isSelected()) {
                    chauffeur.setPreferredBusType("Party Bus");
                }
                if (checkBoxMiniBus.isSelected()) {
                    chauffeur.setPreferredBusType("Mini Bus");
                }
            } else if (checkBoxVikar.isSelected()) {
                isVikar = true;
                DataHandler.getDataHandler().getChauffeurList().add(new Chauffeur(name, address, email, phone, birthday, chauffeurID, isVikar));
            }

            DataHandler.getDataHandler().save();
            successdisplay("Success", "Chauffeur was added.");

            Parent root = FXMLLoader.load(getClass().getResource("../view/chauffeurList.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        } else {
            //alert
            alertdisplay("Wrong Input", alert);
        }
    }

    /**
     * Deletes selected chauffeurs.
     */

    public void deleteChauffeur(ActionEvent actionEvent) throws FileNotFoundException, ParseException {
        ObservableList<Chauffeur> selected;
        selected = listViewChauffeurList.getSelectionModel().getSelectedItems();
        for (Chauffeur aSelected : selected) {
            DataHandler.getDataHandler().getChauffeurList().removeChauffeur(aSelected);
        }
        loadList();
        DataHandler.getDataHandler().save();
        successdisplay("Success", "Chauffeur was deleted.");
    }
}