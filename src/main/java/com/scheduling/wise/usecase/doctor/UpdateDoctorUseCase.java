package com.scheduling.wise.usecase.doctor;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.DoctorGateway;
import com.scheduling.wise.usecase.phone.UpdatePhoneUseCase;
import com.scheduling.wise.usecase.user.UpdateUserUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateDoctorUseCase {
    private final DoctorGateway doctorGateway;

    private final UpdatePhoneUseCase updatePhoneUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Transactional
    public void execute(Long id, Doctor doctor, User user) {
        updatePhoneUseCase.execute(doctor.getPhone().getId(), doctor.getPhone());
        updateUserUseCase.execute(doctor.getUser().getId(), user);
        doctorGateway.update(id, doctor);
    }
}
