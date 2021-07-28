package com.example.springboot.controller;

import com.example.springboot.dao.entity.Enrolled;
import com.example.springboot.dto.EnrollRequest;
import com.example.springboot.dto.EnrolledDto;
import com.example.springboot.service.EnrolledService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EnrolledController {
    private final EnrolledService enrolledService;

    @RequestMapping("/enrolled")
    public List<EnrolledDto> getAllStudentEnrolled(){
        return enrolledService.getAllStudentEnrolled();
    }

//    @RequestMapping("/topscore")
//    public List<TopScore> getStudentsWithHighestScoreForEachSubject(){
//        return enrolledService.getStudentsWithHighestScoreForEachSubject();
//    }

    @RequestMapping("/score/{id}")
    public List<EnrolledDto> getScoreStudent(@PathVariable Long id){
        return enrolledService.getScoreStudent(id);
    }

    @RequestMapping("/score/{id}/{id1}")
    public List<EnrolledDto> getScoreStudentInSemester(@PathVariable Long id, @PathVariable Long id1){
        return enrolledService.getScoreStudentInSemester(id,id1);
    }
//    @RequestMapping("/latestEnrolled/{studentId}")
//    public List<EnrolledDto> getSubjectEnrolledInSemester(@PathVariable Long studentId){
//        return enrolledService.getSubjectEnrolledInSemester(studentId);
//    }
    @RequestMapping("/latestResult/{studentId}")
    public List<EnrolledDto> getLastestResult(@PathVariable Long studentId){
        return enrolledService.getLastestResult(studentId);
    }

    @RequestMapping("/gpa/{id}/{id1}")
    public Float calculateGpaInSemester(@PathVariable Long id, @PathVariable Long id1){
        return enrolledService.calculateGpaInSemester(id,id1);
    }

    @PostMapping("/enrolled")
    public EnrolledDto addEnrolled(@RequestBody EnrollRequest enrollRequest){
        return enrolledService.addEnrolled(enrollRequest);
    }

    @PutMapping("/enrolled/{id}")
    public EnrolledDto updateEnrolled(@RequestBody Enrolled enrolled, @PathVariable Long id){
        return enrolledService.updateEnrolled(enrolled,id);
    }

    @DeleteMapping("/enrolled/{id}")
    public void deleteStudent(@PathVariable Long id) {
        enrolledService.deleteEnrolled(id);
    }

}
