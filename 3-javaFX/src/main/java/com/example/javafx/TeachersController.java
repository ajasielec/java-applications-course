package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class TeachersController {

    public static ObservableList<Teacher> teachers = FXCollections.observableArrayList();
   // public static TeacherGroup selectedTeacherGroup;

    @FXML
    Label titleLabel;

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

    static {
        teachers.add(new Teacher("Ewa", "Werner", TeacherCondition.absent, 1987, 10000));
        // selectedTeacherGroup.addTeacher(teachers.get(0));
    }

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        birthYearColumn.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        teacherTable.setItems(FXCollections.observableArrayList(teachers));

        addButtons();
    }

    private void addButtons() {
        Callback<TableColumn<Teacher, Void>, TableCell<Teacher, Void>> cellFactory = new Callback<TableColumn<Teacher, Void>, TableCell<Teacher, Void>>() {
            @Override
            public TableCell<Teacher, Void> call(final TableColumn<Teacher, Void> param) {
                return new TableCell<Teacher, Void>() { // Jawna deklaracja typów

                    private final Button deleteButton = new Button("Delete");
                    private final Button manageButton = new Button("Manage");

                    {
                        // Konfiguracja przycisku "Delete"
                        deleteButton.setOnAction(event -> {
                            Teacher group = getTableView().getItems().get(getIndex());
                            teachers.remove(group); // Usunięcie z listy
                            teacherTable.refresh();
                            initialize();
                        });

                        // Konfiguracja przycisku "Manage"
                        manageButton.setOnAction(event -> {
                            System.out.println("Manage Teachers");
                            // logika
                        });
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


    public void displayTitle(String title) {
        titleLabel.setText("Teachers in group \"" + title + "\"");
    }

    public void returnToPreviousPages(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("classContainer.fxml"));
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
        // changing scenes
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addTeacher.fxml"));
            Parent root = loader.load();

            AddTeacherController controller = loader.getController();
            // controller.setClassController(this);

            Stage stage = (Stage) teacherTable.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
