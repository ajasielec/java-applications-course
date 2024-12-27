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

    public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}
    public void setGroup(TeacherGroup group) {
        this.group = group;
    }
    public TeacherGroup getGroup() {return group;}
    public void setRating(double rating) {
        this.rating = rating;
    }
    public double getRating() {return rating;}

}
