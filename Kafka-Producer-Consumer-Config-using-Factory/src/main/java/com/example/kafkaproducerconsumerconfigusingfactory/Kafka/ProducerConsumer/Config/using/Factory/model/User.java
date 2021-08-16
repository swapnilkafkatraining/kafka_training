package com.example.kafkaproducerconsumerconfigusingfactory.Kafka.ProducerConsumer.Config.using.Factory.model;

import lombok.Data;

@Data
public class User {

     public enum OrderStatus {
          COMPLETED,
          READY,
          PENDING
     }

     private Integer userId;
     private String firstName;
     private String lastName;
     private String country;
     private OrderStatus orderStatus;
}
