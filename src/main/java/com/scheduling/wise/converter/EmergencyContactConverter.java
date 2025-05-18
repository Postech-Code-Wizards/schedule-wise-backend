package com.scheduling.wise.converter;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.dtos.request.EmergencyContactRequest;
import com.scheduling.wise.domain.dtos.response.EmergencyContactResponse;
import com.scheduling.wise.gateway.database.entities.EmergencyContactEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmergencyContactConverter {
    public EmergencyContact toDomain(EmergencyContactRequest request) {
        if (request == null) return null;

        return new EmergencyContact(
                request.getId(),
                new Patient(request.getPatientId()),
                null, // contactName
                new Phone(request.getPhoneId()),
                request.getRelationshipType(),
                null,
                null
        );
    }

    public EmergencyContactResponse toResponse(EmergencyContact contact) {
        if (contact == null) return null;

        return new EmergencyContactResponse(
                contact.getId(),
                contact.getPatient(),
                contact.getContactName(),
                contact.getPhone(),
                contact.getRelationshipType(),
                contact.getCreatedAt(),
                contact.getUpdatedAt()
        );
    }

    public EmergencyContact toDomain(EmergencyContactEntity entity) {
        if (entity == null) return null;

        return new EmergencyContact(
                entity.getId(),
                entity.getPatient() != null ? new Patient(entity.getPatient().getId()) : null,
                entity.getContactName(),
                entity.getPhone() != null ? new Phone(entity.getPhone().getId()) : null,
                entity.getRelationshipType(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public EmergencyContactEntity toEntity(EmergencyContact contact) {
        if (contact == null) return null;

        EmergencyContactEntity entity = new EmergencyContactEntity();
        entity.setId(contact.getId());
        entity.setPatient(contact.getPatient() != null ? new com.scheduling.wise.gateway.database.entities.PatientEntity() {{
            setId(contact.getPatient().getId());
        }} : null);
        entity.setContactName(contact.getContactName());
        entity.setPhone(contact.getPhone() != null ? new com.scheduling.wise.gateway.database.entities.PhoneEntity() {{
            setId(contact.getPhone().getId());
        }} : null);
        entity.setRelationshipType(contact.getRelationshipType());
        entity.setCreatedAt(contact.getCreatedAt());
        entity.setUpdatedAt(contact.getUpdatedAt());

        return entity;
    }

    public List<EmergencyContact> toDomain(List<EmergencyContactEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<EmergencyContactResponse> toResponse(List<EmergencyContact> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
