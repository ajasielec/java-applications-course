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

    public TeacherGroup() {
        this.teachers = new ArrayList<>();
        this.rates = new ArrayList<>();
    }
    public TeacherGroup(Long id, String name, int maxTeacher) {
        this.id = id;
        this.name = name;
        this.maxTeacher = maxTeacher;
        this.teachers = new ArrayList<>();
        this.rates = new ArrayList<>();
    }

    //getter and setters
    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public void setMaxTeacher(int maxTeacher) {
        this.maxTeacher = maxTeacher;
    }
    public int getMaxTeacher() {
        return maxTeacher;
    }
    public List<Teacher> getTeachers() {
        return teachers;
    }
    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Object> objects) {
        this.rates = new ArrayList<>();
    }
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
