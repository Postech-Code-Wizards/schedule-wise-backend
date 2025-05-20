package com.scheduling.wise.converter;

import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.domain.dtos.request.SymptomRequest;
import com.scheduling.wise.domain.dtos.response.SymptomResponse;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SymptomConverter {
    public Symptom toDomain(SymptomRequest request) {
        if (request == null) return null;

        return new Symptom(
                request.getId(),
                request.getName(),
                null,
                null
        );
    }

    public SymptomResponse toResponse(Symptom domain) {
        if (domain == null) return null;

        return new SymptomResponse(
                domain.getId(),
                domain.getName(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    public Symptom toDomain(SymptomEntity entity) {
        if (entity == null) return null;

        return new Symptom(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public SymptomEntity toEntity(Symptom domain) {
        if (domain == null) return null;

        SymptomEntity entity = new SymptomEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());

        return entity;
    }

    public List<Symptom> toDomain(List<SymptomEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<SymptomResponse> toResponse(List<Symptom> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
