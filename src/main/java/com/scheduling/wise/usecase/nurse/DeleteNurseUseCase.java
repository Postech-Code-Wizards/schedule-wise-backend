package com.scheduling.wise.usecase.nurse;

import com.scheduling.wise.gateway.NurseGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteNurseUseCase {
    private final NurseGateway nurseGateway;

    public void execute(Long id) {
        nurseGateway.delete(id);
    }
}
