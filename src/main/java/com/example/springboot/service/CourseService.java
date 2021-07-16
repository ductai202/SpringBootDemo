package com.example.springboot.service;

import com.example.springboot.dto.TopicDto;
import com.example.springboot.dto.CourseDto;
import com.example.springboot.dao.entity.Course;
import com.example.springboot.dao.entity.Topic;
import com.example.springboot.dao.repository.CourseRepository;
import com.example.springboot.dao.repository.TopicRepository;
import com.sun.istack.NotNull;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;

    private CourseDto mapEntityToDto(Course course) {
        if (course == null) {
            return null;
        }
        // topic = course.getTopic();
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setDescription(course.getDescription());
        courseDto.setName(course.getName());
//        TopicDto topicDto = new TopicDto();
//        topicDto.setId(topic.getId());
//        topicDto.setName(topic.getName());
//        topicDto.setDescription(topic.getDescription());
//        courseDto.setTopicDto(topicDto);
        return courseDto;
    }

    @Transactional
    public List<CourseDto> getAllCoursesByTopicId(Long id) {
        List<CourseDto> courseDtos = new ArrayList<>();
        List<Course> courses = courseRepository.findByTopicId(id);
        for (Course course : courses) {
            courseDtos.add(mapEntityToDto(course));
        }
        return courseDtos;
    }


    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        CourseDto courseDto = mapEntityToDto(course);
        return courseDto ;
    }
    public List<CourseDto> getCourseByName(String name ) {
        List<CourseDto> courseDtos = new ArrayList<>();
        List<Course> courses = courseRepository.findByName(name);
        for (Course course : courses) {
            courseDtos.add(mapEntityToDto(course));
        }
        return courseDtos;
    }
//    public List<CourseDto> getCourseByDescription(String description) {
//        List<CourseDto> courseDtos = new ArrayList<>();
//        List<Course> courses = courseRepository.findByDescription(description);
//        for (Course course : courses) {
//            courseDtos.add(mapEntityToDto(course));
//        }
//        return courseDtos;
//    }

    public CourseDto addCourse(Course course, Long id) {
        Topic topic = topicRepository.findById(id).orElse(null);
        course.setTopic(topic);
        Course courses = courseRepository.save(course);
         CourseDto courseDto = mapEntityToDto(courses);
         return  courseDto;
    }

    public CourseDto updateCourse(Course course, Long id) {
        Course entity = courseRepository.findById(id).orElse(null);
        entity.setDescription(course.getDescription());
        entity.setName(course.getName());
        course = courseRepository.save(entity);
        CourseDto courseDto = mapEntityToDto(course);
        return  courseDto;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
