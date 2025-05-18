package com.scheduling.wise.converter;

import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.domain.dtos.request.PrescriptionDetailsRequest;
import com.scheduling.wise.domain.dtos.response.PrescriptionDetailsResponse;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrescriptionDetailsConverter {
    public PrescriptionDetails toDomain(PrescriptionDetailsRequest request) {
        if (request == null) return null;

        return new PrescriptionDetails(
                request.getId(),
                request.getMedicationName(),
                request.getDosage(),
                request.getFrequency(),
                request.getRouteOfAdministration(),
                request.getInstructions(),
                request.getFollowUpDate(),
                null,
                null
        );
    }

    public PrescriptionDetailsRequest toRequest(PrescriptionDetails domain) {
        if (domain == null) return null;

        return new PrescriptionDetailsRequest(
                domain.getId(),
                domain.getMedicationName(),
                domain.getDosage(),
                domain.getFrequency(),
                domain.getRouteOfAdministration(),
                domain.getInstructions(),
                domain.getFollowUpDate()
        );
    }

    public PrescriptionDetailsResponse toResponse(PrescriptionDetails domain) {
        if (domain == null) return null;

        return new PrescriptionDetailsResponse(
                domain.getId(),
                domain.getMedicationName(),
                domain.getDosage(),
                domain.getFrequency(),
                domain.getRouteOfAdministration(),
                domain.getInstructions(),
                domain.getFollowUpDate(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    public PrescriptionDetails toDomain(PrescriptionDetailsEntity entity) {
        if (entity == null) return null;

        return new PrescriptionDetails(
                entity.getId(),
                entity.getMedicationName(),
                entity.getDosage(),
                entity.getFrequency(),
                entity.getRouteOfAdministration(),
                entity.getInstructions(),
                entity.getFollowUpDate(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public PrescriptionDetailsEntity toEntity(PrescriptionDetails domain) {
        if (domain == null) return null;

        PrescriptionDetailsEntity entity = new PrescriptionDetailsEntity();
        entity.setId(domain.getId());
        entity.setMedicationName(domain.getMedicationName());
        entity.setDosage(domain.getDosage());
        entity.setFrequency(domain.getFrequency());
        entity.setRouteOfAdministration(domain.getRouteOfAdministration());
        entity.setInstructions(domain.getInstructions());
        entity.setFollowUpDate(domain.getFollowUpDate());
        // createdAt e updatedAt são controlados pelo Hibernate, não setar aqui
        // diagnostic deve ser setado externamente no serviço, porque precisa do contexto

        return entity;
    }

    public List<PrescriptionDetails> toDomain(List<PrescriptionDetailsEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<PrescriptionDetailsResponse> toResponse(List<PrescriptionDetails> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
