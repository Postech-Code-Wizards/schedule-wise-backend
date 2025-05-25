package com.scheduling.wise.usecase.patient;

import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.PatientGateway;
import com.scheduling.wise.usecase.phone.UpdatePhoneUseCase;
import com.scheduling.wise.usecase.user.UpdateUserUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePatientUseCase {
    private final PatientGateway patientGateway;

    private final UpdatePhoneUseCase updatePhoneUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Transactional
    public void execute(Long id, Patient patient, User user) {
        updatePhoneUseCase.execute(patient.getId(), patient.getPhone());
        updateUserUseCase.execute(patient.getUser().getId(), user);
        patientGateway.update(id, patient);
    }
}
