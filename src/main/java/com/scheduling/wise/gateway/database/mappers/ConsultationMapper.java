package com.scheduling.wise.gateway.database.mappers;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import com.scheduling.wise.gateway.database.entities.DoctorEntity;
import com.scheduling.wise.gateway.database.entities.NurseEntity;
import com.scheduling.wise.gateway.database.entities.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsultationMapper {

    public ConsultationEntity toEntity(Consultation domain) {
        ConsultationEntity entity = new ConsultationEntity();
        entity.setId(domain.getId());

        DoctorEntity doctor = new DoctorEntity();
        doctor.setId(domain.getDoctor().getId());
        entity.setDoctor(doctor);

        PatientEntity patient = new PatientEntity();
        patient.setId(domain.getPatient().getId());
        entity.setPatient(patient);

        if (domain.getNurse() != null) {
            NurseEntity nurse = new NurseEntity();
            nurse.setId(domain.getNurse().getId());
            entity.setNurse(nurse);
        }

        entity.setStatus(domain.getStatus());
        entity.setScheduledAt(domain.getScheduledAt());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setCompletedAt(domain.getCompletedAt());

        return entity;
    }

    public Consultation toDomain(ConsultationEntity entity) {
        return Consultation.builder()
                .id(entity.getId())
                .doctor(entity.getDoctor().toDomain())
                .patient(entity.getPatient().toDomain())
                .nurse(entity.getNurse() != null ? entity.getNurse().toDomain() : null)
                .status(entity.getStatus())
                .scheduledAt(entity.getScheduledAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .completedAt(entity.getCompletedAt())
                .build();
    }
}
