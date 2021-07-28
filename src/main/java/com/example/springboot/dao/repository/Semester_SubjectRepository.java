package com.example.springboot.dao.repository;

import com.example.springboot.dao.entity.Semester_Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Semester_SubjectRepository extends JpaRepository<Semester_Subject,Long> {

    List<Semester_Subject> findBySemesterId(Long id);

    //Semester_Subject findBySemesterIdAndSubjectId(Long semesterId, Long subjectId);


    void deleteBySemesterIdAndSubjectId(Long semesterId, Long subjectId);


}
