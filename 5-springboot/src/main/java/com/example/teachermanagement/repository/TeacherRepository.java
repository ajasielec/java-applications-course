package com.example.teachermanagement.repository;

import com.example.teachermanagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByGroupId(Long groupId);
    Long countByGroupId(Long groupId);
}
