package com.scheduling.wise.usecase.doctor;

import com.scheduling.wise.gateway.DoctorGateway;
import com.scheduling.wise.usecase.phone.DeletePhoneUseCase;
import com.scheduling.wise.usecase.user.DeleteUserUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteDoctorUseCase {
    private final DoctorGateway doctorGateway;

    private final GetDoctorUseCase getDoctorUseCase;
    private final DeletePhoneUseCase deletePhoneUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Transactional
    public void execute(Long id) {
        var doctor = getDoctorUseCase.execute(id);
        deletePhoneUseCase.execute(doctor.getPhone().getId());
        deleteUserUseCase.execute(doctor.getUser().getId());
        doctorGateway.delete(id);
    }
}
