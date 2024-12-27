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

    public Teacher() {}
    public Teacher(Long id, String firstName, String lastName, TeacherCondition teacherCondition, int birthYear, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacherCondition = teacherCondition;
        this.birthYear = birthYear;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {this.lastName = lastName;}
    public TeacherCondition getTeacherCondition() {
        return teacherCondition;
    }
    public void setTeacherCondition(TeacherCondition teacherCondition) {this.teacherCondition = teacherCondition;}
    public int getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(int birthYear) {this.birthYear = birthYear;}
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {this.salary = salary;}


}
