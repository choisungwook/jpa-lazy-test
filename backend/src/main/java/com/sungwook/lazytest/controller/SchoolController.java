package com.sungwook.lazytest.controller;

import com.sungwook.lazytest.controller.dto.response.school.ConvertedSchools;
import com.sungwook.lazytest.controller.dto.response.school.ResponseSchoolsDTO;
import com.sungwook.lazytest.entity.School;
import com.sungwook.lazytest.repository.SchoolRepository;
import com.sungwook.lazytest.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;
    private final SchoolRepository schoolRepository;

    @GetMapping("/schools")
    public ResponseSchoolsDTO GetSchools(){
        List<ConvertedSchools> converted = schoolRepository.findAll().stream()
                .map(school -> new ConvertedSchools(school.getName()))
                .collect(Collectors.toList());

        return new ResponseSchoolsDTO(converted);
    }

}
