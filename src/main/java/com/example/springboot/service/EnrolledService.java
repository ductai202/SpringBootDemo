package com.example.springboot.service;

import com.example.springboot.dao.dto.TopScore;
import com.example.springboot.dao.entity.Semester;
import com.example.springboot.dao.entity.Subject;
import com.example.springboot.dao.entity.Enrolled;
import com.example.springboot.dao.entity.Student;
import com.example.springboot.dao.repository.SemesterRepository;
import com.example.springboot.dao.repository.SubjectRepository;
import com.example.springboot.dao.repository.EnrolledRepository;
import com.example.springboot.dao.repository.StudentRepository;
import com.example.springboot.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrolledService {
    private final EnrolledRepository enrolledRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final SemesterRepository semesterRepository;

    private EnrolledDto mapEntityToDto(Enrolled enrolled) {
        if (enrolled == null) {
            return null;
        }
        Student student = enrolled.getStudent();
        Subject subject = enrolled.getSubject();
        Semester semester = enrolled.getSemester();
        EnrolledDto enrolledDto = new EnrolledDto();
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        SemesterDto semesterDto = new SemesterDto();
        semesterDto.setId(semester.getId());
        enrolledDto.setId(enrolled.getId());
        enrolledDto.setStudent_id(studentDto.getId());
        enrolledDto.setSubject_id(subjectDto.getId());
        enrolledDto.setSemester_id(semesterDto.getId());
        enrolledDto.setScore(enrolled.getScore());
        return enrolledDto;
    }


    public List<EnrolledDto> getAllStudentEnrolled() {
        List<EnrolledDto> enrolledDtos = new ArrayList<>();
        List<Enrolled> enrolleds = enrolledRepository.findAll();
        for(Enrolled enrolled : enrolleds){
            enrolledDtos.add(mapEntityToDto(enrolled));
        }
            return enrolledDtos;
    }

//    public List<TopScore> getStudentsWithHighestScoreForEachSubject() {
//        List<TopScore> topScores = enrolledRepository.topscore();
//        return  topScores;
//
//    }

    public List<EnrolledDto> getScoreStudent(Long id) {
        List<EnrolledDto> enrolledDtos = new ArrayList<>();
        List<Enrolled> enrolleds = enrolledRepository.findByStudentId(id);
        for (Enrolled enrolled: enrolleds){
            enrolledDtos.add(mapEntityToDto(enrolled));
        }
        return enrolledDtos;
    }

    public List<EnrolledDto> getScoreStudentInSemester(Long id, Long id1) {
        List<EnrolledDto> enrolledDtos = new ArrayList<>();
        List<Enrolled> enrolleds = enrolledRepository.scoreInSemester(id,id1);
        for (Enrolled enrolled: enrolleds){
            enrolledDtos.add(mapEntityToDto(enrolled));
        }
        return enrolledDtos;
    }

    public Float calculateGpaInSemester(Long id, Long id1) {
        List<Enrolled> enrolleds = enrolledRepository.scoreInSemester(id,id1);
        float gpa = 0;
        for(Enrolled enrolled : enrolleds) {
            gpa += enrolled.getScore();
        }
        gpa = gpa/(enrolleds.size());
        gpa = (gpa*4)/10;
        return gpa;
    }



    public EnrolledDto addEnrolled(EnrollRequest enrollRequest) {
        Subject subject = subjectRepository.findById(enrollRequest.getSubject_id()).orElse(null);
        Student student = studentRepository.findById(enrollRequest.getStudent_id()).orElse(null);
        Semester semester = semesterRepository.findById(enrollRequest.getSemester_id()).orElse(null);
        Enrolled enrolled = new Enrolled();
        enrolled.setSubject(subject);
        enrolled.setStudent(student);
        enrolled.setSemester(semester);
        enrolled.setScore(enrollRequest.getScore());
        enrolled = enrolledRepository.save(enrolled);
        return mapEntityToDto(enrolled);
    }

    public EnrolledDto updateEnrolled( Enrolled enrolled,Long id) {
        Enrolled entity = enrolledRepository.findById(id).orElse(null);
        entity.setScore(enrolled.getScore());
        //entity.setSemester(enrolled.getSemester());
        enrolled = enrolledRepository.save(entity);
        EnrolledDto enrolledDto = mapEntityToDto(enrolled);
        return enrolledDto;
    }

    public void deleteEnrolled(Long id) {
        enrolledRepository.deleteById(id);
    }


//    public List<EnrolledDto> getSubjectEnrolledInSemester(Long studentId) {
//        List<EnrolledDto> enrolledDtos = new ArrayList<>();
//        List<Enrolled> enrolleds = enrolledRepository.latestRecord(studentId);
//        for(Enrolled enrolled : enrolleds){
//            enrolledDtos.add(mapEntityToDto(enrolled));
//        }
//        return enrolledDtos;
//
//    }

    public List<EnrolledDto> getLastestResult(Long studentId) {
        List<EnrolledDto> enrolledDtos = new ArrayList<>();
        List<Enrolled> enrolleds = enrolledRepository.latestRecord(studentId);
        for(Enrolled enrolled : enrolleds){
            enrolledDtos.add(mapEntityToDto(enrolled));
        }
        return enrolledDtos;
    }
}
