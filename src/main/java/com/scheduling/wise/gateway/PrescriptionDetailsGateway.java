package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.PrescriptionDetails;

import java.util.List;

public interface PrescriptionDetailsGateway {
    void save(PrescriptionDetails prescriptionDetails);

    List<PrescriptionDetails> getAll();

    PrescriptionDetails getById(Long id);

    void update(Long id, PrescriptionDetails prescriptionDetails);

    void delete(Long id);
}
