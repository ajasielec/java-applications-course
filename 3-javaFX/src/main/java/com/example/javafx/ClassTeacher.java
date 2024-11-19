package com.example.javafx;

import java.util.ArrayList;
import java.util.List;

public class ClassTeacher {
    private String groupName;
    private List<Teacher> teachers;
    private int maxTeachers;

    public ClassTeacher(String groupName, int maxTeachers) {
        this.groupName = groupName;
        this.teachers = new ArrayList<>();
        this.maxTeachers = maxTeachers;
    }

    public String getGroupName() {
        return groupName;
    }
    public int getCapacity(){
        // return ((double)entry.getValue().getTeacherCount() / (double)entry.getValue().maxTeachers() * 100;);
        return (teachers.size()/maxTeachers) * 100;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public void setTeachers(int size) {
        for (int i = 0; i < size; i++) {
            teachers.add(new Teacher());
        }
    }
}
