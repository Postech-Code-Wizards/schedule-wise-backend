package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;

import java.util.List;

public interface PhoneGateway {
    PhoneEntity save(Phone phone);

    List<Phone> getAll();

    Phone getById(Long id);

    void update(Long id, Phone phone);

    void delete(Long id);
}
