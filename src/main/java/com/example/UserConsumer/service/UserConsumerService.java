package com.example.UserConsumer.service;

import com.example.UserConsumer.dto.UserConsumerDTO;
import com.example.UserConsumer.entities.UserConsumer;
import com.example.UserConsumer.repository.UserConsumerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumerService {

    @Autowired
    UserConsumerRepository userConsumerRepository;

    @KafkaListener(topics = "userTopic",groupId = "usergroup4",properties = {"auto.offset.reset:earliest"})
    public void addListenedDetails(ConsumerRecord<String,Object> userConsumerDTO) throws JsonProcessingException {

        System.out.println(userConsumerDTO);

        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UserConsumerDTO userConsumerDTO1=new UserConsumerDTO();
        UserConsumer userConsumer=mapper.readValue(userConsumerDTO.value().toString(),UserConsumer.class);
        System.out.println("Listening to messages on Kafka" + userConsumer);

        UserConsumer userConsumer1=new UserConsumer();
        userConsumer1.setEmail(userConsumer.getEmail());
        userConsumer1.setUserName(userConsumer.getUserName());
        userConsumerRepository.save(userConsumer1);
        System.out.println("Required data stored in Mongo");
    }
}
