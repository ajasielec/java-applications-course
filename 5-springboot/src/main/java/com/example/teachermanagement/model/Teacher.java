package com.example.teachermanagement.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
    private Group group;

}
