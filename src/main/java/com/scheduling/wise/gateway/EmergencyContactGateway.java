package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.gateway.database.entities.EmergencyContactEntity;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;

import java.util.List;

public interface EmergencyContactGateway {
    EmergencyContactEntity save(EmergencyContact emergencyContact, PhoneEntity phoneEntity);

    List<EmergencyContact> getAll();

    EmergencyContact getById(Long id);

    void update(Long id, EmergencyContact emergencyContact);

    void delete(Long id);
}
