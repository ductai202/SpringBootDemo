package com.example.springboot.dao.repository;

import com.example.springboot.dao.entity.Gpa;
import com.example.springboot.dto.GpaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GpaRepository extends JpaRepository<Gpa,Long> {
     List<Gpa> findByStudentId(Long id);

     Gpa findByStudentIdAndSemesterId(Long student_id, Long semester_id);

     @Query(value = "SELECT gpa.semester_id,gpa.student_id,gpa, enrolled.score FROM management.gpa inner join enrolled on gpa.student_id = enrolled.student_id",nativeQuery = true)
     List<Gpa> findGpa();
}
