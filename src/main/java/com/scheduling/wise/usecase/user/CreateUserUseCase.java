package com.scheduling.wise.usecase.user;

import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserGateway userGateway;

    public void execute(User user) {
        user.activateUser(true);
        userGateway.save(user);
    }
}
