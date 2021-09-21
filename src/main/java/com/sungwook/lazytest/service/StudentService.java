package com.sungwook.lazytest.service;

import com.sungwook.lazytest.entity.Student;
import com.sungwook.lazytest.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional
    public Long CreateStudent(String name){
        CreateValidCheck(name);

        Student new_student = Student.builder()
                .name(name)
                .build();

        return studentRepository.save(new_student).getId();
    }

    public Student findByName(String name){
        Student find_student = studentRepository.findByName(name)
                .orElseThrow(() -> new IllegalStateException("학생이 존재하지 않습니다"));

        return find_student;
    }

    /***
     * 학생 생성시 유효성 검사
     * 유효성 검사 실패시 예외발생
     * @param name 학생 이름
     */
    private void CreateValidCheck(String name){
        try{
            Student find_student = findByName(name);
            throw new IllegalStateException("학생이 이미 존재합니다");
        }catch (IllegalStateException e){
            //학생이 존재하지 않으면 정상
        }
    }
}
