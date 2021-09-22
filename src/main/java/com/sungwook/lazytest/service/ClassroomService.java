package com.sungwook.lazytest.service;

import com.sungwook.lazytest.common.exceptions.DataNotInDatabase;
import com.sungwook.lazytest.common.exceptions.FailValidation;
import com.sungwook.lazytest.controller.dto.request.RequestAddSchoolFromClassroomDTO;
import com.sungwook.lazytest.controller.dto.request.RequestCreateClassroomDTO;
import com.sungwook.lazytest.entity.ClassRoom;
import com.sungwook.lazytest.entity.School;
import com.sungwook.lazytest.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
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
        if(!CreateValidCheck(request_dto.getClassroom_name())){
            throw new FailValidation("이미 반이 존재합니다.");
        }

        log.debug("[반생성] 유효성 검사 통과");

        ClassRoom new_class = ClassRoom.builder()
                                        .name(request_dto.getClassroom_name())
                                        .build();

        return classroomRepository.save(new_class).getId();
    }

    public ClassRoom findByName(String name){
        ClassRoom find_classroom = classroomRepository.findByName(name)
                .orElseThrow(() -> new DataNotInDatabase("반 이름이 존재하지 않습니다"));

        return find_classroom;
    }

    public List<ClassRoom> GetAll(){
        return classroomRepository.findAll();
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
     * @param classroom_name
     * @return true(성공), false(실패)
     */
    public boolean CreateValidCheck(String classroom_name){
        // 반이 있는지 검사 -> 있으면 예외발생
        boolean exist_classroom = isExistClassroom(classroom_name);

        if(exist_classroom)
            return false;

        return true;
    }

    /***
     * 반이 있는지 검사
     * @param name
     * @return 존재(true), 미존재(false)
     */
    private boolean isExistClassroom(String name){
        try{
            findByName(name);
            return true;
        }catch (DataNotInDatabase e){
            // 반이 없어 예외가 발생하면 정상
            return false;
        }
    }
}
