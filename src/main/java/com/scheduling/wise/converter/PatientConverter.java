package com.scheduling.wise.converter;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.PatientRequest;
import com.scheduling.wise.domain.dtos.response.PatientResponse;
import com.scheduling.wise.gateway.database.entities.PatientEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientConverter {

    private final UserConverter userConverter;
    private final EmergencyContactConverter emergencyContactConverter;
    private final PhoneConverter phoneConverter;

    public PatientConverter(UserConverter userConverter,
                            EmergencyContactConverter emergencyContactConverter,
                            PhoneConverter phoneConverter) {
        this.userConverter = userConverter;
        this.emergencyContactConverter = emergencyContactConverter;
        this.phoneConverter = phoneConverter;
    }

    public Patient toDomain(PatientRequest request) {
        if (request == null) return null;

        User user = request.getUserId() != null ? new User(request.getUserId()) : null;
        EmergencyContact emergencyContact = request.getEmergencyContactId() != null ? new EmergencyContact(request.getEmergencyContactId()) : null;
        Phone phone = request.getPhoneId() != null ? new Phone(request.getPhoneId()) : null;

        return new Patient(
                request.getId(),
                user,
                emergencyContact,
                phone,
                request.getDateOfBirth(),
                null,
                null
        );
    }

    public PatientResponse toResponse(Patient patient) {
        if (patient == null) return null;

        return new PatientResponse(
                patient.getId(),
                patient.getUser(),
                patient.getEmergencyContact(),
                patient.getPhone(),
                patient.getDateOfBirth(),
                patient.getCreatedAt(),
                patient.getUpdatedAt()
        );
    }

    public Patient toDomain(PatientEntity entity) {
        if (entity == null) return null;

        User user = userConverter.toDomain(entity.getUser());
        EmergencyContact emergencyContact = emergencyContactConverter.toDomain(entity.getEmergencyContact());
        Phone phone = phoneConverter.toDomain(entity.getPhone());

        return new Patient(
                entity.getId(),
                user,
                emergencyContact,
                phone,
                entity.getDateOfBirth(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public PatientEntity toEntity(Patient patient) {
        if (patient == null) return null;

        PatientEntity entity = new PatientEntity();
        entity.setId(patient.getId());
        entity.setUser(userConverter.toEntity(patient.getUser()));
        entity.setDateOfBirth(patient.getDateOfBirth());
        entity.setCreatedAt(patient.getCreatedAt());
        entity.setUpdatedAt(patient.getUpdatedAt());
        entity.setEmergencyContact(emergencyContactConverter.toEntity(patient.getEmergencyContact()));
        entity.setPhone(phoneConverter.toEntity(patient.getPhone()));

        return entity;
    }

    public List<Patient> toDomain(List<PatientEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<PatientResponse> toResponse(List<Patient> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
