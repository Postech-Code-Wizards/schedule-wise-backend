package com.scheduling.wise.usecase.doctor;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.gateway.DoctorGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateDoctorUseCase {
    private final DoctorGateway doctorGateway;

    public void execute(Long id, Doctor doctor) {
        doctorGateway.update(id, doctor);
    }
}
