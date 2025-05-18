package com.scheduling.wise.usecase.patient;

import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.gateway.PatientGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePatientUseCase {
    private final PatientGateway patientGateway;

    public void execute(Long id, Patient patient) {
        patientGateway.update(id, patient);
    }
}
