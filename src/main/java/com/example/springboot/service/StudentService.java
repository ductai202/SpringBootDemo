package com.example.springboot.service;


import com.example.springboot.dao.entity.Enrolled;
import com.example.springboot.dao.entity.Student;
import com.example.springboot.dao.repository.StudentRepository;
import com.example.springboot.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private StudentDto mapEntityToDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setAge(student.getAge());
        studentDto.setName(student.getName());
        return studentDto;
    }

    @Transactional
    public List<StudentDto> getAllStudent(){
        List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        for (Student student : students){
            studentDtos.add(mapEntityToDto(student));
        }
        return studentDtos;
    }

    public StudentDto getStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        StudentDto studentDto = mapEntityToDto(student);
        return studentDto;
    }


    public StudentDto addStudent(Student student) {
        student = studentRepository.save(student);
        return mapEntityToDto(student);
    }

    public StudentDto updateStudent(Long id, Student student) {
        Student entity = studentRepository.findById(id).orElse(null);
        student = studentRepository.save(entity);
        return mapEntityToDto(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}


