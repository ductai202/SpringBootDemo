package com.example.springboot.dao.repository;

import com.example.springboot.dao.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByName(String name);
   // List<Course> findByDescription(String foo);
    List<Subject> findByTopicId(Long id);
}
