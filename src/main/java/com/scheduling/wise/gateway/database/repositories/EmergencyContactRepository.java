package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.EmergencyContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContactEntity, Long> {
}
