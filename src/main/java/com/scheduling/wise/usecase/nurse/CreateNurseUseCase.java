package com.scheduling.wise.usecase.nurse;

import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.NurseGateway;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import com.scheduling.wise.gateway.database.entities.UserEntity;
import com.scheduling.wise.usecase.phone.CreatePhoneUseCase;
import com.scheduling.wise.usecase.user.CreateUserUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNurseUseCase {
    private final NurseGateway nurseGateway;

    private final CreateUserUseCase createUserUseCase;
    private final CreatePhoneUseCase createPhoneUseCase;

    @Transactional
    public void execute(Nurse nurse, User user, Phone phone) {
        PhoneEntity phoneEntity = createPhoneUseCase.execute(phone);
        UserEntity userEntity = createUserUseCase.execute(user);
        nurseGateway.save(nurse, phoneEntity, userEntity);
    }
}
