package com.sungwook.lazytest.controller;

import com.sungwook.lazytest.controller.dto.request.RequestCreateClassroomDTO;
import com.sungwook.lazytest.entity.ClassRoom;
import com.sungwook.lazytest.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classroom")
@Slf4j
public class ClassroomController {
    private final ClassroomService classroomService;

    @GetMapping(value = "/")
    public List<ClassRoom> GetClassrooms(){
        log.debug("------------ 반전체 조회 API 시작 --------------");
        List<ClassRoom> classRooms = classroomService.GetAll();
        log.debug("------------ 반전체 조회 API 종료 --------------");

        return classRooms;
    }

    @PostMapping(value = "/")
    public void CreateClassroom(@RequestBody RequestCreateClassroomDTO requestCreateClassroomDTO){
        log.debug("------------ 반생성 API 시작  --------------");
        classroomService.CreateClassroom(requestCreateClassroomDTO);
        log.debug("------------ 반생성 API 종료  --------------\n");
    }

}
