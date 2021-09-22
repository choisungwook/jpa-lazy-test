package com.sungwook.lazytest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sungwook.lazytest.controller.dto.request.RequestCreateClassroomDTO;
import com.sungwook.lazytest.repository.ClassroomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClassroomControllerTest {
    @Autowired MockMvc mockMvc;

    @Autowired ClassroomRepository classroomRepository;

    @AfterEach
    public void AfterEach(){
        classroomRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("classroom 생성")
    public void CreateClassroom() throws Exception {
        String classroom_name = "classroom1";
        RequestCreateClassroomDTO requestCreateClassroomDTO = new RequestCreateClassroomDTO();
        requestCreateClassroomDTO.setClassroom_name(classroom_name);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/classroom/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestCreateClassroomDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("classroom 중복생성")
    public void CreateClassroomDuplicate() throws Exception {
        String classroom_name = "classroom1";
        RequestCreateClassroomDTO requestCreateClassroomDTO = new RequestCreateClassroomDTO();
        requestCreateClassroomDTO.setClassroom_name(classroom_name);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/classroom/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestCreateClassroomDTO)))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(post("/api/v1/classroom/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestCreateClassroomDTO)))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}