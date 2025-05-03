package com.scheduling.wise.gateway.database.repositories;

import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import com.scheduling.wise.gateway.database.entities.proceduresdtos.ConsultationSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Long> {
    @Procedure(name = "ConsultationEntity.getFutureConsultations")
    List<ConsultationSummaryDTO> getFutureConsultations(@Param("p_patient_id") Long patientId);

    @Procedure(name = "ConsultationEntity.getPatientConsultationHistory")
    List<ConsultationSummaryDTO> getPatientConsultationHistory(@Param("p_patient_id") Long patientId);

}
