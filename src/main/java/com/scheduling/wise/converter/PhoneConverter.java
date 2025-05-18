package com.scheduling.wise.converter;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.dtos.request.PhoneRequest;
import com.scheduling.wise.domain.dtos.response.PhoneResponse;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhoneConverter {
    public Phone toDomain(PhoneRequest request) {
        if (request == null) return null;

        return new Phone(
                request.getId(),
                request.getAreaCode(),
                request.getPhoneNumber(),
                request.getPhoneType(),
                request.getOperator(),
                null,
                null
        );
    }

    public PhoneResponse toResponse(Phone phone) {
        if (phone == null) return null;

        return new PhoneResponse(
                phone.getId(),
                phone.getAreaCode(),
                phone.getPhoneNumber(),
                phone.getPhoneType(),
                phone.getOperator(),
                phone.getCreatedAt(),
                phone.getUpdatedAt()
        );
    }

    public Phone toDomain(PhoneEntity entity) {
        if (entity == null) return null;

        return new Phone(
                entity.getId(),
                entity.getAreaCode(),
                entity.getPhoneNumber(),
                entity.getPhoneType(),
                entity.getOperator(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public PhoneEntity toEntity(Phone phone) {
        if (phone == null) return null;

        PhoneEntity entity = new PhoneEntity();
        entity.setId(phone.getId());
        entity.setAreaCode(phone.getAreaCode());
        entity.setPhoneNumber(phone.getPhoneNumber());
        entity.setPhoneType(phone.getPhoneType());
        entity.setOperator(phone.getOperator());
        // createdAt e updatedAt são controlados pelo Hibernate, não seta aqui.

        // Não setar patient, doctor ou nurse aqui, isso deve ser feito no serviço se necessário.

        return entity;
    }

    public List<Phone> toDomain(List<PhoneEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<PhoneResponse> toResponse(List<Phone> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
