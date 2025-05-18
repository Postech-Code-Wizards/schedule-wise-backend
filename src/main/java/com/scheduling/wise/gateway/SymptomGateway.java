package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Symptom;

import java.util.List;

public interface SymptomGateway {
    void save(Symptom symptom);

    List<Symptom> getAll();

    Symptom getById(Long id);

    void update(Long id, Symptom symptom);

    void delete(Long id);
}
