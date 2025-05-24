package com.scheduling.wise.usecase.patient;

import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.gateway.PatientGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPatientUseCase {
    private final PatientGateway patientGateway;

    public List<Patient> execute() {
        return patientGateway.getAll();
    }
}
