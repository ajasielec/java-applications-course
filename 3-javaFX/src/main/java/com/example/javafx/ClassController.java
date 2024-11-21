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

    private Stage stage;
    private Scene scene;
    private Parent root;

    // private TeachersController teachersController;
    private AddGroupController addGroupController;


    static{
        groupList.add(new TeacherGroup("Math", 3));
        groupList.add(new TeacherGroup("English", 5));
    }


    @FXML
    public void initialize() {
        // connecting columns with data
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());

        groupTable.setItems(groupList);

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
                            GroupManager.setCurrentGroup(group);

                            try {
                                // changing scenes
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("teachersScene.fxml"));
                                root = loader.load();

                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
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
            root = loader.load();

            // getting addGroupController
            addGroupController = loader.getController();
            addGroupController.setClassController(this);

            stage = (Stage) groupTable.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}