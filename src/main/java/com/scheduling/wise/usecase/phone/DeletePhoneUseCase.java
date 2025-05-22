package com.scheduling.wise.usecase.phone;

import com.scheduling.wise.gateway.PhoneGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePhoneUseCase {
    private final PhoneGateway phoneGateway;

    public void execute(Long id) {
        phoneGateway.delete(id);
    }
}
