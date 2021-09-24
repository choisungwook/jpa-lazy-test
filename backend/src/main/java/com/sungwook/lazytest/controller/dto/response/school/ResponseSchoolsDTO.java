package com.sungwook.lazytest.controller.dto.response.school;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseSchoolsDTO {
    List<ConvertedSchools> schools;

    public ResponseSchoolsDTO(List<ConvertedSchools> schools) {
        this.schools = schools;
    }
}
