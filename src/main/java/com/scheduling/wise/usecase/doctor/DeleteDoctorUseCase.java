package com.scheduling.wise.usecase.doctor;

import com.scheduling.wise.gateway.DoctorGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteDoctorUseCase {
    private final DoctorGateway doctorGateway;

    public void execute(Long id) {
        doctorGateway.delete(id);
    }
}
