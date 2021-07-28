package com.example.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopScoreDto {
    private String student_name;
    private String course_name;
    private float score;
    private Long rank;
}
