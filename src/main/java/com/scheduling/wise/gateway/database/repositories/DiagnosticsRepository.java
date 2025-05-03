package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.DiagnosticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticsRepository extends JpaRepository<DiagnosticEntity, Long> {
}
