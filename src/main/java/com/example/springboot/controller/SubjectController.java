package com.example.springboot.controller;

import com.example.springboot.dao.entity.Subject;
import com.example.springboot.dto.SubjectDto;
import com.example.springboot.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final SubjectService courseService;


    @RequestMapping("/topics/{id}/courses")
    public List<SubjectDto> getAllCoursesByTopicId(@PathVariable Long id){
        return courseService.getAllCoursesByTopicId(id);
    }

    @RequestMapping("/courses/{id}")
    public SubjectDto getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
    @RequestMapping("/courses")
    public List<SubjectDto> getCourseByName(@RequestParam String name) {
        return courseService.getCourseByName(name);
    }

    @PostMapping("/topics/{id}/courses")
    public SubjectDto addCourse(@RequestBody Subject course, @PathVariable Long id){
        return  courseService.addCourse(course, id);
    }

    @PutMapping("/courses/{id}")
    public SubjectDto updateCourse(@RequestBody Subject course, @PathVariable Long id){
        return  courseService.updateCourse(course,id);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
         courseService.deleteCourse(id);
    }
}
