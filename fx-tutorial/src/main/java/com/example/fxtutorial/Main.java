package com.example.fxtutorial;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Image icon = new Image(getClass().getResourceAsStream("/cat-icon.png"));
            stage.getIcons().add(icon);
            stage.setTitle("JavaFX tutorial");

            stage.setScene(new Scene(root));
            stage.show();

            stage.setOnCloseRequest(event -> {
                    event.consume();
                    logout(stage);
                    });

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void logout(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?: ");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Logout button clicked");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}