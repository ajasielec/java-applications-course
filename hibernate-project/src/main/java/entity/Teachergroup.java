package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teachergroup")
public class Teachergroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Integer id;

    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @Column(name = "max_teachers", nullable = true)
    private Integer maxTeachers;

    @Column(name = "capacity", nullable = true)
    private Integer capacity;

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

    public Integer getMaxTeachers() {
        return maxTeachers;
    }

    public void setMaxTeachers(Integer maxTeachers) {
        this.maxTeachers = maxTeachers;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

}