package com.example.springboot.service;

import com.example.springboot.dao.entity.*;
import com.example.springboot.dao.repository.SemesterRepository;
import com.example.springboot.dao.repository.Semester_SubjectRepository;
import com.example.springboot.dao.repository.SubjectRepository;
import com.example.springboot.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Semester_SubjectService {
    private final Semester_SubjectRepository semester_subjectRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;

    private Semester_SubjectDto mapEntityToDto(Semester_Subject semester_subject) {
        if (semester_subject == null) {
            return null;
        }
        Subject subject = semester_subject.getSubject();
        Semester semester = semester_subject.getSemester();
        Semester_SubjectDto semester_subjectDto = new Semester_SubjectDto();
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        SemesterDto semesterDto = new SemesterDto();
        semesterDto.setId(semester.getId());
        semester_subjectDto.setSubject_id(subjectDto.getId());
        semester_subjectDto.setSemester_id(semesterDto.getId());
        semester_subjectDto.setId(semester_subject.getId());
        return  semester_subjectDto;
    }

    public List<Semester_SubjectDto> getAllSubjectInSemester(Long id) {
        List<Semester_SubjectDto> semester_subjectDtos = new ArrayList<>();
        List<Semester_Subject> semester_subjects = semester_subjectRepository.findBySemesterId(id);
        for(Semester_Subject semester_subject : semester_subjects){
            semester_subjectDtos.add(mapEntityToDto(semester_subject));
        }
        return semester_subjectDtos;
    }

    public Semester_SubjectDto addSubjectInSemester(SubjectRequest subjectRequest) {
        Semester semester = semesterRepository.findById(subjectRequest.getSemester_id()).orElse(null);
        Subject subject = subjectRepository.findById(subjectRequest.getSubject_id()).orElse(null);
        Semester_Subject semesterSubject = new Semester_Subject();
        semesterSubject.setSubject(subject);
        semesterSubject.setSemester(semester);
        semesterSubject = semester_subjectRepository.save(semesterSubject);
        return mapEntityToDto(semesterSubject) ;
    }

    public Semester_SubjectDto updateSubjectInSemester(Long id, SubjectRequest subjectRequest) {
        Semester_Subject entity = semester_subjectRepository.findById(id).orElse(null);
        Semester semester = semesterRepository.findById(subjectRequest.getSemester_id()).orElse(null);
        Subject subject = subjectRepository.findById(subjectRequest.getSubject_id()).orElse(null);
        entity.setSubject(subject);
        entity.setSemester(semester);
        entity = semester_subjectRepository.save(entity);
        Semester_SubjectDto semester_subjectDto = mapEntityToDto(entity);
        return  semester_subjectDto;
    }

    @Transactional
    public void deleteSubjectInSemester(Long idSemester, Long idSubject) {
        semester_subjectRepository.deleteBySemesterIdAndSubjectId(idSemester,idSubject);
    }
}
