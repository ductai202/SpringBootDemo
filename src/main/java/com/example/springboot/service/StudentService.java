package com.example.springboot.service;


import com.example.springboot.dao.entity.Enrolled;
import com.example.springboot.dao.entity.Student;
import com.example.springboot.dao.repository.StudentRepository;
import com.example.springboot.dao.repository.UserRepository;
import com.example.springboot.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    private StudentDto mapEntityToDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setAge(student.getAge());
        studentDto.setName(student.getName());
        studentDto.setCreated_by(student.getCreated_by());
        studentDto.setUpdated_by(student.getUpdated_by());
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
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        String name = userRepository.findByUsername(username).getName();
        student.setCreated_by(name);
        student = studentRepository.save(student);
        return mapEntityToDto(student);
    }

    public StudentDto updateStudent(Long id, Student student) {
        Student entity = studentRepository.findById(id).orElse(null);
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        String name = userRepository.findByUsername(username).getName();
        entity.setUpdated_by(name);
        student = studentRepository.save(entity);
        return mapEntityToDto(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}


