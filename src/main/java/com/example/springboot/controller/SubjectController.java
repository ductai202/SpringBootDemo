package com.example.springboot.controller;

import com.example.springboot.dao.entity.Subject;
import com.example.springboot.dto.SubjectDto;
import com.example.springboot.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;


    @RequestMapping("/topics/{id}/subjects")
    public List<SubjectDto> getAllSubjectsByTopicId(@PathVariable Long id){
        return subjectService.getAllSubjectsByTopicId(id);
    }

    @RequestMapping("/subjects/{id}")
    public SubjectDto getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }
    @RequestMapping("/subjects")
    public List<SubjectDto> getSubjectByName(@RequestParam String name) {
        return subjectService.getSubjectByName(name);
    }

    @PostMapping("/topics/{id}/subjects")
    public SubjectDto addSubject(@RequestBody Subject subject, @PathVariable Long id){
        return  subjectService.addSubject(subject, id);
    }

    @PutMapping("/subjects/{id}")
    public SubjectDto updateSubject(@RequestBody Subject subject, @PathVariable Long id){
        return  subjectService.updateSubject(subject,id);
    }

    @DeleteMapping("/subjects/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }
}
