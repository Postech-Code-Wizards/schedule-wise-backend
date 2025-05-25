package com.scheduling.wise.usecase.emergencycontact;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.gateway.EmergencyContactGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateEmergencyContactUseCase {
    private final EmergencyContactGateway emergencyContactGateway;

    public void execute(Long id, EmergencyContact emergencyContact) {
        emergencyContactGateway.update(id, emergencyContact);
    }
}
