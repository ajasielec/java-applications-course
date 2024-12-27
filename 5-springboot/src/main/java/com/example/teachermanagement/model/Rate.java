package com.example.teachermanagement.model;

import jakarta.persistence.*;

@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double rating;

    @ManyToOne
    @JoinColumn(name="group_id", nullable = false)
    private TeacherGroup group;

    public void setGroup(TeacherGroup group) {
        this.group = group;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
}
