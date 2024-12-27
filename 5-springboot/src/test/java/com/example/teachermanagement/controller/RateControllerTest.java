package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.Rate;
import com.example.teachermanagement.model.TeacherGroup;
import com.example.teachermanagement.service.RateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
        rate.setRating(5);
        TeacherGroup group = new TeacherGroup();
        group.setId(1L);
        rate.setGroup(group);

        when(rateService.addRate(any(Rate.class))).thenReturn(rate);

        mockMvc.perform(post("/api/rating")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rating\": 5, \"group\": {\"id\": 1}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(jsonPath("$.group.id").value(1));

        verify(rateService, times(1)).addRate(any(Rate.class));
    }

}
