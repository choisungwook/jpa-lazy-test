package com.sungwook.lazytest.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/***
 * 학교 반 도메인
 */
@Entity
@Table(name = "classroom")
@Getter
@NoArgsConstructor
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    /***
     * 반을 학교에 등록
     * @param school
     */
    public void joinSchool(School school){
        this.school = school;
    }
}
