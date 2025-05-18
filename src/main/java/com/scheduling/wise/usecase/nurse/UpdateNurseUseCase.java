package com.scheduling.wise.usecase.nurse;

import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.gateway.NurseGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateNurseUseCase {
    private final NurseGateway nurseGateway;

    public void execute(Long id, Nurse nurse) {
        nurseGateway.update(id, nurse);
    }
}
