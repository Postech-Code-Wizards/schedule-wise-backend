package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;

import java.util.List;

public interface DiagnosticGateway {
    void save(Diagnostic diagnostic, List<SymptomEntity> symptomEntityList, List<PrescriptionDetailsEntity> prescriptionDetailsEntityList);

    List<Diagnostic> getAll(Long id);

    Diagnostic getById(Long id);

    void update(Long id, Diagnostic newDiagnostic, List<SymptomEntity> symptomEntityList, List<PrescriptionDetailsEntity> prescriptionDetailsEntityList);

    void delete(Long id);
}
