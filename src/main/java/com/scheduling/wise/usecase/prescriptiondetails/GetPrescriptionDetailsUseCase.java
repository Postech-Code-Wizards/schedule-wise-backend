package com.scheduling.wise.usecase.prescriptiondetails;

import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.gateway.PrescriptionDetailsGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPrescriptionDetailsUseCase {
    private final PrescriptionDetailsGateway prescriptionDetailsGateway;

    public PrescriptionDetails execute(Long id) {
        return prescriptionDetailsGateway.getById(id);
    }
}
