package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
