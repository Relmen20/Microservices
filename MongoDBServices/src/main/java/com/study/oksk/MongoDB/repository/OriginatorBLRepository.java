package com.study.oksk.MongoDB.repository;

import com.study.oksk.MongoDB.entity.OriginatorsBLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OriginatorBLRepository extends MongoRepository<OriginatorsBLEntity, String> {
}
