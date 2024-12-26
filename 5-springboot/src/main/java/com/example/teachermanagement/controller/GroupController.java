package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.Group;
import com.example.teachermanagement.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping()
    public ResponseEntity<Group> addGroup(@RequestBody Group group) {
        return ResponseEntity.ok(groupService.addGroup(group));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

}
