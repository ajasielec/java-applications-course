package com.example.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class TableViewSample extends Application {

    private TableView<TeacherGroup> table = new TableView<TeacherGroup>();
    private final ObservableList<TeacherGroup> data =
            FXCollections.observableArrayList(
            new TeacherGroup("Math", 10),
            new TeacherGroup("Music", 12),
            new TeacherGroup("English", 6),
            new TeacherGroup("Science", 16),
            new TeacherGroup("Biology", 3)
            );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Teacher Management System");
        stage.setWidth(700);
        stage.setHeight(500);

        final Label label = new Label("Groups of teachers:");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn groupNameCol = new TableColumn("Group name");
        groupNameCol.setMinWidth(150);
        groupNameCol.setCellValueFactory(
                new PropertyValueFactory<TeacherGroup, String>("GroupName"));

        TableColumn capacityCol = new TableColumn("Capacity");
        capacityCol.setMinWidth(100);
        capacityCol.setCellValueFactory(
                new PropertyValueFactory<TeacherGroup, String>("capacity"));

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setMinWidth(150);
        actionCol.setCellValueFactory(
                new PropertyValueFactory<TeacherGroup, String>("action"));

        table.setItems(data);
        table.getColumns().addAll(groupNameCol, capacityCol, actionCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}