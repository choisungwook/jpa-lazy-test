package com.sungwook.lazytest.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/***
 * 학생 도메인
 */
@Entity
@NoArgsConstructor
@Getter
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private ClassRoom classroom;

    @Builder
    public Student(long id, String name, ClassRoom classroom) {
        this.id = id;
        this.name = name;
        this.classroom = classroom;
    }

    /***
     * 학생을 반에 등록
     * @param classroom
     */
    public void JoinClassRoom(ClassRoom classroom){
        this.classroom = classroom;
    }
}
