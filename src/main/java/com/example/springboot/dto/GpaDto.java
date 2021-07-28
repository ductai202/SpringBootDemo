package com.example.springboot.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GpaDto {
    private Long id;
    private Float gpa;
    private Long semester_id;
    private Long student_id;
}
