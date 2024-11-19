package com.example.javafx;

import java.util.ArrayList;
import java.util.List;

public class TeacherGroup {
    private String name;
    private List<Teacher> teachers;
    private int maxTeachers;
    private int capacity;

    public TeacherGroup(String name, int maxTeachers) {
        this.name = name;
        this.teachers = new ArrayList<>();
        this.maxTeachers = maxTeachers;
        this.capacity = (teachers.size()/maxTeachers) * 100;
    }

    public String getName() {
        return name;
    }

    public int getMaxTeachers() {
        return maxTeachers;
    }

    public int getCapacity(){
        // return ((double)entry.getValue().getTeacherCount() / (double)entry.getValue().maxTeachers() * 100;);
        return capacity;
    }

    public void setGroupName(String groupName) {
        this.name = groupName;
    }

    public void setTeachers(int size) {
        for (int i = 0; i < size; i++) {
            teachers.add(new Teacher());
        }
    }
}
