package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Phone;

import java.util.List;

public interface PhoneGateway {
    void save(Phone phone);

    List<Phone> getAll();

    Phone getById(Long id);

    void update(Long id, Phone phone);

    void delete(Long id);
}
