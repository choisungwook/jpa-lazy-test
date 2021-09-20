package com.sungwook.lazytest.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/***
 * 학교 도메인
 */
@Entity
@Table(name = "school")
@Getter
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long id;

    private String name;

    @Builder
    public School(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
