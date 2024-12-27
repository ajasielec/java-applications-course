package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.Teacher;
import com.example.teachermanagement.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

    @GetMapping("/csv")
    public String getAllTeachersAsCSV() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        StringBuilder csv = new StringBuilder("id,firstName,lastName,teacherCondition,birthYear,salary\n");
        for (Teacher teacher : teachers) {
            csv.append(teacher.getId()).append(",")
                    .append(teacher.getFirstName()).append(",")
                    .append(teacher.getLastName()).append(",")
                    .append(teacher.getTeacherCondition()).append(",")
                    .append(teacher.getBirthYear()).append(",")
                    .append(teacher.getSalary()).append("\n");
        }
        return csv.toString();
    }
}
