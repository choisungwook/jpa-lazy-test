package com.sungwook.lazytest.controller;

import com.sungwook.lazytest.controller.dto.request.RequestCreateClassroomDTO;
import com.sungwook.lazytest.controller.dto.response.classroom.GetClassroomsConverted;
import com.sungwook.lazytest.controller.dto.response.classroom.ResponseCreatedClassroomDTO;
import com.sungwook.lazytest.controller.dto.response.classroom.ResponseGetClassroomsDTO;
import com.sungwook.lazytest.entity.ClassRoom;
import com.sungwook.lazytest.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classroom")
@Slf4j
public class ClassroomController {
    private final ClassroomService classroomService;

    @GetMapping(value = "/")
    public ResponseGetClassroomsDTO GetClassrooms(){
        log.debug("------------ 반전체 조회 API 시작 --------------");
        List<ClassRoom> classRooms = classroomService.GetAll();

        List<GetClassroomsConverted> converted_classroom = classRooms.stream()
                .map(classRoom ->
                        GetClassroomsConverted.builder()
                                .classroom_name(classRoom.getName())
                                .build())
                .collect(Collectors.toList());
        log.debug("------------ 반전체 조회 API 종료 --------------\n");

        return new ResponseGetClassroomsDTO(converted_classroom);
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCreatedClassroomDTO CreateClassroom(@RequestBody RequestCreateClassroomDTO requestCreateClassroomDTO){
        log.debug("------------ 반생성 API 시작  --------------");
        Long created_id = classroomService.CreateClassroom(requestCreateClassroomDTO);
        log.debug("------------ 반생성 API 종료  --------------\n");

        return new ResponseCreatedClassroomDTO(created_id);
    }

}
