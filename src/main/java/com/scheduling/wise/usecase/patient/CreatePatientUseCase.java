package com.scheduling.wise.usecase.patient;

import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.gateway.PatientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePatientUseCase {
    private final PatientGateway patientGateway;

    public void execute(Patient patient) {
        patientGateway.save(patient);
    }
}
