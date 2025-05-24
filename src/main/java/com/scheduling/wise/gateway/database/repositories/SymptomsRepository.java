package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomsRepository extends JpaRepository<SymptomEntity, Long> {
    List<SymptomEntity> findByDiagnosticId(Long id);
}
