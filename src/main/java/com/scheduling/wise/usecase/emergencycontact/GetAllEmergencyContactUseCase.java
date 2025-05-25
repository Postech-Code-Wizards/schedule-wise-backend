package com.scheduling.wise.usecase.emergencycontact;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.gateway.EmergencyContactGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllEmergencyContactUseCase {
    private final EmergencyContactGateway emergencyContactGateway;

    public List<EmergencyContact> execute() {
        return emergencyContactGateway.getAll();
    }
}
