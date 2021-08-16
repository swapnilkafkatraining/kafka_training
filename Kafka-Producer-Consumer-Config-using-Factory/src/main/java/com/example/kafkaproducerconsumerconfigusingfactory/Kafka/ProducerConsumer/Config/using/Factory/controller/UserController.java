package com.example.kafkaproducerconsumerconfigusingfactory.Kafka.ProducerConsumer.Config.using.Factory.controller;

import com.example.kafkaproducerconsumerconfigusingfactory.Kafka.ProducerConsumer.Config.using.Factory.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    private final String TOPIC = "kafka-sample-topic-10";

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @PostMapping("/user/sentToKafka")
    public String sendUserDetailsToKafka(@RequestBody User user) {
//        user.setOrderStatus(User.OrderStatus.COMPLETED);
        ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send(TOPIC, user);
        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {

            @Override
            public void onSuccess(SendResult<String, User> result) {
                RecordMetadata recordMetadata = result.getRecordMetadata();
                log.info("inside success callback");
                log.info("Message Successfully send from Kafka Producer");
                System.out.println("Record MetaData Info: " +
                        "{ Topic: " + recordMetadata.topic() + ", " +
                        "Partition: " + recordMetadata.partition() + " }");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("inside failure callback");
                System.out.println("Unable to send message=["
                        + user.toString() + "] due to : " + ex.getMessage());
            }
        });
        return "Message sent to Kafka Successfully.";
    }
}