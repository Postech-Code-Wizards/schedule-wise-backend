package com.scheduling.wise.usecase.prescriptiondetails;

import com.scheduling.wise.converter.PrescriptionDetailsConverter;
import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.gateway.PrescriptionDetailsGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPrescriptionDetailsUseCase {
    private final PrescriptionDetailsGateway prescriptionDetailsGateway;

    private final PrescriptionDetailsConverter converter;

    public List<PrescriptionDetails> execute(Long diagnosticId) {
        return converter.toDomain(prescriptionDetailsGateway.getAllByDiagnosticId(diagnosticId));
    }
}
