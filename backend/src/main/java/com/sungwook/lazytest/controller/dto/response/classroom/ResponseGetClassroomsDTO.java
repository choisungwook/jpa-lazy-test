package com.sungwook.lazytest.controller.dto.response.classroom;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseGetClassroomsDTO {
    private List<GetClassroomsConverted> classrooms;

    public ResponseGetClassroomsDTO(List<GetClassroomsConverted> classrooms) {
        this.classrooms = classrooms;
    }
}
