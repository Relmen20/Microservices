package ru.oksk.study.sms.blacklist.validator.repository;

import ru.oksk.study.sms.blacklist.validator.entity.OriginatorsBLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginatorBLRepository extends MongoRepository<OriginatorsBLEntity, String> {

    OriginatorsBLEntity findByOriginatorId(int originatorId);
}
