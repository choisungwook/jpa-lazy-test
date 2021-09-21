package com.sungwook.lazytest.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

/***
 * 반에 학생을 추가할 때 사용하는 dto
 */
@Getter
@Setter
public class RequestAddClassroomFromStudentDTO {
    private String student_name;
    private String classroom_name;
}
