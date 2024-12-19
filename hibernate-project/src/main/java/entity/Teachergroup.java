package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachergroup")
public class Teachergroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "class_teacher_id")
    private List<Teacher> teachers = new ArrayList<Teacher>();

    @Column(name = "max_teachers")
    private int maxTeachers;

//    @Column(name = "capacity")
//    private Integer capacity;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() { return teachers; }
    public void setTeachers(List<Teacher> teachers) {this.teachers = teachers; }

    public Integer getMaxTeachers() {
        return maxTeachers;
    }
    public void setMaxTeachers(Integer maxTeachers) {
        this.maxTeachers = maxTeachers;
    }

//    public Integer getCapacity() {
//        return capacity;
//    }
//    public void setCapacity(Integer capacity) {
//        this.capacity = capacity;
//    }

}