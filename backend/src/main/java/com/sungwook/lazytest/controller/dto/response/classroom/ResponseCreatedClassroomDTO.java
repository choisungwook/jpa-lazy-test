package com.sungwook.lazytest.controller.dto.response.classroom;

import lombok.Getter;

@Getter
public class ResponseCreatedClassroomDTO {
    private Long created_id;

    public ResponseCreatedClassroomDTO(Long created_id) {
        this.created_id = created_id;
    }
}
