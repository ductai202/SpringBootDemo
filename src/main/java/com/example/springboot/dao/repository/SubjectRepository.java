package com.example.springboot.dao.repository;

import com.example.springboot.dao.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByName(String name);
   // List<Course> findByDescription(String foo);
    List<Subject> findByTopicId(Long id);
}
