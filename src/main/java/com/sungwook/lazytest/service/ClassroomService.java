package com.sungwook.lazytest.service;

import com.sungwook.lazytest.controller.dto.request.RequestAddSchoolFromClassroomDTO;
import com.sungwook.lazytest.controller.dto.request.RequestCreateClassroomDTO;
import com.sungwook.lazytest.entity.ClassRoom;
import com.sungwook.lazytest.entity.School;
import com.sungwook.lazytest.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassroomService {
    private final SchoolService schoolService;
    private final ClassroomRepository classroomRepository;

    @Transactional
    public Long CreateClassroom(RequestCreateClassroomDTO request_dto){
        // 유효성 검사
        ValidCheck(request_dto.getSchool_name(), request_dto.getClassroom_name());

        ClassRoom new_class = ClassRoom.builder()
        .name(request_dto.getClassroom_name())
        .build();

        return classroomRepository.save(new_class).getId();
    }

    public ClassRoom findByName(String name){
        ClassRoom find_classroom = classroomRepository.findByName(name)
                .orElseThrow(() -> new IllegalStateException("반 이름이 존재하지 않습니다"));

        return find_classroom;
    }

    /***
     * 반을 학교에 등록
     * 실패시 예외발생
     * @param request_dto
     */
    @Transactional
    public void AddSchool(RequestAddSchoolFromClassroomDTO request_dto){
        ClassRoom find_classroom = findByName(request_dto.getClassroom_name());
        School find_school = schoolService.findByName(request_dto.getSchool_name());

        find_classroom.joinSchool(find_school);
    }

    /***
     * 유효성 검사
     * @param school_name 학교이름
     * @param classroom_name 반이름
     */
    public void ValidCheck(String school_name, String classroom_name){

        // step1. 학교가 존재하는지 검사 -> 존재하지 않으면 예외발생
        isExistClassroom(school_name);

        // step1. 반이 있는지 검사 -> 있으면 예외발생
        isExistClassroom(classroom_name);
    }

    /***
     * 반이 있는지 검사
     * @param name
     */
    private void isExistClassroom(String name){
        try{
            findByName(name);
            throw new IllegalStateException("반 이름이 이미 존재합니다");
        }catch (IllegalStateException e){
            // 반이 없으면 정상
        }
    }
}
