package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.Teacher;
import com.example.teachermanagement.model.TeacherCondition;
import com.example.teachermanagement.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class TeacherControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();
    }

    @Test
    void testAddTeacher() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Smith");
        teacher.setTeacherCondition(TeacherCondition.ABSENT);
        teacher.setBirthYear(1992);
        teacher.setSalary(6000);

        when(teacherService.addTeacher(any(Teacher.class))).thenReturn(teacher);

        mockMvc.perform(post("/api/teacher")
                .contentType("application/json")
                .content("{\"firstName\":\"John\",\"lastName\":\"Smith\",\"birthYear\":1992}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    @Test
    void testDeleteTeacher() throws Exception {
        doNothing().when(teacherService).deleteTeacher(1L);

        mockMvc.perform(delete("/api/teacher/1"))
                .andExpect(status().isOk());

        verify(teacherService, times(1)).deleteTeacher(1L);
    }

    @Test
    void testGetAllTeachersAsCSV() throws Exception {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1L, "John", "Doe", TeacherCondition.SICK, 1998, 5000));
        teachers.add(new Teacher(2L, "Jane", "Smith", TeacherCondition.DELEGATION, 1985, 5500));

        when(teacherService.getAllTeachers()).thenReturn(teachers);

        MvcResult result = mockMvc.perform(get("/api/teacher/csv"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("Response content: " + result.getResponse().getContentAsString());


        mockMvc.perform(get("/api/teacher/csv"))
                .andExpect(status().isOk())
                .andExpect(content().string("id,firstName,lastName,teacherCondition,birthYear,salary\n" +
                        "1,John,Doe,SICK,1998,5000\n" +
                        "2,Jane,Smith,DELEGATION,1985,5500\n"));
    }

}
