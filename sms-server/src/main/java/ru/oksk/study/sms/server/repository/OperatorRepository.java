package ru.oksk.study.sms.server.repository;

import ru.oksk.study.sms.server.entity.OperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<OperatorEntity, Integer> {
}
