package com.scheduling.wise.usecase.doctor;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.DoctorGateway;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import com.scheduling.wise.gateway.database.entities.UserEntity;
import com.scheduling.wise.usecase.phone.CreatePhoneUseCase;
import com.scheduling.wise.usecase.user.CreateUserUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDoctorUseCase {
    private final DoctorGateway doctorGateway;
    private final CreateUserUseCase createUserUseCase;
    private final CreatePhoneUseCase createPhoneUseCase;

    @Transactional
    public void execute(Doctor doctor, User user, Phone phone) {
        PhoneEntity phoneEntity = createPhoneUseCase.execute(phone);
        UserEntity userEntity = createUserUseCase.execute(user);
        doctorGateway.save(doctor, phoneEntity, userEntity);
    }
}
