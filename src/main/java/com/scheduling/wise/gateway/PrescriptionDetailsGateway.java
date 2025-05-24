package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;

import java.util.List;

public interface PrescriptionDetailsGateway {

    void save(PrescriptionDetails prescriptionDetails);

    List<PrescriptionDetailsEntity> getAllByDiagnosticId(Long id);

    void deleteAll(List<PrescriptionDetailsEntity> prescriptionDetailsEntityList);
}
