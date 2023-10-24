package com.study.oksk.StartPoint.repository;

import com.study.oksk.StartPoint.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository <SessionEntity, Integer> {
    public SessionEntity findBySessionName(String sessionName);
}
