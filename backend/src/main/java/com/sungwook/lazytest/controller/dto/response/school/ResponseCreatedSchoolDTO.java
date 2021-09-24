package com.sungwook.lazytest.controller.dto.response.school;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCreatedSchoolDTO {
    private Long id;

    public ResponseCreatedSchoolDTO(Long id) {
        this.id = id;
    }
}
