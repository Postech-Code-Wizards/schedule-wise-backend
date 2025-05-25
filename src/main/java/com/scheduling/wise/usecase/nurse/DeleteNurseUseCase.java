package com.scheduling.wise.usecase.nurse;

import com.scheduling.wise.gateway.NurseGateway;
import com.scheduling.wise.usecase.phone.DeletePhoneUseCase;
import com.scheduling.wise.usecase.user.DeleteUserUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteNurseUseCase {
    private final NurseGateway nurseGateway;

    private final GetNurseUseCase getNurseUseCase;
    private final DeletePhoneUseCase deletePhoneUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Transactional
    public void execute(Long id) {
        var doctor = getNurseUseCase.execute(id);
        deletePhoneUseCase.execute(doctor.getPhone().getId());
        deleteUserUseCase.execute(doctor.getUser().getId());
        nurseGateway.delete(id);
    }
}
