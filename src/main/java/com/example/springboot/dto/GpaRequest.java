package com.example.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GpaRequest {
    private Long student_id;
    private Long semester_id;
    private Float gpa;
}
