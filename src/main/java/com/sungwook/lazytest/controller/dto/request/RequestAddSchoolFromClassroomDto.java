package com.sungwook.lazytest.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

/***
 * 반을 학교에 등록하는 dto
 */
@Getter
@Setter
public class RequestAddSchoolFromClassroomDto {
    private String school_name;
    private String classroom_name;
}
