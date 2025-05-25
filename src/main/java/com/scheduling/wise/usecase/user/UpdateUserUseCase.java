package com.scheduling.wise.usecase.user;

import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateUserUseCase {
    private final UserGateway userGateway;

    public void execute(Long id, User user) {
        userGateway.update(id, user);
    }
}
