package com.example.teachermanagement.service;

import com.example.teachermanagement.model.TeacherGroup;
import com.example.teachermanagement.model.Teacher;
import com.example.teachermanagement.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public TeacherGroup addGroup(TeacherGroup group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new IllegalArgumentException("Group with ID " + id + " does not exist.");
        }
        groupRepository.deleteById(id);
    }

    public List<TeacherGroup> getAllGroups() {
        return groupRepository.findAll();
    }

    public List<Teacher> getTeachersByGroupId(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"))
                .getTeachers()
                .stream().toList();
    }

    public double calculateGroupFill(Long groupId) {
        TeacherGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));

        int currentCount = group.getTeachers().size();
        return (double) currentCount / group.getMaxTeacher() * 100;
    }
}
