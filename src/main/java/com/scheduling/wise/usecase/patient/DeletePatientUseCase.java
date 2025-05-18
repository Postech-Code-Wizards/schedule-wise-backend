package com.scheduling.wise.usecase.patient;

import com.scheduling.wise.gateway.PatientGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePatientUseCase {
    private final PatientGateway patientGateway;

    public void execute(Long id) {
        patientGateway.delete(id);
    }
}
