package com.example.teachermanagement.model;
import jakarta.persistence.*;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private TeacherCondition teacherCondition;

    private int birthYear;
    private int salary;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    private TeacherGroup group;

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public TeacherCondition getTeacherCondition() {
        return teacherCondition;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public double getSalary() {
        return salary;
    }
}
