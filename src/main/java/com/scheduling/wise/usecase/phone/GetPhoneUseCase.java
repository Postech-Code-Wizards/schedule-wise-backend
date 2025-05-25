package com.scheduling.wise.usecase.phone;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.gateway.PhoneGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPhoneUseCase {
    private final PhoneGateway phoneGateway;

    public Phone execute(Long id) {
        return phoneGateway.getById(id);
    }
}
