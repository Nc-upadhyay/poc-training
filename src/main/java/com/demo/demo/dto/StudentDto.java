package com.demo.demo.dto;

import com.demo.demo.annotation.MaskData;

public class StudentDto {

    private String name;
    @MaskData
    private int roll;

    public StudentDto(String name, int roll) {
        this.name = name;
        this.roll = roll;
    }
}
