package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TeacherCondition status;

    @Column(name = "birth_year", nullable = true)
    private Integer birthYear;

    @Column(name = "salary", nullable = true)
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "teacher_group_id", referencedColumnName = "id")
    private Teachergroup teachergroup;

    // getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TeacherCondition getStatus() {
        return status;
    }
    public void setStatus(TeacherCondition status) {
        this.status = status;
    }

    public Integer getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Teachergroup getTeachergroup() { return teachergroup; }
    public void setTeachergroup(Teachergroup teachergroup) { this.teachergroup = teachergroup; }

}