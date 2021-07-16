package com.example.springboot.dao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
private Long id ;
private String name;
private String description;
private TopicDto topicDto;
        }
