package com.scheduling.wise.usecase.doctor;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.gateway.DoctorGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDoctorUseCase {
    private final DoctorGateway doctorGateway;

    public void execute(Doctor doctor) {
        doctorGateway.save(doctor);
    }
}
