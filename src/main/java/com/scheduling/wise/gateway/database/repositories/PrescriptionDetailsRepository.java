package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionDetailsRepository extends JpaRepository<PrescriptionDetailsEntity, Long> {
}
