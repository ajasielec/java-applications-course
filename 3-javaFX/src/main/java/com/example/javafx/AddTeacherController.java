package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTeacherController {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField birthYearTextField;
    @FXML
    private TextField salaryTextField;
    @FXML
    private MenuButton conditionMenu;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private TeachersController teachersController;

    public void setClassController(TeachersController teachersController) {
        this.teachersController = teachersController;
    }

    @FXML
    void submit(ActionEvent actionEvent) {
        try{
            // user input
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            int birthYear = Integer.parseInt(birthYearTextField.getText());
            int salary = Integer.parseInt(salaryTextField.getText());
            //TeacherCondition condition = conditionMenu.getText();

            // creating new teacher
            Teacher newTeacher = new Teacher(firstName, lastName, TeacherCondition.absent, salary, birthYear);

            // adding new teacher to table
            teachersController.addTeacher(newTeacher);

            //change scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teachersScene.fxml"));
            root = loader.load();

            TeachersController controller = loader.getController();
            // controller.displayTitle(group.getName());

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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
