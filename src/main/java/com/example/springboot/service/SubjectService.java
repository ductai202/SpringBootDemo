package com.example.springboot.service;

import com.example.springboot.dto.CourseDto;
import com.example.springboot.dao.entity.Subject;
import com.example.springboot.dao.entity.Topic;
import com.example.springboot.dao.repository.CourseRepository;
import com.example.springboot.dao.repository.TopicRepository;
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

    private CourseDto mapEntityToDto(Subject course) {
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
        List<Subject> courses = courseRepository.findByTopicId(id);
        for (Subject course : courses) {
            courseDtos.add(mapEntityToDto(course));
        }
        return courseDtos;
    }


    public CourseDto getCourseById(Long id) {
        Subject course = courseRepository.findById(id).orElse(null);
        CourseDto courseDto = mapEntityToDto(course);
        return courseDto ;
    }
    public List<CourseDto> getCourseByName(String name ) {
        List<CourseDto> courseDtos = new ArrayList<>();
        List<Subject> courses = courseRepository.findByName(name);
        for (Subject course : courses) {
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

    public CourseDto addCourse(Subject course, Long id) {
        Topic topic = topicRepository.findById(id).orElse(null);
        course.setTopic(topic);
        Subject courses = courseRepository.save(course);

         CourseDto courseDto = mapEntityToDto(courses);
         return  courseDto;
    }

    public CourseDto updateCourse(Subject course, Long id) {
        Subject entity = courseRepository.findById(id).orElse(null);
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
