package com.sungwook.lazytest.controller;

import com.sungwook.lazytest.controller.dto.request.school.RequestCreateSchoolDTO;
import com.sungwook.lazytest.controller.dto.response.school.ConvertedSchools;
import com.sungwook.lazytest.controller.dto.response.school.ResponseCreatedSchoolDTO;
import com.sungwook.lazytest.controller.dto.response.school.ResponseSchoolsDTO;
import com.sungwook.lazytest.entity.School;
import com.sungwook.lazytest.repository.SchoolRepository;
import com.sungwook.lazytest.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class SchoolController {
    private final SchoolService schoolService;
    private final SchoolRepository schoolRepository;

    @GetMapping("/schools")
    public ResponseSchoolsDTO GetSchools(){
        log.debug("------------ 학교 전체 조회 API 시작 --------------");
        List<ConvertedSchools> converted = schoolRepository.findAll().stream()
                .map(school -> new ConvertedSchools(school.getName()))
                .collect(Collectors.toList());

        return new ResponseSchoolsDTO(converted);
    }

    @PostMapping("/schools")
    public ResponseEntity<ResponseCreatedSchoolDTO> CreateSchool(@RequestBody RequestCreateSchoolDTO RequestCreateSchoolDTO){
        log.debug("------------ 학교 생성 API 시작 --------------");
        return new ResponseEntity<>(
                new ResponseCreatedSchoolDTO(schoolService.CreateSchool(RequestCreateSchoolDTO)),
                HttpStatus.CREATED
        );
    }

}
