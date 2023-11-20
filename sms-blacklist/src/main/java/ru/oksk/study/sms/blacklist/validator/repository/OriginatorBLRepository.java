package ru.oksk.study.sms.blacklist.validator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.oksk.study.sms.blacklist.validator.entity.OriginatorsBLEntity;

@Repository
public interface OriginatorBLRepository extends MongoRepository<OriginatorsBLEntity, String> {

    OriginatorsBLEntity findByOriginatorId(String originatorId);
}
