package com.example.javafx;

public class GroupManager {
    private static TeacherGroup currentGroup;

    public static TeacherGroup getCurrentGroup() {
        return currentGroup;
    }

    public static void setCurrentGroup(TeacherGroup group) {
        currentGroup = group;
    }
}
