package com.sungwook.lazytest.service;

import com.sungwook.lazytest.controller.dto.request.RequestCreateStudentDTO;
import com.sungwook.lazytest.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {
    @Autowired StudentService studentService;
    @Autowired StudentRepository studentRepository;

    @AfterEach
    public void AfterEach(){
        studentRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("학생 생성")
    public void CreateStudent(){
        String student_name = "demo_student1";
        RequestCreateStudentDTO request_dto = new RequestCreateStudentDTO();
        request_dto.setStudent_name(student_name);

        // 예외가 발생하지 않으면 성공
        /***
         * JPA insert 쿼리
         * Hibernate:
         *     insert
         *     into
         *         student
         *         (student_id, classroom_id, name)
         *     values
         *         (null, ?, ?)
         */
        assertDoesNotThrow(() -> studentService.CreateStudent(student_name));
    }
}