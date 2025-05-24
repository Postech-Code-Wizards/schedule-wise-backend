package com.scheduling.wise.converter;

import com.scheduling.wise.domain.*;
import com.scheduling.wise.domain.dtos.request.DiagnosticRequest;
import com.scheduling.wise.domain.dtos.response.DiagnosticsResponse;
import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import com.scheduling.wise.gateway.database.entities.DiagnosticEntity;
import com.scheduling.wise.gateway.database.entities.DoctorEntity;
import com.scheduling.wise.gateway.database.entities.PatientEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiagnosticConverter {
    public Diagnostic toDomain(DiagnosticRequest request) {
        if (request == null) return null;


        return new Diagnostic(
                request.getId(),
                new Consultation(request.getConsultationId()),
                new Patient(request.getPatientId()),
                new Doctor(request.getDoctorId()),
                null,
                null
        );
    }

    public DiagnosticsResponse toResponse(Diagnostic diagnostic) {
        if (diagnostic == null) return null;


        return new DiagnosticsResponse(
                diagnostic.getId(),
                diagnostic.getConsultation(),
                diagnostic.getPatient(),
                diagnostic.getDoctor(),
                diagnostic.getCreatedAt(),
                diagnostic.getUpdatedAt()
        );
    }

    public Diagnostic toDomain(DiagnosticEntity entity) {
        if (entity == null) return null;

        List<Symptom> symptoms = null;

        return new Diagnostic(
                entity.getId(),
                entity.getConsultation() != null ? new Consultation(entity.getConsultation().getId()) : null,
                entity.getPatient() != null ? new Patient(entity.getPatient().getId()) : null,
                entity.getDoctor() != null ? new Doctor(entity.getDoctor().getId()) : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public DiagnosticEntity toEntity(Diagnostic diagnostic) {
        if (diagnostic == null) return null;

        DiagnosticEntity entity = new DiagnosticEntity();
        entity.setId(diagnostic.getId());

        if (diagnostic.getConsultation() != null) {
            ConsultationEntity consultation = new ConsultationEntity();
            consultation.setId(diagnostic.getConsultation().getId());
            entity.setConsultation(consultation);
        }

        if (diagnostic.getPatient() != null) {
            PatientEntity patient = new PatientEntity();
            patient.setId(diagnostic.getPatient().getId());
            entity.setPatient(patient);
        }

        if (diagnostic.getDoctor() != null) {
            DoctorEntity doctor = new DoctorEntity();
            doctor.setId(diagnostic.getDoctor().getId());
            entity.setDoctor(doctor);
        }


        entity.setCreatedAt(diagnostic.getCreatedAt());
        entity.setUpdatedAt(diagnostic.getUpdatedAt());

        return entity;
    }

    public List<Diagnostic> toDomain(List<DiagnosticEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<DiagnosticsResponse> toResponse(List<Diagnostic> diagnostics) {
        if (diagnostics == null) return null;
        return diagnostics.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
