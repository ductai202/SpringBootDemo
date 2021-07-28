package com.example.springboot.controller;

import com.example.springboot.dao.entity.Semester_Subject;
import com.example.springboot.dto.Semester_SubjectDto;
import com.example.springboot.dto.SubjectRequest;
import com.example.springboot.service.Semester_SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Semester_SubjectController {
    private final Semester_SubjectService semester_subjectService;


    @RequestMapping("/subjectinsemester/{idSemester}")
    public List<Semester_SubjectDto> getAllSubjectInSemester(@PathVariable Long idSemester){
        return semester_subjectService.getAllSubjectInSemester(idSemester);
    }

    @PostMapping("/subjectinsemester")
    public  Semester_SubjectDto addSubjectInSemester(@RequestBody SubjectRequest subjectRequest){
        return semester_subjectService.addSubjectInSemester(subjectRequest);
    }

    @PutMapping("/subjectinsemester/{id}")
    public  Semester_SubjectDto updateSubjectInSemester(@RequestBody SubjectRequest subjectRequest,@PathVariable Long id){
        return semester_subjectService.updateSubjectInSemester(id,subjectRequest);
    }


    @DeleteMapping("/subjectinsemester/{idSemester}/{idSubject}")
    public void deleteSubjectInSemester(@PathVariable Long idSemester, @PathVariable Long idSubject){
          semester_subjectService.deleteSubjectInSemester(idSemester,idSubject);
    }




}
