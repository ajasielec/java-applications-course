package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private ClassController classController;

    public void setClassController(ClassController classController) {
        this.classController = classController;
    }

    @FXML
    void submit(ActionEvent actionEvent) {
        try{
            // user input
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String birthYear = birthYearTextField.getText();
            String salary = salaryTextField.getText();
            // String condition = conditionMenu.getText();

            // creating new teacher
            Teacher newTeacher = new Teacher(firstName, lastName);

            // adding new teacher to table


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeForm(ActionEvent actionEvent) {

    }
}
