package com.sungwook.lazytest.controller.dto.response.classroom;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetClassroomsConverted {
    private String classroom_name;
    private String school_name;

    @Builder
    public GetClassroomsConverted(String classroom_name, String school_name) {
        this.classroom_name = classroom_name;
        this.school_name = school_name;
    }
}
