package com.sungwook.lazytest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sungwook.lazytest.controller.dto.request.school.RequestCreateSchoolDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SchoolControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("학교 전체 조회")
    public void GetSchools() throws Exception {
        mockMvc.perform(get("/api/v1/schools")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
    @Test
    @DisplayName("학교 생성")
    public void CreateSchool() throws Exception {
        mockMvc.perform(post("/api/v1/schools")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(
                        new RequestCreateSchoolDTO("test school")
                )))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}