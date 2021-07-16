package com.example.springboot.service;

import com.example.springboot.dto.TopicDto;
import com.example.springboot.dao.entity.Topic;
import com.example.springboot.dao.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    private TopicDto mapEntityToDto(Topic topic) {
        if (topic == null) {
            return null;
        }
        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setName(topic.getName());
        topicDto.setDescription(topic.getDescription());
        return topicDto;
    }

    public List<TopicDto> getAllTopic(){
//        return topics;
        List<TopicDto> topicDtos = new ArrayList<>();
        List<Topic> topics = topicRepository.findAll();
        for (Topic topic : topics) {
            topicDtos.add(mapEntityToDto(topic));
        }
        return topicDtos ;

    }

    public TopicDto getTopic(Long id) {
        Topic topic = topicRepository.findById(id).orElse(null);
        TopicDto topicDto = mapEntityToDto(topic);
        return topicDto;
//        return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    public TopicDto addTopic(Topic topic) {
//        topics.add(topic);
        topic = topicRepository.save(topic);
        return mapEntityToDto(topic);
    }

    public TopicDto updateTopic(Long id, Topic topic) {
       topic =  topicRepository.save(topic);
       return mapEntityToDto(topic);
    }

    public void deleteTopic(Long id) {
//        topics.removeIf(t -> t.getId().equals(id));
         topicRepository.deleteById(id);

    }
}
