package com.example.springboot.controller;

import com.example.springboot.dao.entity.Course;
import com.example.springboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @RequestMapping("/topics/{id}/courses")
    public ResponseEntity<?> getAllCoursesByTopicId(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getAllCoursesByTopicId(id));
    }

    @RequestMapping("/courses/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
    @RequestMapping("/courses")
    public List<Course> getCourseByName(@RequestParam String name) {
        return courseService.getCourseByName(name);
    }
//    @RequestMapping("/courses")
//    public List<Course> getCourseByDescription(@RequestParam String description ) {
//        return courseService.getCourseByDescription(description);
//    }

//    @PostMapping("/topics/{id}/courses")
//    public void addCourse(@RequestBody Course course,@PathVariable Long id){
//        courseService.addCourse(course, id);
//    }
//
//    @PutMapping("/courses/{id}")
//    public void updateCourse(@RequestBody Course course, @PathVariable Long id){
//        courseService.updateCourse(course,id);
//    }
//
//    @DeleteMapping("/courses/{id}")
//    public void deleteCourse(@PathVariable Long id) {
//         courseService.deleteCourse(id);
//    }
}
