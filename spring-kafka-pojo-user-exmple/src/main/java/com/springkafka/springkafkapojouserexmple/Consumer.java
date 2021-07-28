package com.springkafka.springkafkapojouserexmple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springkafka.springkafkapojouserexmple.model.User;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @KafkaListener(topics = "pojo-kafka-example-3", groupId = "group_id")
    public void consumeMessage(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(user));
    }
}
