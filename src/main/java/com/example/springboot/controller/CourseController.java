package com.example.springboot.controller;

import com.example.springboot.dao.entity.Course;
import com.example.springboot.dto.CourseDto;
import com.example.springboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @RequestMapping("/topics/{id}/courses")
    public List<CourseDto> getAllCoursesByTopicId(@PathVariable Long id){
        return courseService.getAllCoursesByTopicId(id);
    }

    @RequestMapping("/courses/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
    @RequestMapping("/courses")
    public List<CourseDto> getCourseByName(@RequestParam String name) {
        return courseService.getCourseByName(name);
    }
//    @RequestMapping("/courses")
//    public List<CourseDto> getCourseByDescription(@RequestParam String description ) {
//        return courseService.getCourseByDescription(description);
//    }

    @PostMapping("/topics/{id}/courses")
    public CourseDto addCourse(@RequestBody Course course,@PathVariable Long id){

        return  courseService.addCourse(course, id);
    }

    @PutMapping("/courses/{id}")
    public CourseDto updateCourse(@RequestBody Course course, @PathVariable Long id){
        return  courseService.updateCourse(course,id);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
         courseService.deleteCourse(id);
    }
}
