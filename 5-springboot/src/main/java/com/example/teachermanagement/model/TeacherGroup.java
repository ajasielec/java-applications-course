package com.example.teachermanagement.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class TeacherGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int maxTeacher;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Teacher> teachers;


    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rates = new ArrayList<>();

    //getter and setters
    public int getMaxTeacher() {
        return maxTeacher;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Rate> getRates() {
        return rates;
    }
}
