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

        // Como request tem só IDs e uma lista de symptom IDs, vamos montar objetos só com IDs (muito importante evitar carregar objetos completos aqui)
        List<Symptom> symptoms = null;
        if (request.getSymptoms() != null) {
            symptoms = request.getSymptoms().stream()
                    .map(Symptom::new)
                    .collect(Collectors.toList());
        }

        return new Diagnostic(
                request.getId(),
                new Consultation(request.getConsultationId()),
                new Patient(request.getPatientId()),
                new Doctor(request.getDoctorId()),
                symptoms,
                new PrescriptionDetails(request.getPrescriptionDetailsIs()), // corrigir se for typo: "Is" em vez de "Id"?
                null,
                null
        );
    }

    public DiagnosticRequest toRequest(Diagnostic diagnostic) {
        if (diagnostic == null) return null;

        List<Long> symptomIds = null;
        if (diagnostic.getSymptoms() != null) {
            symptomIds = diagnostic.getSymptoms().stream()
                    .map(Symptom::getId)
                    .collect(Collectors.toList());
        }

        return new DiagnosticRequest(
                diagnostic.getId(),
                diagnostic.getConsultation() != null ? diagnostic.getConsultation().getId() : null,
                diagnostic.getPatient() != null ? diagnostic.getPatient().getId() : null,
                diagnostic.getDoctor() != null ? diagnostic.getDoctor().getId() : null,
                symptomIds,
                diagnostic.getPrescriptionDetails() != null ? diagnostic.getPrescriptionDetails().getId() : null
        );
    }

    public DiagnosticsResponse toResponse(Diagnostic diagnostic) {
        if (diagnostic == null) return null;

        // Aqui o campo symptom é singular no response, mas no domain é lista — pode ser um problema de modelagem
        // Vou pegar o primeiro symptom pra preencher o response, ajusta se quiser diferente
        Symptom firstSymptom = (diagnostic.getSymptoms() != null && !diagnostic.getSymptoms().isEmpty())
                ? diagnostic.getSymptoms().get(0)
                : null;

        return new DiagnosticsResponse(
                diagnostic.getId(),
                diagnostic.getConsultation(),
                diagnostic.getPatient(),
                diagnostic.getDoctor(),
                firstSymptom,
                diagnostic.getPrescriptionDetails(),
                diagnostic.getCreatedAt(),
                diagnostic.getUpdatedAt()
        );
    }

    public Diagnostic toDomain(DiagnosticEntity entity) {
        if (entity == null) return null;

        List<Symptom> symptoms = null;
        if (entity.getSymptoms() != null) {
            symptoms = entity.getSymptoms().stream()
                    .map(symptomEntity -> new Symptom(symptomEntity.getId()))
                    .collect(Collectors.toList());
        }

        PrescriptionDetails prescription = null;
        if (entity.getPrescriptionDetails() != null && !entity.getPrescriptionDetails().isEmpty()) {
            // Pegando o primeiro só, ajusta se for diferente
            prescription = new PrescriptionDetails(entity.getPrescriptionDetails().get(0).getId());
        }

        return new Diagnostic(
                entity.getId(),
                entity.getConsultation() != null ? new Consultation(entity.getConsultation().getId()) : null,
                entity.getPatient() != null ? new Patient(entity.getPatient().getId()) : null,
                entity.getDoctor() != null ? new Doctor(entity.getDoctor().getId()) : null,
                symptoms,
                prescription,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public DiagnosticEntity toEntity(Diagnostic diagnostic) {
        if (diagnostic == null) return null;

        DiagnosticEntity entity = new DiagnosticEntity();
        entity.setId(diagnostic.getId());
        entity.setConsultation(diagnostic.getConsultation() != null ? new ConsultationEntity() {{
            setId(diagnostic.getConsultation().getId());
        }} : null);
        entity.setPatient(diagnostic.getPatient() != null ? new PatientEntity() {{
            setId(diagnostic.getPatient().getId());
        }} : null);
        entity.setDoctor(diagnostic.getDoctor() != null ? new DoctorEntity() {{
            setId(diagnostic.getDoctor().getId());
        }} : null);

        // sintomas e prescrições: cuidado, isso geralmente é feito por cascata ou em outro serviço.
        // Aqui só seto nulo para evitar efeito colateral.
        entity.setSymptoms(null);
        entity.setPrescriptionDetails(null);

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
