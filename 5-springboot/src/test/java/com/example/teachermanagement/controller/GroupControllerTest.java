package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.Teacher;
import com.example.teachermanagement.model.TeacherCondition;
import com.example.teachermanagement.model.TeacherGroup;
import com.example.teachermanagement.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class GroupControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GroupService groupService;

    @InjectMocks
    private GroupController groupController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).build();
    }

    @Test
    void testAddGroup() throws Exception {
        TeacherGroup group = new TeacherGroup();
        group.setId(1L);
        group.setName("test");
        group.setMaxTeacher(10);
        group.setTeachers(new ArrayList<>());
        group.setRates(new ArrayList<>());

        when(groupService.addGroup(any(TeacherGroup.class))).thenReturn(group);

        MvcResult result = mockMvc.perform(post("/api/group")
                        .contentType("application/json")
                        .content("{\"id\":1,\"name\":\"test\",\"maxTeacher\":10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.maxTeacher").value(10))
                .andReturn();

        System.out.println("Response content: " + result.getResponse().getContentAsString());

        verify(groupService, times(1)).addGroup(any(TeacherGroup.class));
    }

    @Test
    void testDeleteGroup() throws Exception {
        doNothing().when(groupService).deleteGroup(1L);

        mockMvc.perform(delete("/api/group/1"))
                .andExpect(status().isOk());

        verify(groupService, times(1)).deleteGroup(1L);
    }

    @Test
    void getAllGroups() throws Exception {
        List<TeacherGroup> groups = new ArrayList<>();
        groups.add(new TeacherGroup(1L,"Group A", 10));
        groups.add(new TeacherGroup(2L,"Group B", 20));

        when(groupService.getAllGroups()).thenReturn(groups);

        mockMvc.perform(get("/api/group"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Group A"))
                .andExpect(jsonPath("$[0].maxTeacher").value(10))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Group B"))
                .andExpect(jsonPath("$[1].maxTeacher").value(20));

        verify(groupService, times(1)).getAllGroups();
    }

    @Test
    void testGetTeachersInGroup() throws Exception {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1L, "John", "Doe", TeacherCondition.ABSENT, 1990, 5000));
        teachers.add(new Teacher(2L, "Jane", "Smith", TeacherCondition.DELEGATION, 1985, 5500));

        when(groupService.getTeachersByGroupId(1L)).thenReturn(teachers);

        mockMvc.perform(get("/api/group/1/teacher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));

        verify(groupService, times(1)).getTeachersByGroupId(1L);
    }

    @Test
    void testGetGroupFill() throws Exception {
        double fillPercentage = 75.0;

        when(groupService.calculateGroupFill(1L)).thenReturn(fillPercentage);

        mockMvc.perform(get("/api/group/1/fill"))
                .andExpect(status().isOk())
                .andExpect(content().string("75.0"));

        verify(groupService, times(1)).calculateGroupFill(1L);
    }
}
