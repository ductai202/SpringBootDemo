package com.example.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {
        private Long id;
        private String name;
        private Long credits;
        private String description;
}
