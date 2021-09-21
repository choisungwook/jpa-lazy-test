package com.sungwook.lazytest.service;

import com.sungwook.lazytest.controller.dto.RequestAddSchoolFromClassroomDto;
import com.sungwook.lazytest.controller.dto.RequestCreateClassroomDTO;
import com.sungwook.lazytest.repository.ClassroomRepository;
import com.sungwook.lazytest.repository.SchoolRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClassroomServiceTest {
    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    SchoolService schoolService;

    @AfterEach
    public void AfterEach(){
        classroomRepository.deleteAll();
        schoolRepository.deleteAll();
    }

    @Test
    @DisplayName("반 생성-학교등록X")
    public void CreateClassroom(){
        String classroom_name = "classroom1";
        RequestCreateClassroomDTO requestCreateClassroomDTO = new RequestCreateClassroomDTO();
        requestCreateClassroomDTO.setSchool_name("");
        requestCreateClassroomDTO.setClassroom_name(classroom_name);

        // 예외가 발생하지 않으면 성공
        assertDoesNotThrow(() -> classroomService.CreateClassroom(requestCreateClassroomDTO));
    }

    @Test
    @DisplayName("반 생성-학교등록")
    public void CreateClassroomWithShcool(){
        // 반 생성
        String classroom_name = "classroom1";
        RequestCreateClassroomDTO requestCreateClassroomDTO = new RequestCreateClassroomDTO();
        requestCreateClassroomDTO.setSchool_name("");
        requestCreateClassroomDTO.setClassroom_name(classroom_name);
        assertDoesNotThrow(() -> classroomService.CreateClassroom(requestCreateClassroomDTO));

        // 학교 생성
        String school_name = "school1";
        Assertions.assertDoesNotThrow(() -> schoolService.CreateSchool(school_name));

        RequestAddSchoolFromClassroomDto requset_dto = new RequestAddSchoolFromClassroomDto();
        requset_dto.setClassroom_name(classroom_name);
        requset_dto.setSchool_name(school_name);

        /***
         * update 쿼리문 실행
         * Hibernate:
         *     update
         *         classroom
         *     set
         *         name=?,
         *         school_id=?
         *     where
         *         classroom_id=?
         */
        assertDoesNotThrow(() -> classroomService.AddSchool(requset_dto));
    }

}