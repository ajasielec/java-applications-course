package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;


public class ClassController {
    public static ObservableList<TeacherGroup> groupList = FXCollections.observableArrayList();

    @FXML
    private TableView<TeacherGroup> groupTable;
    @FXML
    private TableColumn<TeacherGroup, String> nameColumn;
    @FXML
    private TableColumn<TeacherGroup, Integer> capacityColumn;
    @FXML
    private TableColumn<TeacherGroup, Void> actionColumn;
    @FXML
    private Button addButton;

    static{
        groupList.add(new TeacherGroup("Math", 12));
    }


    @FXML
    public void initialize() {
        // connecting columns with data
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        groupTable.setItems(FXCollections.observableArrayList(groupList));

        // column with actions config
        addButtonToTable();
    }

    private void addButtonToTable() {
        Callback<TableColumn<TeacherGroup, Void>, TableCell<TeacherGroup, Void>> cellFactory = new Callback<TableColumn<TeacherGroup, Void>, TableCell<TeacherGroup, Void>>() {
            @Override
            public TableCell<TeacherGroup, Void> call(final TableColumn<TeacherGroup, Void> param) {
                return new TableCell<TeacherGroup, Void>() { // Jawna deklaracja typów

                    private final Button deleteButton = new Button("Delete");
                    private final Button showButton = new Button("Show Teachers");

                    {
                        // Konfiguracja przycisku "Delete"
                        deleteButton.setOnAction(event -> {
                            TeacherGroup group = getTableView().getItems().get(getIndex());
                            groupList.remove(group); // Usunięcie z listy
                            groupTable.refresh();
                            initialize();
                        });

                        // Konfiguracja przycisku "Show Teachers"
                        showButton.setOnAction(event -> {
                            TeacherGroup group = getTableView().getItems().get(getIndex());
                            System.out.println("Showing teachers for group: " + group.getName());
                            // changing scenes
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("teachersScene.fxml"));
                                System.out.println("Ładowanie pliku FXML...");
                                Parent root = null;
                                root = loader.load();

                                TeachersController controller = loader.getController();
                                controller.displayTitle(group.getName());

                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }catch (IOException e){
                                e.printStackTrace();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Dodanie przycisków do komórki
                            HBox hBox = new HBox(5, deleteButton, showButton);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        };

        actionColumn.setCellFactory(cellFactory);
    }



    public void addGroup(TeacherGroup group){
        groupList.add(group);
        // groupTable.getItems().add(group); //dodanie grupy do tabeli
        groupTable.setItems(FXCollections.observableArrayList(groupList));
    }


    @FXML
    void addTeacherGroup(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addGroup.fxml"));
            Parent root = loader.load();

            // getting addGroupController
            AddGroupController controller = loader.getController();
            controller.setClassController(this);

            Stage stage = (Stage) groupTable.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}