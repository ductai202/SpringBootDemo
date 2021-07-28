package com.example.springboot.dao.repository;

import com.example.springboot.dao.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Long> {


}
