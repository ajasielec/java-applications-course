package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.Rate;
import com.example.teachermanagement.model.TeacherGroup;
import com.example.teachermanagement.service.RateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class RateControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RateService rateService;

    @InjectMocks
    private RatingController ratingController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    }

    @Test
    void testAddRating() throws Exception {
        Rate rate = new Rate();
        rate.setId(1L);
        rate.setRating(5.0);
        rate.setGroup(new TeacherGroup());

        when(rateService.addRate(eq(1L), eq(5.0))).thenReturn(rate);

        mockMvc.perform(post("/api/rating")
                        .param("groupId", "1")
                        .param("rating", "5.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rating").value(5.0));

        verify(rateService, times(1)).addRate(1L, 5.0);
    }
}
