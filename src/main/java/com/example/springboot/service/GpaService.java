package com.example.springboot.service;

import com.example.springboot.dao.entity.*;
import com.example.springboot.dao.repository.EnrolledRepository;
import com.example.springboot.dao.repository.GpaRepository;
import com.example.springboot.dao.repository.SemesterRepository;
import com.example.springboot.dao.repository.StudentRepository;
import com.example.springboot.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GpaService {
    private final GpaRepository gpaRepository;
    private final SemesterRepository semesterRepository;
    private final StudentRepository studentRepository;
    private final EnrolledRepository enrolledRepository;

    private GpaDto mapEntityToDto(Gpa gpa) {
        if (gpa == null) {
            return null;
        }
        GpaDto gpaDto = new GpaDto();
        Semester semester = gpa.getSemester();
        Student student = gpa.getStudent();
        gpaDto.setSemester_id(semester.getId());
        gpaDto.setStudent_id(student.getId());
        gpaDto.setGpa(gpa.getGpa());
        gpaDto.setId(gpa.getId());
        return gpaDto;
    }

    public void calculateGpaInSemester() {
        List<Semester> semesters = semesterRepository.findAll();
        List<Student> students = studentRepository.findAll();
        List<Gpa> gpas = new ArrayList<>();
        for (Semester semester: semesters ) {
            for (Student student : students ){
                List<Enrolled> enrolleds = enrolledRepository.scoreInSemester(semester.getId(),student.getId());
                if (enrolleds.isEmpty()) {
                    continue;
                }
                float gpa = 0;
                for(Enrolled enrolled : enrolleds) {
                    gpa += enrolled.getScore();
                }
                gpa = gpa/(enrolleds.size());
                gpa = (gpa*4)/10;
                if(gpaRepository.findByStudentIdAndSemesterId(student.getId(),semester.getId()) == null ) {
                    Gpa entity = new Gpa();
                    entity.setGpa(gpa);
                    entity.setSemester(semester);
                    entity.setStudent(student);
                    gpas.add(entity);
                }
                else {
                    Gpa entity = gpaRepository.findByStudentIdAndSemesterId(student.getId(),semester.getId());
                    entity.setGpa(gpa);
                    gpaRepository.save(entity);
                }
            }
        }
            gpaRepository.saveAll(gpas);
    }

    public List<GpaDto> getAllGpa(){
        List<GpaDto> gpaDtos = new ArrayList<>();
        List<Gpa> gpas = gpaRepository.findAll();
        for (Gpa gpa : gpas){
            gpaDtos.add(mapEntityToDto(gpa));
        }
        return gpaDtos;


    };

    public List<GpaDto> getGpaByStudentId(Long id){
        List<GpaDto> gpaDtos = new ArrayList<>();
        List<Gpa> gpas = gpaRepository.findByStudentId(id);
        for (Gpa gpa : gpas){
            gpaDtos.add(mapEntityToDto(gpa));
        }
        return gpaDtos;

    };

    public GpaDto addGpa(GpaRequest gpaRequest){
        Student student = studentRepository.findById(gpaRequest.getStudent_id()).orElse(null);
        Semester semester = semesterRepository.findById(gpaRequest.getSemester_id()).orElse(null);
        Gpa gpa = new Gpa();
        gpa.setStudent(student);
        gpa.setSemester(semester);
        gpa.setGpa(gpaRequest.getGpa());
        gpa = gpaRepository.save(gpa);
        return mapEntityToDto(gpa);

    };

    public GpaDto updateGpa(Long id, Gpa gpa){
        Gpa entity = gpaRepository.findById(id).orElse(null);
        entity.setGpa(gpa.getGpa());
        gpa = gpaRepository.save(entity);
        GpaDto gpaDto = mapEntityToDto(gpa);
        return gpaDto;

    };

    public void deleteGpa(Long id){
        gpaRepository.deleteById(id);
    };
}
