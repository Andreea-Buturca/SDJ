package client;

import client.controller.ConnectionController;
import client.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {

    public static Stage stage;
    public static Controller controller;
    public static ConnectionController connectionController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/tripList.fxml"));
        Parent root = (AnchorPane) fxmlLoader.load();
        controller = fxmlLoader.getController();
        try
        {
            connectionController = new ConnectionController();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        primaryStage.setTitle("VIA BUS TRIPS");
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        stage = primaryStage;
        stage.setOnCloseRequest(we -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Exit");
            alert.setHeaderText("You are trying to close VIA Bus trips");
            alert.setContentText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stage.close();
                System.exit(0);
            } else {
                we.consume();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
