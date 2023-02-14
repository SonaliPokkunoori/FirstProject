package com.example.UserConsumer.repository;

import com.example.UserConsumer.entities.UserConsumer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserConsumerRepository extends MongoRepository<UserConsumer, String> {
}
