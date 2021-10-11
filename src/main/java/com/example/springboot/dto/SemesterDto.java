package com.example.springboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter

public class SemesterDto {
    private Long id;
    private String name;
    private Date start;
    private Date end;
}
