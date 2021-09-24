package com.sungwook.lazytest.controller.dto.request.school;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestCreateSchoolDTO {
    private String name;

    public RequestCreateSchoolDTO(String name) {
        this.name = name;
    }
}
