package com.scheduling.wise.usecase.nurse;

import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.NurseGateway;
import com.scheduling.wise.usecase.phone.UpdatePhoneUseCase;
import com.scheduling.wise.usecase.user.UpdateUserUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateNurseUseCase {
    private final NurseGateway nurseGateway;

    private final GetNurseUseCase getNurseUseCase;
    private final UpdatePhoneUseCase updatePhoneUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Transactional
    public void execute(Long id, Nurse nurse, User user) {
        updatePhoneUseCase.execute(nurse.getPhone().getId(), nurse.getPhone());
        updateUserUseCase.execute(nurse.getUser().getId(), user);
        nurseGateway.update(id, nurse);
    }
}
