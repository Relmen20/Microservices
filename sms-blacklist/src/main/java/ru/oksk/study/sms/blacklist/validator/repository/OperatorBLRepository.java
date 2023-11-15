package ru.oksk.study.sms.blacklist.validator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.oksk.study.sms.blacklist.validator.entity.OperatorBLEntity;

@Repository
public interface OperatorBLRepository extends MongoRepository<OperatorBLEntity, String> {
    OperatorBLEntity findByOperatorId(int id);
}
