package com.scheduling.wise.usecase.phone;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.gateway.PhoneGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePhoneUseCase {
    private final PhoneGateway phoneGateway;

    public void execute(Long id, Phone phone) {
        phoneGateway.update(id, phone);
    }
}
