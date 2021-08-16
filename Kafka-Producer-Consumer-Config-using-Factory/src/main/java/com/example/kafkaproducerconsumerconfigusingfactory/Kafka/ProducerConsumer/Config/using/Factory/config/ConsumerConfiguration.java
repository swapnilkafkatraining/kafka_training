package com.example.kafkaproducerconsumerconfigusingfactory.Kafka.ProducerConsumer.Config.using.Factory.config;

import com.example.kafkaproducerconsumerconfigusingfactory.Kafka.ProducerConsumer.Config.using.Factory.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
public class ConsumerConfiguration {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @Bean
    public ConsumerFactory<String, User> consumerFactory() {

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE,
                "com.example.kafkaproducerconsumerconfigusingfactory.Kafka.ProducerConsumer.Config.using.Factory.model.User");
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.kafkaproducerconsumerconfigusingfactory.Kafka.ProducerConsumer.Config.using.Factory");

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRetryTemplate(retryTemplate()); // retry logic
//        factory.setRecoveryCallback( (retryContext -> { // recovery callback (it will get executed after max retry attempts)
//            log.info("inside recovery callback");
//            log.info(retryContext.toString());
//            throw new RuntimeException();
//        }
//        ));

        factory.setErrorHandler(((exception, data) -> {
            log.error("Inside Error Handler");
            log.error("Error in process with Exception {} and the record is {}", exception, data);
        }));

//        factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(kafkaTemplate)));

        return factory;
    }

    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(simpleRetryPolicy());
        return retryTemplate;
    }

    private RetryPolicy simpleRetryPolicy() {
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);
        return  simpleRetryPolicy;
    }
}
