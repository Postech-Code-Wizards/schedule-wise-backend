package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.PatientConverter;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.gateway.PatientGateway;
import com.scheduling.wise.gateway.database.entities.EmergencyContactEntity;
import com.scheduling.wise.gateway.database.entities.PatientEntity;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import com.scheduling.wise.gateway.database.entities.UserEntity;
import com.scheduling.wise.gateway.database.repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PatientJpaGateway implements PatientGateway {
    private final PatientRepository repository;
    private final PatientConverter converter;

    @Override
    public void save(Patient patient, PhoneEntity phoneEntity, UserEntity userEntity, EmergencyContactEntity emergencyContactEntity) {
        PatientEntity patientEntity = converter.toEntity(patient);
        patientEntity.setPhone(phoneEntity);
        patientEntity.setUser(userEntity);
        patientEntity.setEmergencyContact(emergencyContactEntity);
        repository.save(patientEntity);
    }

    @Override
    public List<Patient> getAll() {
        return converter.toDomain(repository.findAll());
    }

    @Override
    public Patient getById(Long id) {
        return converter.toDomain(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found for id " + id)));
    }

    @Override
    public void update(Long id, Patient patient) {
        PatientEntity newPatientEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found for id " + id));
        PatientEntity patientEntity = converter.toEntity(getById(id));

        patientEntity.setConsultations(newPatientEntity.getConsultations());
        patientEntity.setPhone(newPatientEntity.getPhone());
        patientEntity.setDiagnostics(newPatientEntity.getDiagnostics());
        patientEntity.setDateOfBirth(newPatientEntity.getDateOfBirth());
        patientEntity.setEmergencyContact(newPatientEntity.getEmergencyContact());
        patientEntity.setHasAllergies(newPatientEntity.getHasAllergies());
        repository.save(patientEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
