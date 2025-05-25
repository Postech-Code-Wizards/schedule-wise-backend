package com.scheduling.wise.usecase.phone;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.gateway.PhoneGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPhoneUseCase {
    private final PhoneGateway phoneGateway;

    public List<Phone> execute() {
        return phoneGateway.getAll();
    }
}
