package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.TeacherGroup;
import com.example.teachermanagement.model.Teacher;
import com.example.teachermanagement.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public TeacherGroup addGroup(@RequestBody TeacherGroup group) {
        return groupService.addGroup(group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping
    public List<TeacherGroup> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}/teacher")
    public List<Teacher> getTeachersByGroupId(@PathVariable Long id) {
        return groupService.getTeachersByGroupId(id);
    }

    @GetMapping("/{id}/fill")
    public double getGroupFill(@PathVariable Long id) {
        return groupService.calculateGroupFill(id);
    }

}
