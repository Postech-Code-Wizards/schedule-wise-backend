package com.scheduling.wise.usecase.patient;

import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.gateway.PatientGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPatientUseCase {
    private final PatientGateway patientGateway;

    public Patient execute(Long id) {
        return patientGateway.getById(id);
    }
}
