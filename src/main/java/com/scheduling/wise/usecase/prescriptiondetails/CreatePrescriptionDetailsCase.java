package com.scheduling.wise.usecase.prescriptiondetails;

import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.gateway.PrescriptionDetailsGateway;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePrescriptionDetailsCase {
    private final PrescriptionDetailsGateway prescriptionDetailsGateway;

    public PrescriptionDetailsEntity execute(PrescriptionDetails prescriptionDetails) {
        return prescriptionDetailsGateway.save(prescriptionDetails);
    }
}
