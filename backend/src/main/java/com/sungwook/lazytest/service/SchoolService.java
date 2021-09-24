package com.sungwook.lazytest.service;

import com.sungwook.lazytest.common.exceptions.DataNotInDatabase;
import com.sungwook.lazytest.common.exceptions.FailValidation;
import com.sungwook.lazytest.controller.dto.request.school.RequestCreateSchoolDTO;
import com.sungwook.lazytest.entity.School;
import com.sungwook.lazytest.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {
    private final SchoolRepository schoolRepository;

    /***
     * 학교생성
     * @param requestCreateSchoolDTO
     */

    @Transactional
    public Long CreateSchool(RequestCreateSchoolDTO requestCreateSchoolDTO){
        //step1 유효성 검사
        if(!ValidCreateSchool(requestCreateSchoolDTO.getName())){
            throw new FailValidation("이미 학교가 존재합니다.");
        }

        //step2 학교 생성
        School new_school = School.builder()
                .name(requestCreateSchoolDTO.getName())
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
                .orElseThrow(() -> new DataNotInDatabase("학교가 존재하지 않습니다"));

        return find_school;
    }

    /***
     *  학교 생성 전 유효성검사
     * @param name
     * @return True(성공)
     */
    private boolean ValidCreateSchool(String name){
        boolean exist_school = isExistSchool(name);

        if(exist_school)
            return false;

        return true;
    }

    /***
     * 학교가 데이터베이스에 있는지 확인
     * @param name
     * @return True(존재)
     */
    @Transactional(readOnly = true)
    private boolean isExistSchool(String name){
        try{
            School find_school = findByName(name);
            return true;
        }catch (DataNotInDatabase e){
            return false;
        }
    }
}
