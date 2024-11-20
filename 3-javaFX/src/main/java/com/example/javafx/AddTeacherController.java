package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private TeacherCondition selectedCondition = TeacherCondition.absent;
    private TeacherGroup currentGroup;

    public void setCurrentGroup(TeacherGroup currentGroup) {
        this.currentGroup = currentGroup;
    }

    public void setClassController(TeachersController teachersController) {
        this.teachersController = teachersController;
    }

    public void setConditionPresent(ActionEvent actionEvent) {
        selectedCondition = TeacherCondition.present;
        conditionMenu.setText("Present");
    }

    public void setConditionAbsent(ActionEvent actionEvent) {
        selectedCondition = TeacherCondition.absent;
        conditionMenu.setText("Absent");
    }

    public void setConditionSick(ActionEvent actionEvent) {
        selectedCondition = TeacherCondition.sick;
        conditionMenu.setText("Sick");
    }

    public void setConditionDelegation(ActionEvent actionEvent) {
        selectedCondition = TeacherCondition.delegation;
        conditionMenu.setText("Delegation");
    }

    @FXML
    void submit(ActionEvent actionEvent) {
        try{
            // user input
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            int birthYear = Integer.parseInt(birthYearTextField.getText());
            int salary = Integer.parseInt(salaryTextField.getText());
            TeacherCondition condition = selectedCondition;

            // creating new teacher
            Teacher newTeacher = new Teacher(firstName, lastName, condition, salary, birthYear);

            // adding new teacher to table
            teachersController.addTeacher(newTeacher);

            //change scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teachersScene.fxml"));
            root = loader.load();
            teachersController = loader.getController();
            teachersController.setCurrentGroup(currentGroup);
            teachersController.displayTitle(currentGroup.getName());

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (NumberFormatException e) {
            showError("Invalid input. Birth year and salary must be a number.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeForm(ActionEvent actionEvent) throws IOException {
        // change scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("teachersScene.fxml"));
        root = loader.load();

        teachersController = loader.getController();
        teachersController.setCurrentGroup(currentGroup);
        teachersController.displayTitle(currentGroup.getName());

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showError(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

}
