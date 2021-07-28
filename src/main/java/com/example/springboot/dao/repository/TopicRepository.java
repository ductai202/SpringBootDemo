package com.example.springboot.dao.repository;
import com.example.springboot.dao.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
