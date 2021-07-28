package com.example.springboot.dao.repository;

import com.example.springboot.dao.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface StudentRepository extends JpaRepository<Student, Long> {

//    @Query(value = "SELECT * from student where gpa = (select max(gpa) from student)",nativeQuery = true)
//    Student max();
//
//    @Query(value = "SELECT * from student where gpa = (select min(gpa) from student)",nativeQuery = true)
//    Student min();
}
