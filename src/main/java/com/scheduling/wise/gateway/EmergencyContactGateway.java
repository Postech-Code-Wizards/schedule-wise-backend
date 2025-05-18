package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.EmergencyContact;

import java.util.List;

public interface EmergencyContactGateway {
    void save(EmergencyContact emergencyContact);

    List<EmergencyContact> getAll();

    EmergencyContact getById(Long id);

    void update(Long id, EmergencyContact emergencyContact);

    void delete(Long id);
}
