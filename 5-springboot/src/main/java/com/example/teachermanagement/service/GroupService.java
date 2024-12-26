package com.example.teachermanagement.service;

import com.example.teachermanagement.model.Group;
import com.example.teachermanagement.model.Teacher;
import com.example.teachermanagement.repository.GroupRepository;
import com.example.teachermanagement.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    public GroupService(GroupRepository groupRepository, TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }

    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new IllegalArgumentException("Group with ID " + id + " does not exist.");
        }
        groupRepository.deleteById(id);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public List<Teacher> getAllTeachers(Long id) {
        // Zamiast filtrować ręcznie, używamy metody repozytorium
        return teacherRepository.findByGroupId(id);
    }

    public Optional<Double> getGroupFillPercentage(Long id) {
        return groupRepository.findById(id)
                .map(group -> {
                    long teacherCount = teacherRepository.countByGroupId(id);
                    return (double) teacherCount / group.getMaxTeacher() * 100;
                });
    }

    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }
}
