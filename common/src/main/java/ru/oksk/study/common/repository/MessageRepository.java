package ru.oksk.study.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import ru.oksk.study.common.entity.MessageEntity;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.Status;

@Repository
public interface MessageRepository extends MongoRepository<MessageEntity, String> {

    @Query("{'id': ?0}")
    @Update("{'$push': {'statusHistory': ?1}}")
    void updateStatus(String id, Status status);

    @Query("{'id': ?0}")
    @Update("{{'$push': {'statusHistory': ?1}, '$set': {'errorMessage': ?2}}}")
    void updateStatus(String id, Status status, Error error);

}