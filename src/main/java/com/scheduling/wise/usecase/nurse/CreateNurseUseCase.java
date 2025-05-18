package com.scheduling.wise.usecase.nurse;

import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.gateway.NurseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNurseUseCase {
    private final NurseGateway nurseGateway;

    public void execute(Nurse nurse) {
        nurseGateway.save(nurse);
    }
}
