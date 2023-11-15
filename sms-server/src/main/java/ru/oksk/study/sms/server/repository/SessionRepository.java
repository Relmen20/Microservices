package ru.oksk.study.sms.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksk.study.sms.server.entity.SessionEntity;

@Repository
public interface SessionRepository extends JpaRepository <SessionEntity, Integer> {
    SessionEntity findBySessionName(String sessionName);
}
