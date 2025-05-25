package com.scheduling.wise.usecase.doctor;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.gateway.DoctorGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetDoctorUseCase {
    private final DoctorGateway doctorGateway;

    public Doctor execute(Long id) {
        return doctorGateway.getById(id);
    }
}
