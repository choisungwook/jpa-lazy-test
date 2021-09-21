package com.sungwook.lazytest.service;

import com.sungwook.lazytest.controller.dto.request.RequestAddSchoolFromClassroomDTO;
import com.sungwook.lazytest.controller.dto.request.RequestCreateClassroomDTO;
import com.sungwook.lazytest.entity.ClassRoom;
import com.sungwook.lazytest.repository.ClassroomRepository;
import com.sungwook.lazytest.repository.SchoolRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void AfterEach(){
        System.out.println("JPA Repository 초기화 시작");
        classroomRepository.deleteAllInBatch();
        schoolRepository.deleteAllInBatch();
        System.out.println("JPA Repository 초기화 완료");
    }

    @Test
    @DisplayName("junit 설정이 잘 완료되었는지 확인")
    public void Dummy(){
        
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

        RequestAddSchoolFromClassroomDTO requset_dto = new RequestAddSchoolFromClassroomDTO();
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

    @Test
    @DisplayName("반 생성과 Lazy쿼리 테스트")
    public void CreateClassroomWithShcool_And_LazyTest(){
        // 반 생성
        System.out.println("step1. classroom 생성 시작");
        String classroom_name = "classroom1";
        RequestCreateClassroomDTO requestCreateClassroomDTO = new RequestCreateClassroomDTO();
        requestCreateClassroomDTO.setSchool_name("");
        requestCreateClassroomDTO.setClassroom_name(classroom_name);
        assertDoesNotThrow(() -> classroomService.CreateClassroom(requestCreateClassroomDTO));
        System.out.println("step1. classroom 생성 완료\n");

        // 학교 생성
        System.out.println("step2. school 생성 시작");
        String school_name = "school1";
        Assertions.assertDoesNotThrow(() -> schoolService.CreateSchool(school_name));

        RequestAddSchoolFromClassroomDTO requset_dto = new RequestAddSchoolFromClassroomDTO();
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
        System.out.println("step2. school 생성 완료\n");

        System.out.println("step3. 반 검색 시작");
        /***
         * 학교이름을 찾으면 반을 찾는 select문이 실행될까? -> NO. 연관관계가 Lazy여서..
         *
         * LAZY 쿼리
         * 
         * Hibernate:
         *     select
         *         school0_.school_id as school_i1_1_,
         *         school0_.name as name2_1_
         *     from
         *         school school0_
         *     where
         *         school0_.name=?
         * 
         */
        ClassRoom find_classroom = classroomService.findByName(classroom_name);

        System.out.println("step3. 반 검색 종료\n");
    }
}