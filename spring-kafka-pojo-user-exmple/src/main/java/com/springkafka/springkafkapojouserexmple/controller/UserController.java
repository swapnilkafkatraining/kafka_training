package com.springkafka.springkafkapojouserexmple.controller;

import com.springkafka.springkafkapojouserexmple.Producer;
import com.springkafka.springkafkapojouserexmple.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final Producer producer;

    @Autowired
    public UserController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping("/user/sentToKafka")
    public String sendUserDetailsToKafka(@RequestBody User user) {
        this.producer.sendMessage(user);
        return "Message sent to Kafka Successfully.";
    }
}
