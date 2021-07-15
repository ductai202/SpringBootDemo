package com.example.springboot.dao.repository;

import com.example.springboot.dao.entity.Course;
import com.example.springboot.dao.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    List<Course> findByDescription(String foo);
    List<Course> findByTopic(Topic topic);
}
