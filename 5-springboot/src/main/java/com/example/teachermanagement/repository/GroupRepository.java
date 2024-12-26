package com.example.teachermanagement.repository;

import com.example.teachermanagement.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
