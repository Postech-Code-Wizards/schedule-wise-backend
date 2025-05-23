package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Long> {
    List<ConsultationEntity> findConsultationByPatientId(Long id);
}
