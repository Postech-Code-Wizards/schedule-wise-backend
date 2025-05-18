package com.scheduling.wise.usecase.emergencycontact;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.gateway.EmergencyContactGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetEmergencyContactUseCase {
    private final EmergencyContactGateway emergencyContactGateway;

    public EmergencyContact execute(Long id) {
        return emergencyContactGateway.getById(id);
    }
}
