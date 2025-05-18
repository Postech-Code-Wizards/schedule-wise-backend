package com.scheduling.wise.usecase.prescriptiondetails;

import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.gateway.PrescriptionDetailsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePrescriptionDetailsCase {
    private final PrescriptionDetailsGateway prescriptionDetailsGateway;

    public void execute(PrescriptionDetails prescriptionDetails) {
        prescriptionDetailsGateway.save(prescriptionDetails);
    }
}
