package com.study.oksk.Common.repository;

import com.study.oksk.Common.entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageEntity, String> {
}
