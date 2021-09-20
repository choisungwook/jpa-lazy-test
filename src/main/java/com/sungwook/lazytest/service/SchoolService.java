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
        //step1 유효성 검사
        ValidIsExistSchool(name);

        //step2 학교 생성
        School new_school = School.builder()
                .name(name)
                .build();

        School save_school = schoolRepository.save(new_school);

        return save_school.getId();
    }

    /***
     * 학교가 이미 등록되었는지 확인
     * @param name 학교이름
     * @return True/False
     */
    @Transactional(readOnly = true)
    public School findByName(String name){
        School find_school = schoolRepository.findByName(name)
                .orElseThrow(() -> new IllegalStateException("학교가 존재하지 않습니다"));

        return find_school;
    }

    /***
     * 학교가 이미 등록되었는지 검사
     *
     * 학교가 이미 존재하면 예외
     * @param name
     */
    @Transactional(readOnly = true)
    private void ValidIsExistSchool(String name){
        try{
            School find_school = findByName(name);
            throw new IllegalStateException("학교가 이미 존재합니다");
        }catch (IllegalStateException e){
            // 학교가 존재하지 않는 것이 정상
        }
    }
}
