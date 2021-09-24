package com.sungwook.lazytest.service;

import com.sungwook.lazytest.common.exceptions.FailValidation;
import com.sungwook.lazytest.controller.dto.request.school.RequestCreateSchoolDTO;
import com.sungwook.lazytest.entity.School;
import com.sungwook.lazytest.repository.SchoolRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SchoolServiceTest {

    @Autowired
    SchoolService schoolService;

    @Autowired
    SchoolRepository schoolRepository;

    @AfterEach
    public void AfterEach(){
        schoolRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("학교 생성")
    public void CreateSchool(){
        String school_name = "school1";
        RequestCreateSchoolDTO requestCreateSchoolDTO = new RequestCreateSchoolDTO(school_name);

        // 예외가 없으면 성공
        Assertions.assertDoesNotThrow(() -> schoolService.CreateSchool(requestCreateSchoolDTO));
    }

    @Test
    @DisplayName("학교 중복생성 확인")
    public void CreateDuplicateSchool(){
        String school_name = "school1";
        RequestCreateSchoolDTO requestCreateSchoolDTO = new RequestCreateSchoolDTO(school_name);
        Assertions.assertDoesNotThrow(() -> schoolService.CreateSchool(requestCreateSchoolDTO));

        // 오류 발생시 성공
        Assertions.assertThrows(FailValidation.class,
            () -> schoolService.CreateSchool(requestCreateSchoolDTO)
        );
    }

    @Test
    @DisplayName("persistent context 쿼리문 실행 검사")
    public void IsPersistentcontextEqual(){
        String school_name = "school1";
        RequestCreateSchoolDTO requestCreateSchoolDTO = new RequestCreateSchoolDTO(school_name);
        Assertions.assertDoesNotThrow(() -> schoolService.CreateSchool(requestCreateSchoolDTO));

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        School find_school1 = schoolService.findByName(school_name);
        System.out.println(find_school1);

        // 기대: persistent context가 유지되어 select 쿼리문이 실행되지 X
        School find_school2 = schoolService.findByName(school_name);
        System.out.println(find_school2);

        // 기대: persistent context가 유지되어 select 쿼리문이 실행되지 X
        IsPersistentcontextEqual_Subfunction_PrintPersistentcontextInstance(school_name);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Test
    @DisplayName("persistent context 쿼리문 실행 검사 - Transactional사용")
    @Transactional
    public void IsPersistentcontextEqual2(){
        String school_name = "school1";
        RequestCreateSchoolDTO requestCreateSchoolDTO = new RequestCreateSchoolDTO(school_name);
        Assertions.assertDoesNotThrow(() -> schoolService.CreateSchool(requestCreateSchoolDTO));

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        School find_school1 = schoolService.findByName(school_name);
        System.out.println(find_school1);

        // 기대: persistent context가 유지되어 select 쿼리문이 실행되지 X
        School find_school2 = schoolService.findByName(school_name);
        System.out.println(find_school2);

        // 기대: persistent context가 유지되어 select 쿼리문이 실행되지 X
        IsPersistentcontextEqual_Subfunction_PrintPersistentcontextInstance(school_name);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Transactional(readOnly = true)
    public void IsPersistentcontextEqual_Subfunction_PrintPersistentcontextInstance(String school_name){
        School find_school1 = schoolService.findByName(school_name);
        System.out.println(find_school1);
    }
}