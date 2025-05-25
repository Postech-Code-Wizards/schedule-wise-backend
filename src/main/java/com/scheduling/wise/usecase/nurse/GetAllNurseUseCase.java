package com.scheduling.wise.usecase.nurse;

import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.gateway.NurseGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllNurseUseCase {
    private final NurseGateway nurseGateway;

    public List<Nurse> execute() {
        return nurseGateway.getAll();
    }
}
