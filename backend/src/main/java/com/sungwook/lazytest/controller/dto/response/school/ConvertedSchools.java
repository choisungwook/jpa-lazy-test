package com.sungwook.lazytest.controller.dto.response.school;

import lombok.Getter;

@Getter
public class ConvertedSchools {
    private String name;

    public ConvertedSchools(String name) {
        this.name = name;
    }
}
