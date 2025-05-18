package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Nurse;

import java.util.List;

public interface NurseGateway {
    void save(Nurse nurse);

    List<Nurse> getAll();

    Nurse getById(Long id);

    void update(Long id, Nurse nurse);

    void delete(Long id);
}
