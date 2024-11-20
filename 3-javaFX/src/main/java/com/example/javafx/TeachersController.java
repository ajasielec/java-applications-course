package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TeachersController {

    @FXML
    Label titleLabel;


    public void displayTitle(String title) {
        titleLabel.setText("Teachers in group \"" + title + "\"");
    }

    public void returnToPreviousPages(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("classContainer.fxml"));
            System.out.println("Ładowanie pliku FXML...");
            Parent root = null;
            root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void searchTeacher(ActionEvent actionEvent) {
    }

    public void addTeacher(ActionEvent actionEvent) {

    }
}
