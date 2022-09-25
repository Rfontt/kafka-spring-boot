package com.microservice.kafka.controller;

import com.microservice.kafka.pojo.UserEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/user-kafka")
public class UserController {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public String sentMessage(@RequestBody UserEntity userEntity) {
        this.kafkaTemplate.send(
            "user-list-1",
            new UserEntity(
                    userEntity.getName(),
                    userEntity.getEmail()
            )
        );

        return "Success!";
    }

    @KafkaListener(topics = "user-list-1")
    public void listener(
            @Payload UserEntity userEntity,
            ConsumerRecord<String, UserEntity> record
    ) {
        logger.info(
                "Topic [user-list-1] received message from {}",
                userEntity.getName()
        );
        logger.info(record.toString());
    }
}
