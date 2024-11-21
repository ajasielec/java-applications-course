package com.example.javafx;

import java.util.Objects;

public class Teacher {
    private String firstName;
    private String lastName;
    private TeacherCondition condition;
    private int birthYear;
    private int salary;

    public Teacher(String firstName, String surname, TeacherCondition condition, int birthYear, int salary) {
        this.firstName = firstName;
        this.lastName = surname;
        this.condition = condition;
        this.birthYear = birthYear;
        this.salary = salary;
    }
    public Teacher(String firstName, String surname) {
        this.firstName = firstName;
        this.lastName = surname;
    }
    public Teacher(){}

    // getters and setters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public TeacherCondition getCondition() {
        return condition;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setCondition(TeacherCondition condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Sprawdzanie referencji
        if (obj == null || getClass() != obj.getClass()) return false; // Sprawdzenie typu obiektu
        Teacher teacher = (Teacher) obj;
        return firstName.equals(teacher.firstName) && lastName.equals(teacher.lastName); // Porównanie po imieniu i nazwisku
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName); // Hash na podstawie imienia i nazwiska
    }


}
