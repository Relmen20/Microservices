package com.study.oksk.MongoDB.repository;

import com.study.oksk.MongoDB.entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageEntity, String> {
}
