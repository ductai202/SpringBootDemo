package com.example.springboot.service;

import com.example.springboot.dao.entity.Course;
import com.example.springboot.dao.entity.Topic;
import com.example.springboot.dao.repository.CourseRepository;
import com.example.springboot.dao.repository.TopicRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;

    @Transactional
    public List<Course> getAllCoursesByTopicId(Long id){
        Optional<Topic> topic = topicRepository.findById(id);
        Topic topic1 = topic.get();
        List<Course> courses = courseRepository.findByTopic(topic1);
        return courses;
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
    public List<Course> getCourseByName( String name ) {

        return courseRepository.findByName(name);
    }
//    public List<Course> getCourseByDescription(String description) {
//        return courseRepository.findByDescription(description);
//    }

//    public void addCourse(Course course, Long id) {
//        Topic topic = topicRepository.findById(id).orElse(null);
//        course.setTopic(topic);
//         courseRepository.save(course);
//    }
//
//    public void updateCourse(Course course, Long id) {
//        Course entity = getCourseById(id);
//        entity.setDescription(course.getDescription());
//        entity.setName(course.getName());
//        courseRepository.save(entity);
//    }
//
//    public void deleteCourse(Long id) {
//        courseRepository.deleteById(id);
//    }
}
