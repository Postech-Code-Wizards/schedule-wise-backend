package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.gateway.database.entities.EmergencyContactEntity;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import com.scheduling.wise.gateway.database.entities.UserEntity;

import java.util.List;

public interface PatientGateway {
    void save(Patient patient, PhoneEntity phoneEntity, UserEntity userEntity, EmergencyContactEntity emergencyContactEntity);

    List<Patient> getAll();

    Patient getById(Long id);

    void update(Long id, Patient patient);

    void delete(Long id);
}
