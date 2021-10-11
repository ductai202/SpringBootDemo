package com.example.springboot.service;

import com.example.springboot.dto.SubjectDto;
import com.example.springboot.dao.entity.Subject;
import com.example.springboot.dao.entity.Topic;
import com.example.springboot.dao.repository.SubjectRepository;
import com.example.springboot.dao.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;

    private SubjectDto mapEntityToDto(Subject subject) {
        if (subject == null) {
            return null;
        }
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setDescription(subject.getDescription());
        subjectDto.setName(subject.getName());
        subjectDto.setCredits(subject.getCredits());
        return subjectDto;
    }

    @Transactional
    public List<SubjectDto> getAllSubjectsByTopicId(Long id) {
        List<SubjectDto> subjectDtos = new ArrayList<>();
        List<Subject> subjects = subjectRepository.findByTopicId(id);
        for (Subject subject : subjects) {
            subjectDtos.add(mapEntityToDto(subject));
        }
        return subjectDtos;
    }


    public SubjectDto getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        SubjectDto subjectDto = mapEntityToDto(subject);
        return subjectDto ;
    }
    public List<SubjectDto> getSubjectByName(String name ) {
        List<SubjectDto> subjectDtos = new ArrayList<>();
        List<Subject> subjects = subjectRepository.findByName(name);
        for (Subject subject : subjects) {
            subjectDtos.add(mapEntityToDto(subject));
        }
        return subjectDtos;
    }

    public SubjectDto addSubject(Subject subject, Long id) {
        Topic topic = topicRepository.findById(id).orElse(null);
        subject.setTopic(topic);
        Subject subject1 = subjectRepository.save(subject);

         SubjectDto subjectDto = mapEntityToDto(subject1);
         return  subjectDto;
    }

    public SubjectDto updateSubject(Subject subject, Long id) {
        Subject entity = subjectRepository.findById(id).orElse(null);
        entity.setDescription(subject.getDescription());
        entity.setName(subject.getName());
        subject = subjectRepository.save(entity);
        SubjectDto subjectDto = mapEntityToDto(subject);
        return  subjectDto;
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
