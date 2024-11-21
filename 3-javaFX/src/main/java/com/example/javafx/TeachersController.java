package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;

public class TeachersController {

    TeacherGroup currentGroup = GroupManager.getCurrentGroup();

    @FXML
    Label titleLabel;

    @FXML
    TextField searchTextField;

    @FXML
    private TableView<Teacher> teacherTable;
    @FXML
    private TableColumn<Teacher, String> firstNameColumn;
    @FXML
    private TableColumn<Teacher, String> lastNameColumn;
    @FXML
    private TableColumn<Teacher, TeacherCondition> conditionColumn;
    @FXML
    private TableColumn<Teacher, Integer> salaryColumn;
    @FXML
    private TableColumn<Teacher, Integer> birthYearColumn;
    @FXML
    private TableColumn<Teacher, Void> actionColumn;

    @FXML
    public void initialize() {
        if (currentGroup != null) {
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
            salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
            birthYearColumn.setCellValueFactory(new PropertyValueFactory<>("birthYear"));

            displayTitle(currentGroup.getName());

            teacherTable.setItems(currentGroup.getTeachers());
        } else {
            showError("Teacher Group is null");
        }

        addButtons();
    }

    private void addButtons() {
        Callback<TableColumn<Teacher, Void>, TableCell<Teacher, Void>> cellFactory = new Callback<TableColumn<Teacher, Void>, TableCell<Teacher, Void>>() {
            @Override
            public TableCell<Teacher, Void> call(final TableColumn<Teacher, Void> param) {
                return new TableCell<Teacher, Void>() { // Jawna deklaracja typów

                    private final Button deleteButton = new Button("Delete");
                    private final MenuButton manageButton = new MenuButton("Manage");

                    {
                        // Konfiguracja przycisku "Delete"
                        deleteButton.setOnAction(event -> {
                            Teacher teacher = getTableView().getItems().get(getIndex());
                            currentGroup.getTeachers().remove(teacher); // Usunięcie z listy
                            teacherTable.setItems(FXCollections.observableArrayList(currentGroup.getTeachers()));
                            displayTitle(currentGroup.getName());
                        });

                        // Manage elements
                        MenuItem addSalaryItem = new MenuItem("Add Salary");
                        MenuItem changeConditionItem = new MenuItem("Change Condition");

                        // add salary action
                        addSalaryItem.setOnAction(event -> {
                            Teacher teacher = getTableView().getItems().get(getIndex());
                            TextInputDialog dialog = new TextInputDialog();
                            dialog.setTitle("Add Salary");
                            dialog.setHeaderText("Enter the amount to be added:");
                            dialog.setContentText("Amount:");

                            dialog.showAndWait().ifPresent(input->{
                                try{
                                    int amount = Integer.parseInt(input);
                                    teacher.setSalary(amount + teacher.getSalary());
                                    teacherTable.refresh();
                                    displayTitle(currentGroup.getName());

                                } catch (NumberFormatException  e) {
                                    showError("Invalid amount. Please enter a number.");
                                }
                            });
                        });

                        // change condition action
                        changeConditionItem.setOnAction(event -> {
                            Teacher teacher = getTableView().getItems().get(getIndex());
                            ChoiceDialog<TeacherCondition> dialog = new ChoiceDialog<>(teacher.getCondition(), TeacherCondition.values());
                            dialog.setTitle("Change Condition");
                            dialog.setHeaderText("Select a new condition:");
                            dialog.setContentText("Condition:");

                            dialog.showAndWait().ifPresent(newCondition -> {
                                teacher.setCondition(newCondition);
                                teacherTable.refresh();
                            });
                        });

                        // add elements to menu button
                        manageButton.getItems().addAll(addSalaryItem, changeConditionItem);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Dodanie przycisków do komórki
                            HBox hBox = new HBox(5, deleteButton, manageButton);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        };

        actionColumn.setCellFactory(cellFactory);
    }


    public void displayTitle(String groupName) {
        titleLabel.setText("Teachers in group \"" + groupName + "\"");
    }

    public void returnToPreviousPages(ActionEvent actionEvent) {
        changeScene(actionEvent, "classContainer.fxml");
    }

    public void searchTeacher(ActionEvent actionEvent) {
        String input = searchTextField.getText();
        ArrayList<Teacher> foundTeachers = currentGroup.searchPartial(input);
        teacherTable.setItems(FXCollections.observableArrayList(foundTeachers));
        addButtons();
    }

    public void addTeacher(ActionEvent actionEvent) {
        changeScene(actionEvent, "addTeacher.fxml");
    }

    private void changeScene(ActionEvent actionEvent, String fxml) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) teacherTable.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void showError(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

}
