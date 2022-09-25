package com.microservice.kafka.controller;

import com.microservice.kafka.pojo.UserEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@RestController
@RequestMapping("/user-kafka")
public class UserController {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(UserController.class);
    ArrayList<UserEntity> usersList = new ArrayList<>();
    ArrayList<String> emailList = new ArrayList<>();

    @PostMapping("/user")
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

    @PostMapping("/email")
    public String addEmailTopic(@RequestBody UserEntity userEntity) {
        this.kafkaTemplate.send(
                "user-email-list",
                new UserEntity(
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
        usersList.add(userEntity);

        logger.info(
                "Topic [user-list-1] received message from {}",
                userEntity.getName()
        );
        logger.info(record.toString());
    }

    @KafkaListener(topics = "user-email-list")
    public void listenerEmailTopic(
            @Payload UserEntity userEntity,
            ConsumerRecord<String, UserEntity> record
    ) {
        emailList.add(userEntity.getEmail());
    }

    @GetMapping("/user")
    public ResponseEntity<ArrayList<UserEntity>> getUsersList() {
        return new ResponseEntity(usersList, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<ArrayList<String>> getEmailList() {
        return new ResponseEntity(emailList, HttpStatus.OK);
    }
}
