package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import com.scheduling.wise.gateway.database.entities.UserEntity;

import java.util.List;

public interface NurseGateway {
    void save(Nurse nurse, PhoneEntity phoneEntity, UserEntity userEntity);

    List<Nurse> getAll();

    Nurse getById(Long id);

    void update(Long id, Nurse nurse);

    void delete(Long id);
}
