package com.example.springboot.controller;


import com.example.springboot.dao.entity.Gpa;
import com.example.springboot.dto.GpaDto;
import com.example.springboot.dto.GpaRequest;
import com.example.springboot.service.GpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GpaController {
    private final GpaService gpaService;


    @RequestMapping("/gpa")
    public List<GpaDto> getAllGpa(){
        return gpaService.getAllGpa();
    }

    @RequestMapping("/calculate")
    public void calculateGpaInSemester(){
        gpaService.calculateGpaInSemester();
    }

    @RequestMapping("/gpa/{id}")
    public List<GpaDto> getGpaByStudentId(@PathVariable Long id){
        return gpaService.getGpaByStudentId(id);
    }

    @PostMapping("/gpa")
    public GpaDto addGpa(@RequestBody GpaRequest gpaRequest){
        return  gpaService.addGpa(gpaRequest);
    }

    @PutMapping("/gpa/{id}")
    public GpaDto updateGpa(@RequestBody Gpa gpa,@PathVariable Long id){
        return gpaService.updateGpa(id, gpa);
    }

    @DeleteMapping("/gpa/{id}")
    public void deleteGpa(@PathVariable Long id){
        gpaService.deleteGpa(id);
        }
    }



