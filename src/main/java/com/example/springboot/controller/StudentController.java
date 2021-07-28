package com.example.springboot.controller;


import com.example.springboot.dao.entity.Student;
import com.example.springboot.dto.StudentDto;
import com.example.springboot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController  {

    private final StudentService studentService;

    @RequestMapping("/students")
    public List<StudentDto> getAllStudent(){
        return studentService.getAllStudent();
    }

    @RequestMapping("/students/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

//    @RequestMapping("/students/max")
//    public StudentDto getStudentWithHighestGpa(){
//        return studentService.getStudentWithHighestGpa();
//    }
//
//    @RequestMapping("/students/min")
//    public StudentDto getStudentWithLowestGpa(){
//        return studentService.getStudentWithLowestGpa();
//    }

    @PostMapping("/students")
    public StudentDto addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping("/students/{id}")
    public StudentDto updateStudent(@RequestBody Student student, @PathVariable Long id){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}
