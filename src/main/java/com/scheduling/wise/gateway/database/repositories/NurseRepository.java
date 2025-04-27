package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.NurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends JpaRepository<NurseEntity, Long> {
}
