package com.sungwook.lazytest.service;

import com.sungwook.lazytest.entity.School;
import com.sungwook.lazytest.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {
    private final SchoolRepository schoolRepository;

    /***
     * 학교생성
     * @param name
     */

    @Transactional
    public Long CreateSchool(String name){
        isExistSchool(name);

        School new_school = School.builder()
                .name(name)
                .build();

        School save_school = schoolRepository.save(new_school);

        return save_school.getId();
    }

    private void isExistSchool(String name){
        Optional<School> find_school = schoolRepository.findByName(name);

        if(find_school.isPresent()){
            throw new IllegalStateException("이미 존재하는 학교입니다");
        }
    }
}
