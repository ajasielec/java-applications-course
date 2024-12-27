package com.example.teachermanagement.repository;

import com.example.teachermanagement.model.TeacherGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<TeacherGroup, Long> {
}
