package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomsRepository extends JpaRepository<SymptomEntity, Long> {
}
