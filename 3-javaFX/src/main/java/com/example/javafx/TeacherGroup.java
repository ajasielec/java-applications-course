package com.example.javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class TeacherGroup {
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty maxTeachers = new SimpleIntegerProperty();
    private final IntegerProperty capacity = new SimpleIntegerProperty();
    private ObservableList<Teacher> teachers = FXCollections.observableArrayList();


    public TeacherGroup(String name, int maxTeachers) {
        this.name.set(name);
        this.maxTeachers.set(maxTeachers);
        // Update capacity whenever the number of teachers changes
        teachers.addListener((ListChangeListener<Teacher>) change -> {
            updateCapacity();
        });

        // Initialize capacity
        updateCapacity();
    }

    private void updateCapacity() {
        if (maxTeachers.get() > 0) {
            capacity.set((int) (((double) teachers.size() / maxTeachers.get()) * 100));
        } else {
            capacity.set(0); // Prevent division by zero
        }
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getMaxTeachers() {
        return maxTeachers.get();
    }

    public void setMaxTeachers(int maxTeachers) {
        this.maxTeachers.set(maxTeachers);
    }

    public IntegerProperty maxTeachersProperty() {
        return maxTeachers;
    }

    public int getCapacity() {
        return capacity.get();
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public ObservableList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        if (teachers.size() >= maxTeachers.get()) {
            showError("Cannot add teacher: maximum capacity reached.");
        } else if (teachers.contains(teacher)) {
            showError("Cannot add teacher: teacher already exists!");
        } else {
            teachers.add(teacher);
        }
    }

    public ArrayList<Teacher> searchPartial(String partial){
        ArrayList<Teacher> foundTeachers = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getFirstName().toLowerCase().contains(partial.toLowerCase()) || teachers.get(i).getLastName().toLowerCase().contains(partial.toLowerCase())){
                foundTeachers.add(teachers.get(i));
            }
        }
        return foundTeachers;
    }

    private void showError(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void removeTeacher(Teacher teacher) {
        if (teachers.contains(teacher)) {
            teachers.remove(teacher);
        } else {
            System.out.println("Cannot remove teacher: teacher not found!");
        }
    }
}
