package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;

import java.util.List;

public interface PrescriptionDetailsGateway {
    PrescriptionDetailsEntity save(PrescriptionDetails prescriptionDetails);

    List<PrescriptionDetails> getAll();

    PrescriptionDetails getById(Long id);

    void update(Long id, PrescriptionDetails prescriptionDetails);

    void delete(Long id);
}
