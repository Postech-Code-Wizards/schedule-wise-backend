package com.scheduling.wise.usecase.emergencycontact;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.gateway.EmergencyContactGateway;
import com.scheduling.wise.gateway.database.entities.EmergencyContactEntity;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import com.scheduling.wise.usecase.phone.CreatePhoneUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmergencyContactUseCase {
    private final EmergencyContactGateway emergencyContactGateway;

    private final CreatePhoneUseCase createPhoneUseCase;

    public EmergencyContactEntity execute(EmergencyContact emergencyContact, Phone phone) {
        PhoneEntity phoneEntity = createPhoneUseCase.execute(phone);
        return emergencyContactGateway.save(emergencyContact, phoneEntity);
    }
}
