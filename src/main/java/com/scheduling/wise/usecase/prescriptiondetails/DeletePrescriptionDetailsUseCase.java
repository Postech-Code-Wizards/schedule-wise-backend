package com.scheduling.wise.usecase.prescriptiondetails;

import com.scheduling.wise.gateway.PrescriptionDetailsGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePrescriptionDetailsUseCase {
    private final PrescriptionDetailsGateway prescriptionDetailsGateway;

    public void execute(Long id) {
        prescriptionDetailsGateway.delete(id);
    }
}
