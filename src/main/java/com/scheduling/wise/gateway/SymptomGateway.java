package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;

import java.util.List;

public interface SymptomGateway {
    SymptomEntity save(Symptom symptom);

    List<Symptom> getAll();

    Symptom getById(Long id);

    void update(Long id, Symptom symptom);

    void delete(Long id);
}
