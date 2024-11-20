package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGroupController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField maxTeachersField;
    @FXML
    private Button submitButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private ClassController classController;

    public void setClassController(ClassController classController) {
        this.classController = classController;
    }

    @FXML
    void submit(ActionEvent event) {
        try {
            // user input
            String name = nameField.getText();
            int maxTeachers = Integer.parseInt(maxTeachersField.getText());

            // creating new group
            TeacherGroup newGroup = new TeacherGroup(name, maxTeachers);

            // adding new group to table
            classController.addGroup(newGroup);

            // change scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("classContainer.fxml"));
            root = loader.load();

            stage = (Stage) submitButton.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeForm(ActionEvent actionEvent) {

    }

}
