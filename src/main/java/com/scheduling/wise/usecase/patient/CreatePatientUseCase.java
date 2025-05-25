package com.scheduling.wise.usecase.patient;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.PatientGateway;
import com.scheduling.wise.gateway.database.entities.EmergencyContactEntity;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import com.scheduling.wise.gateway.database.entities.UserEntity;
import com.scheduling.wise.usecase.emergencycontact.CreateEmergencyContactUseCase;
import com.scheduling.wise.usecase.phone.CreatePhoneUseCase;
import com.scheduling.wise.usecase.user.CreateUserUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePatientUseCase {
    private final PatientGateway patientGateway;

    private final CreateUserUseCase createUserUseCase;
    private final CreatePhoneUseCase createPhoneUseCase;
    private final CreateEmergencyContactUseCase createEmergencyContactUseCase;

    @Transactional
    public void execute(Patient patient, User user, EmergencyContact emergencyContact, Phone phone, Phone emergencyContactPhone) {
        PhoneEntity patientPhoneEntity = createPhoneUseCase.execute(phone);
        UserEntity patientUserEntity = createUserUseCase.execute(user);
        EmergencyContactEntity emergencyContactEntity = createEmergencyContactUseCase.execute(emergencyContact, emergencyContactPhone);
        patientGateway.save(patient, patientPhoneEntity, patientUserEntity, emergencyContactEntity);
    }
}
