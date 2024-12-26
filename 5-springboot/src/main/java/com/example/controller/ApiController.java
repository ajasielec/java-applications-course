package com.example.controller;

import com.example.model.Group;
import com.example.model.Teacher;
import com.example.repository.GroupRepository;
import com.example.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ApiController {

    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;

    // teacher endpoints
    @PostMapping("/teacher")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/teacher/csv")
    public ResponseEntity<String> getAllTeachersAsCsc() {
        List<Teacher> teachers = teacherRepository.findAll();
        StringBuilder csv = new StringBuilder("id,firstName,lastName,teacherCondition,birthYear,salary,groupId\n");
        for (Teacher teacher : teachers) {
            csv.append(String.format("%d,%s,%s,%s,%d,%d,%d\n",
                    teacher.getId(),
                    teacher.getFirstName(),
                    teacher.getLastName(),
                    teacher.getTeacherCondition(),
                    teacher.getBirthYear(),
                    teacher.getSalary(),
                    teacher.getGroupId()));
        }
        return ResponseEntity.ok(csv.toString());
    }

    // group endpoints
    @PostMapping("/group")
    public ResponseEntity<Group> addGroup(@RequestBody Group group) {
        return ResponseEntity.ok(groupRepository.save(group));
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/group")
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupRepository.findAll());
    }

    @GetMapping("/group/{id}/teacher")
    public ResponseEntity<List<Teacher>> getAllTeachers(@PathVariable Long id) {
        List<Teacher> teachers = teacherRepository.findAll()
                .stream()
                .filter(teacher -> id.equals(teacher.getGroupId()))
                .toList();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/group/{id/fill")
    public ResponseEntity<Double> getGroupFillPercentage(@PathVariable Long id) {
        return groupRepository.findById(id).map(group -> {
            long teacheCount = teacherRepository.findAll()
                    .stream()
                    .filter(teacher -> id.equals(teacher.getGroupId()))
                    .count();
            double fillPercentage = (double) teacheCount / group.getMaxTeacher() * 100;
            return ResponseEntity.ok(fillPercentage);
        }).orElse(ResponseEntity.notFound().build());
    }
}
