package com.example.springboot.service;

import com.example.springboot.dao.entity.Topic;
import com.example.springboot.dao.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public List<Topic> getAllTopic(){
//        return topics;
        return topicRepository.findAll();

    }

    public Topic getTopic(Long id) {
        return topicRepository.findById(id).orElse(null);
//        return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    public void addTopic(Topic topic) {
//        topics.add(topic);
        topicRepository.save(topic);

    }

    public void updateTopic(Long id, Topic topic) {

//        for(int i = 0; i < topics.size(); i++){
//            Topic t = topics.get(i);
//            if(t.getId().equals(id)){
//                topics.set(i, topic);
//                return;
//            }
//        }
        topicRepository.save(topic);
    }

    public void deleteTopic(Long id) {
//        topics.removeIf(t -> t.getId().equals(id));
        topicRepository.deleteById(id);
    }
}
