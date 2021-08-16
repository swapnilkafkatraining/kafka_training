package com.example.kafkaproducerconsumerconfigusingfactory.Kafka.ProducerConsumer.Config.using.Factory;

import com.example.kafkaproducerconsumerconfigusingfactory.Kafka.ProducerConsumer.Config.using.Factory.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "kafka-sample-topic-10", groupId = "group-id")
    public void consume(User user) {
        System.out.println("Consumed Message:" + user.toString());
//           throw new RuntimeException();  // uncomment this to check retry.
    }
}
