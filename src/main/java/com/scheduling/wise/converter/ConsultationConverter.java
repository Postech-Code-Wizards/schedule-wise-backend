package com.scheduling.wise.converter;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.dtos.request.ConsultationRequest;
import com.scheduling.wise.domain.dtos.response.ConsultationResponse;
import com.scheduling.wise.domain.enums.Status;
import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import com.scheduling.wise.gateway.database.entities.DoctorEntity;
import com.scheduling.wise.gateway.database.entities.NurseEntity;
import com.scheduling.wise.gateway.database.entities.PatientEntity;
import com.scheduling.wise.gateway.database.entities.proceduresdtos.ConsultationSummaryDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsultationConverter {
    public Consultation toDomain(ConsultationRequest request) {
        if (request == null) return null;

        return new Consultation(
                request.getId(),
                new Patient(request.getPatientId()),
                new Doctor(request.getDoctorId()),
                new Nurse(request.getNurseId()),
                request.getStatus(),
                request.getScheduledAt(),
                request.getCreatedAt(),
                request.getUpdatedAt(),
                request.getCompletedAt()
        );
    }

    public ConsultationRequest toRequest(Consultation consultation) {
        if (consultation == null) return null;

        return new ConsultationRequest(
                consultation.getId(),
                consultation.getPatient() != null ? consultation.getPatient().getId() : null,
                consultation.getDoctor() != null ? consultation.getDoctor().getId() : null,
                consultation.getNurse() != null ? consultation.getNurse().getId() : null,
                consultation.getStatus(),
                consultation.getScheduledAt(),
                consultation.getCreatedAt(),
                consultation.getUpdatedAt(),
                consultation.getCompletedAt()
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
                consultation.getScheduledAt(),
                consultation.getCreatedAt(),
                consultation.getUpdatedAt(),
                consultation.getCompletedAt()
        );
    }

    public Consultation toDomain(ConsultationEntity entity) {
        if (entity == null) return null;

        return new Consultation(
                entity.getId(),
                entity.getPatient() != null ? new Patient(entity.getPatient().getId()) : null,
                entity.getDoctor() != null ? new Doctor(entity.getDoctor().getId()) : null,
                entity.getNurse() != null ? new Nurse(entity.getNurse().getId()) : null,
                entity.getStatus(),
                entity.getScheduledAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getCompletedAt()
        );
    }

    public ConsultationEntity toEntity(Consultation consultation) {
        if (consultation == null) return null;

        ConsultationEntity entity = new ConsultationEntity();
        entity.setId(consultation.getId());

        entity.setPatient(consultation.getPatient() != null ? new PatientEntity() {{
            setId(consultation.getPatient().getId());
        }} : null);

        entity.setDoctor(consultation.getDoctor() != null ? new DoctorEntity() {{
            setId(consultation.getDoctor().getId());
        }} : null);

        entity.setNurse(consultation.getNurse() != null ? new NurseEntity() {{
            setId(consultation.getNurse().getId());
        }} : null);

        entity.setStatus(consultation.getStatus());
        entity.setScheduledAt(consultation.getScheduledAt());
        entity.setCreatedAt(consultation.getCreatedAt());
        entity.setUpdatedAt(consultation.getUpdatedAt());
        entity.setCompletedAt(consultation.getCompletedAt());

        // Ignorando lista de diagnostics aqui, porque geralmente isso é manipulado em outro nível

        return entity;
    }

    public Consultation toDomain(ConsultationSummaryDTO dto) {
        if (dto == null) return null;

        return new Consultation(
                dto.getConsultationId(),
                new Patient(dto.getPatientId()),
                new Doctor(dto.getDoctorId()),
                new Nurse(dto.getNurseId()),
                Status.valueOf(dto.getStatus()),  // converte String para enum
                dto.getScheduledAt(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getCompletedAt()
        );
    }

    public List<Consultation> toDomain(List<ConsultationSummaryDTO> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<ConsultationResponse> toResponse(List<Consultation> consultations) {
        if (consultations == null) return null;
        return consultations.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
