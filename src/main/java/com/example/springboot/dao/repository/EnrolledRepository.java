package com.example.springboot.dao.repository;

import com.example.springboot.dao.dto.TopScore;
import com.example.springboot.dao.entity.Enrolled;
import com.example.springboot.dao.entity.Semester_Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrolledRepository extends JpaRepository<Enrolled,Long> {

    @Query(value = "SELECT * FROM (\n" +
            "SELECT S1.name as subjectName , S.name as studentName , E.score , RANK()\n" +
            "OVER (PARTITION BY E.subject_id ORDER BY E.score DESC) AS scoreRank\n" +
            "FROM student as S, subject as S1, enrolled as E\n" +
            "WHERE S.id = E.student_id AND S1.id= E.subject_id \n" +
            ") AS ranking\n" +
            "WHERE ranking.scoreRank = 1",nativeQuery = true)
    List<TopScore> topscore();

    @Query(value = "SELECT * FROM management.enrolled where semester_id = :semester_id and student_id = :student_id",nativeQuery=true)
    List<Enrolled> scoreInSemester(@Param("semester_id") Long id1,@Param("student_id") Long id);

    @Query(nativeQuery = true,value = "select * from enrolled where semester_id = (select semester_id from enrolled order by semester_id desc limit 1) and student_id = :student_id ")
    List<Enrolled> latestRecord(@Param("student_id") Long student_id);

    List<Enrolled> findByStudentId(Long id);


}
