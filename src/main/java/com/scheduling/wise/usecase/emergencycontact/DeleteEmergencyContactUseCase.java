package com.scheduling.wise.usecase.emergencycontact;

import com.scheduling.wise.gateway.EmergencyContactGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEmergencyContactUseCase {
    private final EmergencyContactGateway emergencyContactGateway;

    public void execute(Long id) {
        emergencyContactGateway.delete(id);
    }
}
