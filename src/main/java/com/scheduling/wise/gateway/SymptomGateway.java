package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;

import java.util.List;

public interface SymptomGateway {
    void save(SymptomEntity symptomEntity);

    List<SymptomEntity> getAllByDiagnosticId(Long id);

    void deleteAll(List<SymptomEntity> symptomEntityList);
}
