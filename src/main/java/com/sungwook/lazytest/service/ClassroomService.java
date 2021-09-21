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

    /***
     * 반을 생성할 떄는 학교에 등록하지 않아도 된다.
     * @param request_dto
     * @return
     */
    @Transactional
    public Long CreateClassroom(RequestCreateClassroomDTO request_dto){
        // 유효성 검사
        CreateValidCheck(request_dto.getClassroom_name());

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
     * @param classroom_name 반이름
     */
    public void CreateValidCheck(String classroom_name){
        // 반이 있는지 검사 -> 있으면 예외발생
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
