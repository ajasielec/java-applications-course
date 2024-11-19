package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ClassController {
    @FXML
    private Button addButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void addTeacherGroup(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addGroup.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}