package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.DiagnosticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosticsRepository extends JpaRepository<DiagnosticEntity, Long> {
    List<DiagnosticEntity> findAllByConsultationId(Long consultationId);
}
