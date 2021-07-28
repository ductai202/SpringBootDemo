package com.example.springboot.controller;
import com.example.springboot.dao.entity.Semester;
import com.example.springboot.dto.SemesterDto;
import com.example.springboot.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SemesterController {
    final private SemesterService semesterService;

    @RequestMapping("/semester")
    public List<SemesterDto> getAllSemester(){
        return semesterService.getAllSemester();
    }

    @PostMapping("/semester")
    public SemesterDto addSemester(@RequestBody Semester semester){
        return semesterService.addSemester(semester);
    }

    @PutMapping("/semester/{id}")
    public SemesterDto updateSemester(@RequestBody Semester semester, @PathVariable Long id){
        return semesterService.updateSemester(semester,id);
    }

    @DeleteMapping("/semester/{id}")
    public void deleteSemester(@PathVariable Long id) {
        semesterService.deleteSemester(id);
    }


}
