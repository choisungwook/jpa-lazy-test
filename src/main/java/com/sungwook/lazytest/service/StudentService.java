package com.sungwook.lazytest.service;

import com.sungwook.lazytest.controller.dto.request.RequestAddClassroomFromStudentDTO;
import com.sungwook.lazytest.entity.ClassRoom;
import com.sungwook.lazytest.entity.Student;
import com.sungwook.lazytest.repository.StudentRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassroomService classroomService;

    @Transactional
    public Long CreateStudent(String name){
        CreateValidCheck(name);

        Student new_student = Student.builder()
                .name(name)
                .build();

        return studentRepository.save(new_student).getId();
    }

    @Transactional
    public void AddClassroom(RequestAddClassroomFromStudentDTO request_dto){
        AddClassroomValidated Validated = AddClassroomValidCheck(request_dto);

        Validated.getStudent().JoinClassRoom(Validated.getClassroom());
    }

    public Student findByName(String name){
        Student find_student = studentRepository.findByName(name)
                .orElseThrow(() -> new IllegalStateException("학생이 존재하지 않습니다"));

        return find_student;
    }

    /***
     * 학생 생성시 유효성 검사
     * 유효성 검사 실패시 예외발생
     * @param name 학생 이름
     */
    private void CreateValidCheck(String name){
        try{
            Student find_student = findByName(name);
            throw new IllegalStateException("학생이 이미 존재합니다");
        }catch (IllegalStateException e){
            //학생이 존재하지 않으면 정상
        }
    }

    /***
     * 학생을 반에 추가할 떄 유효성 검사
     * 유효성검사 실패시 예외발생
      * @param request_dto
     */
    private AddClassroomValidated AddClassroomValidCheck(RequestAddClassroomFromStudentDTO request_dto){
        Student find_student;
        ClassRoom find_classroom;
        try{
            find_student = findByName(request_dto.getStudent_name());
        }catch (IllegalStateException e){
            throw new IllegalStateException("학생이 존재하지 않습니다.");
        }

        try{
            find_classroom = classroomService.findByName(request_dto.getClassroom_name());
        }catch (IllegalStateException e){
            throw new IllegalStateException("반이 존재하지 않습니다.");
        }

        return AddClassroomValidated.builder()
                .validated_student(find_student)
                .validated_classroom(find_classroom)
                .build();
    }

    @NoArgsConstructor
    @Getter
    private static class AddClassroomValidated{
        private Student student;
        private ClassRoom classroom;

        @Builder
        public AddClassroomValidated(Student validated_student, ClassRoom validated_classroom) {
            this.student = validated_student;
            this.classroom = validated_classroom;
        }
    }
}
