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
    private TeacherGroup currentGroup;
    // private ObservableList<Teacher> teachers = currentGroup.getTeachers();

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
        // teachers.add(new Teacher("Ewa", "Werner", TeacherCondition.absent, 1987, 10000));
        // selectedTeacherGroup.addTeacher(teachers.get(0));
    }

    public void setCurrentGroup(TeacherGroup group) {
        this.currentGroup = group;
    }

    @FXML
    public void initialize() {

        displayTitle(String.valueOf(currentGroup));
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
                    private final MenuButton manageButton = new MenuButton("Manage");

                    {
                        // Konfiguracja przycisku "Delete"
                        deleteButton.setOnAction(event -> {
                            Teacher teacher = getTableView().getItems().get(getIndex());
                            teachers.remove(teacher); // Usunięcie z listy
                            teacherTable.setItems(FXCollections.observableArrayList(teachers));
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

    private void showError(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
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

    public void addTeacher(Teacher teacher) {
        if (currentGroup.getTeachers().size() >= currentGroup.getMaxTeachers()) {
            showError("Cannot add teacher: maximum capacity reached.");
        } else if (teachers.contains(teacher)) {
            showError("Cannot add teacher: teacher already exists!");
        } else {
            teachers.add(teacher);
            teacherTable.setItems(teachers);
            displayTitle(currentGroup.getName());
            teacherTable.refresh();
        }
    }

    public void addTeacher(ActionEvent actionEvent) {
        // changing scenes
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addTeacher.fxml"));
            Parent root = loader.load();

            AddTeacherController controller = loader.getController();
            System.out.println("Controller from loader: " + (controller != null));
            controller.setClassController(this);
            controller.setCurrentGroup(currentGroup);

            Stage stage = (Stage) teacherTable.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
