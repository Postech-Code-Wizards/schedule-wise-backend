package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import com.scheduling.wise.gateway.database.entities.UserEntity;

import java.util.List;

public interface DoctorGateway {
    void save(Doctor doctor, PhoneEntity phoneEntity, UserEntity userEntity);

    List<Doctor> getAll();

    Doctor getById(Long id);

    void update(Long id, Doctor doctor);

    void delete(Long id);
}
