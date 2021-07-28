package com.example.springboot.dao.dto;

import lombok.Getter;
import lombok.Setter;


public interface TopScore {
     String getStudentName();
     String getSubjectName();
     float getScore();
     Long getScoreRank();
}
