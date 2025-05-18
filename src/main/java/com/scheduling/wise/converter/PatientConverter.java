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
    public Patient toDomain(PatientRequest request) {
        if (request == null) return null;

        // Phones e EmergencyContact precisam ser carregados externamente (não vêm no request, só ids)
        return new Patient(
                request.getId(),
                new User(request.getUserId()),
                new EmergencyContact(request.getEmergencyContactId()),
                new Phone(request.getPhoneId()),
                request.getDateOfBirth(),
                null,
                null
        );
    }

    public PatientRequest toRequest(Patient patient) {
        if (patient == null) return null;

        return new PatientRequest(
                patient.getId(),
                patient.getUser() != null ? patient.getUser().getId() : null,
                patient.getEmergencyContact() != null ? patient.getEmergencyContact().getId() : null,
                patient.getPhone() != null ? patient.getPhone().getId() : null,
                patient.getDateOfBirth()
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

        // Pega primeiro telefone da lista, se existir
        Phone phone = null;
        if (entity.getPhones() != null && !entity.getPhones().isEmpty()) {
            phone = new Phone(entity.getPhones().get(0).getId());
        }

        EmergencyContact emergencyContact = null;
        if (entity.getEmergencyContact() != null) {
            emergencyContact = new EmergencyContact(entity.getEmergencyContact().getId());
        }

        return new Patient(
                entity.getId(),
                new User(entity.getUser()),
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
        entity.setUser(patient.getUser() != null ? patient.getUser().getId() : null);
        entity.setDateOfBirth(patient.getDateOfBirth());
        entity.setCreatedAt(patient.getCreatedAt());
        entity.setUpdatedAt(patient.getUpdatedAt());
        // EmergencyContact, Phones e Consultations são gerenciados separadamente
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
