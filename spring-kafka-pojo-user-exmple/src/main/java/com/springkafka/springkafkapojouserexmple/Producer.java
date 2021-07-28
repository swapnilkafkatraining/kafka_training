package com.springkafka.springkafkapojouserexmple;

import com.springkafka.springkafkapojouserexmple.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC = "pojo-kafka-example-3";
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void sendMessage(User user) {
        this.kafkaTemplate.send(TOPIC, user);
    }
}
