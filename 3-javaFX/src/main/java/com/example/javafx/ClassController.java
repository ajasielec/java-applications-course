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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ClassController {
    public static ObservableList<TeacherGroup> groupList = FXCollections.observableArrayList();

    @FXML
    private TableView<TeacherGroup> groupTable;
    @FXML
    private TableColumn<TeacherGroup, String> nameColumn;
    @FXML
    private TableColumn<TeacherGroup, Integer> capacityColumn;
    @FXML
    private Button addButton;


    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        groupTable.setItems(FXCollections.observableArrayList(groupList));
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