package com.example.springboot.controller;

import com.example.springboot.dto.TopicDto;
import com.example.springboot.dao.entity.Topic;

import com.example.springboot.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;


    @RequestMapping("/topics")
    public List<TopicDto> getAllTopic(){
        return topicService.getAllTopic();
    }

    @RequestMapping("/topics/{id}")
    public TopicDto getTopic(@PathVariable Long id) {
        return topicService.getTopic(id);
    }

    @PostMapping("/topics")
    public TopicDto addTopic(@RequestBody Topic topic){
        return topicService.addTopic(topic);
    }

    @PutMapping("/topics/{id}")
    public TopicDto updateTopic(@RequestBody Topic topic, @PathVariable Long id){
        return topicService.updateTopic(id, topic);
    }

    @DeleteMapping("/topics/{id}")
    public void deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
    }
}
