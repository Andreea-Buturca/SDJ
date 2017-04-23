package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import server.controller.ServerController;
import server.model.DataHandler;

import java.util.Optional;

public class Main extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        DataHandler.load();
        ServerController serverController = new ServerController();
        new Thread(serverController, "Server").start();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./view/mainScreen.fxml"));
        primaryStage.setTitle("VIA BUS");
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        stage = primaryStage;
        stage.setOnCloseRequest(we -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Exit");
            alert.setHeaderText("You are trying to close VIA Bus");
            alert.setContentText("Are you ok with this? (Back up will be created)");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                DataHandler.save();
                DataHandler.backUp();
                stage.close();
                System.exit(0);
            } else {
                we.consume();
            }
        });
    }
}
