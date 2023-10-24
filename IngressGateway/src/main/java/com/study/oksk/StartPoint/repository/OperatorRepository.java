package com.study.oksk.StartPoint.repository;

import com.study.oksk.StartPoint.entity.OperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<OperatorEntity, Integer> {
}
