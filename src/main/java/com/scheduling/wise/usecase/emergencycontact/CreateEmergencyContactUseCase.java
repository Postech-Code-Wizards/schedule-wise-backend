package com.scheduling.wise.usecase.emergencycontact;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.gateway.EmergencyContactGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmergencyContactUseCase {
    private final EmergencyContactGateway emergencyContactGateway;

    public void execute(EmergencyContact emergencyContact) {
        emergencyContactGateway.save(emergencyContact);
    }
}
