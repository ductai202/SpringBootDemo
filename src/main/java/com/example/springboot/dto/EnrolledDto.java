package com.example.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrolledDto {

    private Long id;
    private Long student_id;
    private Long subject_id;
    private Long semester_id;
    private Float score;
}

