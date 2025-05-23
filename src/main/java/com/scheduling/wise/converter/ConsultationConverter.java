package com.scheduling.wise.converter;

import com.scheduling.wise.domain.*;
import com.scheduling.wise.domain.dtos.request.ConsultationRequest;
import com.scheduling.wise.domain.dtos.response.ConsultationResponse;
import com.scheduling.wise.gateway.database.entities.*;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsultationConverter {

    public Consultation toDomain(ConsultationRequest request) {
        if (request == null) return null;

        List<Diagnostic> diagnostics = request.getDiagnostics() != null
                ? request.getDiagnostics().stream().map(Diagnostic::new).collect(Collectors.toList())
                : null;

        return new Consultation(
                request.getId(),
                new Patient(request.getPatientId()),
                new Doctor(request.getDoctorId()),
                new Nurse(request.getNurseId()),
                request.getStatus(),
                diagnostics,
                request.getScheduledAt() != null ? ZonedDateTime.parse(request.getScheduledAt()) : null,
                request.getCompletedAt() != null ? ZonedDateTime.parse(request.getCompletedAt()) : null,
                null,
                null
        );
    }

    public ConsultationResponse toResponse(Consultation consultation) {
        if (consultation == null) return null;

        return new ConsultationResponse(
                consultation.getId(),
                consultation.getPatient(),
                consultation.getDoctor(),
                consultation.getNurse(),
                consultation.getStatus(),
                consultation.getDiagnostics(),
                String.valueOf(consultation.getScheduledAt()),
                String.valueOf(consultation.getCompletedAt()),
                String.valueOf(consultation.getCreatedAt()),
                String.valueOf(consultation.getUpdatedAt())
        );
    }

    public Consultation toDomain(ConsultationEntity entity) {
        if (entity == null) return null;

        List<Diagnostic> diagnostics = entity.getDiagnostics() != null
                ? entity.getDiagnostics().stream()
                .map(d -> new Diagnostic(d.getId()))
                .toList()
                : null;

        return new Consultation(
                entity.getId(),
                entity.getPatient() != null ? new Patient(entity.getPatient().getId()) : null,
                entity.getDoctor() != null ? new Doctor(entity.getDoctor().getId()) : null,
                entity.getNurse() != null ? new Nurse(entity.getNurse().getId()) : null,
                entity.getStatus(),
                diagnostics,
                entity.getScheduledAt(),
                entity.getCompletedAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public ConsultationEntity toEntity(Consultation consultation) {
        if (consultation == null) return null;

        ConsultationEntity entity = new ConsultationEntity();
        entity.setId(consultation.getId());

        if (consultation.getPatient() != null) {
            PatientEntity patientEntity = new PatientEntity();
            patientEntity.setId(consultation.getPatient().getId());
            entity.setPatient(patientEntity);
        }

        if (consultation.getDoctor() != null) {
            DoctorEntity doctorEntity = new DoctorEntity();
            doctorEntity.setId(consultation.getDoctor().getId());
            entity.setDoctor(doctorEntity);
        }

        if (consultation.getNurse() != null) {
            NurseEntity nurseEntity = new NurseEntity();
            nurseEntity.setId(consultation.getNurse().getId());
            entity.setNurse(nurseEntity);
        }

        entity.setStatus(consultation.getStatus());
        entity.setScheduledAt(consultation.getScheduledAt());
        entity.setCreatedAt(consultation.getCreatedAt());
        entity.setUpdatedAt(consultation.getUpdatedAt());
        entity.setCompletedAt(consultation.getCompletedAt());

        if (consultation.getDiagnostics() != null) {
            List<DiagnosticEntity> diagnosticEntities = consultation.getDiagnostics().stream().map(diagnostic -> {
                DiagnosticEntity diagnosticEntity = new DiagnosticEntity();
                diagnosticEntity.setId(diagnostic.getId());
                diagnosticEntity.setConsultation(entity);
                return diagnosticEntity;
            }).toList();
            entity.setDiagnostics(diagnosticEntities);
        }

        return entity;
    }

    public List<Consultation> toDomain(List<ConsultationEntity> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<ConsultationResponse> toResponse(List<Consultation> consultations) {
        if (consultations == null) return List.of();
        return consultations.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
