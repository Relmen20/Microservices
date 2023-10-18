package com.study.oksk.MongoDB.repository;

import com.study.oksk.MongoDB.entity.OperatorBLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperatorBLRepository extends MongoRepository<OperatorBLEntity, String> {
}
