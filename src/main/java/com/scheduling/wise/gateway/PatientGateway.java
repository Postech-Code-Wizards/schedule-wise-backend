package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Patient;

import java.util.List;

public interface PatientGateway {
    void save(Patient patient);

    List<Patient> getAll();

    Patient getById(Long id);

    void update(Long id, Patient patient);

    void delete(Long id);
}
