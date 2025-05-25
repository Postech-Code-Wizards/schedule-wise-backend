package com.scheduling.wise.usecase.nurse;

import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.gateway.NurseGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetNurseUseCase {
    private final NurseGateway nurseGateway;

    public Nurse execute(Long id) {
        return nurseGateway.getById(id);
    }
}
