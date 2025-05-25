package com.scheduling.wise.usecase.user;

import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindUserUseCase {
    private final UserGateway userGateway;

    public User execute(Long id) {
        return userGateway.getById(id);
    }
}
