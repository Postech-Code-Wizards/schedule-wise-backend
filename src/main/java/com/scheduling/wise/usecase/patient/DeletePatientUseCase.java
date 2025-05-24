package com.scheduling.wise.usecase.patient;

import com.scheduling.wise.gateway.PatientGateway;
import com.scheduling.wise.usecase.emergencycontact.DeleteEmergencyContactUseCase;
import com.scheduling.wise.usecase.phone.DeletePhoneUseCase;
import com.scheduling.wise.usecase.user.DeleteUserUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePatientUseCase {
    private final PatientGateway patientGateway;

    private final GetPatientUseCase getPatientUseCase;
    private final DeletePhoneUseCase deletePhoneUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final DeleteEmergencyContactUseCase deleteEmergencyContactUseCase;

    @Transactional
    public void execute(Long id) {
        var doctor = getPatientUseCase.execute(id);
        deletePhoneUseCase.execute(doctor.getPhone().getId());
        deleteUserUseCase.execute(doctor.getUser().getId());
        deleteEmergencyContactUseCase.execute(doctor.getEmergencyContact().getId());
        patientGateway.delete(id);
    }
}
