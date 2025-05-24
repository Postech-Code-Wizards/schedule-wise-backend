package com.scheduling.wise.usecase.doctor;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.gateway.DoctorGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllDoctorUseCase {
    private final DoctorGateway doctorGateway;

    public List<Doctor> execute() {
        return doctorGateway.getAll();
    }
}
