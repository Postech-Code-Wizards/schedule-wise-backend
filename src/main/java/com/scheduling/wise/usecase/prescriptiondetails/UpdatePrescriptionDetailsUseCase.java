package com.scheduling.wise.usecase.prescriptiondetails;

import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.gateway.PrescriptionDetailsGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePrescriptionDetailsUseCase {
    private final PrescriptionDetailsGateway prescriptionDetailsGateway;

    public void execute(Long id, PrescriptionDetails prescriptionDetails) {
        prescriptionDetailsGateway.update(id, prescriptionDetails);
    }
}
