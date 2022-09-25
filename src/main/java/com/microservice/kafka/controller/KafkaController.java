package com.microservice.kafka.controller;

import com.microservice.kafka.pojo.AccountEntity;
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

@RequestMapping("/api/kafka")
@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @PostMapping
    public String sentMessage(@RequestBody AccountEntity accountEntity) {
        this.kafkaTemplate.send(
                "transaction-1",
                new AccountEntity(
                        accountEntity.getHolder(),
                        accountEntity.getFunds()
                )
        );

        return "Hello world";
    }

    @KafkaListener(topics = "transaction-1")
    public void listener(
            @Payload AccountEntity accountEntity,
            ConsumerRecord<String, AccountEntity> record
    ) {
        logger.info(
                "Topic [transaction-1] received message from {} with {} PLN ",
                accountEntity.getHolder(), accountEntity.getFunds()
        );
        logger.info(record.toString());
    }
}
