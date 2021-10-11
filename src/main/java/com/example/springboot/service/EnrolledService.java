package com.example.springboot.service;

import com.example.springboot.dao.User.Role;
import com.example.springboot.dao.User.User;
import com.example.springboot.dao.dto.TopScore;
import com.example.springboot.dao.entity.*;
import com.example.springboot.dao.exception.SubjectNotfoundException;
import com.example.springboot.dao.repository.*;
import com.example.springboot.dto.*;
import com.example.springboot.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrolledService {
    private final EnrolledRepository enrolledRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final SemesterRepository semesterRepository;
    private final Semester_SubjectRepository semester_subjectRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


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
        enrolledDto.setCreated_by(enrolled.getCreated_by());
        enrolledDto.setUpdated_by(enrolled.getUpdated_by());
        return enrolledDto;
    }


    public List<EnrolledDto> getAllStudentEnrolled() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Collection<Role> roles   = userRepository.findByUsername(username).getRoles();
        for (Role role : roles) {
            if(role.getName().equals("Role_Admin")){
                continue;
            }
            else throw new IllegalArgumentException("You don't have permission to access !");
        }
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

    public List<EnrolledDto> getScoreStudent() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Long user_id  = userRepository.findByUsername(username).getId();
        Long id = studentRepository.findByUserId(user_id).getId();
        List<EnrolledDto> enrolledDtos = new ArrayList<>();
        List<Enrolled> enrolleds = enrolledRepository.findByStudentId(id);
        for (Enrolled enrolled: enrolleds){
            enrolledDtos.add(mapEntityToDto(enrolled));
        }
        return enrolledDtos;
    }
    public List<EnrolledDto> getScoreStudentAdmin(Long id) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Collection<Role> roles   = userRepository.findByUsername(username).getRoles();
        for (Role role : roles) {
            if(role.getName().equals("Role_Admin")){
                continue;
            }
            else throw new IllegalArgumentException("You don't have permission to access !");
        }
        List<EnrolledDto> enrolledDtos = new ArrayList<>();
        List<Enrolled> enrolleds = enrolledRepository.findByStudentId(id);
        for (Enrolled enrolled: enrolleds){
            enrolledDtos.add(mapEntityToDto(enrolled));
        }
        return enrolledDtos;
    }

    public List<EnrolledDto> getScoreStudentInSemester(Long id, Long id1) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Collection<Role> roles   = userRepository.findByUsername(username).getRoles();
        for (Role role : roles) {
            if(role.getName().equals("Role_Admin")){
                continue;
            }
            else throw new IllegalArgumentException("You don't have permission to access !");
        }
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
        //Subject subject = subjectRepository.findById(enrollRequest.getSubject_id()).orElse(null);
        Student student = studentRepository.findById(enrollRequest.getStudent_id()).orElse(null);
        //Semester semester = semesterRepository.findById(enrollRequest.getSemester_id()).orElse(null);
        Enrolled enrolled = new Enrolled();
        //enrolled.setSubject(subject);
        enrolled.setStudent(student);
        //enrolled.setSemester(semester);
        enrolled.setScore(enrollRequest.getScore());

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        String created_name = userRepository.findByUsername(username).getName();
        enrolled.setCreated_by(created_name);

        Semester_Subject semesterSubject = semester_subjectRepository.findBySemesterIdAndSubjectId(enrollRequest.getSemester_id(),enrollRequest.getSubject_id());
        if(semesterSubject == null) {
            throw new SubjectNotfoundException();
        }
        enrolled.setSubject(semesterSubject.getSubject());
        enrolled.setSemester(semesterSubject.getSemester());
        enrolled = enrolledRepository.save(enrolled);
        return mapEntityToDto(enrolled);
    }

    public EnrolledDto updateEnrolled( Enrolled enrolled, Long id) {
        Enrolled entity = enrolledRepository.findById(id).orElse(null);
        entity.setScore(enrolled.getScore());
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        String updated_name = userRepository.findByUsername(username).getName();
        entity.setUpdated_by(updated_name);
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

    public List<EnrolledDto> getLastestResult() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Long user_id  = userRepository.findByUsername(username).getId();
        Long studentId = studentRepository.findByUserId(user_id).getId();
        List<EnrolledDto> enrolledDtos = new ArrayList<>();
        List<Enrolled> enrolleds = enrolledRepository.latestRecord(studentId);
        for(Enrolled enrolled : enrolleds){
            enrolledDtos.add(mapEntityToDto(enrolled));
        }
        return enrolledDtos;
    }
}
