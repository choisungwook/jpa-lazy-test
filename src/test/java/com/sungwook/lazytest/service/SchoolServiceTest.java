package com.sungwook.lazytest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SchoolServiceTest {

    @Autowired
    SchoolService schoolService;

    @Test
    @DisplayName("학교 생성")
    public void CreateSchool(){
        String school_name = "school1";

        // 예외가 없으면 성공
        Assertions.assertDoesNotThrow(() -> schoolService.CreateSchool(school_name));
    }

    @Test
    @DisplayName("학교 중복생성 확인")
    public void CreateDuplicateSchool(){
        String school_name = "school1";
        Assertions.assertDoesNotThrow(() -> schoolService.CreateSchool(school_name));

        // 오류 발생시 성공
        Assertions.assertThrows(IllegalStateException.class,
            () -> schoolService.CreateSchool(school_name)
        );
    }
}