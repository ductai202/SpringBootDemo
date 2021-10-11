package com.example.springboot.service;


import com.example.springboot.dao.entity.Semester;
import com.example.springboot.dao.exception.DateTimeConflictException;
import com.example.springboot.dao.repository.SemesterRepository;
import com.example.springboot.dto.SemesterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;
    private SemesterDto mapEntityToDto(Semester semester) {
        if (semester == null) {
            return null;
        }
        SemesterDto semesterDto = new SemesterDto();
        semesterDto.setId(semester.getId());
        semesterDto.setName(semester.getName());
        semesterDto.setStart(semester.getStart());
        semesterDto.setEnd(semester.getEnd());
        return semesterDto;
    }
    public List<SemesterDto> getAllSemester() {
        List<SemesterDto> semesterDtos = new ArrayList<>();
        List<Semester> semesters = semesterRepository.findAll();
        for(Semester semester: semesters){
            semesterDtos.add(mapEntityToDto(semester));
        }
        return  semesterDtos;
    }


    public SemesterDto addSemester(Semester semester) {
        Semester semester1 = new Semester();
        semester1.setStart(semester.getStart());
        semester1.setName(semester.getName());
        semester1.setEnd(semester.getEnd());
        List<Semester> semesters = semesterRepository.findAll();
        for(Semester semester2 : semesters) {
            if (semester1.getStart().after(semester2.getEnd()) || semester1.getEnd().before(semester2.getStart())) {
                continue;
            }
            else throw new DateTimeConflictException();
        }
        semesterRepository.save(semester1);
        //semester = semesterRepository.save(semester);
        return mapEntityToDto(semester1);

    }

    public SemesterDto updateSemester(Semester semester, Long id) {
        semester = semesterRepository.save(semester);
        return mapEntityToDto(semester);

    }

    public void deleteSemester(Long id) {
        semesterRepository.deleteById(id);
    }
}
