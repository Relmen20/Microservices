package ru.oksk.study.sms.server.repository;

import ru.oksk.study.sms.server.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository <SessionEntity, Integer> {
    public SessionEntity findBySessionName(String sessionName);
}
