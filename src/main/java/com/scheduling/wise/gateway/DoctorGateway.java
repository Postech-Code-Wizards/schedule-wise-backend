package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Doctor;

import java.util.List;

public interface DoctorGateway {
    void save(Doctor doctor);

    List<Doctor> getAll();

    Doctor getById(Long id);

    void update(Long id, Doctor doctor);

    void delete(Long id);
}
