package com.scheduling.wise.usecase.phone;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.gateway.PhoneGateway;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePhoneUseCase {
    private final PhoneGateway phoneGateway;

    public PhoneEntity execute(Phone phone) {
        return phoneGateway.save(phone);
    }
}
