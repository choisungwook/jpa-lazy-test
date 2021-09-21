package com.sungwook.lazytest.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

/***
 * 반 생성 request dto
 */
@Getter
@Setter
public class RequestCreateClassroomDTO {
    private String school_name;
    private String classroom_name;
}
